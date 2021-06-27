package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class CommitmentRemoveAttachment extends TransactionAttachment {
    private String amountNQT;

    public CommitmentRemoveAttachment(int version, String amountNQT) {
        super(version);
        this.amountNQT = amountNQT;
    }

    public String getAmountNQT() {
        return amountNQT;
    }
}
