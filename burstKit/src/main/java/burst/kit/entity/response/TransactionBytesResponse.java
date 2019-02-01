package burst.kit.entity.response;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public final class TransactionBytesResponse extends BRSResponse {
    private final HexStringByteArray unsignedTransactionBytes;
    private final int confirmations;
    private final HexStringByteArray transactionBytes;

    public TransactionBytesResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, HexStringByteArray unsignedTransactionBytes, int confirmations, HexStringByteArray transactionBytes) {
        super(errorDescription, errorCode, requestProcessingTime);
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
