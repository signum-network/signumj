package burst.kit.entity.response;

/**
 * This class does not extend BRSResponse because it is only ever used as a subtype of responses.
 */
@SuppressWarnings("unused")
public final class TransactionTypeResponse {
    private String description;
    private int value;
    private TransactionSubtypeResponse[] subtypes;

    private TransactionTypeResponse() {}

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public TransactionSubtypeResponse[] getSubtypes() {
        return subtypes;
    }
}
