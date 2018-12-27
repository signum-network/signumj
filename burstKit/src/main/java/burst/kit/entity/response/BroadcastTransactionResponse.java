package burst.kit.entity.response;

import burst.kit.entity.BurstID;
import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class BroadcastTransactionResponse extends BRSResponse {
    private HexStringByteArray fullHash;
    private BurstID transaction;

    private BroadcastTransactionResponse() {}

    public HexStringByteArray getFullHash() {
        return fullHash;
    }

    public BurstID getTransactionID() {
        return transaction;
    }
}
