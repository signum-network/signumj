package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountInfoAttachment extends TransactionAttachment {
    private String name;
    private String description;
    @SerializedName("version.AccountInfo")
    private int version;

    private AccountInfoAttachment() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }
}
