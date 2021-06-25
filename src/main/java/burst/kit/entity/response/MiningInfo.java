package burst.kit.entity.response;

import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.response.http.MiningInfoResponse;
import org.bouncycastle.util.encoders.Hex;

public class MiningInfo {
    private final byte[] generationSignature;
    private final long baseTarget;
    private final long height;
    private final long averageCommitmentNQT;
    private final BurstTimestamp timestamp;

    public MiningInfo(byte[] generationSignature, long baseTarget, long height, long averageCommitmentNQT, BurstTimestamp timestamp) {
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
        this.averageCommitmentNQT = averageCommitmentNQT;
        this.timestamp = timestamp;
    }

    public MiningInfo(MiningInfoResponse miningInfoResponse) {
        this.generationSignature = Hex.decode(miningInfoResponse.getGenerationSignature());
        this.baseTarget = miningInfoResponse.getBaseTarget();
        this.height = miningInfoResponse.getHeight();
        this.averageCommitmentNQT = miningInfoResponse.getAverageCommitmentNQT();
        this.timestamp = BurstTimestamp.fromBurstTimestamp(miningInfoResponse.getTimestamp());
    }

    public byte[] getGenerationSignature() {
        return generationSignature;
    }

    public long getBaseTarget() {
        return baseTarget;
    }

    public long getHeight() {
        return height;
    }
    
    public long getAverageCommitmentNQT() {
    	return averageCommitmentNQT;
    }
    
    public BurstTimestamp getTimestamp() {
    	return timestamp;
    }
}
