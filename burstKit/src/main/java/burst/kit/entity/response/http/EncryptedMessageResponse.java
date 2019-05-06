package burst.kit.entity.response.http;

import burst.kit.entity.BurstEncryptedMessage;
import org.bouncycastle.util.encoders.Hex;

public class EncryptedMessageResponse {
    private final String data;
    private final String nonce;
    private final boolean isText;

    public EncryptedMessageResponse(String data, String nonce, boolean isText) {
        this.data = data;
        this.nonce = nonce;
        this.isText = isText;
    }

    public BurstEncryptedMessage toEncryptedMessage() {
        return new BurstEncryptedMessage(Hex.decode(data), Hex.decode(nonce), isText);
    }

    public String getData() {
        return data;
    }

    public String getNonce() {
        return nonce;
    }

    public boolean isText() {
        return isText;
    }
}
