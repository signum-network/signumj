package burst.kit.entity.response.http;

import burst.kit.entity.BurstID;

@SuppressWarnings("unused")
public final class BlockIDResponse extends BRSResponse {
    private final BurstID block;

    public BlockIDResponse(BurstID block) {
        this.block = block;
    }

    public BurstID getBlockID() {
        return block;
    }
}
