package burst.kit.entity.response.http.attachment;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public final class AccountInfoAttachmentResponse extends TransactionAttachment {
    private final String name;
    private final String description;
    @SerializedName("version.AccountInfo")
    private final int version;

    public AccountInfoAttachmentResponse(String name, String description, int version) {
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
