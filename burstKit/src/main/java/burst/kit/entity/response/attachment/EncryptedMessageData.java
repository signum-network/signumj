package burst.kit.entity.response.attachment;

import burst.kit.entity.HexStringByteArray;

public class EncryptedMessageData {
    private HexStringByteArray data;
    private HexStringByteArray nonce;
    private boolean isText;

    private EncryptedMessageData() {}

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
