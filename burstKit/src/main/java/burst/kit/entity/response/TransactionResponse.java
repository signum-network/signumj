package burst.kit.entity.response;

import burst.kit.entity.*;
import burst.kit.entity.response.attachment.TransactionAttachment;
import io.reactivex.annotations.Nullable;

@SuppressWarnings("unused")
public final class TransactionResponse extends BRSResponse {
    private HexStringByteArray senderPublicKey;
    private HexStringByteArray signature;
    private BurstValue feeNQT;
    private BurstValue amountNQT;
    private int type;
    private int subtype;
    private int confirmations;
    private HexStringByteArray fullHash;
    private int version;
    private BurstID ecBlockId;
    private HexStringByteArray signatureHash;
    private TransactionAttachment attachment;
    private BurstAddress sender;
    private BurstAddress recipient;
    private int ecBlockHeight;
    private int deadline;
    private BurstID transaction;
    private BurstTimestamp timestamp;
    private int height;
    private HexStringByteArray referencedTransactionFullHash;
    private BurstID block;
    private BurstTimestamp blockTimestamp;

    private TransactionResponse() {}

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
