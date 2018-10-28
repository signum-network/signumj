package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

public class MessageAttachment extends TransactionAttachment {
    private String message;
    private boolean messageIsText;
    @SerializedName("version.Message")
    private int version;

    private MessageAttachment() {}

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
