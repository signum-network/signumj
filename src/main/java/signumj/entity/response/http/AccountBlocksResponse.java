package signumj.entity.response.http;

public final class AccountBlocksResponse extends BRSResponse {
    private final BlockResponse[] blocks;

    public AccountBlocksResponse(BlockResponse[] blocks) {
        this.blocks = blocks;
    }

    public BlockResponse[] getBlocks() {
        return blocks;
    }
}
