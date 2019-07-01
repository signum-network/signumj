package burst.kit.crypto.plot.impl;

import burst.kit.crypto.BurstCrypto;
import burst.kit.crypto.plot.PlotCalculator;

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
        shabal256.update(BurstCrypto.getInstance().longToBytes(lastGenId));
        return shabal256.digest();
    }

    @Override
    public int calculateScoop(byte[] genSig, long height) {
        MessageDigest shabal256 = shabal256Supplier.get();
        shabal256.update(genSig);
        shabal256.update(BurstCrypto.getInstance().longToBytes(height));
        BigInteger hashnum = new BigInteger(1, shabal256.digest());
        return hashnum.mod(MiningPlot.SCOOPS_PER_PLOT_BIGINT).intValue();
    }

    @Override
    public BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion) {
        MiningPlot plot = new MiningPlot(shabal256Supplier, accountId, nonce, pocVersion);
        MessageDigest shabal256 = shabal256Supplier.get();
        shabal256.update(genSig);
        plot.hashScoop(shabal256, scoop);
        byte[] hash = shabal256.digest();
        return new BigInteger(1, new byte[] {hash[7], hash[6], hash[5], hash[4], hash[3], hash[2], hash[1], hash[0]});
    }

    @Override
    public BigInteger calculateHit(long accountId, long nonce, byte[] genSig, byte[] scoopData) {
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
}
