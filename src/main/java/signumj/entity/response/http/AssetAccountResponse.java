package signumj.entity.response.http;

public final class AssetAccountResponse extends BRSResponse {
    private final String account;
    private final String accountRS;
    private final String asset;
    private final String quantityQNT;
    private final String unconfirmedQuantityQNT;

    public AssetAccountResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime,
    String account, String accountRS, String asset,
    String quantityQNT, String unconfirmedQuantityQNT) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.account = account;
        this.accountRS = accountRS;
        this.asset = asset;
        this.quantityQNT = quantityQNT;
        this.unconfirmedQuantityQNT = unconfirmedQuantityQNT;
    }

    public String getAccount() {
        return account;
    }

    public String getAccountRS() {
        return accountRS;
    }

    public String getAsset() {
        return asset;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }

    public String getUnconfirmedQuantityQNT() {
        return unconfirmedQuantityQNT;
    }
}
