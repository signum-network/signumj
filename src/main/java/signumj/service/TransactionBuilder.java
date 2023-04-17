package signumj.service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bouncycastle.util.encoders.Hex;

import signumj.crypto.SignumCrypto;
import signumj.entity.EncryptedMessage;
import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;

/**
 * A transaction builder object.
 * 
 * An instance of this object should be used with {@link NodeService#generateTransaction(TransactionBuilder)}
 * to get the unsigned transaction bytes.
 * 
 * The bytes returned by the node can be locally verified by {@link #verify(byte[])}. If the bytes
 * are verified the transaction can be signed by {@link SignumCrypto#signTransaction(byte[], byte[])}
 * and finally broadcast with {@link NodeService#broadcastTransaction(byte[])}.
 *
 */
public class TransactionBuilder {

	static class Type {
		String requestType;
		int type;
		int subType;

		public Type(String requestType, int type, int subType) {
			this.requestType = requestType;
			this.type = type;
			this.subType = subType;
		}

		public String getRequestType() {
			return requestType;
		}

		public int getType() {
			return type;
		}

		public int getSubType() {
			return subType;
		}
	}

	public static Type SEND_MONEY = new Type("sendMoney", 0, 0);
	public static Type SEND_MONEY_MULTI = new Type("sendMoneyMulti", 0, 1);
	public static Type SEND_MONEY_MULTI_SAME = new Type("sendMoneyMultiSame", 0, 2);

	public static Type SEND_MESSAGE = new Type("sendMessage", 1, 0);
	public static Type SET_ALIAS = new Type("setAlias", 1, 1);
	public static Type SET_ACCOUNT_INFO = new Type("setAccountInfo", 1, 5);
	public static Type SELL_ALIAS = new Type("sellAlias", 1, 6);
	public static Type BUY_ALIAS = new Type("buyAlias", 1, 7);
	public static Type SET_TLD = new Type("setTLD", 1, 8);

	public static Type ISSUE_ASSET = new Type("issueAsset", 2, 0);
	public static Type TRANSFER_ASSET = new Type("transferAsset", 2, 1);
	public static Type PLACE_ASK_ORDER = new Type("placeAskOrder", 2, 2);
	public static Type PLACE_BID_ORDER = new Type("placeBidOrder", 2, 3);
	public static Type CANCEL_ASK_ORDER = new Type("cancelAskOrder", 2, 4);
	public static Type CANCEL_BID_ORDER = new Type("cancelBidOrder", 2, 5);
	public static Type MINT_ASSET = new Type("mintAsset", 2, 6);
	public static Type ADD_ASSET_TREASURY = new Type("addAssetTreasuryAccount", 2, 7);
	public static Type DISTRIBUTE_TO_ASSET_HOLDERS = new Type("distributeToAssetHolders", 2, 8);
	public static Type TRANSFER_ASSET_MULTI = new Type("transferAssetMulti", 2, 9);
	public static Type TRANSFER_ASSET_OWNERSHIP = new Type("transferAssetOwnership", 2, 10);

	public static Type SET_REWARD_RECIPIENT = new Type("setRewardRecipient", 20, 0);
	public static Type ADD_COMMITMENT = new Type("addCommitment", 20, 1);
	public static Type REMOVE_COMMITMENT = new Type("removeCommitment", 20, 2);

	public static Type SUBSCRIPTION = new Type("sendMoneySubscription", 21, 3);
	public static Type SUBSCRIPTION_CANCEL = new Type("subscriptionCancel", 21, 4);

	public static Type CREATE_AT = new Type("createATProgram", 22, 0);

	Map<String, String> params = new HashMap<>();
	Type type;
	String body = "";
	int deadline;
	SignumValue fee;
	SignumAddress recipient;
	SignumValue amount;
	SignumValue quantity;
	String referencedTransactionFullHash;
	int frequency;
	boolean mintable = false;

	String message;
	boolean messageIsText = false;

	SignumID asset;
	SignumID order;
	String name, description;
	private Map<SignumAddress, SignumValue> recipients;
	private Set<SignumAddress> recipientsSame;
	private int decimals;
	private SignumValue price;
	private SignumValue quantityMinimum;
	private SignumID assetToDistribute;
	private Map<SignumID, SignumValue> assetIdAndQuantity;
	private SignumID subscription;
	private byte[] creationBytes;
	private String aliasName;
	private String aliasURI = "";
	private SignumID tldId;
	private SignumID alias;
	private String tld;
	private EncryptedMessage encryptedMessage;
	private EncryptedMessage encryptedMessageToSelf;
	private Map<SignumID, SignumValue> assetIdsAndQuantities;

	public TransactionBuilder(Type type, byte[] senderPublicKey, SignumValue fee, int deadline) {
		this.type = type;
		this.fee = fee;
		this.deadline = deadline;

		params.put("publicKey", Hex.toHexString(senderPublicKey));
		params.put("deadline", Integer.toString(deadline));
		params.put("feeNQT", fee.toNQT().toString());
	}

	public TransactionBuilder(String requestType, int type, int subType, byte[] senderPublicKey, SignumValue fee, int deadline) {
		this(new Type(requestType, type, subType), senderPublicKey, fee, deadline);
	}
	
	private void checkValid(String param, Type... validTypes) throws IllegalArgumentException {
		for(Type t : validTypes) {
			if(this.type == t) {
				// found a valid type
				return;
			}
		}
		throw new IllegalArgumentException(param + " is not a valid parameter for " + this.type.getRequestType());
	}

	public TransactionBuilder amount(SignumValue amount) {
		checkValid("amount", SEND_MONEY, SEND_MONEY_MULTI, SEND_MONEY_MULTI_SAME, BUY_ALIAS, SET_TLD,
				TRANSFER_ASSET, DISTRIBUTE_TO_ASSET_HOLDERS, TRANSFER_ASSET_MULTI, ADD_COMMITMENT, SUBSCRIPTION);
		
		this.amount = amount;
		params.put("amountNQT", amount.toNQT().toString());
		return this;
	}

	public TransactionBuilder recipient(SignumAddress recipient) {
		checkValid("recipient", SEND_MONEY, SEND_MESSAGE, SET_REWARD_RECIPIENT, TRANSFER_ASSET, TRANSFER_ASSET_MULTI, SUBSCRIPTION);
		
		this.recipient = recipient;
		params.put("recipient", recipient.getID());
		if(recipient.getPublicKey() != null) {
			params.put("recipientPublicKey", Hex.toHexString(recipient.getPublicKey()));
		}
		return this;
	}

	public TransactionBuilder referencedTransactionFullHash(String referencedTransactionFullHash) {
		this.referencedTransactionFullHash = referencedTransactionFullHash;
		params.put("referencedTransactionFullHash", referencedTransactionFullHash);
		return this;
	}

	public TransactionBuilder message(String message) {
		params.put("message", this.message = message);
		this.messageIsText = true;
		params.put("messageIsText", Boolean.toString(messageIsText));
		return this;
	}

	public TransactionBuilder message(byte[] message) {
		params.put("message", this.message = Hex.toHexString(message));
		this.messageIsText = false;
		params.put("messageIsText", Boolean.toString(messageIsText));
		return this;
	}

	public TransactionBuilder encryptedMessage(EncryptedMessage message) {
		this.encryptedMessage = message;
		params.put("messageToEncryptIsText", Boolean.toString(message.isText()));
		params.put("encryptedMessageData", Hex.toHexString(message.getData()));
		params.put("encryptedMessageNonce", Hex.toHexString(message.getNonce()));
		return this;
	}

	public TransactionBuilder encryptedMessageToSelf(EncryptedMessage message) {
		this.encryptedMessageToSelf = message;
		params.put("messageToEncryptToSelfIsText", Boolean.toString(message.isText()));
		params.put("encryptToSelfMessageData", Hex.toHexString(message.getData()));
		params.put("encryptToSelfMessageNonce", Hex.toHexString(message.getNonce()));
		return this;
	}

	public TransactionBuilder recipients(Map<SignumAddress, SignumValue> recipients) {
		checkValid("recipients", SEND_MONEY_MULTI);

		StringBuilder recipientsString = new StringBuilder();
		if (recipients.size() > 64 || recipients.size() < 2) {
			throw new IllegalArgumentException("Must have 2-64 recipients, received " + recipients.size());
		}
		long total = 0L;
		for (Map.Entry<SignumAddress, SignumValue> recipient : recipients.entrySet()) {
			total += recipient.getValue().longValue();
			recipientsString.append(recipient.getKey().getID()).append(":").append(recipient.getValue().toNQT())
			.append(";");
		}
		recipientsString.setLength(recipientsString.length() - 1);

		this.recipients = recipients;
		this.amount = SignumValue.fromNQT(total);
		params.put("recipients", recipientsString.toString());
		return this;
	}

	public TransactionBuilder recipients(Set<SignumAddress> recipients) {
		checkValid("recipients", SEND_MONEY_MULTI_SAME);

		StringBuilder recipientsString = new StringBuilder();
		if (recipients.size() > 128 || recipients.size() < 2) {
			throw new IllegalArgumentException("Must have 2-128 recipients, received " + recipients.size());
		}
		if (this.amount == null){
			throw new IllegalArgumentException("Amount not set");
		}
		for (SignumAddress recipient : recipients) {
			recipientsString.append(recipient.getID()).append(";");
		}
		recipientsString.setLength(recipientsString.length() - 1);

		this.recipientsSame = recipients;
		this.amount = this.amount.multiply(recipients.size());
		params.put("recipients", recipientsString.toString());
		return this;
	}

	public TransactionBuilder creationBytes(byte[] creationBytes) {
		checkValid("creationBytes", CREATE_AT);

		this.creationBytes = creationBytes;
		if (creationBytes.length >= 2560) {
			body = Hex.toHexString(creationBytes);
		}
		params.put("creationBytes", Hex.toHexString(creationBytes));
		return this;
	}

	public TransactionBuilder name(String name) {
		checkValid("name", SET_ACCOUNT_INFO, ISSUE_ASSET, CREATE_AT);
		
		params.put("name", this.name = name);
		return this;
	}

	public TransactionBuilder description(String description) {
		checkValid("description", SET_ACCOUNT_INFO, ISSUE_ASSET, CREATE_AT);
		
		params.put("description", this.description = description);
		return this;
	}

	public TransactionBuilder mintable(boolean mintable) {
		checkValid("mintable", ISSUE_ASSET);

		params.put("mintable", Boolean.toString(this.mintable = mintable));
		return this;
	}

	public TransactionBuilder broadcast(Boolean broadcast) {
		params.put("broadcast", broadcast.toString());
		return this;
	}

	public TransactionBuilder subscription(SignumID subscription) {
		checkValid("subscription", SUBSCRIPTION_CANCEL);

		this.subscription = subscription;
		params.put("subscription", subscription.getID());
		return this;
	}

	public TransactionBuilder frequency(int frequency) {
		checkValid("frequency", SUBSCRIPTION);
		
		params.put("frequency", Integer.toString(this.frequency = frequency));
		return this;
	}

	public TransactionBuilder order(SignumID order) {
		checkValid("order", CANCEL_ASK_ORDER, CANCEL_BID_ORDER);
		
		params.put("order", order.getID());
		return this;
	}

	public TransactionBuilder asset(SignumID asset) {
		checkValid("asset", TRANSFER_ASSET, MINT_ASSET, DISTRIBUTE_TO_ASSET_HOLDERS);
		
		this.asset = asset;
		params.put("asset", asset.getID());
		return this;
	}

	public TransactionBuilder quantity(SignumValue quantity) {
		checkValid("quantity", ISSUE_ASSET, TRANSFER_ASSET, MINT_ASSET, DISTRIBUTE_TO_ASSET_HOLDERS);
		
		this.quantity = quantity;
		params.put("quantityQNT", quantity.toNQT().toString());
		return this;
	}

	public TransactionBuilder price(SignumValue price) {
		checkValid("price", PLACE_BID_ORDER, PLACE_ASK_ORDER, SELL_ALIAS);
		
		this.price = price;
		params.put("priceQNT", price.toNQT().toString());
		return this;
	}

	public TransactionBuilder assetIdsAndQuantities(Map<SignumID, SignumValue> assetIdsAndQuantities) {
		checkValid("assetIdsAndQuantities", TRANSFER_ASSET_MULTI);
		
		this.assetIdsAndQuantities = assetIdsAndQuantities;
		StringBuilder assetIdAndQuantityString = new StringBuilder();
		if (assetIdsAndQuantities.size() > 4 || assetIdsAndQuantities.size() < 2) {
			throw new IllegalArgumentException("Must have 2-4 assets, recieved " + assetIdsAndQuantities.size());
		}
		for (Map.Entry<SignumID, SignumValue> entry : assetIdsAndQuantities.entrySet()) {
			assetIdAndQuantityString.append(entry.getKey().getID()).append(":").append(entry.getValue().toNQT())
			.append(";");
		}
		assetIdAndQuantityString.setLength(assetIdAndQuantityString.length() - 1);

		params.put("assetIdsAndQuantities", assetIdAndQuantityString.toString());
		return this;
	}

	public TransactionBuilder quantityMinimum(SignumValue quantityMinimum) {
		checkValid("quantityMinimum", DISTRIBUTE_TO_ASSET_HOLDERS);
		
		this.quantityMinimum = quantityMinimum;
		params.put("quantityMinimumQNT", quantityMinimum.toNQT().toString());
		return this;
	}

	public TransactionBuilder assetToDistribute(SignumID assetToDistribute) {
		checkValid("assetToDistribute", DISTRIBUTE_TO_ASSET_HOLDERS);
		
		this.assetToDistribute = assetToDistribute;
		params.put("assetToDistribute", assetToDistribute.getID());
		return this;
	}

	public TransactionBuilder decimals(int decimals) {
		checkValid("decimals", ISSUE_ASSET);
		
		if (decimals < 0 || decimals > 8) {
			throw new IllegalArgumentException("Decimals must be between 0-8");
		}
		params.put("decimals", Integer.toString(this.decimals = decimals));
		return this;
	}

	/**
	 * @param aliasName the alias name
	 * @param alias the alias ID (or null when creating a new alias)
	 * @return
	 */
	public TransactionBuilder alias(String aliasName, SignumID alias) {
		checkValid("alias", SET_ALIAS, SELL_ALIAS);

		this.alias = alias;
		if(alias != null) {
			params.put("alias", alias.getID());
		}
		params.put("aliasName", this.aliasName = aliasName);
		return this;
	}

	public TransactionBuilder aliasURI(String aliasURI) {
		checkValid("aliasURI", SET_ALIAS);

		params.put("aliasURI", this.aliasURI = aliasURI);
		return this;
	}

	/**
	 * @param tld the TLD name
	 * @param tldId the TLD ID (null if creating a new one)
	 * @return
	 */
	public TransactionBuilder tld(String tld, SignumID tldId) {
		checkValid("tld", SET_ALIAS, SELL_ALIAS, BUY_ALIAS, SET_TLD);

		this.tld = tld;
		this.tldId = tldId;
		params.put("tld", tld);
		return this;
	}

	/**
	 * Add a custom query parameter.
	 *
	 * @param param the parameter name
	 * @param value the parameter value
	 * @return the transaction builder
	 */
	public TransactionBuilder param(String param, String value) {
		params.put("param", value);
		return this;
	}

	public Map<String, String> getParams(){
		return params;
	}

	public String getBody() {
		return body;
	}

	public String getRequestType() {
		return type.getRequestType();
	}

	/**
	 * Verifies if the given byte array is compatible with the information sent to the node.
	 *
	 * The attachment as well as the appendices are checked.
	 *
	 * @param bytes the byte array of this transaction
	 * @return true if the byte array matches the transaction data
	 */
	public boolean verify(byte []bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		byte type = buffer.get();
		byte subtype = buffer.get();
		byte txversion = (byte) ((subtype & 0xF0) >> 4);
		subtype = (byte) (subtype & 0x0F);
		buffer.getInt(); // timestamp
		short deadline = buffer.getShort();
		byte[] senderPublicKey = new byte[32];
		buffer.get(senderPublicKey);
		long recipientId = buffer.getLong();
		long amountNQT = buffer.getLong();
		long feeNQT = buffer.getLong();
		String referencedTransactionFullHash = null;
		byte[] referencedTransactionFullHashBytes = new byte[32];
		buffer.get(referencedTransactionFullHashBytes);
		referencedTransactionFullHash = Hex.toHexString(referencedTransactionFullHashBytes);
		byte[] signature = new byte[64];
		buffer.get(signature);
		int flags = 0;
		if (txversion > 0) {
			flags = buffer.getInt();
			buffer.getInt(); // ecBlockHeight
			buffer.getLong(); // ecBlockId
			if (txversion > 1){
				buffer.getLong(); // cashBackId
			}
		}

		if(this.type == ADD_COMMITMENT || this.type == REMOVE_COMMITMENT) {
			// the actual sent amount must be zero and the amount locked/unlocked should be on the attachment
			if(amountNQT != 0L) {
				return false;
			}
			buffer.get(); // version
			amountNQT = buffer.getLong();
		}

		if(this.type.getType()!=type || this.type.getSubType()!=subtype || this.deadline!=deadline ||
				this.fee.longValue() != feeNQT ||
				(this.amount!=null && this.amount.longValue()!=amountNQT) ||
				(this.amount==null && amountNQT!=0L) ||
				(this.referencedTransactionFullHash!=null && !this.referencedTransactionFullHash.equals(referencedTransactionFullHash)) ||
				(this.recipient!=null && recipient.getSignedLongId()!=recipientId)
				|| (this.recipient==null && recipientId != 0l)) {
			return false;
		}

		// parsing the attachment
		if(this.type == SEND_MONEY_MULTI) {
			buffer.get(); // version
			int numberOfRecipients = Byte.toUnsignedInt(buffer.get());
			if(numberOfRecipients != recipients.keySet().size()) {
				return false;
			}
			for (int i = 0; i < numberOfRecipients; ++i) {
				SignumAddress recipientMulti = SignumAddress.fromId(buffer.getLong());
				if(!recipients.containsKey(recipientMulti)) {
					return false;
				}
				if(recipients.get(recipientMulti).longValue() != buffer.getLong()) {
					return false;
				}
			}
		}
		else if(this.type == SEND_MONEY_MULTI_SAME) {
			buffer.get(); // version
			int numberOfRecipients = Byte.toUnsignedInt(buffer.get());
			if(numberOfRecipients != recipientsSame.size()) {
				return false;
			}
			for (int i = 0; i < numberOfRecipients; ++i) {
				SignumAddress recipientMulti = SignumAddress.fromId(buffer.getLong());
				if(!recipientsSame.contains(recipientMulti)) {
					return false;
				}
			}
		}

		else if(this.type == SET_ALIAS) {
			int version = buffer.get();
			String aliasName = readString(buffer, buffer.get());
			String aliasURI = readString(buffer, buffer.getShort());
			if(version > 1) {
				long tldId = buffer.getLong();
				if((this.tldId == null && tldId!=0L)
					|| (this.tld != null && this.tldId.getSignedLongId() != tldId) ) {
					return false;
				}
			}
			if(!this.aliasName.equals(aliasName) || !this.aliasURI.equals(aliasURI)) {
				return false;
			}
		}
		else if(this.type == SET_ACCOUNT_INFO) {
			buffer.get(); // version
			String name = readString(buffer, buffer.get());
			String description = readString(buffer, buffer.getShort());
			if(!name.equals(name) || !description.equals(description)) {
				return false;
			}
		}
		else if(this.type == SELL_ALIAS) {
			int version = buffer.get();
			if(version > 1) {
				long aliasId = buffer.getLong();
				if(this.alias.getSignedLongId() != aliasId) {
					return false;
				}
			}
			else {
				String aliasName = readString(buffer, buffer.get());
				if(!this.aliasName.equals(aliasName)) {
					return false;
				}
			}
			long priceNQT = buffer.getLong();
			if(this.price.longValue() != priceNQT) {
				return false;
			}
		}
		else if(this.type == BUY_ALIAS) {
			int version = buffer.get();
			if(version > 1) {
				long aliasId = buffer.getLong();
				if(this.alias.getSignedLongId() != aliasId) {
					return false;
				}
			}
			else {
				String aliasName = readString(buffer, buffer.get());
				if(!this.aliasName.equals(aliasName)) {
					return false;
				}
			}
		}
		else if(this.type == SET_TLD) {
			buffer.get(); // version
			String tldName = readString(buffer, buffer.get());
			if(!this.tld.equals(tldName)) {
				return false;
			}
		}

		else if(this.type == ISSUE_ASSET) {
			int version = buffer.get();
			String name = readString(buffer, buffer.get());
			String description = readString(buffer, buffer.getShort());
			long quantityQNT = buffer.getLong();
			int decimals = buffer.get();

			boolean mintable = false;
			if(version > 1) {
				mintable = buffer.get() == 1;
			}
			if(!name.equals(name) || !description.equals(description) || this.quantity.longValue()!=quantityQNT
					|| this.decimals!=decimals || this.mintable!=mintable){
				return false;
			}
		}
		else if(this.type == TRANSFER_ASSET || this.type == MINT_ASSET) {
			buffer.get(); // version
			long assetId = buffer.getLong();
			long quantityQNT = buffer.getLong();
			if(this.asset.getSignedLongId() != assetId || this.quantity.longValue() != quantityQNT) {
				return false;
			}
		}
		else if(this.type == TRANSFER_ASSET_MULTI) {
			buffer.get(); // version
			int numberOfAssets = Byte.toUnsignedInt(buffer.get());
			if (numberOfAssets != assetIdsAndQuantities.size()) {
				return false;
			}
			for(int i=0; i < numberOfAssets; i++){
				SignumID assetId = SignumID.fromLong(buffer.getLong());
				long quantity = buffer.getLong();

				if(!assetIdsAndQuantities.containsKey(assetId)
						|| assetIdsAndQuantities.get(assetId).longValue() != quantity){
					return false;
				}
			}
		}
		else if(this.type == PLACE_ASK_ORDER || this.type == PLACE_BID_ORDER) {
			buffer.get(); // version
			long assetId = buffer.getLong();
			long quantityQNT = buffer.getLong();
			long priceNQT = buffer.getLong();
			if(this.asset.getSignedLongId() != assetId || this.quantity.longValue() != quantityQNT || this.price.longValue() != priceNQT) {
				return false;
			}
		}
		else if(this.type == CANCEL_ASK_ORDER || this.type == CANCEL_BID_ORDER) {
			buffer.get(); // version
			long orderId = buffer.getLong();
			if(this.order.getSignedLongId() != orderId) {
				return false;
			}
		}
		else if(this.type == ADD_ASSET_TREASURY || this.type == TRANSFER_ASSET_OWNERSHIP) {
			// empty attachment
		}
		else if(this.type == DISTRIBUTE_TO_ASSET_HOLDERS) {
			buffer.get(); // version
			long assetId = buffer.getLong();
			long minimumAssetQuantityQNT = buffer.getLong();
			long assetIdToDistribute = buffer.getLong();
			long quantityQNT = buffer.getLong();
			if(this.asset.getSignedLongId() != assetId
					|| (this.quantity==null && quantityQNT!=0L)
					|| (this.quantity!=null && this.quantity.longValue() != quantityQNT)
					|| (this.assetToDistribute==null && assetIdToDistribute!=0L)
					|| (this.assetToDistribute!=null && this.assetToDistribute.getSignedLongId() != assetIdToDistribute)
					|| this.quantityMinimum.longValue() != minimumAssetQuantityQNT ){
				return false;
			}
		}
		else if(this.type == TRANSFER_ASSET_MULTI) {
			buffer.get(); // version
			int numberOfAssets = Byte.toUnsignedInt(buffer.get());
			if (numberOfAssets != this.assetIdAndQuantity.keySet().size()) {
				return false;
			}
			for(int i=0; i < numberOfAssets; i++){
				SignumID assetId = SignumID.fromLong(buffer.getLong());
				if(!this.assetIdAndQuantity.keySet().contains(assetId)) {
					return false;
				}
				long quantity = buffer.getLong();
				if(this.assetIdAndQuantity.get(assetId).longValue() != quantity) {
					return false;
				}
			}
		}
		else if(this.type == SET_REWARD_RECIPIENT) {
			buffer.get(); // version
		}
		else if(this.type == SUBSCRIPTION) {
			buffer.get(); // version
			int frequency = buffer.getInt();
			if(this.frequency != frequency) {
				return false;
			}
		}
		else if(this.type == SUBSCRIPTION_CANCEL) {
			buffer.get(); // version
			long subscriptionId = buffer.getLong();

			if(this.subscription.getSignedLongId() != subscriptionId) {
				return false;
			}
		}
		else if(this.type == CREATE_AT) {
			buffer.get(); // version

			String name = readString(buffer, buffer.get());
			String description = readString(buffer, buffer.getShort());

			if(!this.name.equals(name) || !this.description.equals(description)) {
				return false;
			}

			// rest of the parsing is at related; code comes from
			// public AtMachineState( byte[] atId, byte[] creator, byte[] creationBytes, int height ) {
			int startPosition = buffer.position();
			buffer.getShort(); // at version

			buffer.getShort(); //future: reserved for future needs

			int pageSize = 256;
			short codePages = buffer.getShort();
			short dataPages = buffer.getShort();
			buffer.getShort();
			buffer.getShort();

			buffer.getLong();

			int codeLen;
			if ( codePages * pageSize < pageSize + 1 ) {
				codeLen = buffer.get();
				if ( codeLen < 0 )
					codeLen += (Byte.MAX_VALUE + 1) * 2;
			}
			else if ( codePages * pageSize < Short.MAX_VALUE + 1 ) {
				codeLen = buffer.getShort();
				if( codeLen < 0 )
					codeLen += (Short.MAX_VALUE + 1) * 2;
			}
			else {
				codeLen = buffer.getInt();
			}
			byte[] code = new byte[ codeLen ];
			buffer.get( code, 0, codeLen );

			int dataLen;
			if ( dataPages * pageSize < 257 ) {
				dataLen = buffer.get();
				if ( dataLen < 0 )
					dataLen += (Byte.MAX_VALUE + 1) * 2;
			}
			else if ( dataPages * pageSize < Short.MAX_VALUE + 1 ) {
				dataLen = buffer.getShort();
				if ( dataLen < 0 )
					dataLen += (Short.MAX_VALUE + 1) * 2;
			}
			else {
				dataLen = buffer.getInt();
			}
			byte[] data = new byte[ dataLen ];
			buffer.get( data, 0, dataLen );

			int endPosition = buffer.position();
			buffer.position(startPosition);
			byte[] dst = new byte[ endPosition - startPosition ];
			buffer.get( dst , 0 , endPosition - startPosition );

			byte []creationBytes = new byte[dst.length];
			System.arraycopy(this.creationBytes, 0, creationBytes, 0, this.creationBytes.length);
			if(!Arrays.equals(creationBytes, dst)) {
				return false;
			}
		}

		// Parsing the appendices
	    int position = 1;
	    if ((flags & position) != 0) {
	    	// has a message
	    	if(txversion != 0) buffer.get();
	        int messageLength = buffer.getInt();
	        boolean isText = messageLength < 0; // ugly hack
	        if (messageLength < 0) {
	          messageLength &= Integer.MAX_VALUE;
	        }
	        byte []messageBytes = new byte[messageLength];
	        buffer.get(messageBytes);
	        String message = isText ? new String(messageBytes, StandardCharsets.UTF_8) : Hex.toHexString(messageBytes);
	        if(this.messageIsText!=isText || !this.message.equals(message)) {
	        	return false;
	        }
	    }
	    position <<= 1;
	    if ((flags & position) != 0) {
	    	// has an encrypted message
	    	if(txversion != 0) buffer.get();
	    	int length = buffer.getInt();
	    	boolean isText = length < 0;
	    	if (length < 0) {
	    		length &= Integer.MAX_VALUE;
	    	}
	    	byte[] data = new byte[length];
	    	buffer.get(data);
	    	byte[] nonceBytes = new byte[32];
	    	buffer.get(nonceBytes);
	    	
	    	if(this.encryptedMessage.isText()!=isText || !Arrays.equals(this.encryptedMessage.getData(), data)
	    		|| !Arrays.equals(this.encryptedMessage.getNonce(), nonceBytes)){
	    		return false;
	    	}
	    }
	    position <<= 1;
	    if ((flags & position) != 0) {
	    	// has a public key announcement
	    	if(txversion != 0) buffer.get();
	        byte publicKey[] = new byte[32];
	        buffer.get(publicKey);
	        if(this.recipient.getPublicKey()!=null && !Arrays.equals(this.recipient.getPublicKey(), publicKey)) {
	        	return false;
	        }
	    }
	    position <<= 1;
	    if ((flags & position) != 0) {
	    	// has an encrypted to self message
	    	if(txversion != 0) buffer.get();
	    	int length = buffer.getInt();
	    	boolean isText = length < 0;
	    	if (length < 0) {
	    		length &= Integer.MAX_VALUE;
	    	}
	    	byte[] data = new byte[length];
	    	buffer.get(data);
	    	byte[] nonceBytes = new byte[32];
	    	buffer.get(nonceBytes);
	    	
	    	if(this.encryptedMessageToSelf.isText()!=isText || !Arrays.equals(this.encryptedMessageToSelf.getData(), data)
	    		|| !Arrays.equals(this.encryptedMessageToSelf.getNonce(), nonceBytes)){
	    		return false;
	    	}
	    }

		return true;
	}

	private static String readString(ByteBuffer buffer, int numBytes) {
		byte[] bytes = new byte[numBytes];
		buffer.get(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
