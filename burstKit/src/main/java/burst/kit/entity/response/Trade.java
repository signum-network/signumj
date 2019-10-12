package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.TradeResponse;

public class Trade {

    private final BurstTimestamp timestamp;
    private final BurstValue quantity;
    private final BurstValue price;
    private final BurstID asset;
    private final BurstID askOrder;
    private final BurstID bidOrder;
    private final int askOrderHeight;
    private final BurstAddress seller;
    private final BurstAddress buyer;
    private final String block;
    private final int height;
    private final String tradeType;
    private final String name;
    private final int decimals;

    public Trade(TradeResponse tradeResponse) {
        timestamp = BurstTimestamp.fromBurstTimestamp(tradeResponse.getTimestamp());
        quantity = BurstValue.fromPlanck(tradeResponse.getQuantityQNT());
        price = BurstValue.fromPlanck(tradeResponse.getPriceNQT());
        asset = BurstID.fromLong(tradeResponse.getAsset());
        askOrder = BurstID.fromLong(tradeResponse.getAskOrder());
        bidOrder = BurstID.fromLong(tradeResponse.getBidOrder());
        askOrderHeight = tradeResponse.getAskOrderHeight();
        seller = BurstAddress.fromId(tradeResponse.getSeller());
        buyer = BurstAddress.fromId(tradeResponse.getBuyer());
        block = tradeResponse.getBlock();
        height = tradeResponse.getHeight();
        tradeType = tradeResponse.getTradeType();
        name = tradeResponse.getName();
        decimals = tradeResponse.getDecimals();
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

    public BurstID getAsset() {
        return asset;
    }

    public BurstID getAskOrder() {
        return askOrder;
    }

    public BurstID getBidOrder() {
        return bidOrder;
    }

    public int getAskOrderHeight() {
        return askOrderHeight;
    }

    public BurstAddress getSeller() {
        return seller;
    }

    public BurstAddress getBuyer() {
        return buyer;
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
