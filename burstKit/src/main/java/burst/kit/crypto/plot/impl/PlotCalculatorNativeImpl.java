package burst.kit.crypto.plot.impl;

import burst.kit.util.LibShabalLoader;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.function.Supplier;

public class PlotCalculatorNativeImpl extends PlotCalculatorImpl {
    public PlotCalculatorNativeImpl(Supplier<MessageDigest> shabal256Supplier) {
        super(shabal256Supplier);
        LibShabalLoader.ensureLoaded();
    }

    @Override
    public BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion) {
        byte[] scoopBuffer = new byte[MiningPlot.SCOOP_SIZE];
        LibShabalLoader.getInstance().create_scoop(accountId, nonce, scoop, (byte) pocVersion, scoopBuffer, 0);
        return calculateHit(accountId, nonce, genSig, scoopBuffer);
    }
}
