package burst.kit.entity.response;

import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GenerateTransactionResponse extends BRSResponse {
    private final boolean broadcasted;
    private final HexStringByteArray signatureHash;
    private final HexStringByteArray fullHash;
    private final HexStringByteArray transactionBytes;
    private final HexStringByteArray unsignedTransactionBytes;
    @SerializedName("transactionJSON")
    private final TransactionResponse transactionResponse;
    @SerializedName("transaction")
    private final BurstID transactionID;

    public GenerateTransactionResponse(boolean broadcasted, HexStringByteArray signatureHash, HexStringByteArray fullHash, HexStringByteArray transactionBytes, HexStringByteArray unsignedTransactionBytes, TransactionResponse transactionResponse, BurstID transactionID) {
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

    public HexStringByteArray getSignatureHash() {
        return signatureHash;
    }

    public HexStringByteArray getFullHash() {
        return fullHash;
    }

    public HexStringByteArray getTransactionBytes() {
        return transactionBytes;
    }

    public HexStringByteArray getUnsignedTransactionBytes() {
        return unsignedTransactionBytes;
    }

    public TransactionResponse getTransactionResponse() {
        return transactionResponse;
    }

    public BurstID getTransactionID() {
        return transactionID;
    }
}
