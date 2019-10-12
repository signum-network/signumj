package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AssetAccountResponse;

public class AssetAccount {
    private final BurstAddress account;
    private final BurstID asset;
    private final BurstValue quantity;
    private final BurstValue unconfirmedQuantity;

    public AssetAccount(BurstAddress account, BurstID asset, BurstValue quantity,
            BurstValue unconfirmedQuantity) {
        this.account = account;
        this.asset = asset;
        this.quantity = quantity;
        this.unconfirmedQuantity = unconfirmedQuantity;
    }

    public AssetAccount(AssetAccountResponse accountResponse) {
        this.account = BurstAddress.fromEither(accountResponse.getAccount());
        this.asset = BurstID.fromLong(accountResponse.getAsset());
        this.quantity= BurstValue.fromPlanck(accountResponse.getQuantityQNT());
        this.unconfirmedQuantity = BurstValue.fromPlanck(accountResponse.getUnconfirmedQuantityQNT());
    }

    public BurstAddress getAccount() {
        return account;
    }

    public BurstID getAsset() {
        return asset;
    }

    public BurstValue getQuantity() {
        return quantity;
    }

    public BurstValue getUnconfirmedQuantity() {
        return unconfirmedQuantity;
    }

}
