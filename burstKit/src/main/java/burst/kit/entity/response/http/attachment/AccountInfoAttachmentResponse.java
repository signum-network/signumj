package burst.kit.entity.response.http.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.entity.response.attachment.AccountInfoAttachment;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public final class AccountInfoAttachmentResponse extends TransactionAttachmentResponse {
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

    @Override
    public TransactionAttachment toAttachment() {
        return new AccountInfoAttachment(version, name, description);
    }
}
