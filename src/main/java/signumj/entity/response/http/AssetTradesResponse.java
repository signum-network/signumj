package signumj.entity.response.http;

public final class AssetTradesResponse extends BRSResponse {
    private final TradeResponse[] trades;

    public AssetTradesResponse(TradeResponse[] assetTrades) {
        this.trades = assetTrades;
    }

    public TradeResponse[] getTrades() {
        return trades;
    }
}
