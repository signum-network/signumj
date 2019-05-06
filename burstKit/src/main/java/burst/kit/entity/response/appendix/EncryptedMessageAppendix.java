package burst.kit.entity.response.appendix;

import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.response.TransactionAppendix;

public abstract class EncryptedMessageAppendix extends TransactionAppendix {
    private final BurstEncryptedMessage encryptedMessage;

    private EncryptedMessageAppendix(int version, BurstEncryptedMessage encryptedMessage) {
        super(version);
        this.encryptedMessage = encryptedMessage;
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
