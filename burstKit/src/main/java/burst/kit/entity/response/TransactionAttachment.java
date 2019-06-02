package burst.kit.entity.response;

import burst.kit.entity.response.attachment.*;
import burst.kit.service.impl.grpc.BrsApi;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;

public abstract class TransactionAttachment extends TransactionAppendix { // TODO add missing transaction types
    protected TransactionAttachment(int version) {
        super(version);
    }

    public static TransactionAttachment fromProtobuf(Any protobuf, int transactionVersion) {
        try {
            if (protobuf.is(BrsApi.AccountInfoAttachment.class)) {
                return new AccountInfoAttachment(protobuf.unpack(BrsApi.AccountInfoAttachment.class));
            } else if (protobuf.is(BrsApi.ATCreationAttachment.class)) {
                return new ATCreationAttachment(protobuf.unpack(BrsApi.ATCreationAttachment.class));
            } else if (protobuf.is(BrsApi.MultiOutAttachment.class)) {
                return new MultiOutAttachment(protobuf.unpack(BrsApi.MultiOutAttachment.class));
            } else if (protobuf.is(BrsApi.MultiOutSameAttachment.class)) {
                return new MultiOutSameAttachment(protobuf.unpack(BrsApi.MultiOutSameAttachment.class));
            } else if (protobuf.is(BrsApi.RewardRecipientAssignmentAttachment.class)) {
                return new RewardRecipientAssignmentAttachment(protobuf.unpack(BrsApi.RewardRecipientAssignmentAttachment.class).getVersion());
            } else if (protobuf.is(BrsApi.OrdinaryPaymentAttachment.class)) {
                return new OrdinaryPaymentAttachment(transactionVersion);
            } else {
                return null; // If we do not support that attachment
            }
        } catch (InvalidProtocolBufferException e) {
            return null;
        }
    }
}
