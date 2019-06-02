package burst.kit.entity.response;

import burst.kit.entity.response.appendix.EncryptedMessageAppendix;
import burst.kit.entity.response.appendix.PlaintextMessageAppendix;
import burst.kit.service.impl.grpc.BrsApi;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;

public abstract class TransactionAppendix { // TODO add missing appendixes
    private final int version;

    protected TransactionAppendix(int version) {
        this.version = version;
    }

    public static TransactionAppendix fromProtobuf(Any protobuf) {
        try {
            if (protobuf.is(BrsApi.EncryptedMessageAppendix.class)) {
                return EncryptedMessageAppendix.fromProtobuf(protobuf.unpack(BrsApi.EncryptedMessageAppendix.class));
            } else if (protobuf.is(BrsApi.MessageAppendix.class)) {
                return new PlaintextMessageAppendix(protobuf.unpack(BrsApi.MessageAppendix.class));
            } else {
                return null; // If we do not support that appendix
            }
        } catch (InvalidProtocolBufferException e) {
            return null;
        }
    }

    public int getVersion() {
        return version;
    }
}
