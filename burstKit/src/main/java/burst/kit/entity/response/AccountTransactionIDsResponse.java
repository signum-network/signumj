package burst.kit.entity.response;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class AccountTransactionIDsResponse extends BRSResponse {
    private final BurstID[] transactionIds;

    public AccountTransactionIDsResponse(BurstID[] transactionIds) {
        this.transactionIds = transactionIds;
    }

    public BurstID[] getTransactionIds() {
        return transactionIds;
    }
}
