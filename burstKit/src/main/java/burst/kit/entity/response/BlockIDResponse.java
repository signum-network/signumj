package burst.kit.entity.response;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class BlockIDResponse extends BRSResponse {
    private BurstID block;

    private BlockIDResponse() {}

    public BurstID getBlockID() {
        return block;
    }
}
