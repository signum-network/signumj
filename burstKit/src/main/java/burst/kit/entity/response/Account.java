package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AccountResponse;

public class Account {
    private final BurstAddress id;
    private final BurstValue balance;
    private final BurstValue effectiveBalance;
    private final BurstValue forgedBalance;
    private final BurstValue unconfirmedBalance;
    private final byte[] publicKey;
    private final String description;
    private final String name;

    public Account(BurstAddress id, BurstValue balance, BurstValue effectiveBalance, BurstValue forgedBalance, BurstValue unconfirmedBalance, byte[] publicKey, String description, String name) {
        this.id = id;
        this.balance = balance;
        this.effectiveBalance = effectiveBalance;
        this.forgedBalance = forgedBalance;
        this.unconfirmedBalance = unconfirmedBalance;
        this.publicKey = publicKey;
        this.description = description;
        this.name = name;
    }

    public Account(AccountResponse accountResponse) {
        this.id = accountResponse.getAccount();
        this.balance = accountResponse.getBalanceNQT();
        this.effectiveBalance = accountResponse.getEffectiveBalanceNQT();
        this.forgedBalance = accountResponse.getForgedBalanceNQT();
        this.unconfirmedBalance = accountResponse.getUnconfirmedBalanceNQT();
        this.publicKey = accountResponse.getPublicKey().getBytes();
        this.description = accountResponse.getDescription();
        this.name = accountResponse.getName();
    }

    public BurstAddress getId() {
        return id;
    }

    public BurstValue getBalance() {
        return balance;
    }

    public BurstValue getEffectiveBalance() {
        return effectiveBalance;
    }

    public BurstValue getForgedBalance() {
        return forgedBalance;
    }

    public BurstValue getUnconfirmedBalance() {
        return unconfirmedBalance;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
