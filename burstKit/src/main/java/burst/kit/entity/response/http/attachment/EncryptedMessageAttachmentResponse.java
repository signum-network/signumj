package burst.kit.entity.response.http.attachment;

import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.response.TransactionAppendix;
import burst.kit.entity.response.appendix.EncryptedMessageAppendix;
import com.google.gson.annotations.SerializedName;

public class EncryptedMessageAttachmentResponse extends TransactionAppendixResponse {
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

    @Override
    public TransactionAppendix toAppendix() {
        return new EncryptedMessageAppendix.ToRecipient(version, encryptedMessage);
    }
}
