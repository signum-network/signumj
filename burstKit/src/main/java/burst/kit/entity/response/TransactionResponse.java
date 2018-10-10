package burst.kit.entity.response;

import burst.kit.entity.*;
import burst.kit.entity.response.attachment.TransactionAttachment;

@SuppressWarnings("unused")
public class TransactionResponse extends BRSResponse {
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
    private int ecBlockHeight;
    private int deadline;
    private BurstID transaction;
    private BurstTimestamp timestamp;
    private int height;

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
}
