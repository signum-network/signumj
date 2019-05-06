package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public class MiningInfoResponse extends BRSResponse {
    private final String generationSignature;
    private final long baseTarget;
    private final long height;

    public MiningInfoResponse(String generationSignature, long baseTarget, long height) {
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
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
}
