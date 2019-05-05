package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.TransactionResponse;

public class Transaction {
    private final BurstAddress recipient;
    private final BurstAddress sender;
    private final BurstID blockId;
    private final BurstID ecBlockId;
    private final BurstID id;
    private final BurstTimestamp blockTimestamp;
    private final BurstTimestamp timestamp;
    private final BurstValue amount;
    private final BurstValue fee;
    private final byte[] fullHash;
    private final byte[] referencedTransactionFullHash;
    private final byte[] senderPublicKey;
    private final byte[] signature;
    private final byte[] signatureHash;
    private final int blockHeight;
    private final int confirmations;
    private final int ecBlockHeight;
    private final int subtype;
    private final int type;
    private final int version;
    private final Attachment attachment;
    private final Appendix[] appendages;
    private final short deadline;

    public Transaction(BurstAddress recipient, BurstAddress sender, BurstID blockId, BurstID ecBlockId, BurstID id, BurstTimestamp blockTimestamp, BurstTimestamp timestamp, BurstValue amount, BurstValue fee, byte[] fullHash, byte[] referencedTransactionFullHash, byte[] senderPublicKey, byte[] signature, byte[] signatureHash, int blockHeight, int confirmations, int ecBlockHeight, int subtype, int type, int version, Attachment attachment, Appendix[] appendages, short deadline) {
        this.recipient = recipient;
        this.sender = sender;
        this.blockId = blockId;
        this.ecBlockId = ecBlockId;
        this.id = id;
        this.blockTimestamp = blockTimestamp;
        this.timestamp = timestamp;
        this.amount = amount;
        this.fee = fee;
        this.fullHash = fullHash;
        this.referencedTransactionFullHash = referencedTransactionFullHash;
        this.senderPublicKey = senderPublicKey;
        this.signature = signature;
        this.signatureHash = signatureHash;
        this.blockHeight = blockHeight;
        this.confirmations = confirmations;
        this.ecBlockHeight = ecBlockHeight;
        this.subtype = subtype;
        this.type = type;
        this.version = version;
        this.attachment = attachment;
        this.appendages = appendages;
        this.deadline = deadline;
    }

    public Transaction(TransactionResponse transactionResponse) {
        this.recipient = transactionResponse.getRecipient();
        this.sender = transactionResponse.getSender();
        this.blockId = transactionResponse.getBlockId();
        this.ecBlockId = transactionResponse.getEcBlockId();
        this.id = transactionResponse.getTransactionID();
        this.blockTimestamp = transactionResponse.getBlockTimestamp();
        this.timestamp = transactionResponse.getTimestamp();
        this.amount = transactionResponse.getAmountNQT();
        this.fee = transactionResponse.getFeeNQT();
        this.fullHash = transactionResponse.getFullHash().getBytes();
        this.referencedTransactionFullHash = transactionResponse.getReferencedTransactionFullHash().getBytes();
        this.senderPublicKey = transactionResponse.getSenderPublicKey().getBytes();
        this.signature = transactionResponse.getSignature().getBytes();
        this.signatureHash = transactionResponse.getSignatureHash().getBytes();
        this.blockHeight = transactionResponse.getHeight();
        this.confirmations = transactionResponse.getConfirmations();
        this.ecBlockHeight = transactionResponse.getEcBlockHeight();
        this.subtype = transactionResponse.getSubtype();
        this.type = transactionResponse.getType();
        this.version = transactionResponse.getVersion();
        this.attachment = Attachment.fromResponse(transactionResponse.getAttachment());
        this.appendages = new Appendix[0]; // TODO
        this.deadline = transactionResponse.getDeadline();
    }

    public BurstAddress getRecipient() {
        return recipient;
    }

    public BurstAddress getSender() {
        return sender;
    }

    public BurstID getBlockId() {
        return blockId;
    }

    public BurstID getEcBlockId() {
        return ecBlockId;
    }

    public BurstID getId() {
        return id;
    }

    public BurstTimestamp getBlockTimestamp() {
        return blockTimestamp;
    }

    public BurstTimestamp getTimestamp() {
        return timestamp;
    }

    public BurstValue getAmount() {
        return amount;
    }

    public BurstValue getFee() {
        return fee;
    }

    public byte[] getFullHash() {
        return fullHash;
    }

    public byte[] getReferencedTransactionFullHash() {
        return referencedTransactionFullHash;
    }

    public byte[] getSenderPublicKey() {
        return senderPublicKey;
    }

    public byte[] getSignature() {
        return signature;
    }

    public byte[] getSignatureHash() {
        return signatureHash;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public int getEcBlockHeight() {
        return ecBlockHeight;
    }

    public int getSubtype() {
        return subtype;
    }

    public int getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public Appendix[] getAppendages() {
        return appendages;
    }

    public short getDeadline() {
        return deadline;
    }
}
