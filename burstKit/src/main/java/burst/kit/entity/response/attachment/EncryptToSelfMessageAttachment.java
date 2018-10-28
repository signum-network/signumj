package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

public class EncryptToSelfMessageAttachment extends TransactionAttachment {
    @SerializedName("version.EncryptToSelfMessage")
    private int version;
    private EncryptedMessageData encryptToSelfMessage;

    private EncryptToSelfMessageAttachment() {}

    public int getVersion() {
        return version;
    }

    public EncryptedMessageData getEncryptToSelfMessage() {
        return encryptToSelfMessage;
    }
}
