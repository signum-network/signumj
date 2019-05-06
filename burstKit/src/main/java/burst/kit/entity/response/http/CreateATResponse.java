package burst.kit.entity.response.http;

import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;

public class CreateATResponse extends GenerateTransactionResponse {
    private final String error;

    public CreateATResponse(boolean broadcasted, HexStringByteArray signatureHash, HexStringByteArray fullHash, HexStringByteArray transactionBytes, HexStringByteArray unsignedTransactionBytes, TransactionResponse transactionResponse, BurstID transactionID, String error) {
        super(broadcasted, signatureHash, fullHash, transactionBytes, unsignedTransactionBytes, transactionResponse, transactionID);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
