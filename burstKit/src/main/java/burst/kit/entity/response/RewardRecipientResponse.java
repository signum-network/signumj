package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;

@SuppressWarnings("unused")
public class RewardRecipientResponse extends BRSResponse {
    private BurstAddress rewardRecipient;

    private RewardRecipientResponse() {}

    public BurstAddress getRewardRecipient() {
        return rewardRecipient;
    }
}
