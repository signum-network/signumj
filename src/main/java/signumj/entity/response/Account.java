package signumj.entity.response;

import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.entity.response.http.AccountResponse;

import org.bouncycastle.util.encoders.Hex;

public class Account {
    private final SignumAddress id;
    private final SignumValue balance;
    private final SignumValue commitmentNQT;
    private final SignumValue committedBalanceNQT;
    private final SignumValue forgedBalance;
    private final SignumValue unconfirmedBalance;
    private final byte[] publicKey;
    private final String description;
    private final String name;

    public Account(SignumAddress id, SignumValue balance, SignumValue commitmentNQT, SignumValue committedBalanceNQT, SignumValue forgedBalance, SignumValue unconfirmedBalance, byte[] publicKey, String description, String name) {
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
        this.id = SignumAddress.fromEither(accountResponse.getAccount());
        this.balance = SignumValue.fromNQT(accountResponse.getBalanceNQT());
        this.commitmentNQT = SignumValue.fromNQT(accountResponse.getCommitmentNQT());
        this.committedBalanceNQT = SignumValue.fromNQT(accountResponse.getCommittedBalanceNQT());
        this.forgedBalance = SignumValue.fromNQT(accountResponse.getForgedBalanceNQT());
        this.unconfirmedBalance = SignumValue.fromNQT(accountResponse.getUnconfirmedBalanceNQT());
        this.publicKey = accountResponse.getPublicKey() == null ? new byte[32] : Hex.decode(accountResponse.getPublicKey());
        this.description = accountResponse.getDescription();
        this.name = accountResponse.getName();
    }

    public SignumAddress getId() {
        return id;
    }

    public SignumValue getBalance() {
        return balance;
    }
    
    public SignumValue getCommitment() {
        return commitmentNQT;
    }

    public SignumValue getCommittedBalance() {
        return committedBalanceNQT;
    }

    public SignumValue getForgedBalance() {
        return forgedBalance;
    }

    public SignumValue getUnconfirmedBalance() {
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
