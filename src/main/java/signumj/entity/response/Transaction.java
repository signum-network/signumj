package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.http.TransactionResponse;
import signumj.entity.response.http.attachment.TransactionAppendixResponse;
import signumj.response.attachment.OrdinaryPaymentAttachment;

import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;

public class Transaction {
    private final SignumAddress recipient;
    private final SignumAddress sender;
    private final SignumID blockId;
    private final SignumID ecBlockId;
    private final SignumID id;
    private final SignumTimestamp blockTimestamp;
    private final SignumTimestamp timestamp;
    private final SignumValue amount;
    private final SignumValue fee;
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
    private final TransactionAttachment attachment;
    private final TransactionAppendix[] appendages;
    private final short deadline;

    public Transaction(SignumAddress recipient, SignumAddress sender, SignumID blockId, SignumID ecBlockId, SignumID id, SignumTimestamp blockTimestamp, SignumTimestamp timestamp, SignumValue amount, SignumValue fee, byte[] fullHash, byte[] referencedTransactionFullHash, byte[] senderPublicKey, byte[] signature, byte[] signatureHash, int blockHeight, int confirmations, int ecBlockHeight, int subtype, int type, int version, TransactionAttachment attachment, TransactionAppendix[] appendages, short deadline) {
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
        this.recipient = SignumAddress.fromEither(transactionResponse.getRecipient());
        this.sender = SignumAddress.fromEither(transactionResponse.getSender());
        this.blockId = SignumID.fromLong(transactionResponse.getBlock());
        this.ecBlockId = SignumID.fromLong(transactionResponse.getEcBlockId());
        this.id = SignumID.fromLong(transactionResponse.getTransaction());
        this.blockTimestamp = SignumTimestamp.fromBurstTimestamp(transactionResponse.getBlockTimestamp());
        this.timestamp = SignumTimestamp.fromBurstTimestamp(transactionResponse.getTimestamp());
        this.amount = SignumValue.fromNQT(transactionResponse.getAmountNQT());
        this.fee = SignumValue.fromNQT(transactionResponse.getFeeNQT());
        this.fullHash = Hex.decode(transactionResponse.getFullHash());
        this.referencedTransactionFullHash = transactionResponse.getReferencedTransactionFullHash() == null ? null : Hex.decode(transactionResponse.getReferencedTransactionFullHash());
        this.senderPublicKey = Hex.decode(transactionResponse.getSenderPublicKey());
        this.signature = transactionResponse.getSignature() == null ? null : Hex.decode(transactionResponse.getSignature());
        this.signatureHash = transactionResponse.getSignatureHash() == null ? null : Hex.decode(transactionResponse.getSignatureHash());
        this.blockHeight = transactionResponse.getHeight();
        this.confirmations = transactionResponse.getConfirmations();
        this.ecBlockHeight = transactionResponse.getEcBlockHeight();
        this.subtype = transactionResponse.getSubtype();
        this.type = transactionResponse.getType();
        this.version = transactionResponse.getVersion();
        this.attachment = transactionResponse.getAttachment() == null ? new OrdinaryPaymentAttachment(transactionResponse.getVersion()) : transactionResponse.getAttachment().getAttachment().toAttachment();
        this.appendages = transactionResponse.getAttachment() == null ? new TransactionAppendix[0] : Arrays.stream(transactionResponse.getAttachment().getAppendages())
                .map(TransactionAppendixResponse::toAppendix)
                .toArray(TransactionAppendix[]::new);
        this.deadline = transactionResponse.getDeadline();
    }

    public SignumAddress getRecipient() {
        return recipient;
    }

    public SignumAddress getSender() {
        return sender;
    }

    public SignumID getBlockId() {
        return blockId;
    }

    public SignumID getEcBlockId() {
        return ecBlockId;
    }

    public SignumID getId() {
        return id;
    }

    public SignumTimestamp getBlockTimestamp() {
        return blockTimestamp;
    }

    public SignumTimestamp getTimestamp() {
        return timestamp;
    }

    public SignumValue getAmount() {
        return amount;
    }

    public SignumValue getFee() {
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

    public TransactionAttachment getAttachment() {
        return attachment;
    }

    public TransactionAppendix[] getAppendages() {
        return appendages;
    }

    public short getDeadline() {
        return deadline;
    }
}
