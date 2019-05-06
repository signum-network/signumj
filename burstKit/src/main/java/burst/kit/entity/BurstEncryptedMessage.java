package burst.kit.entity;

import burst.kit.crypto.BurstCrypto;

public final class BurstEncryptedMessage {
    private final byte[] data;
    private final byte[] nonce;

    private final boolean isText;

    /**
     * Create from known encrypted data
     * @param data The data bytes
     * @param nonce The nonce bytes
     * @param isText Whether the data is text
     */
    public BurstEncryptedMessage(byte[] data, byte[] nonce, boolean isText) {
        this.data = data;
        this.nonce = nonce;
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
     * @return The data bytes
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @return The nonce bytes
     */
    public byte[] getNonce() {
        return nonce;
    }

    /**
     * @return Whether the plaintext data is text
     */
    public boolean isText() {
        return isText;
    }
}
