package burst.kit.test;

import burst.kit.crypto.BurstCrypto;
import burst.kit.crypto.plot.impl.MiningPlot;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnit4.class)
public class BurstCryptoPlottingTest {
    private final BurstCrypto burstCrypto = BurstCrypto.getInstance();

    /**
     * Test plotting a single nonce
     */
    private void testPlot(byte pocVersion) {
        long testAccountId = 1234L;
        long testNonce = 4321L;
        byte[] reference = new byte[MiningPlot.PLOT_SIZE];

        // Reference plotter
        new MiningPlot(burstCrypto.getShabal256(), testAccountId, testNonce, pocVersion, reference, 0);

        byte[] compare = new byte[MiningPlot.PLOT_SIZE];
        burstCrypto.plotNonce(testAccountId, testNonce, pocVersion, compare, 0);

        assertArrayEquals(reference, compare);
    }

    /**
     * Test plotting 2 nonces (designed to test batch plotting without triggering SIMD optimizations)
     */
    private void testPlotMultiple_smallCount(byte pocVersion) {
        long testAccountId = 12345L;
        long testNonce = 54321L;
        byte[] firstNonceReference = new byte[MiningPlot.PLOT_SIZE];
        byte[] secondNonceReference = new byte[MiningPlot.PLOT_SIZE];

        // Reference plotter
        new MiningPlot(burstCrypto.getShabal256(), testAccountId, testNonce, pocVersion, firstNonceReference, 0);
        new MiningPlot(burstCrypto.getShabal256(), testAccountId, testNonce + 1, pocVersion, secondNonceReference, 0);

        byte[] firstNonceCompare = new byte[MiningPlot.PLOT_SIZE];
        byte[] secondNonceCompare = new byte[MiningPlot.PLOT_SIZE];
        byte[] bothNonces = new byte[MiningPlot.PLOT_SIZE * 2];
        burstCrypto.plotNonces(testAccountId, testNonce, 2, pocVersion, bothNonces, 0);
        System.arraycopy(bothNonces, 0, firstNonceCompare, 0, MiningPlot.PLOT_SIZE);
        System.arraycopy(bothNonces, MiningPlot.PLOT_SIZE, secondNonceCompare, 0, MiningPlot.PLOT_SIZE);

        System.out.println("First Ref:   " + Hex.toHexString(firstNonceReference, 0, 128));
        System.out.println("First Comp:  " + Hex.toHexString(firstNonceCompare, 0, 128));
        System.out.println("Second Ref:  " + Hex.toHexString(secondNonceReference, 0, 128));
        System.out.println("Second Comp: " + Hex.toHexString(secondNonceCompare, 0, 128));

        assertArrayEquals(firstNonceReference, firstNonceCompare);
        assertArrayEquals(secondNonceReference, secondNonceCompare);
    }

    /**
     * Test plotting lots of nonces, should trigger SIMD optimizations where available.
     */
    void testPlotMultiple_largeCount(byte pocVersion) {
        long testAccountId = 123456L;
        long testNonce = 654321L;
        int nonceCount = 33; // Should trigger 2 rounds even on AVX-512f, plus an extra round to force it to use regular plotting and SIMD plotting
        byte[] reference = new byte[nonceCount * MiningPlot.PLOT_SIZE];

        // Reference plotter
        for (int i = 0; i < nonceCount; i++) {
            new MiningPlot(burstCrypto.getShabal256(), testAccountId, testNonce + i, pocVersion, reference, i * MiningPlot.PLOT_SIZE);
        }

        byte[] compare = new byte[nonceCount * MiningPlot.PLOT_SIZE];
        burstCrypto.plotNonces(testAccountId, testNonce, nonceCount, pocVersion, compare, 0);

        assertArrayEquals(reference, compare);
    }

    @Test
    public void testPlot_poc1() {
        testPlot((byte) 1);
    }

    @Test
    public void testPlot_poc2() {
        testPlot((byte) 2);
    }

    @Test
    public void testPlotMultiple_smallCount_poc1() {
        testPlotMultiple_smallCount((byte) 1);
    }

    @Test
    public void testPlotMultiple_smallCount_poc2() {
        testPlotMultiple_smallCount((byte) 2);
    }

    @Test
    public void testPlotMultiple_largeCount_poc1() {
        testPlotMultiple_largeCount((byte) 1);
    }

    @Test
    public void testPlotMultiple_largeCount_poc2() {
        testPlotMultiple_largeCount((byte) 2);
    }
}
