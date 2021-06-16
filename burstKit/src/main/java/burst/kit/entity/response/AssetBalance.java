package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AssetAccountResponse;

public class AssetBalance {
    private final BurstAddress accountAddress;
    private final BurstID assetId;
    /**
     * Quantity of the asset owned by the account. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of how many of the specific asset there are.
     */
    private final BurstValue balance;
    /**
     * Unconfirmed quantity of the asset owned by the account. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of how many of the specific asset there are.
     */
    private final BurstValue unconfirmedBalance;

    public AssetBalance(BurstAddress accountAddress, BurstID assetId, BurstValue balance, BurstValue unconfirmedBalance) {
        this.accountAddress = accountAddress;
        this.assetId = assetId;
        this.balance = balance;
        this.unconfirmedBalance = unconfirmedBalance;
    }

    public AssetBalance(AssetAccountResponse accountResponse) {
        this.accountAddress = BurstAddress.fromEither(accountResponse.getAccount());
        this.assetId = BurstID.fromLong(accountResponse.getAsset());
        this.balance = BurstValue.fromPlanck(accountResponse.getQuantityQNT());
        this.unconfirmedBalance = BurstValue.fromPlanck(accountResponse.getUnconfirmedQuantityQNT());
    }

    public BurstAddress getAccountAddress() {
        return accountAddress;
    }

    public BurstID getAssetId() {
        return assetId;
    }

    public BurstValue getBalance() {
        return balance;
    }

    public BurstValue getUnconfirmedBalance() {
        return unconfirmedBalance;
    }
}
