package burst.kit.entity.response.http;

import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;

import java.math.BigInteger;

@SuppressWarnings("unused")
public final class BlockchainStatusResponse extends BRSResponse {
    private final BurstID lastBlock;
    private final String application;
    private final boolean isScanning;
    private final BigInteger cumulativeDifficulty;
    private final long lastBlockchainFeederHeight;
    private final long numberOfBlocks;
    private final BurstTimestamp time;
    private final String version;
    private final String lastBlockchainFeeder;

    public BlockchainStatusResponse(BurstID lastBlock, String application, boolean isScanning, BigInteger cumulativeDifficulty, long lastBlockchainFeederHeight, long numberOfBlocks, BurstTimestamp time, String version, String lastBlockchainFeeder) {
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

    public BurstID getLastBlock() {
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

    public BurstTimestamp getTime() {
        return time;
    }

    public String getVersion() {
        return version;
    }

    public String getLastBlockchainFeeder() {
        return lastBlockchainFeeder;
    }
}
