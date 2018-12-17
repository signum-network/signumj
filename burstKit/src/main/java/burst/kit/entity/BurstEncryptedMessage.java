package burst.kit.entity;

import brs.crypto.Crypto;
import burst.kit.burst.BurstCrypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BurstEncryptedMessage {

    private static final ThreadLocal<SecureRandom> secureRandom = ThreadLocal.withInitial(SecureRandom::new);

    /**
     Needs to be a HexStringByteArray for serialization.
      */
    private final HexStringByteArray data;
    /**
     Needs to be a HexStringByteArray for serialization.
     */
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

    public static BurstEncryptedMessage encrypt(String message, byte[] myPrivateKey, byte[] theirPublicKey) {
        return BurstCrypto.getInstance().encryptMessage(message, myPrivateKey, theirPublicKey);
    }

    public byte[] decrypt(byte[] myPrivateKey, byte[] theirPublicKey) {
        return BurstCrypto.getInstance().decryptMessage(this, myPrivateKey, theirPublicKey);
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
