package burst.kit.entity.response;

@SuppressWarnings("unused")
public final class AccountTransactionsResponse extends BRSResponse {
    private TransactionResponse[] transactions;

    private AccountTransactionsResponse() {}

    public TransactionResponse[] getTransactions() {
        return transactions;
    }
}
