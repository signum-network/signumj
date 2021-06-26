package burst.kit.entity.response.http;

public final class BlockchainStatusResponse extends BRSResponse {
    private final String lastBlock;
    private final String application;
    private final boolean isScanning;
    private final String cumulativeDifficulty;
    private final String averageCommitmentNQT;
    private final int lastBlockchainFeederHeight;
    private final int numberOfBlocks;
    private final int time;
    private final String version;
    private final String lastBlockchainFeeder;
    private final int lastBlockTimestamp;

    public BlockchainStatusResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String lastBlock, String application, boolean isScanning, String cumulativeDifficulty, int lastBlockchainFeederHeight, int numberOfBlocks, int time, String version, String lastBlockchainFeeder, String averageCommitmentNQT, int lastBlockTimestamp) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.lastBlock = lastBlock;
        this.application = application;
        this.isScanning = isScanning;
        this.cumulativeDifficulty = cumulativeDifficulty;
        this.averageCommitmentNQT = averageCommitmentNQT;
        this.lastBlockchainFeederHeight = lastBlockchainFeederHeight;
        this.numberOfBlocks = numberOfBlocks;
        this.time = time;
        this.version = version;
        this.lastBlockchainFeeder = lastBlockchainFeeder;
        this.lastBlockTimestamp = lastBlockTimestamp;
    }

    public String getLastBlock() {
        return lastBlock;
    }

    public String getApplication() {
        return application;
    }

    public boolean isScanning() {
        return isScanning;
    }

    public String getCumulativeDifficulty() {
        return cumulativeDifficulty;
    }

    public int getLastBlockchainFeederHeight() {
        return lastBlockchainFeederHeight;
    }

    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public int getTime() {
        return time;
    }

    public String getVersion() {
        return version;
    }

    public String getLastBlockchainFeeder() {
        return lastBlockchainFeeder;
    }
    
    public int getLastBlockTimestamp() {
    	return lastBlockTimestamp;
    }
    
    public String getAverageCommitmentNQT() {
    	return averageCommitmentNQT;
    }
}
