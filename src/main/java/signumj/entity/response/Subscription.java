package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.http.SubscriptionResponse;

public class Subscription {
    private final SignumID id;
    private final SignumAddress sender;
    private final SignumAddress recipient;
    private final SignumID alias;
    private final String aliasName;
    private final SignumID tld;
    private String tldName;
    private final SignumValue amount;
    private final int frequency;
    private final SignumTimestamp timeNext;
    
    public Subscription(SubscriptionResponse response) {
        this.id = SignumID.fromLong(response.getId());
        this.sender = SignumAddress.fromEither(response.getSender());
        this.recipient = SignumAddress.fromEither(response.getRecipient());
        this.alias = SignumID.fromLong(response.getAlias());
        this.aliasName = response.getAliasName();
        this.tld = SignumID.fromLong(response.getTLD());
        this.tldName = response.getTldName();
        this.timeNext = SignumTimestamp.fromBurstTimestamp(response.getTimeNext());
        this.amount = SignumValue.fromNQT(response.getAmountNQT());
        this.frequency = response.getFrequency();
    }

	public SignumAddress getSender() {
		return sender;
	}

	public SignumAddress getRecipient() {
		return recipient;
	}

	public SignumID getId() {
		return id;
	}

	public SignumTimestamp getTimeNext() {
		return timeNext;
	}

	public int getFrequency() {
		return frequency;
	}

	public SignumValue getAmount() {
		return amount;
	}

	public String getTldName() {
		return tldName;
	}

	public void setTldName(String tldName) {
		this.tldName = tldName;
	}

	public SignumID getAlias() {
		return alias;
	}

	public String getAliasName() {
		return aliasName;
	}

	public SignumID getTld() {
		return tld;
	}
}
