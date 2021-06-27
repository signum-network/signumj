package signumj.entity.response.http.attachment;

import com.google.gson.annotations.SerializedName;

import signumj.entity.response.TransactionAttachment;
import signumj.response.attachment.CommitmentRemoveAttachment;

public class CommitmentRemoveAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.CommitmentRemove")
    private final int version;
    private final String amountNQT;

    public CommitmentRemoveAttachmentResponse(int version, String amountNQT) {
        this.version = version;
        this.amountNQT = amountNQT;
    }

    public int getVersion() {
        return version;
    }

    public String getAmountNQT() {
        return amountNQT;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new CommitmentRemoveAttachment(version, amountNQT);
    }
}
