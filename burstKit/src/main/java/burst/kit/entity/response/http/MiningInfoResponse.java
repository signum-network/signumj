package burst.kit.entity.response.http;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class MiningInfoResponse extends BRSResponse {
    private final HexStringByteArray generationSignature;
    private final long baseTarget;
    private final long height;

    public MiningInfoResponse(HexStringByteArray generationSignature, long baseTarget, long height) {
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
