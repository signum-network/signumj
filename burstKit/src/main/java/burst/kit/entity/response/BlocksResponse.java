package burst.kit.entity.response;

@SuppressWarnings("unused")
public final class BlocksResponse extends BRSResponse {
    private BlockResponse[] blocks;

    private BlocksResponse() {}

    public BlockResponse[] getBlocks() {
        return blocks;
    }
}
