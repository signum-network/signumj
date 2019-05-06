package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public class BroadcastTransactionResponse extends BRSResponse {
    private final String fullHash;
    private final String transaction;
    private final Integer numberPeersSentTo;

    public BroadcastTransactionResponse(String fullHash, String transaction, Integer numberPeersSentTo) {
        this.fullHash = fullHash;
        this.transaction = transaction;
        this.numberPeersSentTo = numberPeersSentTo;
    }

    public String getFullHash() {
        return fullHash;
    }

    public String getTransactionID() {
        return transaction;
    }

    public Integer getNumberPeersSentTo() {
        return numberPeersSentTo;
    }
}
