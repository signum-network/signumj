package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstEncryptedMessage;
import com.google.gson.annotations.SerializedName;

public class EncryptedMessageAttachment extends TransactionAttachment {
    private BurstEncryptedMessage encryptedMessage;
    @SerializedName("version.EncryptedMessage")
    private int version;

    private EncryptedMessageAttachment() {}

    public BurstEncryptedMessage getEncryptedMessage() {
        return encryptedMessage;
    }

    public int getVersion() {
        return version;
    }
}
