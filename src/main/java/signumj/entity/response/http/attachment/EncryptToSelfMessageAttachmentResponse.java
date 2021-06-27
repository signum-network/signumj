package signumj.entity.response.http.attachment;

import signumj.entity.response.TransactionAppendix;
import signumj.entity.response.http.EncryptedMessageResponse;
import signumj.response.appendix.EncryptedMessageAppendix;

import com.google.gson.annotations.SerializedName;

public class EncryptToSelfMessageAttachmentResponse extends TransactionAppendixResponse {
    private final EncryptedMessageResponse encryptToSelfMessage;
    @SerializedName("version.EncryptToSelfMessage")
    private final int version;

    public EncryptToSelfMessageAttachmentResponse(EncryptedMessageResponse encryptToSelfMessage, int version) {
        this.encryptToSelfMessage = encryptToSelfMessage;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public EncryptedMessageResponse getEncryptToSelfMessage() {
        return encryptToSelfMessage;
    }

    @Override
    public TransactionAppendix toAppendix() {
        return new EncryptedMessageAppendix.ToSelf(version, encryptToSelfMessage.toEncryptedMessage());
    }
}
