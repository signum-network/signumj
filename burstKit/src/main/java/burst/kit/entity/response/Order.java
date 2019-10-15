package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.OrderResponse;

public class Order {

    private final BurstID order;
    private final BurstID asset;
    private final BurstAddress account;
    private final BurstValue quantity;
    private final BurstValue price;
    private final int height;
    private final String type;

    public Order(OrderResponse orderResponse) {
        order = BurstID.fromLong(orderResponse.getOrder());
        asset = BurstID.fromLong(orderResponse.getAsset());
        account = BurstAddress.fromId(orderResponse.getAccount());
        quantity = BurstValue.fromPlanck(orderResponse.getQuantityQNT());
        price = BurstValue.fromBurst(orderResponse.getPriceNQT());
        height = orderResponse.getHeight();
        type = orderResponse.getType();
    }

    public BurstID getOrder() {
        return order;
    }

    public BurstID getAsset() {
        return asset;
    }

    public BurstAddress getAccount() {
        return account;
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

    public String getType() {
        return type;
    }
    
}
