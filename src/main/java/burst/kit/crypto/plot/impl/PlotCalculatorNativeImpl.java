package burst.kit.crypto.plot.impl;

import burst.kit.util.LibShabal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.function.Supplier;

/**
 * Using this will throw NPE if the native library is not loaded.
 */
public class PlotCalculatorNativeImpl extends PlotCalculatorImpl {
    public PlotCalculatorNativeImpl(Supplier<MessageDigest> shabal256Supplier) {
        super(shabal256Supplier);
    }

    @Override
    public BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion) {
        byte[] scoopBuffer = new byte[MiningPlot.SCOOP_SIZE];
        LibShabal.create_scoop(accountId, nonce, scoop, (byte) pocVersion, scoopBuffer, 0);
        return calculateHit(genSig, scoopBuffer);
    }
}
