package burst.kit.entity.response.appendix;

import burst.kit.entity.response.TransactionAppendix;
import burst.kit.service.impl.grpc.BrsApi;

import java.nio.charset.StandardCharsets;

public class PlaintextMessageAppendix extends TransactionAppendix {
    private final String message;
    private final boolean isText;

    public PlaintextMessageAppendix(int version, String message, boolean isText) {
        super(version);
        this.message = message;
        this.isText = isText;
    }

    public PlaintextMessageAppendix(BrsApi.MessageAppendix messageAppendix) {
        super(messageAppendix.getVersion());
        this.message = new String(messageAppendix.getMessage().toByteArray(), StandardCharsets.UTF_8);
        this.isText = messageAppendix.getIsText();
    }

    public String getMessage() {
        return message;
    }

    public boolean isText() {
        return isText;
    }
}
