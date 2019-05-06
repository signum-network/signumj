package burst.kit.entity.response.http.attachment;

import burst.kit.entity.response.TransactionAppendix;
import burst.kit.entity.response.appendix.PlaintextMessageAppendix;
import com.google.gson.annotations.SerializedName;

public class MessageAttachmentResponse extends TransactionAppendixResponse {
    private final String message;
    private final boolean messageIsText;
    @SerializedName("version.Message")
    private final int version;

    public MessageAttachmentResponse(String message, boolean messageIsText, int version) {
        this.message = message;
        this.messageIsText = messageIsText;
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMessageIsText() {
        return messageIsText;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public TransactionAppendix toAppendix() {
        return new PlaintextMessageAppendix(version, message, messageIsText);
    }
}
