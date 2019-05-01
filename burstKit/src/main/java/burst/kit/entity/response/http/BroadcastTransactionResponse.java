package burst.kit.entity.response.http;

import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class BroadcastTransactionResponse extends BRSResponse {
    private final HexStringByteArray fullHash;
    private final BurstID transaction;

    public BroadcastTransactionResponse(HexStringByteArray fullHash, BurstID transaction) {
        this.fullHash = fullHash;
        this.transaction = transaction;
    }

    public HexStringByteArray getFullHash() {
        return fullHash;
    }

    public BurstID getTransactionID() {
        return transaction;
    }
}
