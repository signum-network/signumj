package signumj.entity.response.http;

public final class BidOrdersResponse extends BRSResponse {
    private final OrderResponse[] bidOrders;

    public BidOrdersResponse(OrderResponse[] bidOrders) {
        this.bidOrders = bidOrders;
    }

    public OrderResponse[] getOrders() {
        return bidOrders;
    }
}
