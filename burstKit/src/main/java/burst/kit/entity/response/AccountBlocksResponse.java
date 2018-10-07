package burst.kit.entity.response;

@SuppressWarnings("unused")
public class AccountBlocksResponse extends BRSResponse {
    private BlockResponse[] blocks;

    private AccountBlocksResponse() {}

    public BlockResponse[] getBlocks() {
        return blocks;
    }
}
