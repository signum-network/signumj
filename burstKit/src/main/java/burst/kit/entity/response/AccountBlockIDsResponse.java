package burst.kit.entity.response;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class AccountBlockIDsResponse extends BRSResponse {
    private final BurstID[] blockIds;

    public AccountBlockIDsResponse(BurstID[] blockIds) {
        this.blockIds = blockIds;
    }

    public BurstID[] getBlockIds() {
        return blockIds;
    }
}
