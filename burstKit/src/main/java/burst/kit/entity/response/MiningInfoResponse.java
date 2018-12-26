package burst.kit.entity.response;

import burst.kit.entity.HexStringByteArray;

@SuppressWarnings("unused")
public class MiningInfoResponse extends BRSResponse {
    private HexStringByteArray generationSignature;
    private long baseTarget;
    private long height;

    private MiningInfoResponse() {}

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
