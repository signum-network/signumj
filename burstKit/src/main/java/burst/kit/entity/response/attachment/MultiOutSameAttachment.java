package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.response.TransactionAttachment;
import burst.kit.service.impl.grpc.BrsApi;

public class MultiOutSameAttachment extends TransactionAttachment {
    private BurstAddress[] recipients;

    public MultiOutSameAttachment(int version, BurstAddress[] recipients) {
        super(version);
        this.recipients = recipients;
    }

    public MultiOutSameAttachment(BrsApi.MultiOutSameAttachment multiOutSameAttachment) {
        super(multiOutSameAttachment.getVersion());
        this.recipients = multiOutSameAttachment.getRecipientsList()
                .stream()
                .map(BurstAddress::fromId)
                .toArray(BurstAddress[]::new);
    }

    public BurstAddress[] getRecipients() {
        return recipients;
    }
}
