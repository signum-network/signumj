package burst.kit.entity.response.http.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.entity.response.attachment.ATCreationAttachment;
import com.google.gson.annotations.SerializedName;
import org.bouncycastle.util.encoders.Hex;

public final class ATCreationAttachmentResponse extends TransactionAttachmentResponse {
    private final String name;
    private final String description;
    private final String creationBytes;
    @SerializedName("version.AutomatedTransactionsCreation")
    private final int version;

    public ATCreationAttachmentResponse(String name, String description, String creationBytes, int version) {
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

    public String getCreationBytes() {
        return creationBytes;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new ATCreationAttachment(version, name, description, Hex.decode(creationBytes));
    }
}
