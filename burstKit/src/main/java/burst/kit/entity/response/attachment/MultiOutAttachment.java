package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.TransactionAttachment;

import java.util.Map;

public class MultiOutAttachment extends TransactionAttachment {
    private final Map<BurstAddress, BurstValue> outputs;

    public MultiOutAttachment(int version, Map<BurstAddress, BurstValue> outputs) {
        super(version);
        this.outputs = outputs;
    }

    public Map<BurstAddress, BurstValue> getOutputs() {
        return outputs;
    }
}
