package burst.kit.entity.response.http.attachment;

import burst.kit.entity.HexStringByteArray;
import com.google.gson.annotations.SerializedName;

public final class ATCreationAttachmentResponse extends TransactionAttachment {
    private final String name;
    private final String description;
    private final HexStringByteArray creationBytes;
    @SerializedName("version.AutomatedTransactionsCreation")
    private final int version;

    public ATCreationAttachmentResponse(String name, String description, HexStringByteArray creationBytes, int version) {
        this.name = name;
        this.description = description;
        this.creationBytes = creationBytes;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HexStringByteArray getCreationBytes() {
        return creationBytes;
    }

    public int getVersion() {
        return version;
    }
}
