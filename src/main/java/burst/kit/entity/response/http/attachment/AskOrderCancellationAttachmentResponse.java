package burst.kit.entity.response.http.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.entity.response.attachment.AskOrderCancellationAttachment;
import com.google.gson.annotations.SerializedName;

public class AskOrderCancellationAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.AskOrderCancellation")
    private final int version;
    private final String order;

    public AskOrderCancellationAttachmentResponse(int version, String order) {
        this.version = version;
        this.order = order;
    }

    public int getVersion() {
        return version;
    }

    public String getOrder() {
        return order;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new AskOrderCancellationAttachment(version, order);
    }
}
