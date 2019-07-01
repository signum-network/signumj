package burst.kit.entity.response;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.attachment.OrdinaryPaymentAttachment;
import burst.kit.entity.response.http.TransactionResponse;
import burst.kit.entity.response.http.attachment.TransactionAppendixResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;

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
    private final TransactionAttachment attachment;
    private final TransactionAppendix[] appendages;
    private final short deadline;

    public Transaction(BurstAddress recipient, BurstAddress sender, BurstID blockId, BurstID ecBlockId, BurstID id, BurstTimestamp blockTimestamp, BurstTimestamp timestamp, BurstValue amount, BurstValue fee, byte[] fullHash, byte[] referencedTransactionFullHash, byte[] senderPublicKey, byte[] signature, byte[] signatureHash, int blockHeight, int confirmations, int ecBlockHeight, int subtype, int type, int version, TransactionAttachment attachment, TransactionAppendix[] appendages, short deadline) {
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
        this.recipient = BurstAddress.fromEither(transactionResponse.getRecipient());
        this.sender = BurstAddress.fromEither(transactionResponse.getSender());
        this.blockId = BurstID.fromLong(transactionResponse.getBlock());
        this.ecBlockId = BurstID.fromLong(transactionResponse.getEcBlockId());
        this.id = BurstID.fromLong(transactionResponse.getTransaction());
        this.blockTimestamp = new BurstTimestamp(transactionResponse.getBlockTimestamp());
        this.timestamp = new BurstTimestamp(transactionResponse.getTimestamp());
        this.amount = BurstValue.fromPlanck(transactionResponse.getAmountNQT());
        this.fee = BurstValue.fromPlanck(transactionResponse.getFeeNQT());
        this.fullHash = Hex.decode(transactionResponse.getFullHash());
        this.referencedTransactionFullHash = transactionResponse.getReferencedTransactionFullHash() == null ? null : Hex.decode(transactionResponse.getReferencedTransactionFullHash());
        this.senderPublicKey = Hex.decode(transactionResponse.getSenderPublicKey());
        this.signature = Hex.decode(transactionResponse.getSignature());
        this.signatureHash = Hex.decode(transactionResponse.getSignatureHash());
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

    public Transaction(BrsApi.Transaction transaction) {
        BurstCrypto burstCrypto = BurstCrypto.getInstance();
        BrsApi.BasicTransaction basicTransaction = transaction.getTransaction();
        this.recipient = BurstAddress.fromId(transaction.getId());
        this.sender = BurstAddress.fromId(basicTransaction.getSenderId());
        this.blockId = BurstID.fromLong(transaction.getBlock());
        this.ecBlockId = BurstID.fromLong(basicTransaction.getEcBlockId());
        this.id = BurstID.fromLong(transaction.getId());
        this.blockTimestamp = new BurstTimestamp(transaction.getBlockTimestamp());
        this.timestamp = new BurstTimestamp(basicTransaction.getTimestamp());
        this.amount = BurstValue.fromPlanck(basicTransaction.getAmount());
        this.fee = BurstValue.fromPlanck(basicTransaction.getFee());
        this.fullHash = transaction.getFullHash().toByteArray();
        this.referencedTransactionFullHash = basicTransaction.getReferencedTransactionFullHash().toByteArray();
        this.senderPublicKey = basicTransaction.getSenderPublicKey().toByteArray();
        this.signature = basicTransaction.getSignature().toByteArray();
        this.signatureHash = burstCrypto.getSha256().digest(basicTransaction.getSignature().toByteArray()); // TODO check this is correct
        this.blockHeight = transaction.getBlockHeight();
        this.confirmations = transaction.getConfirmations();
        this.ecBlockHeight = basicTransaction.getEcBlockHeight();
        this.subtype = basicTransaction.getSubtype();
        this.type = basicTransaction.getType();
        this.version = basicTransaction.getVersion();
        this.attachment = TransactionAttachment.fromProtobuf(basicTransaction.getAttachment(), basicTransaction.getVersion());
        this.appendages = basicTransaction.getAppendagesList()
                .stream()
                .map(TransactionAppendix::fromProtobuf)
                .toArray(TransactionAppendix[]::new);
        this.deadline = (short) basicTransaction.getDeadline();
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
