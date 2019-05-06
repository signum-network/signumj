package burst.kit.entity.response.http.attachment;

import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.response.TransactionAppendix;
import burst.kit.entity.response.appendix.EncryptedMessageAppendix;
import com.google.gson.annotations.SerializedName;

public class EncryptToSelfMessageAttachmentResponse extends TransactionAppendixResponse {
    private final BurstEncryptedMessage encryptToSelfMessage;
    @SerializedName("version.EncryptToSelfMessage")
    private final int version;

    public EncryptToSelfMessageAttachmentResponse(BurstEncryptedMessage encryptToSelfMessage, int version) {
        this.encryptToSelfMessage = encryptToSelfMessage;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public BurstEncryptedMessage getEncryptToSelfMessage() {
        return encryptToSelfMessage;
    }

    @Override
    public TransactionAppendix toAppendix() {
        return new EncryptedMessageAppendix.ToSelf(version, encryptToSelfMessage);
    }
}
