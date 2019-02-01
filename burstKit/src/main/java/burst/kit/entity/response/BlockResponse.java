package burst.kit.entity.response;

import burst.kit.entity.*;

@SuppressWarnings("unused")
public final class BlockResponse extends BRSResponse {
    private final HexStringByteArray previousBlockHash;
    private final int payloadLength;
    private final BurstValue totalAmountNQT;
    private final HexStringByteArray generationSignature;
    private final BurstAddress generator;
    private final HexStringByteArray generatorPublicKey;
    private final long baseTarget;
    private final HexStringByteArray payloadHash;
    private final String blockReward; // Converted to BurstValue in getter
    private final BurstID nextBlock;
    private final int scoopNum;
    private final int numberOfTransactions;
    private final HexStringByteArray blockSignature;
    private final BurstID[] transactions;
    private final String nonce;
    private final int version;
    private final BurstValue totalFeeNQT;
    private final BurstID previousBlock;
    private final BurstID block;
    private final long height;
    private final BurstTimestamp timestamp;

    public BlockResponse(HexStringByteArray previousBlockHash, int payloadLength, BurstValue totalAmountNQT, HexStringByteArray generationSignature, BurstAddress generator, HexStringByteArray generatorPublicKey, long baseTarget, HexStringByteArray payloadHash, String blockReward, BurstID nextBlock, int scoopNum, int numberOfTransactions, HexStringByteArray blockSignature, BurstID[] transactions, String nonce, int version, BurstValue totalFeeNQT, BurstID previousBlock, BurstID block, long height, BurstTimestamp timestamp) {
        this.previousBlockHash = previousBlockHash;
        this.payloadLength = payloadLength;
        this.totalAmountNQT = totalAmountNQT;
        this.generationSignature = generationSignature;
        this.generator = generator;
        this.generatorPublicKey = generatorPublicKey;
        this.baseTarget = baseTarget;
        this.payloadHash = payloadHash;
        this.blockReward = blockReward;
        this.nextBlock = nextBlock;
        this.scoopNum = scoopNum;
        this.numberOfTransactions = numberOfTransactions;
        this.blockSignature = blockSignature;
        this.transactions = transactions;
        this.nonce = nonce;
        this.version = version;
        this.totalFeeNQT = totalFeeNQT;
        this.previousBlock = previousBlock;
        this.block = block;
        this.height = height;
        this.timestamp = timestamp;
    }

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
