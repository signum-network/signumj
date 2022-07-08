package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class ATCreationAttachment extends TransactionAttachment {
    private static final long serialVersionUID = -9070242267421533901L;
	private final String name;
    private final String description;
    private final byte[] creationBytes;

    public ATCreationAttachment(int version, String name, String description, byte[] creationBytes) {
        super(version);
        this.name = name;
        this.description = description;
        this.creationBytes = creationBytes;
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
