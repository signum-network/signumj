package burst.kit.burst;

import brs.crypto.Crypto;
import brs.util.Convert;
import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

class BurstCryptoImpl extends AbstractBurstCrypto {
    private final MessageDigest sha256;
    private final ThreadLocal<SecureRandom> secureRandom = ThreadLocal.withInitial(SecureRandom::new);

    BurstCryptoImpl() {
        this.sha256 = getSha256();
    }

    private MessageDigest getSha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // TODO fix
        }
    }

    @Override
    public HexStringByteArray getPrivateKey(String passphrase) {
        byte[] s = sha256.digest(stringToBytes(passphrase));
        Curve25519.clamp(s);
        return new HexStringByteArray(s);
    }

    @Override
    public HexStringByteArray getPublicKey(byte[] privateKey) {
        byte[] publicKey = new byte[32];
        Curve25519.keygen(publicKey, null, privateKey);
        return new HexStringByteArray(publicKey); // TODO just return bytes and convert to hsba in AbstractBurstCrypto
    }

    @Override
    public BurstAddress getBurstAddressFromPublic(byte[] publicKey) {
        byte[] hash = sha256.digest(publicKey);
        BigInteger bigInteger = new BigInteger(1, new byte[] {hash[7], hash[6], hash[5], hash[4], hash[3], hash[2], hash[1], hash[0]});
        return BurstAddress.fromNumericId(new BurstID(bigInteger.longValue()));
    }

    @Override
    public HexStringByteArray getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey) {
        byte[] sharedSecret = new byte[32];
        Curve25519.curve(sharedSecret, myPrivateKey, theirPublicKey);
        return new HexStringByteArray(sharedSecret);
    }

    @Override
    public HexStringByteArray sign(byte[] message, byte[] privateKey) {
        byte[] P = new byte[32];
        byte[] s = new byte[32];
        MessageDigest digest = getSha256();

        Curve25519.keygen(P, s, privateKey);
        byte[] m = digest.digest(message);

        digest.update(m);
        byte[] x = digest.digest(s);

        byte[] Y = new byte[32];
        Curve25519.keygen(Y, null, x);

        digest.update(m);
        byte[] h = digest.digest(Y);

        byte[] v = new byte[32];
        Curve25519.sign(v, h, x, s);

        byte[] signature = new byte[64];
        System.arraycopy(v, 0, signature, 0, 32);
        System.arraycopy(h, 0, signature, 32, 32);

        return new HexStringByteArray(signature);
    }

    @Override
    public boolean verify(byte[] signature, byte[] message, byte[] publicKey, boolean enforceCanonical) {
        if (enforceCanonical && (!Curve25519.isCanonicalSignature(signature) || !Curve25519.isCanonicalPublicKey(publicKey))) {
            return false;
        }

        byte[] Y = new byte[32];
        byte[] v = new byte[32];
        System.arraycopy(signature, 0, v, 0, 32);
        byte[] h = new byte[32];
        System.arraycopy(signature, 32, h, 0, 32);
        Curve25519.verify(Y, v, h, publicKey);

        MessageDigest digest = getSha256();
        byte[] m = digest.digest(message);
        digest.update(m);
        byte[] h2 = digest.digest(Y);

        return Arrays.equals(h, h2);
    }

    @Override
    public HexStringByteArray aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        try {
            byte[] dhSharedSecret = new byte[32];
            Curve25519.curve(dhSharedSecret, myPrivateKey, theirPublicKey);
            for (int i = 0; i < 32; i++) {
                dhSharedSecret[i] ^= nonce[i];
            }
            byte[] key = sha256.digest(dhSharedSecret);
            byte[] iv = new byte[16];
            secureRandom.get().nextBytes(iv);
            PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
            aes.init(true, ivAndKey);
            byte[] output = new byte[aes.getOutputSize(plaintext.length)];
            int ciphertextLength = aes.processBytes(plaintext, 0, plaintext.length, output, 0);
            ciphertextLength += aes.doFinal(output, ciphertextLength);
            byte[] result = new byte[iv.length + ciphertextLength];
            System.arraycopy(iv, 0, result, 0, iv.length);
            System.arraycopy(output, 0, result, iv.length, ciphertextLength);
            return new HexStringByteArray(result);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public HexStringByteArray aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        try {
            if (encrypted.length < 16 || encrypted.length % 16 != 0) {
                throw new InvalidCipherTextException("invalid ciphertext");
            }
            byte[] iv = Arrays.copyOfRange(encrypted, 0, 16);
            byte[] ciphertext = Arrays.copyOfRange(encrypted, 16, encrypted.length);
            byte[] dhSharedSecret = new byte[32];
            Curve25519.curve(dhSharedSecret, myPrivateKey, theirPublicKey);
            for (int i = 0; i < 32; i++) {
                dhSharedSecret[i] ^= nonce[i];
            }
            byte[] key = sha256.digest(dhSharedSecret);
            PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
            aes.init(false, ivAndKey);
            byte[] output = new byte[aes.getOutputSize(ciphertext.length)];
            int plaintextLength = aes.processBytes(ciphertext, 0, ciphertext.length, output, 0);
            plaintextLength += aes.doFinal(output, plaintextLength);
            byte[] result = new byte[plaintextLength];
            System.arraycopy(output, 0, result, 0, result.length);
            return new HexStringByteArray(result);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public BurstEncryptedMessage encryptMessage(String message, byte[] myPrivateKey, byte[] theirPublicKey) {
        if (message.length() == 0) {
            return null; // TODO empty
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(message.getBytes(StandardCharsets.UTF_8));
            gzip.flush();
            gzip.close();
            byte[] compressedPlaintext = bos.toByteArray();
            byte[] nonce = new byte[32];
            secureRandom.get().nextBytes(nonce);
            byte[] data = aesEncrypt(compressedPlaintext, myPrivateKey, theirPublicKey, nonce).getBytes();
            return new BurstEncryptedMessage(data, nonce);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] decryptMessage(BurstEncryptedMessage message, byte[] myPrivateKey, byte[] theirPublicKey) {
        if (message.getData().getBytes().length == 0) {
            return message.getData().getBytes();
        }
        byte[] compressedPlaintext = aesDecrypt(message.getData().getBytes(), myPrivateKey, theirPublicKey, message.getNonce().getBytes()).getBytes();
        try (ByteArrayInputStream bis = new ByteArrayInputStream(compressedPlaintext);
             GZIPInputStream gzip = new GZIPInputStream(bis);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int nRead;
            while ((nRead = gzip.read(buffer, 0, buffer.length)) > 0) {
                bos.write(buffer, 0, nRead);
            }
            bos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
