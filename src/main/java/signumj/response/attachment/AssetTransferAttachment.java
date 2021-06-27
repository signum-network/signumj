package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class AssetTransferAttachment extends TransactionAttachment {
    private String asset;
    private String quantityQNT;

    public AssetTransferAttachment(int version, String asset, String quantityQNT) {
        super(version);
        this.asset = asset;
        this.quantityQNT = quantityQNT;
    }

    //TODO constructor for BrsApi
    public String getAsset() {
        return asset;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }
}
