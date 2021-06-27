package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.http.OrderResponse;
import signumj.service.ApiException;

import java.util.Locale;

public class AssetOrder {
    private final SignumID id;
    private final SignumID assetId;
    private final SignumAddress accountAddress;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final SignumValue quantity;
    /**
     * Price per asset.
     */
    private final SignumValue price;
    private final int height;
    private final OrderType type;

    public AssetOrder(SignumID id, SignumID assetId, SignumAddress accountAddress, SignumValue quantity, SignumValue price, int height, OrderType type) {
        this.id = id;
        this.assetId = assetId;
        this.accountAddress = accountAddress;
        this.quantity = quantity;
        this.price = price;
        this.height = height;
        this.type = type;
    }

    public AssetOrder(OrderResponse orderResponse) {
        this.id = SignumID.fromLong(orderResponse.getOrder());
        this.assetId = SignumID.fromLong(orderResponse.getAsset());
        this.accountAddress = SignumAddress.fromId(orderResponse.getAccount());
        this.quantity = SignumValue.fromNQT(orderResponse.getQuantityQNT());
        this.price = SignumValue.fromNQT(orderResponse.getPriceNQT());
        this.height = orderResponse.getHeight();
        this.type = OrderType.parse(orderResponse.getType());
    }

    public SignumID getId() {
        return id;
    }

    public SignumID getAssetId() {
        return assetId;
    }

    public SignumAddress getAccountAddress() {
        return accountAddress;
    }

    public SignumValue getQuantity() {
        return quantity;
    }

    public SignumValue getPrice() {
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
                default: throw new ApiException("Could not parse Order Type " + orderType);
            }
        }
    }
}
