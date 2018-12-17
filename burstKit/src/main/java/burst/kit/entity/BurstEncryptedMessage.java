package burst.kit.entity;

import burst.kit.burst.BurstCrypto;

public class BurstEncryptedMessage {
    /**
     Needs to be a HexStringByteArray for serialization.
      */
    private final HexStringByteArray data;
    /**
     Needs to be a HexStringByteArray for serialization.
     */
    private final HexStringByteArray nonce;

    private final boolean isText;

    public BurstEncryptedMessage(HexStringByteArray data, HexStringByteArray nonce, boolean isText) {
        this.data = data;
        this.nonce = nonce;
        this.isText = isText;
    }

    public BurstEncryptedMessage(byte[] data, byte[] nonce, boolean isText) {
        this.data = new HexStringByteArray(data);
        this.nonce = new HexStringByteArray(nonce);
        this.isText = isText;
    }

    public byte[] decrypt(String myPassphrase, byte[] theirPublicKey) {
        return BurstCrypto.getInstance().decryptMessage(this, myPassphrase, theirPublicKey);
    }

    public byte[] decrypt(byte[] myPrivateKey, byte[] theirPublicKey) {
        return BurstCrypto.getInstance().decryptMessage(this, myPrivateKey, theirPublicKey);
    }

    public HexStringByteArray getHexStringData() {
        return data;
    }

    public byte[] getData() {
        return data.getBytes();
    }

    public HexStringByteArray getHexStringNonce() {
        return nonce;
    }

    public byte[] getNonce() {
        return nonce.getBytes();
    }

    public boolean isText() {
        return isText;
    }
}
