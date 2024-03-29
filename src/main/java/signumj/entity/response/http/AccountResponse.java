package signumj.entity.response.http;

public final class AccountResponse extends BRSResponse {
    private final String unconfirmedBalanceNQT;
    private final String guaranteedBalanceNQT;
    private final String effectiveBalanceNQT; // TODO we don't use this...
    private final String commitmentNQT;
    private final String committedBalanceNQT;
    private final String name;
    private final String description;
    private final String forgedBalanceNQT;
    private final String balanceNQT;
    private final String publicKey;
    private final String account;
    private final AssetBalanceResponse[] assetBalances;
    private final AssetBalanceResponse[] unconfirmedAssetBalances;
    
    public static final class AssetBalanceResponse {
    	private final String asset;
        private final String balanceQNT;

        public AssetBalanceResponse(String asset, String balanceQNT) {
            this.asset = asset;
            this.balanceQNT = balanceQNT;
        }

        public String getAsset() {
            return asset;
        }

        public String getBalanceQNT() {
            return balanceQNT;
        }
    }


    public AccountResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String unconfirmedBalanceNQT, String guaranteedBalanceNQT, String effectiveBalanceNQT, String name, String description, String forgedBalanceNQT, String balanceNQT, String commitmentNQT, String committedBalanceNQT, String publicKey, String account, AssetBalanceResponse[] assetBalances, AssetBalanceResponse[] unconfirmedAssetBalances) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.unconfirmedBalanceNQT = unconfirmedBalanceNQT;
        this.guaranteedBalanceNQT = guaranteedBalanceNQT;
        this.effectiveBalanceNQT = effectiveBalanceNQT;
        this.name = name;
        this.description = description;
        this.forgedBalanceNQT = forgedBalanceNQT;
        this.balanceNQT = balanceNQT;
        this.commitmentNQT = commitmentNQT;
        this.committedBalanceNQT = committedBalanceNQT;
        this.publicKey = publicKey;
        this.account = account;
        this.assetBalances = assetBalances;
        this.unconfirmedAssetBalances = unconfirmedAssetBalances;
    }

    public String getUnconfirmedBalanceNQT() {
        return unconfirmedBalanceNQT;
    }
    
    public String getCommitmentNQT() {
        return commitmentNQT;
    }

    public String getCommittedBalanceNQT() {
        return committedBalanceNQT;
    }

    public String getGuaranteedBalanceNQT() {
        return guaranteedBalanceNQT;
    }

    public String getEffectiveBalanceNQT() {
        return effectiveBalanceNQT;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getForgedBalanceNQT() {
        return forgedBalanceNQT;
    }

    public String getBalanceNQT() {
        return balanceNQT;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getAccount() {
        return account;
    }
    
    public AssetBalanceResponse[] getAssetBalances() {
    	return assetBalances;
    }
    
    public AssetBalanceResponse[] getUnconfirmedAssetBalances() {
    	return unconfirmedAssetBalances;
    }
}
