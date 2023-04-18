package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.http.TLDResponse;

public class TLD {
    private final SignumAddress account;
    private final String aliasName;
    private final SignumID alias;
    private final SignumTimestamp timestamp;
    private final SignumValue price;
    private final SignumAddress buyer;
    private final int numberOfAliases;
    
    public TLD(TLDResponse response) {
        this.account = SignumAddress.fromEither(response.getAccount());
        this.aliasName = response.getAliasName();
        this.alias = SignumID.fromLong(response.getAlias());
        this.timestamp = SignumTimestamp.fromBurstTimestamp(response.getTimestamp());
        this.price = SignumValue.fromNQT(response.getPriceNQT());
        this.buyer = SignumAddress.fromEither(response.getBuyer());
        this.numberOfAliases = response.getNumberOfAliases();
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

	public SignumTimestamp getTimestamp() {
		return timestamp;
	}

	public int getNumberOfAliases() {
		return numberOfAliases;
	}

	public SignumValue getPrice() {
		return price;
	}

	public SignumAddress getBuyer() {
		return buyer;
	}
}
