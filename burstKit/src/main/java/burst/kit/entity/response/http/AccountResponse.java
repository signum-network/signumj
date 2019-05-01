package burst.kit.entity.response.http;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.HexStringByteArray;
import burst.kit.entity.BurstValue;

@SuppressWarnings("unused")
public final class AccountResponse extends BRSResponse {
    private final BurstValue unconfirmedBalanceNQT;
    private final BurstValue guaranteedBalanceNQT;
    private final BurstValue effectiveBalanceNQT;
    private final String name;
    private final String description;
    private final BurstValue forgedBalanceNQT;
    private final BurstValue balanceNQT;
    private final HexStringByteArray publicKey;
    private final BurstAddress account;

    public AccountResponse(BurstValue unconfirmedBalanceNQT, BurstValue guaranteedBalanceNQT, BurstValue effectiveBalanceNQT, String name, String description, BurstValue forgedBalanceNQT, BurstValue balanceNQT, HexStringByteArray publicKey, BurstAddress account) {
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

    public HexStringByteArray getPublicKey() {
        return publicKey;
    }

    public BurstAddress getAccount() {
        return account;
    }
}
