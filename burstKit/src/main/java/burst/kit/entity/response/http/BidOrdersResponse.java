package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class BidOrdersResponse extends BRSResponse {
    private final OrderResponse[] bidOrders;

    public BidOrdersResponse(OrderResponse[] bidOrders) {
        this.bidOrders = bidOrders;
    }

    public OrderResponse[] getOrders() {
        return bidOrders;
    }
}
