package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.response.TransactionAttachment;

public class MultiOutSameAttachment extends TransactionAttachment {
    private BurstAddress[] recipients;

    public MultiOutSameAttachment(int version, BurstAddress[] recipients) {
        super(version);
        this.recipients = recipients;
    }

    public BurstAddress[] getRecipients() {
        return recipients;
    }
}
