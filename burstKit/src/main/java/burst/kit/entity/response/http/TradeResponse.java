package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class TradeResponse extends BRSResponse {
    private final int timestamp;
    private final String quantityQNT;
    private final String priceNQT;
    private final String asset;
    private final String askOrder;
    private final String bidOrder;
    private final int askOrderHeight;
    private final String seller;
    private final String sellerRS;
    private final String buyer;
    private final String buyerRS;
    private final String block;
    private final int height;
    private final String tradeType;
    private final String name;
    private final int decimals;

    public TradeResponse(int timestamp, String quantityQNT, String priceNQT, String asset, String askOrder,
            String bidOrder, int askOrderHeight, String seller, String sellerRS, String buyer, String buyerRS,
            String block, int height, String tradeType, String name, int decimals) {
        this.timestamp = timestamp;
        this.quantityQNT = quantityQNT;
        this.priceNQT = priceNQT;
        this.asset = asset;
        this.askOrder = askOrder;
        this.bidOrder = bidOrder;
        this.askOrderHeight = askOrderHeight;
        this.seller = seller;
        this.sellerRS = sellerRS;
        this.buyer = buyer;
        this.buyerRS = buyerRS;
        this.block = block;
        this.height = height;
        this.tradeType = tradeType;
        this.name = name;
        this.decimals = decimals;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }

    public String getPriceNQT() {
        return priceNQT;
    }

    public String getAsset() {
        return asset;
    }

    public String getAskOrder() {
        return askOrder;
    }

    public String getBidOrder() {
        return bidOrder;
    }

    public int getAskOrderHeight() {
        return askOrderHeight;
    }

    public String getSeller() {
        return seller;
    }

    public String getSellerRS() {
        return sellerRS;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getBuyerRS() {
        return buyerRS;
    }

    public String getBlock() {
        return block;
    }

    public int getHeight() {
        return height;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getName() {
        return name;
    }

    public int getDecimals() {
        return decimals;
    }
}
