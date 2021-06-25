package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class TransactionBytesResponse extends BRSResponse {
    private final String unsignedTransactionBytes;
    private final int confirmations;
    private final String transactionBytes;

    public TransactionBytesResponse(String unsignedTransactionBytes, int confirmations, String transactionBytes) {
        this.unsignedTransactionBytes = unsignedTransactionBytes;
        this.confirmations = confirmations;
        this.transactionBytes = transactionBytes;
    }

    public String getUnsignedTransactionBytes() {
        return unsignedTransactionBytes;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public String getTransactionBytes() {
        return transactionBytes;
    }
}
