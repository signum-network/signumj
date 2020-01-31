package burst.kit.crypto.plot.impl;

import burst.kit.util.LibShabalLoader;

import java.security.MessageDigest;
import java.util.function.Supplier;

public class PlotCalculatorNativeImpl extends PlotCalculatorImpl {
    public PlotCalculatorNativeImpl(Supplier<MessageDigest> shabal256Supplier) {
        super(shabal256Supplier);
        LibShabalLoader.ensureLoaded();
    }

    // TODO override stuffs
}
