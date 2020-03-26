package burst.kit.test.crypto.plot;

import burst.kit.crypto.BurstCrypto;
import burst.kit.crypto.plot.PlotCalculator;
import burst.kit.crypto.plot.impl.PlotCalculatorImpl;
import burst.kit.crypto.plot.impl.PlotCalculatorNativeImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@RunWith(JUnit4.class)
public class PlotCalculatorImplBenchmark {
    @Test
    public void benchmark64Reps() {
        runBenchmark(new PlotCalculatorImpl(BurstCrypto.getInstance()::getShabal256), 64);
    }

    @Test
    public void benchmark64RepsNative() {
        runBenchmark(new PlotCalculatorNativeImpl(BurstCrypto.getInstance()::getShabal256), 64);
    }

    private void runBenchmark(PlotCalculator plotCalculator, int numberOfIterations) {
        byte[] myGenSig = "abcdefghijklmnopqrstuvwxyzabcdef".getBytes(StandardCharsets.UTF_8);
        long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            long myStart = System.currentTimeMillis();
            BigInteger hit = plotCalculator.calculateHit(42069, i, myGenSig, 1234, 2);
            System.out.println((System.currentTimeMillis() - myStart) + "ms to calculate " + hit);
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println("Total Duration: " + duration + "ms");
    }
}
