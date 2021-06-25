package burst.kit.entity.response.http.attachment;

import burst.kit.entity.response.TransactionAttachment;
import burst.kit.entity.response.attachment.AssetTransferAttachment;
import com.google.gson.annotations.SerializedName;

public class AssetTransferAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.AssetTransfer")
    private final int version;
    private final String asset;
    private final String quantityQNT;

    public AssetTransferAttachmentResponse(int version, String asset, String quantityQNT) {
        this.version = version;
        this.asset = asset;
        this.quantityQNT = quantityQNT;
    }

    public int getVersion() {
        return version;
    }

    public String getAsset() {
        return asset;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new AssetTransferAttachment(version, asset, quantityQNT);
    }
}
