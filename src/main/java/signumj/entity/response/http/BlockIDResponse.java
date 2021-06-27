package signumj.entity.response.http;

public final class BlockIDResponse extends BRSResponse {
    private final String block;

    public BlockIDResponse(String block) {
        this.block = block;
    }

    public String getBlockID() {
        return block;
    }
}
