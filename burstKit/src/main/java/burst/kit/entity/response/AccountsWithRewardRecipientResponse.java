package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;

@SuppressWarnings("unused")
public final class AccountsWithRewardRecipientResponse extends BRSResponse {
    private BurstAddress[] accounts;

    private AccountsWithRewardRecipientResponse() {}

    public BurstAddress[] getAccounts() {
        return accounts;
    }
}
