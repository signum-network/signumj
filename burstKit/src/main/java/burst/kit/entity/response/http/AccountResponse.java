package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class AccountResponse extends BRSResponse {
    private final String unconfirmedBalanceNQT;
    private final String guaranteedBalanceNQT;
    private final String effectiveBalanceNQT; // TODO we don't use this...
    private final String name;
    private final String description;
    private final String forgedBalanceNQT;
    private final String balanceNQT;
    private final String publicKey;
    private final String account;

    public AccountResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, String unconfirmedBalanceNQT, String guaranteedBalanceNQT, String effectiveBalanceNQT, String name, String description, String forgedBalanceNQT, String balanceNQT, String publicKey, String account) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.unconfirmedBalanceNQT = unconfirmedBalanceNQT;
        this.guaranteedBalanceNQT = guaranteedBalanceNQT;
        this.effectiveBalanceNQT = effectiveBalanceNQT;
        this.name = name;
        this.description = description;
        this.forgedBalanceNQT = forgedBalanceNQT;
        this.balanceNQT = balanceNQT;
        this.publicKey = publicKey;
        this.account = account;
    }

    public String getUnconfirmedBalanceNQT() {
        return unconfirmedBalanceNQT;
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
}
