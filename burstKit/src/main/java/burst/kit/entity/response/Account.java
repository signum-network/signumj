package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.http.AccountResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

public class Account {
    private final BurstAddress id;
    private final BurstValue balance;
    private final BurstValue commitmentNQT;
    private final BurstValue committedBalanceNQT;
    private final BurstValue forgedBalance;
    private final BurstValue unconfirmedBalance;
    private final byte[] publicKey;
    private final String description;
    private final String name;

    public Account(BurstAddress id, BurstValue balance, BurstValue commitmentNQT, BurstValue committedBalanceNQT, BurstValue forgedBalance, BurstValue unconfirmedBalance, byte[] publicKey, String description, String name) {
        this.id = id;
        this.balance = balance;
        this.commitmentNQT = commitmentNQT;
        this.committedBalanceNQT = committedBalanceNQT;
        this.forgedBalance = forgedBalance;
        this.unconfirmedBalance = unconfirmedBalance;
        this.publicKey = publicKey;
        this.description = description;
        this.name = name;
    }

    public Account(AccountResponse accountResponse) {
        this.id = BurstAddress.fromEither(accountResponse.getAccount());
        this.balance = BurstValue.fromPlanck(accountResponse.getBalanceNQT());
        this.commitmentNQT = BurstValue.fromPlanck(accountResponse.getCommitmentNQT());
        this.committedBalanceNQT = BurstValue.fromPlanck(accountResponse.getCommittedBalanceNQT());
        this.forgedBalance = BurstValue.fromPlanck(accountResponse.getForgedBalanceNQT());
        this.unconfirmedBalance = BurstValue.fromPlanck(accountResponse.getUnconfirmedBalanceNQT());
        this.publicKey = accountResponse.getPublicKey() == null ? new byte[32] : Hex.decode(accountResponse.getPublicKey());
        this.description = accountResponse.getDescription();
        this.name = accountResponse.getName();
    }

    public Account(BrsApi.Account account) {
        this.id = BurstAddress.fromId(account.getId());
        this.balance = BurstValue.fromPlanck(account.getBalance());
        this.commitmentNQT = BurstValue.fromPlanck(account.getCommitmentNQT());
        this.committedBalanceNQT = BurstValue.fromPlanck(account.getCommittedBalanceNQT());
        this.forgedBalance = BurstValue.fromPlanck(account.getForgedBalance());
        this.unconfirmedBalance = BurstValue.fromPlanck(account.getUnconfirmedBalance());
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
    
    public BurstValue getCommitment() {
        return commitmentNQT;
    }

    public BurstValue getCommittedBalance() {
        return committedBalanceNQT;
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
