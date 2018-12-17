package burst.kit.burst;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.BurstID;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jcajce.provider.digest.SHA256;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

class BurstCryptoImpl extends AbstractBurstCrypto {

    static final BurstCryptoImpl INSTANCE = new BurstCryptoImpl();

    private final MessageDigest sha256;
    private final ThreadLocal<SecureRandom> secureRandom = ThreadLocal.withInitial(SecureRandom::new);
    private final long EPOCH_BEGINNING;

    private BurstCryptoImpl() {
        this.sha256 = getSha256();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.YEAR, 2014);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.EPOCH_BEGINNING = calendar.getTimeInMillis();
    }

    private MessageDigest getSha256() {
        return new SHA256.Digest();
    }

    @Override
    public byte[] getPrivateKey(String passphrase) {
        byte[] s = sha256.digest(stringToBytes(passphrase));
        Curve25519.clamp(s);
        return s;
    }

    @Override
    public byte[] getPublicKey(byte[] privateKey) {
        byte[] publicKey = new byte[32];
        Curve25519.keygen(publicKey, null, privateKey);
        return publicKey;
    }

    @Override
    public BurstAddress getBurstAddressFromPublic(byte[] publicKey) {
        byte[] hash = sha256.digest(publicKey);
        BigInteger bigInteger = new BigInteger(1, new byte[] {hash[7], hash[6], hash[5], hash[4], hash[3], hash[2], hash[1], hash[0]});
        return BurstAddress.fromNumericId(new BurstID(bigInteger.longValue()));
    }

    @Override
    public BurstID fullHashToId(byte[] hash) throws IllegalArgumentException {
        if (hash == null || hash.length < 8) {
            throw new IllegalArgumentException("Invalid hash: " + Arrays.toString(hash));
        }
        BigInteger bigInteger = new BigInteger(1, new byte[] {hash[7], hash[6], hash[5], hash[4], hash[3], hash[2], hash[1], hash[0]});
        return new BurstID(bigInteger.longValue());
    }

    @Override
    public byte[] getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey) {
        byte[] sharedSecret = new byte[32];
        Curve25519.curve(sharedSecret, myPrivateKey, theirPublicKey);
        return sharedSecret;
    }

    @Override
    public byte[] sign(byte[] message, byte[] privateKey) {
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

        return signature;
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
    public byte[] aesEncrypt(byte[] plaintext, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        try {
            byte[] sharedSecret = getSharedSecret(myPrivateKey, theirPublicKey);
            for (int i = 0; i < 32; i++) {
                sharedSecret[i] ^= nonce[i];
            }
            byte[] key = sha256.digest(sharedSecret);
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
            return result;
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] aesDecrypt(byte[] encrypted, byte[] myPrivateKey, byte[] theirPublicKey, byte[] nonce) {
        try {
            if (encrypted.length < 16 || encrypted.length % 16 != 0) {
                throw new InvalidCipherTextException("invalid ciphertext");
            }
            byte[] iv = Arrays.copyOfRange(encrypted, 0, 16);
            byte[] ciphertext = Arrays.copyOfRange(encrypted, 16, encrypted.length);
            byte[] sharedSecret = getSharedSecret(myPrivateKey, theirPublicKey);
            for (int i = 0; i < 32; i++) {
                sharedSecret[i] ^= nonce[i];
            }
            byte[] key = sha256.digest(sharedSecret);
            PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
            CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
            aes.init(false, ivAndKey);
            byte[] output = new byte[aes.getOutputSize(ciphertext.length)];
            int plaintextLength = aes.processBytes(ciphertext, 0, ciphertext.length, output, 0);
            plaintextLength += aes.doFinal(output, plaintextLength);
            byte[] result = new byte[plaintextLength];
            System.arraycopy(output, 0, result, 0, result.length);
            return result;
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public BurstEncryptedMessage encryptBytesMessage(byte[] message, byte[] myPrivateKey, byte[] theirPublicKey) {
        return encryptPlainMessage(message, false, myPrivateKey, theirPublicKey);
    }

    @Override
    public BurstEncryptedMessage encryptTextMessage(String message, byte[] myPrivateKey, byte[] theirPublicKey) {
        return encryptPlainMessage(stringToBytes(message), true, myPrivateKey, theirPublicKey);
    }

    private BurstEncryptedMessage encryptPlainMessage(byte[] message, boolean isText, byte[] myPrivateKey, byte[] theirPublicKey) {
        if (message.length == 0) {
            return new BurstEncryptedMessage(new byte[0], new byte[0], isText);
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(message);
            gzip.flush();
            gzip.close();
            byte[] compressedPlaintext = bos.toByteArray();
            byte[] nonce = new byte[32];
            secureRandom.get().nextBytes(nonce);
            byte[] data = aesEncrypt(compressedPlaintext, myPrivateKey, theirPublicKey, nonce);
            return new BurstEncryptedMessage(data, nonce, isText);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] decryptMessage(BurstEncryptedMessage message, byte[] myPrivateKey, byte[] theirPublicKey) {
        if (message.getData().length == 0) {
            return message.getData();
        }
        byte[] compressedPlaintext = aesDecrypt(message.getData(), myPrivateKey, theirPublicKey, message.getNonce());
        try (ByteArrayInputStream bis = new ByteArrayInputStream(compressedPlaintext); GZIPInputStream gzip = new GZIPInputStream(bis); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int nRead;
            while((nRead = gzip.read(buffer, 0, buffer.length)) > 0) {
                bos.write(buffer, 0, nRead);
            }
            bos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String rsEncode(BurstID burstID) {
        return ReedSolomon.encode(burstID.getSignedLongId());
    }

    @Override
    public BurstID rsDecode(String rs) throws IllegalArgumentException {
        rs = rs.toUpperCase();
        long rsValue;
        try {
            rsValue = ReedSolomon.decode(rs);
        } catch (ReedSolomon.DecodeException e) {
            throw new IllegalArgumentException("Reed-Solomon decode failed", e);
        }
        return new BurstID(rsValue);
    }

    @Override
    public Date fromEpochTime(long epochTime) {
        return new Date(EPOCH_BEGINNING + epochTime * 1000L);
    }
}
