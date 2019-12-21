package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class OrderResponse extends BRSResponse {
    private final String order;
    private final String asset;
    private final String account;
    private final String accountRS;
    private final String quantityQNT;
    private final String priceNQT;
    private final int height;
    private final String type;

    public OrderResponse(String order, String asset, String account, String accountRS, String quantityQNT, String priceNQT, int height, String type) {
        this.order = order;
        this.asset = asset;
        this.account = account;
        this.accountRS = accountRS;
        this.quantityQNT = quantityQNT;
        this.priceNQT = priceNQT;
        this.height = height;
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public String getAsset() {
        return asset;
    }

    public String getAccount() {
        return account;
    }

    public String getAccountRS() {
        return accountRS;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }

    public String getPriceNQT() {
        return priceNQT;
    }

    public int getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }
}
