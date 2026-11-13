package signumj.crypto.plot.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.function.Supplier;

import signumj.util.LibShabal;
import signumj.util.LibShabalScoops;

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

    /**
     * Uses the SIMD batch function of libshabal 1.5+, and one native call per scoop with older versions.
     */
    @Override
    public byte[] calculateScoops(long[] accountIds, long[] nonces, int[] scoops, int pocVersion) {
        validateScoopsInput(accountIds, nonces, scoops);
        byte[] result = new byte[accountIds.length * MiningPlot.SCOOP_SIZE];
        if (accountIds.length == 0) {
            return result;
        }
        if (LibShabalScoops.LOAD_ERROR == null) {
            int status = LibShabalScoops.create_scoops(accountIds, nonces, scoops, accountIds.length,
                    (byte) pocVersion, result, 0);
            if (status != 0) {
                throw new IllegalStateException("libshabal create_scoops failed with status " + status);
            }
            return result;
        }
        for (int i = 0; i < accountIds.length; i++) {
            LibShabal.create_scoop(accountIds[i], nonces[i], scoops[i], (byte) pocVersion, result, i * MiningPlot.SCOOP_SIZE);
        }
        return result;
    }
}
