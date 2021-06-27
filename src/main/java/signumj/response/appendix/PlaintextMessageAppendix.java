package signumj.response.appendix;

import signumj.entity.response.TransactionAppendix;

public class PlaintextMessageAppendix extends TransactionAppendix {
    private final String message;
    private final boolean isText;

    public PlaintextMessageAppendix(int version, String message, boolean isText) {
        super(version);
        this.message = message;
        this.isText = isText;
    }

    public String getMessage() {
        return message;
    }

    public boolean isText() {
        return isText;
    }
}
