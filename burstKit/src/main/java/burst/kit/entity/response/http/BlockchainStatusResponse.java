package burst.kit.entity.response.http;

import java.math.BigInteger;

@SuppressWarnings("unused")
public final class BlockchainStatusResponse extends BRSResponse {
    private final String lastBlock;
    private final String application;
    private final boolean isScanning;
    private final BigInteger cumulativeDifficulty;
    private final long lastBlockchainFeederHeight;
    private final long numberOfBlocks;
    private final int time;
    private final String version;
    private final String lastBlockchainFeeder;

    public BlockchainStatusResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String lastBlock, String application, boolean isScanning, BigInteger cumulativeDifficulty, long lastBlockchainFeederHeight, long numberOfBlocks, int time, String version, String lastBlockchainFeeder) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.lastBlock = lastBlock;
        this.application = application;
        this.isScanning = isScanning;
        this.cumulativeDifficulty = cumulativeDifficulty;
        this.lastBlockchainFeederHeight = lastBlockchainFeederHeight;
        this.numberOfBlocks = numberOfBlocks;
        this.time = time;
        this.version = version;
        this.lastBlockchainFeeder = lastBlockchainFeeder;
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

    public BigInteger getCumulativeDifficulty() {
        return cumulativeDifficulty;
    }

    public long getLastBlockchainFeederHeight() {
        return lastBlockchainFeederHeight;
    }

    public long getNumberOfBlocks() {
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
}
