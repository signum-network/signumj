package signumj.entity.response.http;

public class AssetResponse extends BRSResponse  {
    private String issuer;
    private String issuerRS;
    private String account;
    private String accountRS;
    private String name;
    private String description;
    private int decimals;
    private boolean mintable;
    private String quantityQNT;
    private String quantityBurntQNT;
    private String asset;
    private String quantityCirculatingQNT;
	private int numberOfTrades;
    private int numberOfTransfers;
    private int numberOfAccounts;
    private String volumeQNT;
    private String priceHigh;
    private String priceLow;
    private String priceOpen;
    private String priceClose;

    public String getVolumeQNT() {
		return volumeQNT;
	}

	public String getPriceHigh() {
		return priceHigh;
	}

	public String getPriceLow() {
		return priceLow;
	}

	public String getPriceOpen() {
		return priceOpen;
	}

	public String getPriceClose() {
		return priceClose;
	}

	public String getIssuer() {
        return issuer;
    }

    public String getIssuerRS() {
        return issuerRS;
    }
    
    public String getAccount() {
        return account;
    }

    public String getAccountRS() {
        return accountRS;
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

    public String getQuantityQNT() {
        return quantityQNT;
    }

    public String getQuantityBurntQNT() {
        return quantityBurntQNT;
    }

    public String getAsset() {
        return asset;
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
    
    public String getQuantityCirculatingQNT() {
		return quantityCirculatingQNT;
	}
}
