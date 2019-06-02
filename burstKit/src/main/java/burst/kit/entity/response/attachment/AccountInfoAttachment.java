package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.service.impl.grpc.BrsApi;

public class AccountInfoAttachment extends TransactionAttachment {
    private final String name;
    private final String description;

    public AccountInfoAttachment(int version, String name, String description) {
        super(version);
        this.name = name;
        this.description = description;
    }

    public AccountInfoAttachment(BrsApi.AccountInfoAttachment accountInfoAttachment) {
        super(accountInfoAttachment.getVersion());
        this.name = accountInfoAttachment.getName();
        this.description = accountInfoAttachment.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
