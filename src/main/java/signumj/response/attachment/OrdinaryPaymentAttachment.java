package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

// TODO this is currently the default for unsupported types
public class OrdinaryPaymentAttachment extends TransactionAttachment {
    private static final long serialVersionUID = -7379443372572665326L;

	public OrdinaryPaymentAttachment(int version) {
        super(version);
    }
}
