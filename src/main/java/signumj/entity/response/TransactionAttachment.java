package signumj.entity.response;

public abstract class TransactionAttachment extends TransactionAppendix { // TODO add missing transaction types
    private static final long serialVersionUID = 5637526951658415524L;

	protected TransactionAttachment(int version) {
        super(version);
    }
}
