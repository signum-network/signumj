package burst.kit.entity.response.http;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class AtIDsResponse extends BRSResponse {
    private final BurstID[] atIds;

    public AtIDsResponse(BurstID[] atIds) {
        this.atIds = atIds;
    }

    public BurstID[] getAtIds() {
        return atIds;
    }
}
