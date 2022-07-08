package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class CommitmentRemoveAttachment extends TransactionAttachment {
    private static final long serialVersionUID = 4400348880115950274L;
	private String amountNQT;

    public CommitmentRemoveAttachment(int version, String amountNQT) {
        super(version);
        this.amountNQT = amountNQT;
    }

    public String getAmountNQT() {
        return amountNQT;
    }
}
