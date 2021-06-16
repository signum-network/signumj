package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.TradeResponse;
import burst.kit.service.BurstApiException;

import java.util.Locale;

public class AssetTrade {
    private final BurstTimestamp timestamp;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final BurstValue quantity; // TODO update this to be an AssetValue or similar class that takes into account the actual number of decimals of this asset
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

    public AssetTrade(BurstTimestamp timestamp, BurstValue quantity, BurstValue price, BurstID assetId, BurstID askOrderId, BurstID bidOrderId, int askOrderHeight, BurstAddress sellerAddress, BurstAddress buyerAddress, BurstID blockId, int height, TradeType type, String assetName, int assetDecimals) {
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

    public AssetTrade(TradeResponse tradeResponse) {
        this.timestamp = BurstTimestamp.fromBurstTimestamp(tradeResponse.getTimestamp());
        this.quantity = BurstValue.fromPlanck(tradeResponse.getQuantityQNT());
        this.price = BurstValue.fromPlanck(tradeResponse.getPriceNQT());
        this.assetId = BurstID.fromLong(tradeResponse.getAsset());
        this.askOrderId = BurstID.fromLong(tradeResponse.getAskOrder());
        this.bidOrderId = BurstID.fromLong(tradeResponse.getBidOrder());
        this.askOrderHeight = tradeResponse.getAskOrderHeight();
        this.sellerAddress = BurstAddress.fromId(tradeResponse.getSeller());
        this.buyerAddress = BurstAddress.fromId(tradeResponse.getBuyer());
        this.blockId = BurstID.fromLong(tradeResponse.getBlock());
        this.height = tradeResponse.getHeight();
        this.type = TradeType.parse(tradeResponse.getTradeType());
        this.assetName = tradeResponse.getName();
        this.assetDecimals = tradeResponse.getDecimals();
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
