package burst.kit.entity.response.http;

/**
 * This class does not extend BRSResponse because it is only ever used as a subtype of responses.
 */
@SuppressWarnings("unused")
public final class TransactionTypeResponse {
    private final String description;
    private final int value;
    private final TransactionSubtypeResponse[] subtypes;

    public TransactionTypeResponse(String description, int value, TransactionSubtypeResponse[] subtypes) {
        this.description = description;
        this.value = value;
        this.subtypes = subtypes;
    }

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
