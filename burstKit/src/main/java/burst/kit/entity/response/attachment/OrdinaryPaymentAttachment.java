package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.service.impl.grpc.BrsApi;

// TODO this is currently the default for unsupported types
public class OrdinaryPaymentAttachment extends TransactionAttachment {
    public OrdinaryPaymentAttachment(int version) {
        super(version);
    }
}
