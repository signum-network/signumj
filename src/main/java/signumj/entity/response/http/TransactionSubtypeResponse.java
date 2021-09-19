package signumj.entity.response.http;

/**
 * This class does not extend BRSResponse because it is only ever used as a subtype of responses.
 */
public final class TransactionSubtypeResponse {
    private final String description;
    private final int value;
    private final long minimumFeeConstantNQT;
    private final long minimumFeeAppendagesNQT;

    public TransactionSubtypeResponse(String description, int value, long minimumFeeConstantNQT, long minimumFeeAppendagesNQT) {
        this.description = description;
        this.value = value;
        this.minimumFeeConstantNQT = minimumFeeConstantNQT;
        this.minimumFeeAppendagesNQT = minimumFeeAppendagesNQT;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
    
    public long getMinimumFeeAppendagesNQT() {
    	return minimumFeeAppendagesNQT;
    }
    
    public long getMinimumFeeConstantNQT() {
    	return minimumFeeConstantNQT;
    }
}
