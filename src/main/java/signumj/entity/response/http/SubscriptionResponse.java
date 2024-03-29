package signumj.entity.response.http;

public final class SubscriptionResponse extends BRSResponse {
    private String id;
    private String senderRS;
    private String sender;
    private String recipient;
    private String recipientRS;
    private String amountNQT;
    private String alias;
    private String aliasName;
    private String tld;
    private String tldName;
    private int freqency;
    private int timeNext;
	
    public String getId() {
		return id;
	}
    
    public String getSenderRS() {
		return senderRS;
	}

	public String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getRecipientRS() {
		return recipientRS;
	}

	public int getFrequency() {
		return freqency;
	}
	
	public int getTimeNext() {
		return timeNext;
	}

	public String getAmountNQT() {
		return amountNQT;
	}

	public String getAlias() {
		return alias;
	}

	public String getAliasName() {
		return aliasName;
	}

	public String getTLD() {
		return tld;
	}

	public String getTldName() {
		return tldName;
	}
}
