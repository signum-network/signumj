package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public final class AccountsWithRewardRecipientResponse extends BRSResponse {
    private final String[] accounts;

    public AccountsWithRewardRecipientResponse(String[] accounts) {
        this.accounts = accounts;
    }

    public String[] getAccounts() {
        return accounts;
    }
}
