package burst.kit.test.crypto.plot;

import burst.kit.crypto.BurstCrypto;
import burst.kit.crypto.plot.PlotCalculator;
import burst.kit.test.TestVariables;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public abstract class PlotCalculatorTest {
    private static final byte[] exampleGenSig = BurstCrypto.getInstance().parseHexString("6ec823b5fd86c4aee9f7c3453cacaf4a43296f48ede77e70060ca8225c2855d0");
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
        assertEquals("3d3941a3d148627ea9f1a00f41674f652e1283db37b1317c9bb340efa99f1ac0", BurstCrypto.getInstance().toHexString(genSig));
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
        assertEquals(new BigInteger("16142911724569013009"), plotCalculator.calculateHit(TestVariables.EXAMPLE_ACCOUNT_ID.getSignedLongId(), 0, exampleGenSig, BurstCrypto.getInstance().parseHexString("6ec823b5fd86c4aee9f7c3453cacaf4a43296f48ede77e70060ca8225c2855d06ec823b5fd86c4aee9f7c3453cacaf4a43296f48ede77e70060ca8225c2855d0")));
    }
}
