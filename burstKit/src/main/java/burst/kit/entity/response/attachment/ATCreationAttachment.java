package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.service.impl.grpc.BrsApi;

public class ATCreationAttachment extends TransactionAttachment {
    private final String name;
    private final String description;
    private final byte[] creationBytes;

    public ATCreationAttachment(int version, String name, String description, byte[] creationBytes) {
        super(version);
        this.name = name;
        this.description = description;
        this.creationBytes = creationBytes;
    }

    public ATCreationAttachment(BrsApi.ATCreationAttachment atCreationAttachment) {
        super(atCreationAttachment.getVersion());
        this.name = atCreationAttachment.getName();
        this.description = atCreationAttachment.getDescription();
        this.creationBytes = atCreationAttachment.getCreationBytes().toByteArray();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getCreationBytes() {
        return creationBytes;
    }
}
