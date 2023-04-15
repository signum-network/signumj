package signumj.entity.response.http;

public final class AliasResponse extends BRSResponse {
    private final String account;
    private final String accountRS;
    private final String aliasName;
    private final String alias;
    private final String aliasURI;
    private final String tld;
    private final String tldName;
    private final String priceNQT;
    private final String buyer;

	public AliasResponse(String account, String accountRS, String aliasName, String alias, String aliasURI, String tld, String tldName, String priceNQT, String buyer) {
        this.account = account;
        this.accountRS = accountRS;
        this.aliasName = aliasName;
        this.alias = alias;
        this.aliasURI = aliasURI;
        this.tld = tld;
        this.tldName = tldName;
        this.priceNQT = priceNQT;
        this.buyer = buyer;
    }
	
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

	public String getAliasURI() {
		return aliasURI;
	}

	public String getTld() {
		return tld;
	}

	public String getTldName() {
		return tldName;
	}

	public String getPriceNQT() {
		return priceNQT;
	}

	public String getBuyer() {
		return buyer;
	}
}
