package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AssetResponse;

public class Asset {
    private final BurstID accountId;
    private final BurstAddress assetAddress;
    private final String name;
    private final int decimals;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     */
    private final BurstValue quantity;
    private final BurstID assetId;
    private int numberOfTrades;
    private int numberOfTransfers;
    private int numberOfAccounts;

    public Asset(BurstID accountId, BurstAddress assetAddress, String name, int decimals, BurstValue quantity, BurstID assetId, int numberOfTrades, int numberOfTransfers, int numberOfAccounts) {
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
        this.accountId = BurstID.fromLong(assetResponse.getAccount());
        this.assetAddress = BurstAddress.fromRs(assetResponse.getAccountRS());
        this.name = assetResponse.getName();
        this.decimals = assetResponse.getDecimals();
        this.quantity = BurstValue.fromPlanck(assetResponse.getQuantityQNT());
        this.assetId = BurstID.fromLong(assetResponse.getAsset());
        this.numberOfTrades = assetResponse.getNumberOfTrades();
        this.numberOfTransfers = assetResponse.getNumberOfTrades();
        this.numberOfAccounts = assetResponse.getNumberOfAccounts();
    }

    public BurstID getAccountId() {
        return accountId;
    }

    public BurstAddress getAssetAddress() {
        return assetAddress;
    }

    public String getName() {
        return name;
    }

    public int getDecimals() {
        return decimals;
    }

    public BurstValue getQuantity() {
        return quantity;
    }

    public BurstID getAssetId() {
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
