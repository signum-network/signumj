package signumj.response.appendix;

import signumj.entity.EncryptedMessage;
import signumj.entity.response.TransactionAppendix;

public abstract class EncryptedMessageAppendix extends TransactionAppendix {
    private final EncryptedMessage encryptedMessage;

    private EncryptedMessageAppendix(int version, EncryptedMessage encryptedMessage) {
        super(version);
        this.encryptedMessage = encryptedMessage;
    }

    public EncryptedMessage getEncryptedMessage() {
        return encryptedMessage;
    }

    public static class ToRecipient extends EncryptedMessageAppendix {
        public ToRecipient(int version, EncryptedMessage encryptedMessage) {
            super(version, encryptedMessage);
        }
    }

    public static class ToSelf extends EncryptedMessageAppendix {
        public ToSelf(int version, EncryptedMessage encryptedMessage) {
            super(version, encryptedMessage);
        }
    }
}
