package signumj.response.attachment;

import signumj.entity.response.TransactionAttachment;

public class AskOrderPlacementAttachment extends TransactionAttachment {
    private static final long serialVersionUID = -1756442388893825574L;
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
