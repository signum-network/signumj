package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.OrderResponse;
import burst.kit.service.BurstApiException;

import java.util.Locale;

public class AssetOrder {
    private final BurstID id;
    private final BurstID assetId;
    private final BurstAddress accountAddress;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final BurstValue quantity;
    /**
     * Price per asset.
     */
    private final BurstValue price;
    private final int height;
    private final OrderType type;

    public AssetOrder(BurstID id, BurstID assetId, BurstAddress accountAddress, BurstValue quantity, BurstValue price, int height, OrderType type) {
        this.id = id;
        this.assetId = assetId;
        this.accountAddress = accountAddress;
        this.quantity = quantity;
        this.price = price;
        this.height = height;
        this.type = type;
    }

    public AssetOrder(OrderResponse orderResponse) {
        this.id = BurstID.fromLong(orderResponse.getOrder());
        this.assetId = BurstID.fromLong(orderResponse.getAsset());
        this.accountAddress = BurstAddress.fromId(orderResponse.getAccount());
        this.quantity = BurstValue.fromPlanck(orderResponse.getQuantityQNT());
        this.price = BurstValue.fromPlanck(orderResponse.getPriceNQT());
        this.height = orderResponse.getHeight();
        this.type = OrderType.parse(orderResponse.getType());
    }

    public BurstID getId() {
        return id;
    }

    public BurstID getAssetId() {
        return assetId;
    }

    public BurstAddress getAccountAddress() {
        return accountAddress;
    }

    public BurstValue getQuantity() {
        return quantity;
    }

    public BurstValue getPrice() {
        return price;
    }

    public int getHeight() {
        return height;
    }

    public OrderType getType() {
        return type;
    }

    public enum OrderType {
        ASK,
        BID,
        ;

        public static OrderType parse(String orderType) {
            switch (orderType.toLowerCase(Locale.ENGLISH).trim()) {
                case "ask": return ASK;
                case "bid": return BID;
                default: throw new BurstApiException("Could not parse Order Type " + orderType);
            }
        }
    }
}
