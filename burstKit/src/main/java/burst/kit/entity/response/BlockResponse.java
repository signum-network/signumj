package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public class BlockResponse extends BRSResponse {
    private String previousBlockHash;
    private int payloadLength;
    private BurstValue totalAmountNQT;
    private String generationSignature;
    private BurstAddress creator;
    private String generatorPublicKey;
    private long baseTarget;
    private String payloadHash;
    private String blockReward;
    private BurstID nextBlock;
    private int scoopNum;
    private int numberOfTransactions;
    private String blockSignature;
    private BurstID[] transactions;
    private String nonce;
    private int version;
    private BurstValue totalFeeNQT;
    private BurstID previousBlock;
    private BurstID block;
    private long height;
    private BurstTimestamp timestamp;

    private BlockResponse() {}

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public BurstValue getTotalAmountNQT() {
        return totalAmountNQT;
    }

    public String getGenerationSignature() {
        return generationSignature;
    }

    public BurstAddress getCreator() {
        return creator;
    }

    public String getGeneratorPublicKey() {
        return generatorPublicKey;
    }

    public long getBaseTarget() {
        return baseTarget;
    }

    public String getPayloadHash() {
        return payloadHash;
    }

    public BurstValue getBlockReward() {
        return BurstValue.fromBurst(blockReward);
    }

    public BurstID getNextBlock() {
        return nextBlock;
    }

    public int getScoopNum() {
        return scoopNum;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public String getBlockSignature() {
        return blockSignature;
    }

    public BurstID[] getTransactions() {
        return transactions;
    }

    public String getNonce() {
        return nonce;
    }

    public int getVersion() {
        return version;
    }

    public BurstValue getTotalFeeNQT() {
        return totalFeeNQT;
    }

    public BurstID getPreviousBlock() {
        return previousBlock;
    }

    public BurstID getBlock() {
        return block;
    }

    public long getHeight() {
        return height;
    }

    public BurstTimestamp getTimestamp() {
        return timestamp;
    }
}
