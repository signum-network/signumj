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
    private final SignumValue quantity;
    private final SignumValue quantityBurnt;
    private final SignumID assetId;
    private final SignumValue quantityCirculating;
    private final int numberOfTrades;
    private final int numberOfTransfers;
    private final int numberOfAccounts;
    private final SignumValue volume;
    private final SignumValue priceHigh;
    private final SignumValue priceLow;
    private final SignumValue priceOpen;
    private final SignumValue priceClose;

    public Asset(AssetResponse assetResponse) {
        this.account = SignumAddress.fromId(assetResponse.getAccount());
        this.issuer = assetResponse.getIssuer() == null ? this.account : SignumAddress.fromId(assetResponse.getIssuer());
        this.name = assetResponse.getName();
        this.description = assetResponse.getDescription();
        this.decimals = assetResponse.getDecimals();
        this.quantity = SignumValue.fromNQT(assetResponse.getQuantityQNT());
        this.quantityBurnt = SignumValue.fromNQT(assetResponse.getQuantityBurntQNT());
        this.mintable = assetResponse.getMintable();
        this.assetId = SignumID.fromLong(assetResponse.getAsset());
        this.quantityCirculating = SignumValue.fromNQT(assetResponse.getQuantityCirculatingQNT());
        this.numberOfTrades = assetResponse.getNumberOfTrades();
        this.numberOfTransfers = assetResponse.getNumberOfTrades();
        this.numberOfAccounts = assetResponse.getNumberOfAccounts();
        this.volume = SignumValue.fromNQT(assetResponse.getVolumeQNT());
        this.priceHigh = SignumValue.fromNQT(assetResponse.getPriceHigh());
        this.priceLow = SignumValue.fromNQT(assetResponse.getPriceLow());
        this.priceOpen = SignumValue.fromNQT(assetResponse.getPriceOpen());
        this.priceClose = SignumValue.fromNQT(assetResponse.getPriceClose());
    }

    public SignumValue getQuantityCirculating() {
		return quantityCirculating;
	}

	public SignumValue getVolume() {
		return volume;
	}

	public SignumValue getPriceHigh() {
		return priceHigh;
	}

	public SignumValue getPriceLow() {
		return priceLow;
	}

	public SignumValue getPriceOpen() {
		return priceOpen;
	}

	public SignumValue getPriceClose() {
		return priceClose;
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
