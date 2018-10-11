package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class ConstantsResponse extends BRSResponse {
    private int maxBlockPayloadLength;
    private BurstAddress genesisAccountId;
    private BurstID genesisBlockId;
    private TransactionTypeResponse[] transactionTypes;
    private PeerStateResponse[] peerStates;
    private int maxArbitraryMessageLength;
    // TODO requestTypes

    private ConstantsResponse() {}

    public int getMaxBlockPayloadLength() {
        return maxBlockPayloadLength;
    }

    public BurstAddress getGenesisAccountId() {
        return genesisAccountId;
    }

    public BurstID getGenesisBlockId() {
        return genesisBlockId;
    }

    public TransactionTypeResponse[] getTransactionTypes() {
        return transactionTypes;
    }

    public PeerStateResponse[] getPeerStates() {
        return peerStates;
    }

    public int getMaxArbitraryMessageLength() {
        return maxArbitraryMessageLength;
    }
}
