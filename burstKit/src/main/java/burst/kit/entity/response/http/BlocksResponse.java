package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class BlocksResponse extends BRSResponse {
    private final BlockResponse[] blocks;

    public BlocksResponse(BlockResponse[] blocks) {
        this.blocks = blocks;
    }

    public BlockResponse[] getBlocks() {
        return blocks;
    }
}
