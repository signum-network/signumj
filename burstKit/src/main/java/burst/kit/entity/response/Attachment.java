package burst.kit.entity.response;

import burst.kit.entity.response.http.attachment.TransactionAttachment;

public class Attachment extends Appendix {
    public static Attachment fromResponse(TransactionAttachment transactionAttachment) {
        return new Attachment(); // TODO
    }
}
