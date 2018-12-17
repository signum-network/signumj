package burst.kit.burst;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.HexStringByteArray;

import java.security.NoSuchAlgorithmException;

public interface BurstCrypto {
    HexStringByteArray getPrivateKey(String passphrase); // Converged.
    
    HexStringByteArray getPublicKey(String passphrase);
    HexStringByteArray getPublicKey(byte[] privateKey); // Converged.
    HexStringByteArray getPublicKey(HexStringByteArray privateKey);
    
    BurstAddress getBurstAddressFromPassphrase(String passphrase);
    BurstAddress getBurstAddressFromPrivate(byte[] privateKey);
    BurstAddress getBurstAddressFromPrivate(HexStringByteArray privateKey);
    BurstAddress getBurstAddressFromPublic(byte[] publicKey); // Converged.
    BurstAddress getBurstAddressFromPublic(HexStringByteArray publicKey);

    HexStringByteArray getSharedSecret(String myPassphrase, byte[] theirPublicKey);
    HexStringByteArray getSharedSecret(String myPassphrase, HexStringByteArray theirPublicKey);
    HexStringByteArray getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey); // Converged.
    HexStringByteArray getSharedSecret(byte[] myPrivateKey, HexStringByteArray theirPublicKey);
    HexStringByteArray getSharedSecret(HexStringByteArray myPrivateKey, byte[] theirPublicKey);
    HexStringByteArray getSharedSecret(HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey);

    HexStringByteArray sign(byte[] message, String passphrase);
    HexStringByteArray sign(byte[] message, byte[] privateKey); // Converged.
    HexStringByteArray sign(byte[] message, HexStringByteArray privateKey);
    HexStringByteArray sign(HexStringByteArray message, String passphrase);
    HexStringByteArray sign(HexStringByteArray message, byte[] privateKey);
    HexStringByteArray sign(HexStringByteArray message, HexStringByteArray privateKey);
    HexStringByteArray sign(String message, String passphrase);
    HexStringByteArray sign(String message, byte[] privateKey);
    HexStringByteArray sign(String message, HexStringByteArray privateKey);

    boolean verify(byte[] signature, byte[] message, byte[] publicKey, boolean enforceCanonical); // Converged.
    boolean verify(HexStringByteArray signature, HexStringByteArray message, HexStringByteArray publicKey, boolean enforceCanonical);

    // TODO multiplex this properly.
    HexStringByteArray aesEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey);
    HexStringByteArray aesEncrypt(HexStringByteArray plaintext, String myPassphrase, byte[] theirPublicKey);
    HexStringByteArray aesEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey);
    HexStringByteArray aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey);
    HexStringByteArray aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce); // Converged.
    HexStringByteArray aesEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce);
    HexStringByteArray aesEncrypt(HexStringByteArray plaintext, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey);
    HexStringByteArray aesEncrypt(HexStringByteArray plaintext, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey, HexStringByteArray nonce);
    HexStringByteArray aesEncrypt(String plaintext, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey, HexStringByteArray nonce);

    // TODO multiplex this properly.
    HexStringByteArray aesDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey);
    HexStringByteArray aesDecrypt(HexStringByteArray encrypted, String myPassphrase, byte[] theirPublicKey);
    HexStringByteArray aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey);
    HexStringByteArray aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce); // Converged.
    HexStringByteArray aesDecrypt(HexStringByteArray encrypted, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey);
    HexStringByteArray aesDecrypt(HexStringByteArray encrypted, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey, HexStringByteArray nonce);

    BurstEncryptedMessage encryptMessage(String message, byte[] myPrivateKey, byte[] theirPublicKey);

    byte[] decryptMessage(BurstEncryptedMessage message, byte[] myPrivateKey, byte[] theirPublicKey);

    static BurstCrypto getInstance() {
        return new BurstCryptoImpl(); // TODO singleton
    }
}
