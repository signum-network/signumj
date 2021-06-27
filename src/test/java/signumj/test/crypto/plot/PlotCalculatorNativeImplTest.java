package signumj.test.crypto.plot;

import signumj.crypto.SignumCrypto;
import signumj.crypto.plot.PlotCalculator;
import signumj.crypto.plot.impl.PlotCalculatorNativeImpl;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlotCalculatorNativeImplTest extends PlotCalculatorTest {
    @Override
    protected PlotCalculator getPlotCalculator() {
        return new PlotCalculatorNativeImpl(() -> SignumCrypto.getInstance().getShabal256());
    }
}
