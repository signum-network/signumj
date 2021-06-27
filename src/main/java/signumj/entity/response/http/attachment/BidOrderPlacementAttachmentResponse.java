package signumj.entity.response.http.attachment;

import signumj.entity.response.TransactionAttachment;
import signumj.response.attachment.BidOrderPlacementAttachment;

import com.google.gson.annotations.SerializedName;

public class BidOrderPlacementAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.BidOrderPlacement")
    private final int version;
    private final String asset;
    private final String quantityQNT;
    private final String priceNQT;

    public BidOrderPlacementAttachmentResponse(int version, String asset, String quantityQNT, String priceNQT) {
        this.version = version;
        this.asset = asset;
        this.quantityQNT = quantityQNT;
        this.priceNQT = priceNQT;
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

    public String getPriceNQT() {
        return priceNQT;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new BidOrderPlacementAttachment(version, asset, quantityQNT, priceNQT);
    }
}
