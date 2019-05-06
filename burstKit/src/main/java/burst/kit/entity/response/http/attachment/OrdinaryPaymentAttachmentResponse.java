package burst.kit.entity.response.http.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.entity.response.attachment.OrdinaryPaymentAttachment;

@SuppressWarnings("WeakerAccess")
public final class OrdinaryPaymentAttachmentResponse extends TransactionAttachmentResponse {
    OrdinaryPaymentAttachmentResponse() {}

    @Override
    public TransactionAttachment toAttachment() {
        return new OrdinaryPaymentAttachment(1);
    }
}
