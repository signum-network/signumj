package burst.kit.entity.response.http;

import burst.kit.entity.BurstAddress;

@SuppressWarnings("unused")
public final class AccountsWithRewardRecipientResponse extends BRSResponse {
    private final BurstAddress[] accounts;

    public AccountsWithRewardRecipientResponse(BurstAddress[] accounts) {
        this.accounts = accounts;
    }

    public BurstAddress[] getAccounts() {
        return accounts;
    }
}
