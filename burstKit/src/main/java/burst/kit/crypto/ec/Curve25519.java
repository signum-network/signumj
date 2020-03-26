package burst.kit.crypto.ec;

public interface Curve25519 {
    void clampPrivateKey(byte[] privateKey);
    byte[] getPublicKey(byte[] privateKey);
    byte[] getSharedSecret(byte[] privateKey, byte[] publicKey);

    byte[] sign(byte[] messageSha256, byte[] privateKey);
    boolean verify(byte[] messageSha256, byte[] signature, byte[] publicKey, boolean enforceCanonical);
}
