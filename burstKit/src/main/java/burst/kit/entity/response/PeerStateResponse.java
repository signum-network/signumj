package burst.kit.entity.response;

/**
 * This class does not extend BRSResponse because it is only ever used as a subtype of responses.
 */
@SuppressWarnings("unused")
public final class PeerStateResponse {
    private String description;
    private int value;

    private PeerStateResponse() {}

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
