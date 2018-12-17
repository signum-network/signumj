package burst.kit.burst;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.HexStringByteArray;

import java.nio.charset.StandardCharsets;

abstract class AbstractBurstCrypto implements BurstCrypto {
    protected byte[] stringToBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }
    
    @Override
    public HexStringByteArray getPublicKey(String passphrase) {
        return getPublicKey(getPrivateKey(passphrase));
    }

    @Override
    public HexStringByteArray getPublicKey(HexStringByteArray privateKey) {
        return getPublicKey(privateKey.getBytes());
    }

    @Override
    public BurstAddress getBurstAddressFromPassphrase(String passphrase) {
        return getBurstAddressFromPrivate(getPrivateKey(passphrase));
    }

    @Override
    public BurstAddress getBurstAddressFromPrivate(byte[] privateKey) {
        return getBurstAddressFromPublic(privateKey);
    }

    @Override
    public BurstAddress getBurstAddressFromPrivate(HexStringByteArray privateKey) {
        return getBurstAddressFromPublic(privateKey);
    }

    @Override
    public BurstAddress getBurstAddressFromPublic(HexStringByteArray publicKey) {
        return getBurstAddressFromPublic(publicKey.getBytes());
    }

    @Override
    public HexStringByteArray getSharedSecret(String myPassphrase, byte[] theirPublicKey) {
        return getSharedSecret(getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public HexStringByteArray getSharedSecret(String myPassphrase, HexStringByteArray theirPublicKey) {
        return getSharedSecret(getPrivateKey(myPassphrase), theirPublicKey);
    }

    @Override
    public HexStringByteArray getSharedSecret(byte[] myPrivateKey, HexStringByteArray theirPublicKey) {
        return getSharedSecret(myPrivateKey, theirPublicKey.getBytes());
    }

    @Override
    public HexStringByteArray getSharedSecret(HexStringByteArray myPrivateKey, byte[] theirPublicKey) {
        return getSharedSecret(myPrivateKey.getBytes(), theirPublicKey);
    }

    @Override
    public HexStringByteArray getSharedSecret(HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey) {
        return getSharedSecret(myPrivateKey.getBytes(), theirPublicKey.getBytes());
    }
    
    @Override
    public HexStringByteArray sign(byte[] message, String passphrase) {
        return sign(message, getPrivateKey(passphrase));
    }
    
    @Override
    public HexStringByteArray sign(byte[] message, HexStringByteArray privateKey) {
        return sign(message, privateKey.getBytes());
    }
    
    @Override
    public HexStringByteArray sign(HexStringByteArray message, String passphrase) {
        return sign(message.getBytes(), getPrivateKey(passphrase));
    }
    
    @Override
    public HexStringByteArray sign(HexStringByteArray message, byte[] privateKey) {
        return sign(message.getBytes(), privateKey);
    }
    
    @Override
    public HexStringByteArray sign(HexStringByteArray message, HexStringByteArray privateKey) {
        return sign(message.getBytes(), privateKey.getBytes());
    }
    
    @Override
    public HexStringByteArray sign(String message, String passphrase) {
        return sign(stringToBytes(message), getPrivateKey(passphrase));
    }
    @Override
    public HexStringByteArray sign(String message, byte[] privateKey) {
        return sign(stringToBytes(message), privateKey);
    }
    
    @Override
    public HexStringByteArray sign(String message, HexStringByteArray privateKey) {
        return sign(stringToBytes(message), privateKey.getBytes());
    }

    @Override
    public boolean verify(HexStringByteArray signature, HexStringByteArray message, HexStringByteArray publicKey, boolean enforceCanonical) {
        return verify(signature.getBytes(), message.getBytes(), publicKey.getBytes(), enforceCanonical);
    }

    @Override
    public HexStringByteArray aesEncrypt(byte[] plaintext, String myPassphrase, byte[] theirPublicKey) {
        return aesEncrypt(plaintext, getPrivateKey(myPassphrase).getBytes(), theirPublicKey);
    }

    @Override
    public HexStringByteArray aesEncrypt(HexStringByteArray plaintext, String myPassphrase, byte[] theirPublicKey) {
        return aesEncrypt(plaintext.getBytes(), getPrivateKey(myPassphrase).getBytes(), theirPublicKey);
    }

    @Override
    public HexStringByteArray aesEncrypt(String plaintext, String myPassphrase, byte[] theirPublicKey) {
        return aesEncrypt(stringToBytes(plaintext), getPrivateKey(myPassphrase).getBytes(), theirPublicKey);
    }

    @Override
    public HexStringByteArray aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey) {
        return aesEncrypt(plaintext, myPrivateKey, new byte[32]);
    }

    @Override
    public HexStringByteArray aesEncrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        return aesEncrypt(stringToBytes(plaintext), myPrivateKey, theirPublicKey, nonce);
    }

    @Override
    public HexStringByteArray aesEncrypt(HexStringByteArray plaintext, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey) {
        return aesEncrypt(plaintext.getBytes(), myPrivateKey.getBytes(), theirPublicKey.getBytes());
    }

    @Override
    public HexStringByteArray aesEncrypt(HexStringByteArray plaintext, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey, HexStringByteArray nonce) {
        return aesEncrypt(plaintext.getBytes(), myPrivateKey.getBytes(), theirPublicKey.getBytes(), nonce.getBytes());
    }

    @Override
    public HexStringByteArray aesEncrypt(String plaintext, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey, HexStringByteArray nonce) {
        return aesEncrypt(stringToBytes(plaintext) ,myPrivateKey.getBytes(), theirPublicKey.getBytes(), nonce.getBytes());
    }

    @Override
    public HexStringByteArray aesDecrypt(byte[] encrypted, String myPassphrase, byte[] theirPublicKey) {
        return aesDecrypt(encrypted, getPrivateKey(myPassphrase).getBytes(), theirPublicKey);
    }

    @Override
    public HexStringByteArray aesDecrypt(HexStringByteArray encrypted, String myPassphrase, byte[] theirPublicKey) {
        return aesDecrypt(encrypted.getBytes(), getPrivateKey(myPassphrase).getBytes(), theirPublicKey);
    }

    @Override
    public HexStringByteArray aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey) {
        return aesDecrypt(encrypted, myPrivateKey, theirPublicKey, new byte[32]);
    }

    @Override
    public HexStringByteArray aesDecrypt(HexStringByteArray encrypted, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey) {
        return aesDecrypt(encrypted.getBytes(), myPrivateKey.getBytes(), theirPublicKey.getBytes());
    }

    @Override
    public HexStringByteArray aesDecrypt(HexStringByteArray encrypted, HexStringByteArray myPrivateKey, HexStringByteArray theirPublicKey, HexStringByteArray nonce) {
        return aesDecrypt(encrypted.getBytes(), myPrivateKey.getBytes(), theirPublicKey.getBytes(), nonce.getBytes());
    }
}
