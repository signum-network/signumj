package burst.kit.entity.response;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class AccountBlockIDsResponse extends BRSResponse {
    private BurstID[] blockIds;

    private AccountBlockIDsResponse() {}

    public BurstID[] getBlockIds() {
        return blockIds;
    }
}
