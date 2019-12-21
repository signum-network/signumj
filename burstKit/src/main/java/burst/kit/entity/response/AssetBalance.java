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
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final BurstValue quantity;
    /**
     * Unconfirmed quantity of the asset owned by the account. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of now many of the specific asset there are.
     */
    private final BurstValue unconfirmedQuantity;

    public AssetBalance(BurstAddress accountAddress, BurstID assetId, BurstValue quantity, BurstValue unconfirmedQuantity) {
        this.accountAddress = accountAddress;
        this.assetId = assetId;
        this.quantity = quantity;
        this.unconfirmedQuantity = unconfirmedQuantity;
    }

    public AssetBalance(AssetAccountResponse accountResponse) {
        this.accountAddress = BurstAddress.fromEither(accountResponse.getAccount());
        this.assetId = BurstID.fromLong(accountResponse.getAsset());
        this.quantity= BurstValue.fromPlanck(accountResponse.getQuantityQNT());
        this.unconfirmedQuantity = BurstValue.fromPlanck(accountResponse.getUnconfirmedQuantityQNT());
    }

    public BurstAddress getAccountAddress() {
        return accountAddress;
    }

    public BurstID getAssetId() {
        return assetId;
    }

    public BurstValue getQuantity() {
        return quantity;
    }

    public BurstValue getUnconfirmedQuantity() {
        return unconfirmedQuantity;
    }
}
