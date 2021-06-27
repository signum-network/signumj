package signumj.entity.response.http;

public final class AccountBlockIDsResponse extends BRSResponse {
    private final String[] blockIds;

    public AccountBlockIDsResponse(String[] blockIds) {
        this.blockIds = blockIds;
    }

    public String[] getBlockIds() {
        return blockIds;
    }
}
