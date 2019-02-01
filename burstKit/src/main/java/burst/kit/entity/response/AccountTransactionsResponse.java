package burst.kit.entity.response;

@SuppressWarnings("unused")
public final class AccountTransactionsResponse extends BRSResponse {
    private final TransactionResponse[] transactions;

    public AccountTransactionsResponse(TransactionResponse[] transactions) {
        this.transactions = transactions;
    }

    public TransactionResponse[] getTransactions() {
        return transactions;
    }
}
