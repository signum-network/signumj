package signumj.entity.response;

import java.io.Serializable;

public abstract class TransactionAppendix implements Serializable{ // TODO add missing appendixes
    private final int version;

    protected TransactionAppendix(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
