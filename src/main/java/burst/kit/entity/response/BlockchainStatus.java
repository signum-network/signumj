package burst.kit.entity.response;

import java.math.BigInteger;

import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.BlockchainStatusResponse;

public class BlockchainStatus {
    private final BurstID lastBlock;
	private final String application;
    private final boolean isScanning;
    private final BigInteger cumulativeDifficulty;
    private final BurstValue averageCommitment;
    private final long lastBlockchainFeederHeight;
    private final long numberOfBlocks;
    private final BurstTimestamp time;
    private final String version;
    private final String lastBlockchainFeeder;
    private final BurstTimestamp lastBlockTimestamp;

    public BlockchainStatus(BlockchainStatusResponse blockchainStatusResponse) {
        lastBlock = BurstID.fromLong(blockchainStatusResponse.getLastBlock());
        application = blockchainStatusResponse.getApplication();
        isScanning = blockchainStatusResponse.isScanning();
        cumulativeDifficulty = new BigInteger(blockchainStatusResponse.getCumulativeDifficulty());
        averageCommitment = BurstValue.fromPlanck(blockchainStatusResponse.getAverageCommitmentNQT());
        lastBlockchainFeederHeight = blockchainStatusResponse.getLastBlockchainFeederHeight();
        numberOfBlocks = blockchainStatusResponse.getNumberOfBlocks();
        time = BurstTimestamp.fromBurstTimestamp(blockchainStatusResponse.getTime());
        version = blockchainStatusResponse.getVersion();
        lastBlockchainFeeder = blockchainStatusResponse.getLastBlockchainFeeder();
        lastBlockTimestamp = BurstTimestamp.fromBurstTimestamp(blockchainStatusResponse.getLastBlockTimestamp());
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

	public BurstValue getAverageCommitment() {
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
