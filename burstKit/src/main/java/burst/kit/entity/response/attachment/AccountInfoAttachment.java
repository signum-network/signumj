package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public final class AccountInfoAttachment extends TransactionAttachment {
    private final String name;
    private final String description;
    @SerializedName("version.AccountInfo")
    private final int version;

    public AccountInfoAttachment(String name, String description, int version) {
        this.name = name;
        this.description = description;
        this.version = version;
    }

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
