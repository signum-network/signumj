package signumj.entity.response.http;

public final class TLDResponse extends BRSResponse {
    private String account;
    private String accountRS;
    private String aliasName;
    private String alias;
    private int timestamp;
    private String priceNQT;
    private String buyer;
    private int numberOfAliases;
	
    public String getAccount() {
		return account;
	}

	public String getAccountRS() {
		return accountRS;
	}

	public String getAliasName() {
		return aliasName;
	}

	public String getAlias() {
		return alias;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public String getPriceNQT() {
		return priceNQT;
	}

	public String getBuyer() {
		return buyer;
	}
	
	public int getNumberOfAliases() {
		return numberOfAliases;
	}
}
