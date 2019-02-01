package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

public class MessageAttachment extends TransactionAttachment {
    private final String message;
    private final boolean messageIsText;
    @SerializedName("version.Message")
    private final int version;

    public MessageAttachment(String message, boolean messageIsText, int version) {
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
}
