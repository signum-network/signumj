package signumj.test.crypto.plot;

import signumj.crypto.SignumCrypto;
import signumj.crypto.plot.PlotCalculator;
import signumj.crypto.plot.impl.PlotCalculatorImpl;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlotCalculatorImplTest extends PlotCalculatorTest {
    @Override
    protected PlotCalculator getPlotCalculator() {
        return new PlotCalculatorImpl(() -> SignumCrypto.getInstance().getShabal256());
    }
}
