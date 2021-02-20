package burst.kit.crypto;

import burst.kit.crypto.ec.Curve25519;
import burst.kit.crypto.ec.Curve25519Impl;
import burst.kit.crypto.ec.Curve25519NativeImpl;
import burst.kit.crypto.hash.BurstHashProvider;
import burst.kit.crypto.hash.shabal.Shabal256;
import burst.kit.crypto.plot.PlotCalculator;
import burst.kit.crypto.plot.impl.MiningPlot;
import burst.kit.crypto.plot.impl.PlotCalculatorImpl;
import burst.kit.crypto.plot.impl.PlotCalculatorNativeImpl;
import burst.kit.crypto.rs.ReedSolomon;
import burst.kit.crypto.rs.ReedSolomonImpl;
import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.util.LibShabal;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

class BurstCryptoImpl extends AbstractBurstCrypto {

    static final BurstCryptoImpl INSTANCE = new BurstCryptoImpl();

    private final SecureRandom secureRandom = new SecureRandom();
    private final ReedSolomon reedSolomon;
    private final Curve25519 curve25519;
    private final Curve25519 nativeCurve25519;
    private final PlotCalculator plotCalculator;
    private final PlotCalculator nativePlotCalculator;

    /**
     * The Burst Epoch, as a unix time
     */
    private final long epochBeginning;

    private final AtomicBoolean nativeEnabled = new AtomicBoolean(true);

    private BurstCryptoImpl() {
        this.reedSolomon = new ReedSolomonImpl();
        this.curve25519 = new Curve25519Impl(this::getSha256);
        this.nativeCurve25519 = new Curve25519NativeImpl();
        this.plotCalculator = new PlotCalculatorImpl(this::getShabal256);
        this.nativePlotCalculator = new PlotCalculatorNativeImpl(this::getShabal256);
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
            return new Shabal256(); // Fallback to our own implementation
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
    public MessageDigest getMD5() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return new MD5.Digest(); // Fallback to Bouncy Castle's implementation
        }
    }

    @Override
    public byte[] getPrivateKey(String passphrase) {
        byte[] privateKey = getSha256().digest(stringToBytes(passphrase));
        if (nativeEnabled()) {
            nativeCurve25519.clampPrivateKey(privateKey);
        } else {
            curve25519.clampPrivateKey(privateKey);
        }
        return privateKey;
    }

    @Override
    public byte[] getPublicKey(byte[] privateKey) {
        if (nativeEnabled()) {
            return nativeCurve25519.getPublicKey(privateKey);
        } else {
            return curve25519.getPublicKey(privateKey);
        }
    }

    @Override
    public BurstAddress getBurstAddressFromPublic(byte[] publicKey) {
        BurstAddress address = BurstAddress.fromId(hashToId(getSha256().digest(publicKey)));
        address.setPublicKey(publicKey);
        return address;
    }

    @Override
    public BurstID hashToId(byte[] hash) {
        if (hash == null || hash.length < 8) {
            throw new IllegalArgumentException("Invalid hash: " + Arrays.toString(hash));
        }
        return BurstID.fromLong(bytesToLongLE(hash));
    }

    @Override
    public byte[] getSharedSecret(byte[] myPrivateKey, byte[] theirPublicKey) {
        if (nativeEnabled()) {
            return nativeCurve25519.getSharedSecret(myPrivateKey, theirPublicKey);
        } else {
            return curve25519.getSharedSecret(myPrivateKey, theirPublicKey);
        }
    }

    @Override
    public byte[] sign(byte[] message, byte[] privateKey) {
        byte[] messageSha256 = getSha256().digest(message);
        if (nativeEnabled()) {
            return nativeCurve25519.sign(messageSha256, privateKey);
        } else {
            return curve25519.sign(messageSha256, privateKey);
        }
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
        byte[] messageSha256 = getSha256().digest(message);
        if (nativeEnabled()) {
            return nativeCurve25519.verify(messageSha256, signature, publicKey, enforceCanonical);
        } else {
            return curve25519.verify(messageSha256, signature, publicKey, enforceCanonical);
        }
    }

    @Override
    public byte[] aesEncrypt(byte[] plaintext, byte[] signingKey, byte[] nonce) {
        if (signingKey.length != 32) {
            throw new IllegalArgumentException("Key length must be 32 bytes");
        }
        try {
            for (int i = 0; i < 32; i++) {
                signingKey[i] ^= nonce[i];
            }
            byte[] key = getSha256().digest(signingKey);
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
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
    public byte[] aesDecrypt(byte[] encrypted, byte[] signingKey, byte[] nonce) {
        if (signingKey.length != 32) {
            throw new IllegalArgumentException("Key length must be 32 bytes");
        }
        try {
            if (encrypted.length < 16 || encrypted.length % 16 != 0) {
                throw new InvalidCipherTextException("invalid ciphertext");
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
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(message);
            gzip.flush();
            gzip.close();
            byte[] compressedPlaintext = bos.toByteArray();
            byte[] nonce = new byte[32];
            secureRandom.nextBytes(nonce);
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
    public BurstID rsDecode(String rs) {
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
    public long fromBurstTime(int burstTime) {
        return epochBeginning + (burstTime * 1000L);
    }

    @Override
    public int toBurstTime(long unixTime) {
        return Math.toIntExact((unixTime - epochBeginning) / 1000);
    }

    @Override
    public long bytesToLongBE(byte[] bytes, int offset) {
        return ((long) (bytes[offset + 7] & 0xFF))
                | (((long) (bytes[offset + 6] & 0xFF)) << 8)
                | (((long) (bytes[offset + 5] & 0xFF)) << 16)
                | (((long) (bytes[offset + 4] & 0xFF)) << 24)
                | (((long) (bytes[offset + 3] & 0xFF)) << 32)
                | (((long) (bytes[offset + 2] & 0xFF)) << 40)
                | (((long) (bytes[offset + 1] & 0xFF)) << 48)
                | (((long) (bytes[offset] & 0xFF)) << 56);
    }

    @Override
    public long bytesToLongLE(byte[] bytes, int offset) {
        return ((long) (bytes[offset] & 0xFF))
                | (((long) (bytes[offset + 1] & 0xFF)) << 8)
                | (((long) (bytes[offset + 2] & 0xFF)) << 16)
                | (((long) (bytes[offset + 3] & 0xFF)) << 24)
                | (((long) (bytes[offset + 4] & 0xFF)) << 32)
                | (((long) (bytes[offset + 5] & 0xFF)) << 40)
                | (((long) (bytes[offset + 6] & 0xFF)) << 48)
                | (((long) (bytes[offset + 7] & 0xFF)) << 56);
    }

    @Override
    public int bytesToIntBE(byte[] bytes, int offset) {
        return (bytes[offset + 3] & 0xFF)
                | ((bytes[offset + 2] & 0xFF) << 8)
                | ((bytes[offset + 1] & 0xFF) << 16)
                | ((bytes[offset] & 0xFF) << 24);
    }

    @Override
    public int bytesToIntLE(byte[] bytes, int offset) {
        return (bytes[offset] & 0xFF)
                | ((bytes[offset + 1] & 0xFF) << 8)
                | ((bytes[offset + 2] & 0xFF) << 16)
                | ((bytes[offset + 3] & 0xFF) << 24);
    }

    @Override
    public void longToBytesBE(long l, byte[] target, int offset) {
        offset += 7;
        for (int i = 0; i <= 7; i++) {
            target[offset - i] = (byte)((l>>(8*i)) & 0xFF);
        }
    }

    @Override
    public void longToBytesLE(long l, byte[] target, int offset) {
        for (int i = 0; i < 8; i++) {
            target[offset + i] = (byte)((l>>(8*i)) & 0xFF);
        }
    }

    @Override
    public void intToBytesBE(int i, byte[] target, int offset) {
        offset += 3;
        for (int index = 0; index < 4; index++) {
            target[offset - index] = (byte)((i>>(8*index)) & 0xFF);
        }
    }

    @Override
    public void intToBytesLE(int i, byte[] target, int offset) {
        for (int index = 0; index < 4; index++) {
            target[offset + index] = (byte)((i>>(8*index)) & 0xFF);
        }
    }

    @Override
    public String toHexString(byte[] bytes) {
        return Hex.toHexString(bytes);
    }

    @Override
    public byte[] parseHexString(String string) {
        return Hex.decode(string);
    }

    @Override
    public byte[] calculateGenerationSignature(byte[] lastGenSig, long lastGenId) {
        if (nativeEnabled()) {
            return nativePlotCalculator.calculateGenerationSignature(lastGenSig, lastGenId);
        } else {
            return plotCalculator.calculateGenerationSignature(lastGenSig, lastGenId);
        }
    }

    @Override
    public int calculateScoop(byte[] genSig, long height) {
        if (nativeEnabled()) {
            return nativePlotCalculator.calculateScoop(genSig, height);
        } else {
            return plotCalculator.calculateScoop(genSig, height);
        }
    }

    @Override
    public BigInteger calculateHit(long accountId, long nonce, byte[] genSig, int scoop, int pocVersion) {
        if (nativeEnabled()) {
            return nativePlotCalculator.calculateHit(accountId, nonce, genSig, scoop, pocVersion);
        } else {
            return plotCalculator.calculateHit(accountId, nonce, genSig, scoop, pocVersion);
        }
    }

    @Override
    public BigInteger calculateHit(byte[] genSig, byte[] scoopData) {
        if (nativeEnabled()) {
            return nativePlotCalculator.calculateHit(genSig, scoopData);
        } else {
            return plotCalculator.calculateHit(genSig, scoopData);
        }
    }

    @Override
    public BigInteger calculateDeadline(long accountId, long nonce, byte[] genSig, int scoop, long baseTarget, int pocVersion) {
        if (nativeEnabled()) {
            return nativePlotCalculator.calculateDeadline(accountId, nonce, genSig, scoop, baseTarget, pocVersion);
        } else {
            return plotCalculator.calculateDeadline(accountId, nonce, genSig, scoop, baseTarget, pocVersion);
        }
    }

    private int pageSize(short nPages) {
        if (nPages <= 1) {
            return 1;
        } else {
            return nPages < 128 ? 2 : 4;
        }
    }

    private void putLength(int nPages, int length, ByteBuffer buffer) {
        if (nPages <= 1) {
            buffer.put((byte) length);
        } else if (nPages < 128) {
            buffer.putShort((short) length);
        } else {
            buffer.putInt(length);
        }
    }

    @Override
    public byte[] getATCreationBytes(short atVersion, byte[] code, byte[] data, short dPages, short csPages, short usPages, BurstValue minActivationAmount) {
        short cPages = (short) (code.length / 256 + ((code.length % 256) != 0 ? 1 : 0));

        if (dPages < 0 || csPages < 0 || usPages < 0) {
            throw new IllegalArgumentException();
        }

        long minActivationAmountPlanck = minActivationAmount.toPlanck().longValueExact();

        int creationLength = 4; // version + reserved
        creationLength += 8; // pages
        creationLength += 8; // minActivationAmount
        creationLength += pageSize(cPages); // code size
        creationLength += code.length;
        creationLength += pageSize(dPages); // data size
        creationLength += data.length;

        ByteBuffer creation = ByteBuffer.allocate(creationLength);
        creation.order(ByteOrder.LITTLE_ENDIAN);
        creation.putShort(atVersion);
        creation.putShort((short) 0);
        creation.putShort(cPages);
        creation.putShort(dPages);
        creation.putShort(csPages);
        creation.putShort(usPages);
        creation.putLong(minActivationAmountPlanck);
        putLength(cPages, code.length, creation);
        creation.put(code);
        putLength(dPages, data.length, creation);
        creation.put(data);
        return creation.array();
    }

    @Override
    public void plotNonce(long accountId, long nonce, byte pocVersion, byte[] buffer, int offset) {
        if (buffer.length - offset < MiningPlot.PLOT_SIZE) {
            throw new IllegalArgumentException("Buffer does not have enough space to store plot"); // TODO better message
        }
        if (nativeEnabled()) {
            LibShabal.create_plot(accountId, nonce, pocVersion, buffer, offset);
        } else {
            new MiningPlot(getShabal256(), accountId, nonce, pocVersion, buffer, offset);
        }
    }

    @Override
    public void plotNonces(long accountId, long startNonce, long nonceCount, byte pocVersion, byte[] buffer, int offset) {
        if (buffer.length - offset < nonceCount * MiningPlot.PLOT_SIZE) {
            throw new IllegalArgumentException("Buffer does not have enough space to store plots. " + (nonceCount * MiningPlot.PLOT_SIZE) + " bytes required, length of provided buffer from offset is " + (buffer.length - offset));
        }
        if (nativeEnabled()) {
            LibShabal.create_plots(accountId, startNonce, nonceCount, pocVersion, buffer, offset);
        } else {
            for (long i = 0; i < nonceCount; i++) {
                plotNonce(accountId, startNonce + i, pocVersion, buffer, offset + ((int) i) * MiningPlot.PLOT_SIZE);
            }
        }
    }

    @Override
    public boolean nativeEnabled() {
        return nativeEnabled.get() && LibShabal.LOAD_ERROR == null;
    }

    @Override
    public void setNativeEnabled(boolean enabled) {
        nativeEnabled.set(enabled);
    }
    
    private static final BigInteger COMPL_REF = BigInteger.ONE.shiftLeft(256);
    private static final BigInteger MAX_VALUE = BigInteger.ONE.shiftLeft(255);
    
    @Override
    public String toBase36String(byte[] bytes) {
        BigInteger big = new BigInteger(bytes);
        if (big.compareTo(BigInteger.ZERO) < 0)
           big = big.add(COMPL_REF);
        return big.toString(36).toUpperCase();
    }

    @Override
    public byte[] parseBase36String(String string) {
        BigInteger base = new BigInteger(string, 36);
        if(base.compareTo(MAX_VALUE) > 0)
            base = base.subtract(COMPL_REF);
        return base.toByteArray();
    }
}
