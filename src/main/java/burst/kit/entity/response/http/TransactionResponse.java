package burst.kit.entity.response.http;

import burst.kit.entity.response.http.attachment.TransactionAttachmentAndAppendagesResponse;

@SuppressWarnings("unused")
public final class TransactionResponse extends BRSResponse {
    private final String senderPublicKey;
    private final String signature;
    private final String feeNQT;
    private final String amountNQT;
    private final int type;
    private final int subtype;
    private final int confirmations;
    private final String fullHash;
    private final int version;
    private final String ecBlockId;
    private final String signatureHash;
    private final TransactionAttachmentAndAppendagesResponse attachment;
    private final String sender;
    private final String recipient;
    private final int ecBlockHeight;
    private final short deadline;
    private final String transaction;
    private final int timestamp;
    private final int height;
    private final String referencedTransactionFullHash;
    private final String block;
    private final int blockTimestamp;

    public TransactionResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String senderPublicKey, String signature, String feeNQT, String amountNQT, int type, int subtype, int confirmations, String fullHash, int version, String ecBlockId, String signatureHash, TransactionAttachmentAndAppendagesResponse attachment, String sender, String recipient, int ecBlockHeight, short deadline, String transaction, int timestamp, int height, String referencedTransactionFullHash, String block, int blockTimestamp) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.senderPublicKey = senderPublicKey;
        this.signature = signature;
        this.feeNQT = feeNQT;
        this.amountNQT = amountNQT;
        this.type = type;
        this.subtype = subtype;
        this.confirmations = confirmations;
        this.fullHash = fullHash;
        this.version = version;
        this.ecBlockId = ecBlockId;
        this.signatureHash = signatureHash;
        this.attachment = attachment;
        this.sender = sender;
        this.recipient = recipient;
        this.ecBlockHeight = ecBlockHeight;
        this.deadline = deadline;
        this.transaction = transaction;
        this.timestamp = timestamp;
        this.height = height;
        this.referencedTransactionFullHash = referencedTransactionFullHash;
        this.block = block;
        this.blockTimestamp = blockTimestamp;
    }

    public String getSenderPublicKey() {
        return senderPublicKey;
    }

    public String getSignature() {
        return signature;
    }

    public String getFeeNQT() {
        return feeNQT;
    }

    public String getAmountNQT() {
        return amountNQT;
    }

    public int getType() {
        return type;
    }

    public int getSubtype() {
        return subtype;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public String getFullHash() {
        return fullHash;
    }

    public int getVersion() {
        return version;
    }

    public String getEcBlockId() {
        return ecBlockId;
    }

    public String getSignatureHash() {
        return signatureHash;
    }

    public TransactionAttachmentAndAppendagesResponse getAttachment() {
        return attachment;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public int getEcBlockHeight() {
        return ecBlockHeight;
    }

    public short getDeadline() {
        return deadline;
    }

    public String getTransaction() {
        return transaction;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getHeight() {
        return height;
    }

    public String getReferencedTransactionFullHash() {
        return referencedTransactionFullHash;
    }

    public String getBlock() {
        return block;
    }

    public int getBlockTimestamp() {
        return blockTimestamp;
    }
}
