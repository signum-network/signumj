package signumj.entity.response.http;

public final class AccountUnconfirmedTransactionsResponse extends BRSResponse {
    private final TransactionResponse[] unconfirmedTransactions;

    public AccountUnconfirmedTransactionsResponse(TransactionResponse[] unconfirmedTransactions) {
        this.unconfirmedTransactions = unconfirmedTransactions;
    }

    public TransactionResponse[] getUnconfirmedTransactions() {
        return unconfirmedTransactions;
    }
}
