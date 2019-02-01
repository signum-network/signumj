package burst.kit.entity.response;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class MiningInfoResponse extends BRSResponse {
    private final HexStringByteArray generationSignature;
    private final long baseTarget;
    private final long height;

    public MiningInfoResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, HexStringByteArray generationSignature, long baseTarget, long height) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
    }

    public HexStringByteArray getGenerationSignature() {
        return generationSignature;
    }

    public long getBaseTarget() {
        return baseTarget;
    }

    public long getHeight() {
        return height;
    }
}
