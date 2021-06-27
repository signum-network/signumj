package signumj.entity.response.http;

public final class AccountTransactionIDsResponse extends BRSResponse {
    private final String[] transactionIds;

    public AccountTransactionIDsResponse(String[] transactionIds) {
        this.transactionIds = transactionIds;
    }

    public String[] getTransactionIds() {
        return transactionIds;
    }
}
