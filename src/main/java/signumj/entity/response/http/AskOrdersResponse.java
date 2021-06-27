package signumj.entity.response.http;

public final class AskOrdersResponse extends BRSResponse {
    private final OrderResponse[] askOrders;

    public AskOrdersResponse(OrderResponse[] askOrders) {
        this.askOrders = askOrders;
    }

    public OrderResponse[] getOrders() {
        return askOrders;
    }
}
