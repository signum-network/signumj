package signumj.crypto.plot;

import java.math.BigInteger;

public interface PlotCalculator {
    byte[] calculateGenerationSignature(byte[] lastGenSig, long lastGenId);
    int calculateScoop(byte[] genSig, long height);
    BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion);
    BigInteger calculateHit(byte[] genSig, byte[] scoopData);
    BigInteger calculateDeadline(long accountId, long nonce, byte[] genSig, int scoop, long baseTarget, int pocVersion);

    /**
     * Calculates one scoop for each (account id, nonce, scoop) triple, e.g. to verify a batch of blocks.
     * The hit of entry {@code i} is {@code calculateHit(genSig, scoop i)}.
     *
     * @return {@code accountIds.length * 64} bytes, scoop {@code i} starts at offset {@code i * 64}
     * @throws IllegalArgumentException if the arrays differ in length or a scoop is not in [0, 4096)
     */
    byte[] calculateScoops(long[] accountIds, long[] nonces, int[] scoops, int pocVersion);
}
