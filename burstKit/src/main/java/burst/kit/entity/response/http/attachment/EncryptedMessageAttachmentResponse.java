package burst.kit.entity.response.http.attachment;

import burst.kit.entity.BurstEncryptedMessage;
import com.google.gson.annotations.SerializedName;

public class EncryptedMessageAttachmentResponse extends TransactionAttachment {
    private final BurstEncryptedMessage encryptedMessage;
    @SerializedName("version.EncryptedMessage")
    private final int version;

    public EncryptedMessageAttachmentResponse(BurstEncryptedMessage encryptedMessage, int version) {
        this.encryptedMessage = encryptedMessage;
        this.version = version;
    }

    public BurstEncryptedMessage getEncryptedMessage() {
        return encryptedMessage;
    }

    public int getVersion() {
        return version;
    }
}
