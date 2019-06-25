package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AccountResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

public class Account {
    private final BurstAddress id;
    private final BurstValue balance;
    private final BurstValue forgedBalance;
    private final BurstValue unconfirmedBalance;
    private final byte[] publicKey;
    private final String description;
    private final String name;

    public Account(BurstAddress id, BurstValue balance, BurstValue forgedBalance, BurstValue unconfirmedBalance, byte[] publicKey, String description, String name) {
        this.id = id;
        this.balance = balance;
        this.forgedBalance = forgedBalance;
        this.unconfirmedBalance = unconfirmedBalance;
        this.publicKey = publicKey;
        this.description = description;
        this.name = name;
    }

    public Account(AccountResponse accountResponse) {
        this.id = BurstAddress.fromEither(accountResponse.getAccount());
        this.balance = BurstValue.fromPlanck(accountResponse.getBalanceNQT());
        this.forgedBalance = BurstValue.fromPlanck(accountResponse.getForgedBalanceNQT());
        this.unconfirmedBalance = BurstValue.fromPlanck(accountResponse.getUnconfirmedBalanceNQT());
        this.publicKey = accountResponse.getPublicKey() == null ? new byte[32] : Hex.decode(accountResponse.getPublicKey());
        this.description = accountResponse.getDescription();
        this.name = accountResponse.getName();
    }

    public Account(BrsApi.Account account) {
        this.id = BurstAddress.fromId(account.getId());
        this.balance = BurstValue.fromPlanck(account.getBalance());
        this.forgedBalance = BurstValue.fromBurst(account.getForgedBalance());
        this.unconfirmedBalance = BurstValue.fromBurst(account.getUnconfirmedBalance());
        this.publicKey = account.getPublicKey().toByteArray();
        this.description = account.getDescription();
        this.name = account.getName();
    }

    public BurstAddress getId() {
        return id;
    }

    public BurstValue getBalance() {
        return balance;
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
