package burst.kit.crypto.plot;

import java.math.BigInteger;

public interface PlotCalculator {
    byte[] calculateGenerationSignature(byte[] lastGenSig, long lastGenId);
    int calculateScoop(byte[] genSig, long height);
    BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion);
    BigInteger calculateHit(long accountId, long nonce, byte[] genSig, byte[] scoopData);
    BigInteger calculateDeadline(long accountId, long nonce, byte[] genSig, int scoop, long baseTarget, int pocVersion);
}
