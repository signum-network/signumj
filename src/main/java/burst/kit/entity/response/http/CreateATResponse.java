package burst.kit.entity.response.http;

public class CreateATResponse extends GenerateTransactionResponse {
    private final String error;

    public CreateATResponse(boolean broadcasted, String signatureHash, String fullHash, String transactionBytes, String unsignedTransactionBytes, TransactionResponse transactionResponse, String transactionID, String error) {
        super(broadcasted, signatureHash, fullHash, transactionBytes, unsignedTransactionBytes, transactionResponse, transactionID);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
