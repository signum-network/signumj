package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.http.AssetAccountResponse;

public class AssetBalance {
    private final SignumAddress accountAddress;
    private final SignumID assetId;
    /**
     * Quantity of the asset owned by the account. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of how many of the specific asset there are.
     */
    private final SignumValue balance;
    /**
     * Unconfirmed quantity of the asset owned by the account. Not actually in Burst; The BurstValue class is used as a utility.
     * Actually measured in terms of how many of the specific asset there are.
     */
    private final SignumValue unconfirmedBalance;

    public AssetBalance(SignumAddress accountAddress, SignumID assetId, SignumValue balance, SignumValue unconfirmedBalance) {
        this.accountAddress = accountAddress;
        this.assetId = assetId;
        this.balance = balance;
        this.unconfirmedBalance = unconfirmedBalance;
    }

    public AssetBalance(AssetAccountResponse accountResponse) {
        this.accountAddress = SignumAddress.fromEither(accountResponse.getAccount());
        this.assetId = SignumID.fromLong(accountResponse.getAsset());
        this.balance = SignumValue.fromNQT(accountResponse.getQuantityQNT());
        this.unconfirmedBalance = SignumValue.fromNQT(accountResponse.getUnconfirmedQuantityQNT());
    }

    public SignumAddress getAccountAddress() {
        return accountAddress;
    }

    public SignumID getAssetId() {
        return assetId;
    }

    public SignumValue getBalance() {
        return balance;
    }

    public SignumValue getUnconfirmedBalance() {
        return unconfirmedBalance;
    }
}
