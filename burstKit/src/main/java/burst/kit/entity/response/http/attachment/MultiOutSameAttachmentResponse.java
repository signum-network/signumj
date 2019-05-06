package burst.kit.entity.response.http.attachment;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.response.TransactionAttachment;
import burst.kit.entity.response.attachment.MultiOutSameAttachment;
import com.google.gson.annotations.SerializedName;

public final class MultiOutSameAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.MultiSameOutCreation")
    private final int version;
    private final BurstAddress[] recipients;

    public MultiOutSameAttachmentResponse(int version, BurstAddress[] recipients) {
        this.version = version;
        this.recipients = recipients;
    }

    public int getVersion() {
        return version;
    }

    public BurstAddress[] getRecipients() {
        return recipients;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new MultiOutSameAttachment(version, recipients);
    }
}
