package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class BidOrderPlacementAttachment extends TransactionAttachment {
    private String asset;
    private String quantityQNT;
    private String priceNQT;

    public BidOrderPlacementAttachment(int version, String asset, String quantityQNT, String priceNQT) {
        super(version);
        this.asset = asset;
        this.quantityQNT = quantityQNT;
        this.priceNQT = priceNQT;
    }
    //TODO constructor for BrsApi

    public String getAsset() {
        return asset;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }

    public String getPriceNQT() {
        return priceNQT;
    }
}
