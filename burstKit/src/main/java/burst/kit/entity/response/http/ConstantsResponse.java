package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class ConstantsResponse extends BRSResponse {
    private final int maxBlockPayloadLength;
    private final String genesisAccountId;
    private final String genesisBlockId;
    private final TransactionTypeResponse[] transactionTypes;
    private final PeerStateResponse[] peerStates;
    private final int maxArbitraryMessageLength;
    // TODO requestTypes

    public ConstantsResponse(int maxBlockPayloadLength, String genesisAccountId, String genesisBlockId, TransactionTypeResponse[] transactionTypes, PeerStateResponse[] peerStates, int maxArbitraryMessageLength) {
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

    public String getGenesisAccountId() {
        return genesisAccountId;
    }

    public String getGenesisBlockId() {
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
