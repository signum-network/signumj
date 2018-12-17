package burst.kit.burst;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.BurstID;

import java.util.Date;

public interface BurstCrypto {
    byte[] getPrivateKey(String passphrase);
    
    byte[] getPublicKey(String passphrase);
    byte[] getPublicKey(byte[] privateKey);
    
    BurstAddress getBurstAddressFromPassphrase(String passphrase);
    BurstAddress getBurstAddressFromPrivate(byte[] privateKey);
    BurstAddress getBurstAddressFromPublic(byte[] publicKey);

    BurstID fullHashToId(byte[] fullHash) throws IllegalArgumentException;

    byte[] getSharedSecret(String myPassphrase, byte[] theirPublicKey);
    byte[] getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey);

    byte[] sign(byte[] message, String passphrase);
    byte[] sign(byte[] message, byte[] privateKey);
    byte[] sign(String message, String passphrase);
    byte[] sign(String message, byte[] privateKey);

    boolean verify(byte[] signature, byte[] message, byte[] publicKey, boolean enforceCanonical);

    byte[] aesEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey);
    byte[] aesEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey, byte[] nonce);
    byte[] aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey);
    byte[] aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);
    byte[] aesEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey);
    byte[] aesEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey, byte[] nonce);
    byte[] aesEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey);
    byte[] aesEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);

    byte[] aesDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey);
    byte[] aesDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey, byte[] nonce);
    byte[] aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey);
    byte[] aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);
    byte[] aesDecrypt(String encrypted, String myPassphrase, byte[] theirPublicKey);
    byte[] aesDecrypt(String encrypted, String myPassphrase, byte[] theirPublicKey, byte[] nonce);
    byte[] aesDecrypt(String encrypted, byte[] myPrivateKey, byte[] theirPublicKey);
    byte[] aesDecrypt(String encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);

    BurstEncryptedMessage encryptBytesMessage(byte[] message, String myPassphrase, byte[] theirPublicKey);
    BurstEncryptedMessage encryptBytesMessage(byte[] message, byte[] myPrivateKey, byte[] theirPublicKey);
    BurstEncryptedMessage encryptTextMessage(String message, String myPassphrase, byte[] theirPublicKey);
    BurstEncryptedMessage encryptTextMessage(String message, byte[] myPrivateKey, byte[] theirPublicKey);

    byte[] decryptMessage(BurstEncryptedMessage message, String myPassphrase, byte[] theirPublicKey);
    byte[] decryptMessage(BurstEncryptedMessage message, byte[] myPrivateKey, byte[] theirPublicKey);

    String rsEncode(BurstID burstID);
    BurstID rsDecode(String rs) throws IllegalArgumentException;

    Date fromEpochTime(long epochTime);

    static BurstCrypto getInstance() {
        return BurstCryptoImpl.INSTANCE;
    }
}
