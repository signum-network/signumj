package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.http.AliasResponse;

public class Alias {
    private final SignumAddress account;
    private final String aliasName;
    private final SignumID alias;
    private final String aliasURI;
    private final SignumID tld;
    private final String tldName;
    private final SignumValue price;
    private final SignumAddress buyer;
    
    public Alias(SignumAddress account, String aliasName, SignumID alias, String aliasURI, SignumID tld, String tldName, SignumValue price, SignumAddress buyer) {
        this.account = account;
        this.aliasName = aliasName;
        this.alias = alias;
        this.aliasURI = aliasURI;
        this.tld = tld;
        this.tldName = tldName;
        this.price = price;
        this.buyer = buyer;
    }

    public Alias(AliasResponse aliasResponse) {
        this.account = SignumAddress.fromEither(aliasResponse.getAccount());
        this.aliasName = aliasResponse.getAliasName();
        this.alias = SignumID.fromLong(aliasResponse.getAlias());
        this.aliasURI = aliasResponse.getAliasURI();
        this.tld = SignumID.fromLong(aliasResponse.getTld());
        this.tldName = aliasResponse.getTldName();
        this.price = SignumValue.fromNQT(aliasResponse.getPriceNQT());
        this.buyer = SignumAddress.fromEither(aliasResponse.getBuyer());
    }

	public SignumAddress getAccount() {
		return account;
	}

	public String getAliasName() {
		return aliasName;
	}

	public SignumID getAlias() {
		return alias;
	}

	public String getAliasURI() {
		return aliasURI;
	}

	public SignumID getTld() {
		return tld;
	}

	public String getTldName() {
		return tldName;
	}

	public SignumValue getPrice() {
		return price;
	}

	public SignumAddress getBuyer() {
		return buyer;
	}
}
