package burst.kit.entity.response;

import burst.kit.entity.response.http.MiningInfoResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

public class MiningInfo {
    private final byte[] generationSignature;
    private final long baseTarget;
    private final long height;
    private final long averageCommitment;

    public MiningInfo(byte[] generationSignature, long baseTarget, long height, long averageCommitment) {
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
        this.averageCommitment = averageCommitment;
    }

    public MiningInfo(MiningInfoResponse miningInfoResponse) {
        this.generationSignature = Hex.decode(miningInfoResponse.getGenerationSignature());
        this.baseTarget = miningInfoResponse.getBaseTarget();
        this.height = miningInfoResponse.getHeight();
        this.averageCommitment = miningInfoResponse.getAverageCommitment();
    }

    public MiningInfo(BrsApi.MiningInfo miningInfo) {
        this.generationSignature = miningInfo.getGenerationSignature().toByteArray();
        this.baseTarget = miningInfo.getBaseTarget();
        this.height = miningInfo.getHeight();
        this.averageCommitment = miningInfo.getAverageCommitment();
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
    
    public long getAverageCommitment() {
    	return averageCommitment;
    }
}
