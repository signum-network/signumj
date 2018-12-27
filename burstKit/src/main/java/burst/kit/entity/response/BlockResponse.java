package burst.kit.entity.response;

import burst.kit.entity.*;

@SuppressWarnings("unused")
public final class BlockResponse extends BRSResponse {
    private HexStringByteArray previousBlockHash;
    private int payloadLength;
    private BurstValue totalAmountNQT;
    private HexStringByteArray generationSignature;
    private BurstAddress generator;
    private HexStringByteArray generatorPublicKey;
    private long baseTarget;
    private HexStringByteArray payloadHash;
    private String blockReward; // Converted to BurstValue in getter
    private BurstID nextBlock;
    private int scoopNum;
    private int numberOfTransactions;
    private HexStringByteArray blockSignature;
    private BurstID[] transactions;
    private String nonce;
    private int version;
    private BurstValue totalFeeNQT;
    private BurstID previousBlock;
    private BurstID block;
    private long height;
    private BurstTimestamp timestamp;

    private BlockResponse() {}

    public HexStringByteArray getPreviousBlockHash() {
        return previousBlockHash;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public BurstValue getTotalAmountNQT() {
        return totalAmountNQT;
    }

    public HexStringByteArray getGenerationSignature() {
        return generationSignature;
    }

    public BurstAddress getGenerator() {
        return generator;
    }

    public HexStringByteArray getGeneratorPublicKey() {
        return generatorPublicKey;
    }

    public long getBaseTarget() {
        return baseTarget;
    }

    public HexStringByteArray getPayloadHash() {
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

    public HexStringByteArray getBlockSignature() {
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
