package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;

@SuppressWarnings("unused")
public final class AtIDsResponse extends BRSResponse {
    private final BurstAddress[] atIds;

    public AtIDsResponse(BurstAddress[] atIds) {
        this.atIds = atIds;
    }

    public BurstAddress[] getAtIds() {
        return atIds;
    }
}
