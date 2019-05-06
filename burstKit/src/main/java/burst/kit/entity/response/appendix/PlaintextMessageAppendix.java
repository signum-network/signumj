package burst.kit.entity.response.appendix;

import burst.kit.entity.response.TransactionAppendix;

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
