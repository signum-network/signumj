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
    private final SignumValue amount;
    private final int frequency;
    private final SignumTimestamp timeNext;
    
    public Subscription(SubscriptionResponse response) {
        this.id = SignumID.fromLong(response.getId());
        this.sender = SignumAddress.fromEither(response.getSender());
        this.recipient = SignumAddress.fromEither(response.getRecipient());
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
}
