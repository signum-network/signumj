package burst.kit.test.crypto.plot;

import burst.kit.crypto.BurstCrypto;
import burst.kit.crypto.plot.PlotCalculator;
import burst.kit.crypto.plot.impl.PlotCalculatorNativeImpl;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PlotCalculatorNativeImplTest extends PlotCalculatorTest {
    @Override
    protected PlotCalculator getPlotCalculator() {
        return new PlotCalculatorNativeImpl(() -> BurstCrypto.getInstance().getShabal256());
    }
}
