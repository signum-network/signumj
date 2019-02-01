package burst.kit.entity.response;

import burst.kit.entity.*;
import burst.kit.entity.response.attachment.TransactionAttachment;
import io.reactivex.annotations.Nullable;

@SuppressWarnings("unused")
public final class TransactionResponse extends BRSResponse {
    private final HexStringByteArray senderPublicKey;
    private final HexStringByteArray signature;
    private final BurstValue feeNQT;
    private final BurstValue amountNQT;
    private final int type;
    private final int subtype;
    private final int confirmations;
    private final HexStringByteArray fullHash;
    private final int version;
    private final BurstID ecBlockId;
    private final HexStringByteArray signatureHash;
    private final TransactionAttachment attachment;
    private final BurstAddress sender;
    private final BurstAddress recipient;
    private final int ecBlockHeight;
    private final int deadline;
    private final BurstID transaction;
    private final BurstTimestamp timestamp;
    private final int height;
    private final HexStringByteArray referencedTransactionFullHash;
    private final BurstID block;
    private final BurstTimestamp blockTimestamp;

    public TransactionResponse(HexStringByteArray senderPublicKey, HexStringByteArray signature, BurstValue feeNQT, BurstValue amountNQT, int type, int subtype, int confirmations, HexStringByteArray fullHash, int version, BurstID ecBlockId, HexStringByteArray signatureHash, TransactionAttachment attachment, BurstAddress sender, BurstAddress recipient, int ecBlockHeight, int deadline, BurstID transaction, BurstTimestamp timestamp, int height, HexStringByteArray referencedTransactionFullHash, BurstID block, BurstTimestamp blockTimestamp) {
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

    public HexStringByteArray getSenderPublicKey() {
        return senderPublicKey;
    }

    public HexStringByteArray getSignature() {
        return signature;
    }

    public BurstValue getFeeNQT() {
        return feeNQT;
    }

    public BurstValue getAmountNQT() {
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

    public HexStringByteArray getFullHash() {
        return fullHash;
    }

    public int getVersion() {
        return version;
    }

    public BurstID getEcBlockId() {
        return ecBlockId;
    }

    public HexStringByteArray getSignatureHash() {
        return signatureHash;
    }

    public TransactionAttachment getAttachment() {
        return attachment;
    }

    public BurstAddress getSender() {
        return sender;
    }

    /**
     * @return The recipient OR NULL if the transaction does not have a recipient
     */
    @Nullable
    public BurstAddress getRecipient() {
        return recipient;
    }

    public int getEcBlockHeight() {
        return ecBlockHeight;
    }

    public int getDeadline() {
        return deadline;
    }

    public BurstID getTransactionID() {
        return transaction;
    }

    public BurstTimestamp getTimestamp() {
        return timestamp;
    }

    public int getHeight() {
        return height;
    }

    public HexStringByteArray getReferencedTransactionFullHash() {
        return referencedTransactionFullHash;
    }

    public BurstID getBlockId() {
        return block;
    }

    public BurstTimestamp getBlockTimestamp() {
        return blockTimestamp;
    }
}
