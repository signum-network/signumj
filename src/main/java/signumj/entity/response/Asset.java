package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.http.AssetResponse;

public class Asset {
    private final SignumAddress issuer;
    private final SignumAddress account;
    private final String name;
    private final String description;
    private final int decimals;
    private final boolean mintable;
    /**
     * Quantity of the asset. Not actually in Burst; The BurstValue class is used as a utility.
     */
    private final SignumValue quantity;
    private final SignumValue quantityBurnt;
    private final SignumID assetId;
    private int numberOfTrades;
    private int numberOfTransfers;
    private int numberOfAccounts;

    public Asset(SignumAddress issuer, SignumAddress account, String name, String description, int decimals, SignumValue quantity, SignumValue quantityBurnt, SignumID assetId, boolean mintable, int numberOfTrades, int numberOfTransfers, int numberOfAccounts) {
        this.issuer = issuer;
        this.account = account;
        this.name = name;
        this.description = description;
        this.decimals = decimals;
        this.quantity = quantity;
        this.quantityBurnt = quantityBurnt;
        this.assetId = assetId;
        this.mintable = mintable;
        this.numberOfTrades = numberOfTrades;
        this.numberOfTransfers = numberOfTransfers;
        this.numberOfAccounts = numberOfAccounts;
    }

    public Asset(AssetResponse assetResponse) {
        this.account = SignumAddress.fromId(assetResponse.getAccount());
        this.issuer = SignumAddress.fromId(assetResponse.getIssuer());
        this.name = assetResponse.getName();
        this.description = assetResponse.getDescription();
        this.decimals = assetResponse.getDecimals();
        this.quantity = SignumValue.fromNQT(assetResponse.getQuantityQNT());
        this.quantityBurnt = SignumValue.fromNQT(assetResponse.getQuantityBurntQNT());
        this.mintable = assetResponse.getMintable();
        this.assetId = SignumID.fromLong(assetResponse.getAsset());
        this.numberOfTrades = assetResponse.getNumberOfTrades();
        this.numberOfTransfers = assetResponse.getNumberOfTrades();
        this.numberOfAccounts = assetResponse.getNumberOfAccounts();
    }

    public SignumAddress getAccount() {
        return account;
    }

    public SignumAddress getIssuer() {
        return issuer;
    }
    
    public boolean getMintable() {
    	return mintable;
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public int getDecimals() {
        return decimals;
    }

    public SignumValue getQuantity() {
        return quantity;
    }

    public SignumValue getQuantityBurnt() {
        return quantityBurnt;
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
