package burst.kit.entity.response.http;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public final class TransactionBytesResponse extends BRSResponse {
    private final HexStringByteArray unsignedTransactionBytes;
    private final int confirmations;
    private final HexStringByteArray transactionBytes;

    public TransactionBytesResponse(HexStringByteArray unsignedTransactionBytes, int confirmations, HexStringByteArray transactionBytes) {
        this.unsignedTransactionBytes = unsignedTransactionBytes;
        this.confirmations = confirmations;
        this.transactionBytes = transactionBytes;
    }

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
