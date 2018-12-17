package burst.kit.burst;


import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;

import java.nio.charset.StandardCharsets;

abstract class AbstractBurstCrypto implements BurstCrypto {
    protected byte[] stringToBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getPublicKey(String passphrase) {
        return getPublicKey(getPrivateKey(passphrase));
    }

    @Override
    public BurstAddress getBurstAddressFromPassphrase(String passphrase) {
        return getBurstAddressFromPrivate(getPrivateKey(passphrase));
    }

    @Override
    public BurstAddress getBurstAddressFromPrivate(byte[] privateKey) {
        return getBurstAddressFromPublic(getPublicKey(privateKey));
    }

    @Override
    public byte[] getSharedSecret(String myPassphrase, byte[] theirPublicKey) {
        return getSharedSecret(getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public byte[] sign(byte[] message, String passphrase) {
        return sign(message, getPrivateKey(passphrase));
    }

    @Override
    public byte[] sign(String message, String passphrase) {
        return sign(stringToBytes(message), getPrivateKey(passphrase));
    }

    @Override
    public byte[] sign(String message, byte[] privateKey) {
        return sign(stringToBytes(message), privateKey);
    }

    @Override
    public byte[] aesEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey) {
        return aesEncrypt(plaintext, getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public byte[] aesEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey, byte[] nonce) {
        return aesEncrypt(plaintext, getPrivateKey(myPassphrase), theirPublicKey, nonce);
    }

    @Override
    public byte[] aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey) {
        return aesEncrypt(plaintext, myPrivateKey, theirPublicKey, new byte[32]);
    }

    @Override
    public byte[] aesEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey) {
        return aesEncrypt(stringToBytes(plaintext), getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public byte[] aesEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey, byte[] nonce) {
        return aesEncrypt(stringToBytes(plaintext), getPrivateKey(myPassphrase), theirPublicKey, nonce);
    }

    @Override
    public byte[] aesEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey) {
        return aesEncrypt(stringToBytes(plaintext), myPrivateKey, theirPublicKey);
    }

    @Override
    public byte[] aesEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        return aesEncrypt(stringToBytes(plaintext), myPrivateKey, theirPublicKey, nonce);
    }

    @Override
    public byte[] aesDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey) {
        return aesDecrypt(encrypted, getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public byte[] aesDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey, byte[] nonce) {
        return aesDecrypt(encrypted, getPrivateKey(myPassphrase), theirPublicKey, nonce);
    }

    @Override
    public byte[] aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey) {
        return aesDecrypt(encrypted, myPrivateKey, theirPublicKey, new byte[32]);
    }

    @Override
    public byte[] aesDecrypt(String encrypted, String myPassphrase, byte[] theirPublicKey) {
        return aesDecrypt(stringToBytes(encrypted), getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public byte[] aesDecrypt(String encrypted, String myPassphrase, byte[] theirPublicKey, byte[] nonce) {
        return aesDecrypt(stringToBytes(encrypted), getPrivateKey(myPassphrase), theirPublicKey, nonce);
    }

    @Override
    public byte[] aesDecrypt(String encrypted, byte[] myPrivateKey, byte[] theirPublicKey) {
        return aesDecrypt(stringToBytes(encrypted), myPrivateKey, theirPublicKey);
    }

    @Override
    public byte[] aesDecrypt(String encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        return aesDecrypt(stringToBytes(encrypted), myPrivateKey, theirPublicKey, nonce);
    }

    @Override
    public BurstEncryptedMessage encryptBytesMessage(byte[] message, String myPassphrase, byte[] theirPublicKey) {
        return encryptBytesMessage(message, getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public BurstEncryptedMessage encryptTextMessage(String message, String myPassphrase, byte[] theirPublicKey) {
        return encryptTextMessage(message, getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public byte[] decryptMessage(BurstEncryptedMessage message, String myPassphrase, byte[] theirPublicKey) {
        return decryptMessage(message, getPrivateKey(myPassphrase), theirPublicKey);
    }
}
