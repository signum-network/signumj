package signumj.entity.response.http.attachment;

import com.google.gson.annotations.SerializedName;

import signumj.entity.response.TransactionAttachment;
import signumj.response.attachment.CommitmentAddAttachment;

public class CommitmentAddAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.CommitmentAdd")
    private final int version;
    private final String amountNQT;

    public CommitmentAddAttachmentResponse(int version, String amountNQT) {
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
        return new CommitmentAddAttachment(version, amountNQT);
    }
}
