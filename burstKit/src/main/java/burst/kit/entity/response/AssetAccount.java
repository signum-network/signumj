package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AssetAccountResponse;

public class AssetAccount {
    private final BurstAddress account;
    private final BurstID asset;
    private final BurstValue quantityQNT;
    private final BurstValue unconfirmedQuantityQNT;

    public AssetAccount(BurstAddress account, BurstID asset, BurstValue quantityQNT,
            BurstValue unconfirmedQuantityQNT) {
        this.account = account;
        this.asset = asset;
        this.quantityQNT = quantityQNT;
        this.unconfirmedQuantityQNT = unconfirmedQuantityQNT;
    }

    public AssetAccount(AssetAccountResponse accountResponse) {
        this.account = BurstAddress.fromEither(accountResponse.getAccount());
        this.asset = BurstID.fromLong(accountResponse.getAsset());
        this.quantityQNT= BurstValue.fromPlanck(accountResponse.getQuantityQNT());
        this.unconfirmedQuantityQNT = BurstValue.fromPlanck(accountResponse.getUnconfirmedQuantityQNT());
    }

    public BurstAddress getAccount() {
        return account;
    }

    public BurstID getAsset() {
        return asset;
    }

    public BurstValue getQuantityQNT() {
        return quantityQNT;
    }

    public BurstValue getUnconfirmedQuantityQNT() {
        return unconfirmedQuantityQNT;
    }

}
