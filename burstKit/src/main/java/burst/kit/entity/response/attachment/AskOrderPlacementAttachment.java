package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;

public class AskOrderPlacementAttachment extends TransactionAttachment {
    private String asset;
    private String quantityQNT;
    private String priceNQT;

    public AskOrderPlacementAttachment(int version, String asset, String quantityQNT, String priceNQT) {
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
