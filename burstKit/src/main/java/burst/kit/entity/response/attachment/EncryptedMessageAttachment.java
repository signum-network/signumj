package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

public class EncryptedMessageAttachment extends TransactionAttachment {
    private EncryptedMessageData encryptedMessage;
    @SerializedName("version.EncryptedMessageData")
    private int version;

    private EncryptedMessageAttachment() {}

    public EncryptedMessageData getEncryptedMessage() {
        return encryptedMessage;
    }

    public int getVersion() {
        return version;
    }
}
