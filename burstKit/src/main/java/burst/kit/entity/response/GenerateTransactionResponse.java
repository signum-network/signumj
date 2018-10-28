package burst.kit.entity.response;

import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GenerateTransactionResponse extends BRSResponse {
    private boolean broadcasted;
    private HexStringByteArray signatureHash;
    private HexStringByteArray fullHash;
    private HexStringByteArray transactionBytes;
    private HexStringByteArray unsignedTransactionBytes;
    @SerializedName("transactionJSON")
    private TransactionResponse transactionResponse;
    @SerializedName("transaction")
    private BurstID transactionID;

    private GenerateTransactionResponse() {}

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
