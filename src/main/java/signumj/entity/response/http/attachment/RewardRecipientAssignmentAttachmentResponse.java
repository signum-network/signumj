package signumj.entity.response.http.attachment;

import signumj.entity.response.TransactionAttachment;
import signumj.response.attachment.RewardRecipientAssignmentAttachment;

import com.google.gson.annotations.SerializedName;

public final class RewardRecipientAssignmentAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.RewardRecipientAssignment")
    private final int version;

    public RewardRecipientAssignmentAttachmentResponse(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new RewardRecipientAssignmentAttachment(version);
    }
}
