package burst.kit.entity.response;

import java.math.BigInteger;

import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.response.http.BlockchainStatusResponse;

public class BlockchainStatus {
    private final int lastBlock;
	private final String application;
    private final boolean isScanning;
    private final BigInteger cumulativeDifficulty;
    private final BigInteger averageCommitment;
    private final long lastBlockchainFeederHeight;
    private final long numberOfBlocks;
    private final BurstTimestamp time;
    private final String version;
    private final String lastBlockchainFeeder;
    private final BurstTimestamp lastBlockTimestamp;

    public BlockchainStatus(BlockchainStatusResponse blockchainStatusResponse) {
        lastBlock = blockchainStatusResponse.getLastBlock();
        application = blockchainStatusResponse.getApplication();
        isScanning = blockchainStatusResponse.isScanning();
        cumulativeDifficulty = new BigInteger(blockchainStatusResponse.getCumulativeDifficulty());
        averageCommitment = new BigInteger(blockchainStatusResponse.getAverageCommitment());
        lastBlockchainFeederHeight = blockchainStatusResponse.getLastBlockchainFeederHeight();
        numberOfBlocks = blockchainStatusResponse.getNumberOfBlocks();
        time = BurstTimestamp.fromBurstTimestamp(blockchainStatusResponse.getTime());
        version = blockchainStatusResponse.getVersion();
        lastBlockchainFeeder = blockchainStatusResponse.getLastBlockchainFeeder();
        lastBlockTimestamp = BurstTimestamp.fromBurstTimestamp(blockchainStatusResponse.getLastBlockTimestamp());
    }
    
    public int getLastBlock() {
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

	public BigInteger getAverageCommitment() {
		return averageCommitment;
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

	public BurstTimestamp getLastBlockTimestamp() {
		return lastBlockTimestamp;
	}
}
