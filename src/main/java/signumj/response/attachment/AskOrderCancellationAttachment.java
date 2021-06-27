package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class AskOrderCancellationAttachment extends TransactionAttachment {
    private String order;

    public AskOrderCancellationAttachment(int version, String order) {
        super(version);
        this.order = order;
    }
    //TODO constructor for BrsApi

    public String getOrder() {
        return order;
    }
}
