package burst.kit.entity.response;

@SuppressWarnings("unused")
public final class BlocksResponse {
    private BlockResponse[] blocks;

    private BlocksResponse() {}

    public BlockResponse[] getBlocks() {
        return blocks;
    }
}
