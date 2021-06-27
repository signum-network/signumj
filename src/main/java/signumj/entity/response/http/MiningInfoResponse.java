package signumj.entity.response.http;

public class MiningInfoResponse extends BRSResponse {
    private final String generationSignature;
    private final long baseTarget;
    private final long height;
    private final long averageCommitmentNQT;
    private final int timestamp;

    public MiningInfoResponse(String generationSignature, long baseTarget, long height, long averageCommitmentNQT, int timestamp) {
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
        this.averageCommitmentNQT = averageCommitmentNQT;
        this.timestamp = timestamp;
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
    
    public long getAverageCommitmentNQT() {
    	return averageCommitmentNQT;
    }
    
    public int getTimestamp() {
    	return timestamp;
    }
}
