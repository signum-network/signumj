package signumj.response.attachment;

import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.entity.response.TransactionAttachment;

import java.util.Map;

public class MultiOutAttachment extends TransactionAttachment {
    private final Map<SignumAddress, SignumValue> outputs;

    public MultiOutAttachment(int version, Map<SignumAddress, SignumValue> outputs) {
        super(version);
        this.outputs = outputs;
    }

    public Map<SignumAddress, SignumValue> getOutputs() {
        return outputs;
    }
}
