package burst.kit.entity.response.attachment;

import burst.kit.entity.response.TransactionAttachment;

public class AssetIssuanceAttachment extends TransactionAttachment {
    private String name;
    private String description;
    private int decimals;
    private String quantityQNT;

    public AssetIssuanceAttachment(int version, String name, String description, int decimals, String quantityQNT) {
        super(version);
        this.name = name;
        this.description = description;
        this.decimals = decimals;
        this.quantityQNT = quantityQNT;
    }
    //TODO constructor for BrsApi
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDecimals() {
        return decimals;
    }

    public String getQuantityQNT() {
        return quantityQNT;
    }
}
