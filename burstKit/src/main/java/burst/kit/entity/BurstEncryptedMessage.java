package burst.kit.entity;

import burst.kit.crypto.BurstCrypto;

public final class BurstEncryptedMessage {
    /**
     Needs to be a HexStringByteArray for serialization.
      */
    private final HexStringByteArray data;

    /**
     Needs to be a HexStringByteArray for serialization.
     */
    private final HexStringByteArray nonce;

    private final boolean isText;

    /**
     * Create from known encrypted data
     * @param data The data bytes
     * @param nonce The nonce bytes
     * @param isText Whether the data is text
     */
    public BurstEncryptedMessage(HexStringByteArray data, HexStringByteArray nonce, boolean isText) {
        this.data = data;
        this.nonce = nonce;
        this.isText = isText;
    }

    /**
     * Create from known encrypted data
     * @param data The data bytes
     * @param nonce The nonce bytes
     * @param isText Whether the data is text
     */
    public BurstEncryptedMessage(byte[] data, byte[] nonce, boolean isText) {
        this.data = new HexStringByteArray(data);
        this.nonce = new HexStringByteArray(nonce);
        this.isText = isText;
    }

    /**
     * Decrypt the encrypted message
     * @param myPassphrase Your passphrase (Assuming you sent / received the encrypted message)
     * @param theirPublicKey Their public key (Assuming they sent / were sent the encrypted message)
     * @return The decrypted message
     */
    public byte[] decrypt(String myPassphrase, byte[] theirPublicKey) {
        return BurstCrypto.getInstance().decryptMessage(this, myPassphrase, theirPublicKey);
    }

    /**
     * Decrypt the encrypted message
     * @param myPrivateKey Your private key (Assuming you sent / received the encrypted message)
     * @param theirPublicKey Their public key (Assuming they sent / were sent the encrypted message)
     * @return The decrypted message
     */
    public byte[] decrypt(byte[] myPrivateKey, byte[] theirPublicKey) {
        return BurstCrypto.getInstance().decryptMessage(this, myPrivateKey, theirPublicKey);
    }

    /**
     * @return The data bytes wrapped in a HexStringByteArray
     */
    public HexStringByteArray getHexStringData() {
        return data;
    }
    /**
     * @return The data bytes
     */
    public byte[] getData() {
        return data.getBytes();
    }

    /**
     * @return The nonce bytes wrapped in a HexStringByteArray
     */
    public HexStringByteArray getHexStringNonce() {
        return nonce;
    }

    /**
     * @return The nonce bytes
     */
    public byte[] getNonce() {
        return nonce.getBytes();
    }

    /**
     * @return Whether the plaintext data is text
     */
    public boolean isText() {
        return isText;
    }
}
