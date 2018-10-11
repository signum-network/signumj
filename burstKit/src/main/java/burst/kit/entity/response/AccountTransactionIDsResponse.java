package burst.kit.entity.response;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class AccountTransactionIDsResponse extends BRSResponse {
    private BurstID[] transactionIds;

    private AccountTransactionIDsResponse() {}

    public BurstID[] getTransactionIds() {
        return transactionIds;
    }
}
