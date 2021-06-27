package signumj.crypto.ec;

import signumj.util.LibShabal;

public class Curve25519NativeImpl implements Curve25519 {
    @Override
    public void clampPrivateKey(byte[] privateKey) {
        // Literally faster to do in Java.
        privateKey[31] &= 0x7F;
        privateKey[31] |= 0x40;
        privateKey[0] &= 0xF8;
    }

    @Override
    public byte[] getPublicKey(byte[] privateKey) {
        byte[] publicKey = new byte[32];
        LibShabal.curve25519_get_public_key(privateKey, publicKey);
        return publicKey;
    }

    @Override
    public byte[] getSharedSecret(byte[] privateKey, byte[] publicKey) {
        byte[] sharedSecret = new byte[32];
        LibShabal.curve25519_get_shared_secret(privateKey, publicKey, sharedSecret);
        return sharedSecret;
    }

    @Override
    public byte[] sign(byte[] messageSha256, byte[] privateKey) {
        byte[] signature = new byte[64];
        LibShabal.curve25519_sign(privateKey, messageSha256, signature);
        return signature;
    }

    @Override
    public boolean verify(byte[] messageSha256, byte[] signature, byte[] publicKey, boolean enforceCanonical) {
        return byteToBool(LibShabal.curve25519_verify(publicKey, signature, messageSha256, boolToByte(enforceCanonical)));
    }

    private boolean byteToBool(byte b) {
        return b != 0;
    }

    private byte boolToByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }
}
