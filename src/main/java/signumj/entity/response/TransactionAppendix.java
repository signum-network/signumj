package signumj.entity.response;

public abstract class TransactionAppendix { // TODO add missing appendixes
    private final int version;

    protected TransactionAppendix(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
