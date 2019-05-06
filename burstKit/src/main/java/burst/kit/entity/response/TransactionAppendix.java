package burst.kit.entity.response;

public abstract class TransactionAppendix {
    private final int version;

    protected TransactionAppendix(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
