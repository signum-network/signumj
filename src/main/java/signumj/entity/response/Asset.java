package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.http.AssetResponse;

public class Asset {
    private final SignumID accountId;
    private final SignumAddress assetAddress;
    private final String name;
    private final int decimals;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     */
    private final SignumValue quantity;
    private final SignumID assetId;
    private int numberOfTrades;
    private int numberOfTransfers;
    private int numberOfAccounts;

    public Asset(SignumID accountId, SignumAddress assetAddress, String name, int decimals, SignumValue quantity, SignumID assetId, int numberOfTrades, int numberOfTransfers, int numberOfAccounts) {
        this.accountId = accountId;
        this.assetAddress = assetAddress;
        this.name = name;
        this.decimals = decimals;
        this.quantity = quantity;
        this.assetId = assetId;
        this.numberOfTrades = numberOfTrades;
        this.numberOfTransfers = numberOfTransfers;
        this.numberOfAccounts = numberOfAccounts;
    }

    public Asset(AssetResponse assetResponse) {
        this.accountId = SignumID.fromLong(assetResponse.getAccount());
        this.assetAddress = SignumAddress.fromRs(assetResponse.getAccountRS());
        this.name = assetResponse.getName();
        this.decimals = assetResponse.getDecimals();
        this.quantity = SignumValue.fromNQT(assetResponse.getQuantityQNT());
        this.assetId = SignumID.fromLong(assetResponse.getAsset());
        this.numberOfTrades = assetResponse.getNumberOfTrades();
        this.numberOfTransfers = assetResponse.getNumberOfTrades();
        this.numberOfAccounts = assetResponse.getNumberOfAccounts();
    }

    public SignumID getAccountId() {
        return accountId;
    }

    public SignumAddress getAssetAddress() {
        return assetAddress;
    }

    public String getName() {
        return name;
    }

    public int getDecimals() {
        return decimals;
    }

    public SignumValue getQuantity() {
        return quantity;
    }

    public SignumID getAssetId() {
        return assetId;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public int getNumberOfTransfers() {
        return numberOfTransfers;
    }

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }
}
