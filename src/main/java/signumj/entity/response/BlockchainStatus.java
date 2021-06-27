package signumj.entity.response;

import java.math.BigInteger;

import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.http.BlockchainStatusResponse;

public class BlockchainStatus {
    private final SignumID lastBlock;
	private final String application;
    private final boolean isScanning;
    private final BigInteger cumulativeDifficulty;
    private final SignumValue averageCommitment;
    private final long lastBlockchainFeederHeight;
    private final long numberOfBlocks;
    private final SignumTimestamp time;
    private final String version;
    private final String lastBlockchainFeeder;
    private final SignumTimestamp lastBlockTimestamp;

    public BlockchainStatus(BlockchainStatusResponse blockchainStatusResponse) {
        lastBlock = SignumID.fromLong(blockchainStatusResponse.getLastBlock());
        application = blockchainStatusResponse.getApplication();
        isScanning = blockchainStatusResponse.isScanning();
        cumulativeDifficulty = new BigInteger(blockchainStatusResponse.getCumulativeDifficulty());
        averageCommitment = SignumValue.fromNQT(blockchainStatusResponse.getAverageCommitmentNQT());
        lastBlockchainFeederHeight = blockchainStatusResponse.getLastBlockchainFeederHeight();
        numberOfBlocks = blockchainStatusResponse.getNumberOfBlocks();
        time = SignumTimestamp.fromBurstTimestamp(blockchainStatusResponse.getTime());
        version = blockchainStatusResponse.getVersion();
        lastBlockchainFeeder = blockchainStatusResponse.getLastBlockchainFeeder();
        lastBlockTimestamp = SignumTimestamp.fromBurstTimestamp(blockchainStatusResponse.getLastBlockTimestamp());
    }
    
    public SignumID getLastBlock() {
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

	public SignumValue getAverageCommitment() {
		return averageCommitment;
	}

	public long getLastBlockchainFeederHeight() {
		return lastBlockchainFeederHeight;
	}

	public long getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public SignumTimestamp getTime() {
		return time;
	}

	public String getVersion() {
		return version;
	}

	public String getLastBlockchainFeeder() {
		return lastBlockchainFeeder;
	}

	public SignumTimestamp getLastBlockTimestamp() {
		return lastBlockTimestamp;
	}
}
