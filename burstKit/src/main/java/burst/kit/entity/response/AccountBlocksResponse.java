package burst.kit.entity.response;

@SuppressWarnings("unused")
public final class AccountBlocksResponse extends BRSResponse {
    private BlockResponse[] blocks;

    private AccountBlocksResponse() {}

    public BlockResponse[] getBlocks() {
        return blocks;
    }
}
