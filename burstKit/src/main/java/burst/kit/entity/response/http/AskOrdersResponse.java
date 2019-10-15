package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class AskOrdersResponse extends BRSResponse {
    private final OrderResponse[] askOrders;

    public AskOrdersResponse(OrderResponse[] askOrders) {
        this.askOrders = askOrders;
    }

    public OrderResponse[] getOrders() {
        return askOrders;
    }
}
