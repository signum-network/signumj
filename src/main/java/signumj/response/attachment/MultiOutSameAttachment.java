package signumj.response.attachment;

import signumj.entity.SignumAddress;
import signumj.entity.response.TransactionAttachment;

public class MultiOutSameAttachment extends TransactionAttachment {
    private static final long serialVersionUID = 7898117921738749990L;
	private SignumAddress[] recipients;

    public MultiOutSameAttachment(int version, SignumAddress[] recipients) {
        super(version);
        this.recipients = recipients;
    }

    public SignumAddress[] getRecipients() {
        return recipients;
    }
}
