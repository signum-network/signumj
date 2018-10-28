package burst.kit.entity;

public class BurstEncryptedMessage {
    private final HexStringByteArray data;
    private final HexStringByteArray nonce;
    private final boolean isText;

    public BurstEncryptedMessage(HexStringByteArray data, HexStringByteArray nonce, boolean isText) {
        this.data = data;
        this.nonce = nonce;
        this.isText = isText;
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
