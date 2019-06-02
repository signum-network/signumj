package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.TransactionAttachment;
import burst.kit.service.impl.grpc.BrsApi;

import java.util.Map;
import java.util.stream.Collectors;

public class MultiOutAttachment extends TransactionAttachment {
    private final Map<BurstAddress, BurstValue> outputs;

    public MultiOutAttachment(int version, Map<BurstAddress, BurstValue> outputs) {
        super(version);
        this.outputs = outputs;
    }

    public MultiOutAttachment(BrsApi.MultiOutAttachment multiOutAttachment) {
        super(multiOutAttachment.getVersion());
        this.outputs = multiOutAttachment.getRecipientsList()
                .stream()
                .collect(Collectors.toMap(recipient -> BurstAddress.fromId(recipient.getRecipient()), recipient -> BurstValue.fromPlanck(recipient.getAmount())));
    }

    public Map<BurstAddress, BurstValue> getOutputs() {
        return outputs;
    }
}
