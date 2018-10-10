package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountInfoAttachment extends TransactionAttachment {
    private String name;
    private String description;
    @SerializedName("version.AccountInfo")
    private int version;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public AttachmentType getType() {
        return AttachmentType.ACCOUNT_INFO;
    }
}
