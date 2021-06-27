package signumj.entity.response.http.attachment;

import signumj.entity.response.TransactionAttachment;

public abstract class TransactionAttachmentResponse {
    public abstract TransactionAttachment toAttachment();
}
