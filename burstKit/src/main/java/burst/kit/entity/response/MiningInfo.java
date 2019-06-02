package burst.kit.entity.response;

import burst.kit.entity.response.http.MiningInfoResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

public class MiningInfo {
    private final byte[] generationSignature;
    private final long baseTarget;
    private final long height;

    public MiningInfo(byte[] generationSignature, long baseTarget, long height) {
        this.generationSignature = generationSignature;
        this.baseTarget = baseTarget;
        this.height = height;
    }

    public MiningInfo(MiningInfoResponse miningInfoResponse) {
        this.generationSignature = Hex.decode(miningInfoResponse.getGenerationSignature());
        this.baseTarget = miningInfoResponse.getBaseTarget();
        this.height = miningInfoResponse.getHeight();
    }

    public MiningInfo(BrsApi.MiningInfo miningInfo) {
        this.generationSignature = miningInfo.getGenerationSignature().toByteArray();
        this.baseTarget = miningInfo.getBaseTarget();
        this.height = miningInfo.getHeight();
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
}
