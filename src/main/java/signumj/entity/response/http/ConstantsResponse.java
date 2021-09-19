package signumj.entity.response.http;

public final class ConstantsResponse extends BRSResponse {
    private final int maxBlockPayloadLength;
    private final String genesisAccountId;
    private final String genesisBlockId;
    private final String addressPrefix;
	private final String valueSuffix;
    private final TransactionTypeResponse[] transactionTypes;
    private final PeerStateResponse[] peerStates;
    private final int maxArbitraryMessageLength;
    // TODO requestTypes

    public ConstantsResponse(int maxBlockPayloadLength, String genesisAccountId, String genesisBlockId,
    		String addressPrefix, String valueSuffix, TransactionTypeResponse[] transactionTypes, PeerStateResponse[] peerStates, int maxArbitraryMessageLength) {
        this.maxBlockPayloadLength = maxBlockPayloadLength;
        this.genesisAccountId = genesisAccountId;
        this.genesisBlockId = genesisBlockId;
        this.addressPrefix = addressPrefix;
        this.valueSuffix = valueSuffix;
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
    
    public String getAddressPrefix() {
		return addressPrefix;
	}

	public String getValueSuffix() {
		return valueSuffix;
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
