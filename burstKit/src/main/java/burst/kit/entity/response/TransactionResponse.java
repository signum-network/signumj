package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import com.google.gson.JsonObject;

@SuppressWarnings("unused")
public class TransactionResponse extends BRSResponse {
    private String senderPublicKey;
    private String signature;
    private BurstValue feeNQT;
    private BurstValue amountNQT;
    private int type;
    private int subtype;
    private int confirmations;
    private String fullHash;
    private int version;
    private BurstID ecBlockId;
    private String signatureHash;
    private JsonObject attachment;
    private BurstAddress sender;
    private int ecBlockHeight;
    private int deadline;
    private BurstID transaction;
    private BurstTimestamp timestamp;
    private int height;

    private TransactionResponse() {} // TODO get attachment

    public String getSenderPublicKey() {
        return senderPublicKey;
    }

    public String getSignature() {
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

    public String getFullHash() {
        return fullHash;
    }

    public int getVersion() {
        return version;
    }

    public BurstID getEcBlockId() {
        return ecBlockId;
    }

    public String getSignatureHash() {
        return signatureHash;
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
