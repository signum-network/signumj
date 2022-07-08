package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class BidOrderCancellationAttachment extends TransactionAttachment {
    private static final long serialVersionUID = 2619746208813142236L;
	private String order;

    public BidOrderCancellationAttachment(int version, String order) {
        super(version);
        this.order = order;
    }
    //TODO constructor for BrsApi

    public String getOrder() {
        return order;
    }
}
