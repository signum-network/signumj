package signumj.entity.response;

import signumj.entity.SignumTimestamp;
import signumj.entity.response.http.MiningInfoResponse;

import org.bouncycastle.util.encoders.Hex;

public class MiningInfo {
    private final byte[] generationSignature;
    private final long baseTarget;
    private final long height;
    private final long averageCommitmentNQT;
    private final SignumTimestamp timestamp;

    public MiningInfo(byte[] generationSignature, long baseTarget, long height, long averageCommitmentNQT, SignumTimestamp timestamp) {
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
        this.timestamp = SignumTimestamp.fromBurstTimestamp(miningInfoResponse.getTimestamp());
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
    
    public SignumTimestamp getTimestamp() {
    	return timestamp;
    }
}
