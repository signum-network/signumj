package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public class MiningInfoResponse extends BRSResponse {
    private final String generationSignature;
    private final long baseTarget;
    private final long height;
    private final long averageCommitment;

    public MiningInfoResponse(String generationSignature, long baseTarget, long height, long averageCommitment) {
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
        this.averageCommitment = averageCommitment;
    }

    public String getGenerationSignature() {
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
