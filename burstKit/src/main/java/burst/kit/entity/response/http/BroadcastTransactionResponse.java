package burst.kit.entity.response.http;

import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class BroadcastTransactionResponse extends BRSResponse {
    private final HexStringByteArray fullHash;
    private final BurstID transaction;
    private final Integer numberPeersSentTo;

    public BroadcastTransactionResponse(HexStringByteArray fullHash, BurstID transaction, Integer numberPeersSentTo) {
        this.fullHash = fullHash;
        this.transaction = transaction;
        this.numberPeersSentTo = numberPeersSentTo;
    }

    public HexStringByteArray getFullHash() {
        return fullHash;
    }

    public BurstID getTransactionID() {
        return transaction;
    }

    public Integer getNumberPeersSentTo() {
        return numberPeersSentTo;
    }
}
