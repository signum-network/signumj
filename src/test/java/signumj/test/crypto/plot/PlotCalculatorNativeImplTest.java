package signumj.test.crypto.plot;

import signumj.crypto.SignumCrypto;
import signumj.crypto.plot.PlotCalculator;
import signumj.crypto.plot.impl.PlotCalculatorNativeImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import signumj.util.LibShabal;
import signumj.util.LibShabalScoops;

import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class PlotCalculatorNativeImplTest extends PlotCalculatorTest {
    @Override
    protected PlotCalculator getPlotCalculator() {
        return new PlotCalculatorNativeImpl(() -> SignumCrypto.getInstance().getShabal256());
    }

    @Test
    public void testBundledLibraryProvidesBatchScoops() {
        // without it calculateScoops silently falls back to one native call per scoop
        assertNull("native library not loaded", LibShabal.LOAD_ERROR);
        assertNull("libshabal without create_scoops", LibShabalScoops.LOAD_ERROR);
    }
}
