package burst.kit.crypto;

import burst.kit.crypto.ec.Curve25519;
import burst.kit.crypto.ec.Curve25519Impl;
import burst.kit.crypto.hash.BurstHashProvider;
import burst.kit.crypto.hash.shabal.Shabal256;
import burst.kit.crypto.rs.ReedSolomon;
import burst.kit.crypto.rs.ReedSolomonImpl;
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
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import org.bouncycastle.jcajce.provider.digest.SHA256;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

class BurstCryptoImpl extends AbstractBurstCrypto {

    static final BurstCryptoImpl INSTANCE = new BurstCryptoImpl();

    private final ThreadLocal<SecureRandom> secureRandom = ThreadLocal.withInitial(SecureRandom::new);
    private final Curve25519 curve25519;
    private final ReedSolomon reedSolomon;
    private final long epochBeginning;

    private BurstCryptoImpl() {
        this.curve25519 = new Curve25519Impl(this::getSha256);
        this.reedSolomon = new ReedSolomonImpl();
        this.epochBeginning = calculateEpochBeginning();
        BurstHashProvider.init();
    }

    private long calculateEpochBeginning() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.YEAR, 2014);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    @Override
    public MessageDigest getSha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return new SHA256.Digest(); // Fallback to Bouncy Castle's implementation
        }
    }

    @Override
    public MessageDigest getShabal256() {
        try {
            return MessageDigest.getInstance("Shabal-256");
        } catch (NoSuchAlgorithmException e) {
            return new Shabal256();
        }
    }

    @Override
    public MessageDigest getRipeMD160() {
        try {
            return MessageDigest.getInstance("RIPEMD-160");
        } catch (NoSuchAlgorithmException e) {
            return new RIPEMD160.Digest(); // Fallback to Bouncy Castle's implementation
        }
    }

    @Override
    public byte[] getPrivateKey(String passphrase) {
        byte[] privateKey = getSha256().digest(stringToBytes(passphrase));
        curve25519.clampPrivateKey(privateKey);
        return privateKey;
    }

    @Override
    public byte[] getPublicKey(byte[] privateKey) {
        return curve25519.getPublicKey(privateKey);
    }

    @Override
    public BurstAddress getBurstAddressFromPublic(byte[] publicKey) {
        return BurstAddress.fromId(hashToId(getSha256().digest(publicKey)));
    }

    @Override
    public BurstID hashToId(byte[] hash) throws IllegalArgumentException {
        if (hash == null || hash.length < 8) {
            throw new IllegalArgumentException("Invalid hash: " + Arrays.toString(hash));
        }
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (hash[7-i] & 0xFF);
        }
        return BurstID.fromLong(result);
    }

    @Override
    public byte[] getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey) {
        return curve25519.getSharedSecret(myPrivateKey, theirPublicKey);
    }

    @Override
    public byte[] sign(byte[] message, byte[] privateKey) {
        return curve25519.sign(message, privateKey);
    }

    @Override
    public byte[] signTransaction(byte[] privateKey, byte[] unsignedTransaction) {
        byte[] signature = sign(unsignedTransaction, privateKey);
        byte[] signedTransaction = new byte[unsignedTransaction.length];
        System.arraycopy(unsignedTransaction, 0, signedTransaction, 0, unsignedTransaction.length); // Duplicate the transaction
        System.arraycopy(signature, 0, signedTransaction, 96, 64); // Insert the signature
        return signedTransaction;
    }

    @Override
    public boolean verify(byte[] signature, byte[] message, byte[] publicKey, boolean enforceCanonical) {
        return curve25519.verify(message, signature, publicKey, enforceCanonical);
    }

    @Override
    public byte[] aesEncrypt(byte[] plaintext, byte[] signingKey, byte[] nonce) throws IllegalArgumentException {
        if (signingKey.length != 32) {
            throw new IllegalArgumentException("Key length must be 32 bytes");
        }
        try {
            for (int i = 0; i < 32; i++) {
                signingKey[i] ^= nonce[i];
            }
            byte[] key = getSha256().digest(signingKey);
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
    public byte[] aesDecrypt(byte[] encrypted, byte[] signingKey, byte[] nonce) throws IllegalArgumentException {
        if (signingKey.length != 32) {
            throw new IllegalArgumentException("Key length must be 32 bytes");
        }
        try {
            if (encrypted.length < 16 || encrypted.length % 16 != 0) {
                throw new InvalidCipherTextException("invalid ciphertext"); // TODO
            }
            byte[] iv = Arrays.copyOfRange(encrypted, 0, 16);
            byte[] ciphertext = Arrays.copyOfRange(encrypted, 16, encrypted.length);
            for (int i = 0; i < 32; i++) {
                signingKey[i] ^= nonce[i];
            }
            byte[] key = getSha256().digest(signingKey);
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
            byte[] data = aesSharedEncrypt(compressedPlaintext, myPrivateKey, theirPublicKey, nonce);
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
        byte[] compressedPlaintext = aesSharedDecrypt(message.getData(), myPrivateKey, theirPublicKey, message.getNonce());
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
        return reedSolomon.encode(burstID.getSignedLongId());
    }

    @Override
    public BurstID rsDecode(String rs) throws IllegalArgumentException {
        rs = rs.toUpperCase();
        long rsValue;
        try {
            rsValue = reedSolomon.decode(rs);
        } catch (ReedSolomon.DecodeException e) {
            throw new IllegalArgumentException("Reed-Solomon decode failed", e);
        }
        return BurstID.fromLong(rsValue);
    }

    @Override
    public Date fromEpochTime(long epochTime) {
        return new Date(epochBeginning + epochTime * 1000L);
    }
}
