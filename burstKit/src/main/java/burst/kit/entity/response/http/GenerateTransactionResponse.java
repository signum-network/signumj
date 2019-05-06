package burst.kit.entity.response.http;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GenerateTransactionResponse extends BRSResponse {
    private final boolean broadcasted;
    private final String signatureHash;
    private final String fullHash;
    private final String transactionBytes;
    private final String unsignedTransactionBytes;
    @SerializedName("transactionJSON")
    private final TransactionResponse transactionResponse;
    @SerializedName("transaction")
    private final String transactionID;

    public GenerateTransactionResponse(boolean broadcasted, String signatureHash, String fullHash, String transactionBytes, String unsignedTransactionBytes, TransactionResponse transactionResponse, String transactionID) {
        this.broadcasted = broadcasted;
        this.signatureHash = signatureHash;
        this.fullHash = fullHash;
        this.transactionBytes = transactionBytes;
        this.unsignedTransactionBytes = unsignedTransactionBytes;
        this.transactionResponse = transactionResponse;
        this.transactionID = transactionID;
    }

    public boolean isBroadcasted() {
        return broadcasted;
    }

    public String getSignatureHash() {
        return signatureHash;
    }

    public String getFullHash() {
        return fullHash;
    }

    public String getTransactionBytes() {
        return transactionBytes;
    }

    public String getUnsignedTransactionBytes() {
        return unsignedTransactionBytes;
    }

    public TransactionResponse getTransactionResponse() {
        return transactionResponse;
    }

    public String getTransactionID() {
        return transactionID;
    }
}
