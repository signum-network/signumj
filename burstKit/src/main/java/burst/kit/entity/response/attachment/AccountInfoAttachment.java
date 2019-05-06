package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;

public class AccountInfoAttachment extends TransactionAttachment {
    private final String name;
    private final String description;

    public AccountInfoAttachment(int version, String name, String description) {
        super(version);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
