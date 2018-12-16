package burst.kit.entity;

import brs.BurstException;
import brs.crypto.Crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BurstEncryptedMessage {

    private static final ThreadLocal<SecureRandom> secureRandom = ThreadLocal.withInitial(SecureRandom::new);

    private final HexStringByteArray data;
    private final HexStringByteArray nonce;
    private final boolean isText = true; // TODO

    public BurstEncryptedMessage(HexStringByteArray data, HexStringByteArray nonce) {
        this.data = data;
        this.nonce = nonce;
    }

    public BurstEncryptedMessage(byte[] data, byte[] nonce) {
        this.data = new HexStringByteArray(data);
        this.nonce = new HexStringByteArray(nonce);
    }

    public static BurstEncryptedMessage encrypt(String plaintext, byte[] myPrivateKey, byte[] theirPublicKey) {
        if (plaintext.length() == 0) {
            return null; // TODO empty
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(plaintext.getBytes(StandardCharsets.UTF_8));
            gzip.flush();
            gzip.close();
            byte[] compressedPlaintext = bos.toByteArray();
            byte[] nonce = new byte[32];
            secureRandom.get().nextBytes(nonce);
            byte[] data = Crypto.aesEncrypt(compressedPlaintext, myPrivateKey, theirPublicKey, nonce);
            return new BurstEncryptedMessage(data, nonce);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] decrypt(byte[] myPrivateKey, byte[] theirPublicKey) {
        if (data.getBytes().length == 0) {
            return data.getBytes();
        }
        byte[] compressedPlaintext = Crypto.aesDecrypt(data.getBytes(), myPrivateKey, theirPublicKey, nonce.getBytes());
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

    public HexStringByteArray getData() {
        return data;
    }

    public HexStringByteArray getNonce() {
        return nonce;
    }

    public boolean isText() {
        return isText;
    }
}
