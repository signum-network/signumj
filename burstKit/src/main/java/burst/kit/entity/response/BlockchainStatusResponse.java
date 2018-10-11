package burst.kit.entity.response;

import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;

import java.math.BigInteger;

@SuppressWarnings("unused")
public final class BlockchainStatusResponse extends BRSResponse {
    private BurstID lastBlock;
    private String application;
    private boolean isScanning;
    private BigInteger cumulativeDifficulty;
    private long lastBlockchainFeederHeight;
    private long numberOfBlocks;
    private BurstTimestamp time;
    private String version;
    private String lastBlockchainFeeder;

    private BlockchainStatusResponse() {}

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
