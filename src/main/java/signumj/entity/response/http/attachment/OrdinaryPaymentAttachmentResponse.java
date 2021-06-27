package signumj.entity.response.http.attachment;

import signumj.entity.response.TransactionAttachment;
import signumj.response.attachment.OrdinaryPaymentAttachment;

public final class OrdinaryPaymentAttachmentResponse extends TransactionAttachmentResponse {
    OrdinaryPaymentAttachmentResponse() {}

    @Override
    public TransactionAttachment toAttachment() {
        return new OrdinaryPaymentAttachment(1);
    }
}
