package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class BlockResponse extends BRSResponse {
    private final String previousBlockHash;
    private final int payloadLength;
    private final String totalAmountNQT;
    private final String generationSignature;
    private final String generator;
    private final String generatorPublicKey;
    private final long baseTarget;
    private final String payloadHash;
    private final String blockReward; // Converted to BurstValue in getter
    private final String nextBlock;
    private final int scoopNum;
    private final int numberOfTransactions;
    private final String blockSignature;
    private final String[] transactions;
    private final String nonce;
    private final int version;
    private final String totalFeeNQT;
    private final String previousBlock;
    private final String block;
    private final int height;
    private final int timestamp;

    public BlockResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String previousBlockHash, int payloadLength, String totalAmountNQT, String generationSignature, String generator, String generatorPublicKey, long baseTarget, String payloadHash, String blockReward, String nextBlock, int scoopNum, int numberOfTransactions, String blockSignature, String[] transactions, String nonce, int version, String totalFeeNQT, String previousBlock, String block, int height, int timestamp) {
        super(errorDescription, errorCode, requestProcessingTime);
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

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public String getTotalAmountNQT() {
        return totalAmountNQT;
    }

    public String getGenerationSignature() {
        return generationSignature;
    }

    public String getGenerator() {
        return generator;
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

    public String getBlockReward() {
        return blockReward;
    }

    public String getNextBlock() {
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

    public String[] getTransactions() {
        return transactions;
    }

    public String getNonce() {
        return nonce;
    }

    public int getVersion() {
        return version;
    }

    public String getTotalFeeNQT() {
        return totalFeeNQT;
    }

    public String getPreviousBlock() {
        return previousBlock;
    }

    public String getBlock() {
        return block;
    }

    public int getHeight() {
        return height;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
