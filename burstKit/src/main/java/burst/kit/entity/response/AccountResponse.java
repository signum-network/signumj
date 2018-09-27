package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public class AccountResponse extends BRSResponse {
    private BurstValue unconfirmedBalanceNQT;
    private BurstValue guaranteedBalanceNQT;
    private BurstValue effectiveBalanceNQT;
    private String name;
    private String description;
    private BurstValue forgedBalanceNQT;
    private BurstValue balanceNQT;
    private String publicKey;
    private BurstAddress account;

    private AccountResponse() {}

    public BurstValue getUnconfirmedBalanceNQT() {
        return unconfirmedBalanceNQT;
    }

    public BurstValue getGuaranteedBalanceNQT() {
        return guaranteedBalanceNQT;
    }

    public BurstValue getEffectiveBalanceNQT() {
        return effectiveBalanceNQT;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BurstValue getForgedBalanceNQT() {
        return forgedBalanceNQT;
    }

    public BurstValue getBalanceNQT() {
        return balanceNQT;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public BurstAddress getAccount() {
        return account;
    }
}
