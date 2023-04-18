package signumj.entity.response.http;

public final class SubscriptionsResponse extends BRSResponse {
    private SubscriptionResponse[] subscriptions;

    public SubscriptionResponse[] getSubscriptions() {
        return subscriptions;
    }
}
