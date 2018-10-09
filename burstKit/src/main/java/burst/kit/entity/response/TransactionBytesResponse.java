package burst.kit.entity.response;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class TransactionBytesResponse extends BRSResponse {
    private HexStringByteArray unsignedTransactionBytes;
    private int confirmations;
    private HexStringByteArray transactionBytes;

    private TransactionBytesResponse() {}

    public HexStringByteArray getUnsignedTransactionBytes() {
        return unsignedTransactionBytes;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public HexStringByteArray getTransactionBytes() {
        return transactionBytes;
    }
}
