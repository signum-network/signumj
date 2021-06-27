package signumj.entity.response.http;

public final class AccountsAssetResponse extends BRSResponse {
    private final AssetAccountResponse[] accountAssets;

    public AccountsAssetResponse(AssetAccountResponse[] accountAssets) {
        this.accountAssets = accountAssets;
    }

    public AssetAccountResponse[] getAccountsAsset() {
        return accountAssets;
    }
}
