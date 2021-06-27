package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.http.TradeResponse;
import signumj.service.ApiException;

import java.util.Locale;

public class AssetTrade {
    private final SignumTimestamp timestamp;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final SignumValue quantity; // TODO update this to be an AssetValue or similar class that takes into account the actual number of decimals of this asset
    /**
     * Price per asset.
     */
    private final SignumValue price;
    private final SignumID assetId;
    private final SignumID askOrderId;
    private final SignumID bidOrderId;
    private final int askOrderHeight;
    private final SignumAddress sellerAddress;
    private final SignumAddress buyerAddress;
    private final SignumID blockId;
    private final int height;
    private final TradeType type;
    private final String assetName;
    private final int assetDecimals;

    public AssetTrade(SignumTimestamp timestamp, SignumValue quantity, SignumValue price, SignumID assetId, SignumID askOrderId, SignumID bidOrderId, int askOrderHeight, SignumAddress sellerAddress, SignumAddress buyerAddress, SignumID blockId, int height, TradeType type, String assetName, int assetDecimals) {
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
        this.timestamp = SignumTimestamp.fromBurstTimestamp(tradeResponse.getTimestamp());
        this.quantity = SignumValue.fromPlanck(tradeResponse.getQuantityQNT());
        this.price = SignumValue.fromPlanck(tradeResponse.getPriceNQT());
        this.assetId = SignumID.fromLong(tradeResponse.getAsset());
        this.askOrderId = SignumID.fromLong(tradeResponse.getAskOrder());
        this.bidOrderId = SignumID.fromLong(tradeResponse.getBidOrder());
        this.askOrderHeight = tradeResponse.getAskOrderHeight();
        this.sellerAddress = SignumAddress.fromId(tradeResponse.getSeller());
        this.buyerAddress = SignumAddress.fromId(tradeResponse.getBuyer());
        this.blockId = SignumID.fromLong(tradeResponse.getBlock());
        this.height = tradeResponse.getHeight();
        this.type = TradeType.parse(tradeResponse.getTradeType());
        this.assetName = tradeResponse.getName();
        this.assetDecimals = tradeResponse.getDecimals();
    }
    
    public SignumTimestamp getTimestamp() {
        return timestamp;
    }

    public SignumValue getQuantity() {
        return quantity;
    }

    public SignumValue getPrice() {
        return price;
    }

    public SignumID getAssetId() {
        return assetId;
    }

    public SignumID getAskOrderId() {
        return askOrderId;
    }

    public SignumID getBidOrderId() {
        return bidOrderId;
    }

    public int getAskOrderHeight() {
        return askOrderHeight;
    }

    public SignumAddress getSellerAddress() {
        return sellerAddress;
    }

    public SignumAddress getBuyerAddress() {
        return buyerAddress;
    }

    public SignumID getBlockId() {
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
                default: throw new ApiException("Could not parse Trade Type " + tradeType);
            }
        }
    }
}
