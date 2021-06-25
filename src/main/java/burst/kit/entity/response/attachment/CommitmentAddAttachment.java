package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;

public class CommitmentAddAttachment extends TransactionAttachment {
    private String amountNQT;

    public CommitmentAddAttachment(int version, String amountNQT) {
        super(version);
        this.amountNQT = amountNQT;
    }

    public String getAmountNQT() {
        return amountNQT;
    }
}
