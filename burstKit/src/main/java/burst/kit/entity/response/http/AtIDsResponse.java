package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class AtIDsResponse extends BRSResponse {
    private final String[] atIds;

    public AtIDsResponse(String[] atIds) {
        this.atIds = atIds;
    }

    public String[] getAtIds() {
        return atIds;
    }
}
