package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.TradeResponse;
import burst.kit.service.BurstApiException;

import java.util.Locale;

public class Trade {
    private final BurstTimestamp timestamp;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final BurstValue quantity;
    /**
     * Price per asset.
     */
    private final BurstValue price;
    private final BurstID assetId;
    private final BurstID askOrderId;
    private final BurstID bidOrderId;
    private final int askOrderHeight;
    private final BurstAddress sellerAddress;
    private final BurstAddress buyerAddress;
    private final BurstID blockId;
    private final int height;
    private final TradeType type;
    private final String assetName;
    private final int assetDecimals;

    public Trade(BurstTimestamp timestamp, BurstValue quantity, BurstValue price, BurstID assetId, BurstID askOrderId, BurstID bidOrderId, int askOrderHeight, BurstAddress sellerAddress, BurstAddress buyerAddress, BurstID blockId, int height, TradeType type, String assetName, int assetDecimals) {
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.price = price;
        this.assetId = assetId;
        this.askOrderId = askOrderId;
        this.bidOrderId = bidOrderId;
        this.askOrderHeight = askOrderHeight;
        this.sellerAddress = sellerAddress;
        this.buyerAddress = buyerAddress;
        this.blockId = blockId;
        this.height = height;
        this.type = type;
        this.assetName = assetName;
        this.assetDecimals = assetDecimals;
    }

    public Trade(TradeResponse tradeResponse) {
        timestamp = BurstTimestamp.fromBurstTimestamp(tradeResponse.getTimestamp());
        quantity = BurstValue.fromPlanck(tradeResponse.getQuantityQNT());
        price = BurstValue.fromPlanck(tradeResponse.getPriceNQT());
        assetId = BurstID.fromLong(tradeResponse.getAsset());
        askOrderId = BurstID.fromLong(tradeResponse.getAskOrder());
        bidOrderId = BurstID.fromLong(tradeResponse.getBidOrder());
        askOrderHeight = tradeResponse.getAskOrderHeight();
        sellerAddress = BurstAddress.fromId(tradeResponse.getSeller());
        buyerAddress = BurstAddress.fromId(tradeResponse.getBuyer());
        blockId = BurstID.fromLong(tradeResponse.getBlock());
        height = tradeResponse.getHeight();
        type = TradeType.parse(tradeResponse.getTradeType());
        assetName = tradeResponse.getName();
        assetDecimals = tradeResponse.getDecimals();
    }

    public BurstTimestamp getTimestamp() {
        return timestamp;
    }

    public BurstValue getQuantity() {
        return quantity;
    }

    public BurstValue getPrice() {
        return price;
    }

    public BurstID getAssetId() {
        return assetId;
    }

    public BurstID getAskOrderId() {
        return askOrderId;
    }

    public BurstID getBidOrderId() {
        return bidOrderId;
    }

    public int getAskOrderHeight() {
        return askOrderHeight;
    }

    public BurstAddress getSellerAddress() {
        return sellerAddress;
    }

    public BurstAddress getBuyerAddress() {
        return buyerAddress;
    }

    public BurstID getBlockId() {
        return blockId;
    }

    public int getHeight() {
        return height;
    }

    public TradeType getType() {
        return type;
    }

    public String getAssetName() {
        return assetName;
    }

    public int getAssetDecimals() {
        return assetDecimals;
    }

    public enum TradeType {
        BUY,
        SELL,
        ;
        public static TradeType parse(String tradeType) {
            switch (tradeType.toLowerCase(Locale.ENGLISH).trim()) {
                case "buy": return BUY;
                case "sell": return SELL;
                default: throw new BurstApiException("Could not parse Trade Type " + tradeType);
            }
        }
    }
}
