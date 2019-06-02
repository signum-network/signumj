package burst.kit.entity.response.appendix;

import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.response.TransactionAppendix;
import burst.kit.service.impl.grpc.BrsApi;

public abstract class EncryptedMessageAppendix extends TransactionAppendix {
    private final BurstEncryptedMessage encryptedMessage;

    private EncryptedMessageAppendix(int version, BurstEncryptedMessage encryptedMessage) {
        super(version);
        this.encryptedMessage = encryptedMessage;
    }

    private static BurstEncryptedMessage encryptedMessageFromProtobuf(BrsApi.EncryptedData encryptedData, boolean isText) {
        return new BurstEncryptedMessage(encryptedData.getData().toByteArray(), encryptedData.getNonce().toByteArray(), isText);
    }

    public static EncryptedMessageAppendix fromProtobuf(BrsApi.EncryptedMessageAppendix encryptedMessageAppendix) {
        switch(encryptedMessageAppendix.getType()) {
            case TO_RECIPIENT:
                return new ToRecipient(encryptedMessageAppendix.getVersion(), encryptedMessageFromProtobuf(encryptedMessageAppendix.getEncryptedData(), encryptedMessageAppendix.getIsText()));
            case TO_SELF:
                return new ToRecipient(encryptedMessageAppendix.getVersion(), encryptedMessageFromProtobuf(encryptedMessageAppendix.getEncryptedData(), encryptedMessageAppendix.getIsText()));
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }

    public BurstEncryptedMessage getEncryptedMessage() {
        return encryptedMessage;
    }

    public static class ToRecipient extends EncryptedMessageAppendix {
        public ToRecipient(int version, BurstEncryptedMessage encryptedMessage) {
            super(version, encryptedMessage);
        }
    }

    public static class ToSelf extends EncryptedMessageAppendix {
        public ToSelf(int version, BurstEncryptedMessage encryptedMessage) {
            super(version, encryptedMessage);
        }
    }
}
