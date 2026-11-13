package signumj.crypto.plot.impl;

import signumj.crypto.SignumCrypto;
import signumj.crypto.plot.PlotCalculator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.function.Supplier;

public class PlotCalculatorImpl implements PlotCalculator { // TODO all of this can be optimized
    private final Supplier<MessageDigest> shabal256Supplier;

    public PlotCalculatorImpl(Supplier<MessageDigest> shabal256Supplier) {
        this.shabal256Supplier = shabal256Supplier;
    }

    @Override
    public byte[] calculateGenerationSignature(byte[] lastGenSig, long lastGenId) {
        MessageDigest shabal256 = shabal256Supplier.get();
        shabal256.update(lastGenSig);
        shabal256.update(SignumCrypto.getInstance().longToBytesBE(lastGenId));
        return shabal256.digest();
    }

    @Override
    public int calculateScoop(byte[] genSig, long height) {
        MessageDigest shabal256 = shabal256Supplier.get();
        shabal256.update(genSig);
        shabal256.update(SignumCrypto.getInstance().longToBytesBE(height));
        BigInteger hashnum = new BigInteger(1, shabal256.digest());
        return hashnum.mod(MiningPlot.SCOOPS_PER_PLOT_BIGINT).intValue();
    }

    @Override
    public BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion) {
        MiningPlot plot = new MiningPlot(shabal256Supplier.get(), accountId, nonce, pocVersion);
        MessageDigest shabal256 = shabal256Supplier.get();
        shabal256.update(genSig);
        plot.hashScoop(shabal256, scoop);
        byte[] hash = shabal256.digest();
        return new BigInteger(1, new byte[] {hash[7], hash[6], hash[5], hash[4], hash[3], hash[2], hash[1], hash[0]});
    }

    @Override
    public BigInteger calculateHit(byte[] genSig, byte[] scoopData) {
        MessageDigest shabal256 = shabal256Supplier.get();
        shabal256.update(genSig);
        shabal256.update(scoopData);
        byte[] hash = shabal256.digest();
        return new BigInteger(1, new byte[] {hash[7], hash[6], hash[5], hash[4], hash[3], hash[2], hash[1], hash[0]});
    }

    @Override
    public BigInteger calculateDeadline(long accountId, long nonce, byte[] genSig, int scoop, long baseTarget, int pocVersion) {
        BigInteger hit = calculateHit(accountId, nonce, genSig, scoop, pocVersion);
        return hit.divide(BigInteger.valueOf(baseTarget));
    }

    @Override
    public byte[] calculateScoops(long[] accountIds, long[] nonces, int[] scoops, int pocVersion) {
        validateScoopsInput(accountIds, nonces, scoops);
        byte[] result = new byte[accountIds.length * MiningPlot.SCOOP_SIZE];
        for (int i = 0; i < accountIds.length; i++) {
            byte[] scoop = new MiningPlot(shabal256Supplier.get(), accountIds[i], nonces[i], pocVersion).getScoop(scoops[i]);
            System.arraycopy(scoop, 0, result, i * MiningPlot.SCOOP_SIZE, MiningPlot.SCOOP_SIZE);
        }
        return result;
    }

    protected static void validateScoopsInput(long[] accountIds, long[] nonces, int[] scoops) {
        if (accountIds.length != nonces.length || accountIds.length != scoops.length) {
            throw new IllegalArgumentException("accountIds, nonces and scoops must have the same length, got "
                    + accountIds.length + ", " + nonces.length + " and " + scoops.length);
        }
        for (int scoop : scoops) {
            if (scoop < 0 || scoop >= MiningPlot.SCOOPS_PER_PLOT) {
                throw new IllegalArgumentException("Scoop must be in [0, " + MiningPlot.SCOOPS_PER_PLOT + "), got " + scoop);
            }
        }
    }
}
