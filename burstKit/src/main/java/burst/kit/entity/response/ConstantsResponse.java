package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class ConstantsResponse extends BRSResponse {
    private final int maxBlockPayloadLength;
    private final BurstAddress genesisAccountId;
    private final BurstID genesisBlockId;
    private final TransactionTypeResponse[] transactionTypes;
    private final PeerStateResponse[] peerStates;
    private final int maxArbitraryMessageLength;
    // TODO requestTypes


    public ConstantsResponse(int maxBlockPayloadLength, BurstAddress genesisAccountId, BurstID genesisBlockId, TransactionTypeResponse[] transactionTypes, PeerStateResponse[] peerStates, int maxArbitraryMessageLength) {
        this.maxBlockPayloadLength = maxBlockPayloadLength;
        this.genesisAccountId = genesisAccountId;
        this.genesisBlockId = genesisBlockId;
        this.transactionTypes = transactionTypes;
        this.peerStates = peerStates;
        this.maxArbitraryMessageLength = maxArbitraryMessageLength;
    }

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
