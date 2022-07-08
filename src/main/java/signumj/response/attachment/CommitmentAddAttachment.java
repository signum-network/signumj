package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class CommitmentAddAttachment extends TransactionAttachment {
    private static final long serialVersionUID = 7272224736628004504L;
	private String amountNQT;

    public CommitmentAddAttachment(int version, String amountNQT) {
        super(version);
        this.amountNQT = amountNQT;
    }

    public String getAmountNQT() {
        return amountNQT;
    }
}
