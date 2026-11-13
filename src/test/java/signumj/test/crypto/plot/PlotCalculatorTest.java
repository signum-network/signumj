package signumj.test.crypto.plot;

import signumj.crypto.SignumCrypto;
import signumj.crypto.plot.PlotCalculator;
import signumj.crypto.plot.impl.MiningPlot;
import signumj.test.TestVariables;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class PlotCalculatorTest {
    private static final byte[] exampleGenSig = SignumCrypto.getInstance().parseHexString("6ec823b5fd86c4aee9f7c3453cacaf4a43296f48ede77e70060ca8225c2855d0");
    private static final long exampleBaseTarget = 70312;
    private static final int exampleHeight = 500000;

    private PlotCalculator plotCalculator;

    protected abstract PlotCalculator getPlotCalculator();

    @Before
    public void setUp() {
        plotCalculator = getPlotCalculator();
    }

    @Test
    public void testPlotCalculatorCalculateGenerationSignature() {
        byte[] genSig = plotCalculator.calculateGenerationSignature(exampleGenSig, TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId());
        assertEquals("3d3941a3d148627ea9f1a00f41674f652e1283db37b1317c9bb340efa99f1ac0", SignumCrypto.getInstance().toHexString(genSig));
    }

    @Test
    public void testPlotCalculatorCalculateScoop() {
        assertEquals(3696, plotCalculator.calculateScoop(exampleGenSig, exampleHeight));
    }

    @Test
    public void testPlotCalculatorCalculateDeadline() {
        BigInteger poc1deadline = plotCalculator.calculateDeadline(TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId(), 0, exampleGenSig, plotCalculator.calculateScoop(exampleGenSig, exampleHeight), exampleBaseTarget, 1);
        assertEquals(BigInteger.valueOf(76595641219705L), poc1deadline);
        BigInteger poc2deadline = plotCalculator.calculateDeadline(TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId(), 0, exampleGenSig, plotCalculator.calculateScoop(exampleGenSig, exampleHeight), exampleBaseTarget, 2);
        assertEquals(BigInteger.valueOf(190678252334964L), poc2deadline);
    }

    @Test
    public void testPlotCalculatorCalculateHit() {
        assertEquals(new BigInteger("11245277265433361116"), plotCalculator.calculateHit(TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId(), 0, exampleGenSig, 0, 1));
        assertEquals(new BigInteger("18324085073558450598"), plotCalculator.calculateHit(TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId(), 0, exampleGenSig, 0, 2));
        // Scoop data is the generation signature repeated - not intended to be actual scoop data for the purpose of this test. It is twice as long as the gensig as this is the expected scoop size.
        assertEquals(new BigInteger("16142911724569013009"), plotCalculator.calculateHit(exampleGenSig, SignumCrypto.getInstance().parseHexString("6ec823b5fd86c4aee9f7c3453cacaf4a43296f48ede77e70060ca8225c2855d06ec823b5fd86c4aee9f7c3453cacaf4a43296f48ede77e70060ca8225c2855d0")));
    }

    @Test
    public void testPlotCalculatorCalculateScoopsGivesKnownHits() {
        long accountId = TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId();
        byte[] poc1 = plotCalculator.calculateScoops(new long[] {accountId}, new long[] {0}, new int[] {0}, 1);
        byte[] poc2 = plotCalculator.calculateScoops(new long[] {accountId}, new long[] {0}, new int[] {0}, 2);
        // same values as testPlotCalculatorCalculateHit
        assertEquals(new BigInteger("11245277265433361116"), plotCalculator.calculateHit(exampleGenSig, poc1));
        assertEquals(new BigInteger("18324085073558450598"), plotCalculator.calculateHit(exampleGenSig, poc2));
    }

    @Test
    public void testPlotCalculatorCalculateScoopsMatchesMiningPlot() {
        // more than two SIMD batches of every width plus a remainder, unrelated accounts and nonces
        int count = 35;
        long[] accountIds = new long[count];
        long[] nonces = new long[count];
        int[] scoops = new int[count];
        long state = 0x9e3779b97f4a7c15L;
        for (int i = 0; i < count; i++) {
            state = state * 6364136223846793005L + 1442695040888963407L;
            accountIds[i] = state;
            state = state * 6364136223846793005L + 1442695040888963407L;
            nonces[i] = state;
            scoops[i] = i % 5 == 0 ? 0 : i % 5 == 1 ? MiningPlot.SCOOPS_PER_PLOT - 1 : (int) ((state >>> 33) % MiningPlot.SCOOPS_PER_PLOT);
        }
        for (int pocVersion = 1; pocVersion <= 2; pocVersion++) {
            byte[] result = plotCalculator.calculateScoops(accountIds, nonces, scoops, pocVersion);
            assertEquals(count * MiningPlot.SCOOP_SIZE, result.length);
            for (int i = 0; i < count; i++) {
                byte[] expected = new MiningPlot(SignumCrypto.getInstance().getShabal256(), accountIds[i], nonces[i], pocVersion).getScoop(scoops[i]);
                assertArrayEquals("entry " + i + " poc " + pocVersion, expected,
                        Arrays.copyOfRange(result, i * MiningPlot.SCOOP_SIZE, (i + 1) * MiningPlot.SCOOP_SIZE));
            }
        }
    }

    @Test
    public void testPlotCalculatorCalculateScoopsWithNoEntriesReturnsEmpty() {
        assertEquals(0, plotCalculator.calculateScoops(new long[0], new long[0], new int[0], 2).length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlotCalculatorCalculateScoopsRejectsDifferentLengths() {
        plotCalculator.calculateScoops(new long[] {1, 2}, new long[] {1}, new int[] {0, 0}, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlotCalculatorCalculateScoopsRejectsScoopTooLarge() {
        plotCalculator.calculateScoops(new long[] {1}, new long[] {1}, new int[] {MiningPlot.SCOOPS_PER_PLOT}, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlotCalculatorCalculateScoopsRejectsNegativeScoop() {
        plotCalculator.calculateScoops(new long[] {1}, new long[] {1}, new int[] {-1}, 2);
    }
}
