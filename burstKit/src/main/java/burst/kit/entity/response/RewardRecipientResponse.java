package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;

@SuppressWarnings("unused")
public class RewardRecipientResponse extends BRSResponse {
    private final BurstAddress rewardRecipient;

    public RewardRecipientResponse(BurstAddress rewardRecipient) {
        this.rewardRecipient = rewardRecipient;
    }

    public BurstAddress getRewardRecipient() {
        return rewardRecipient;
    }
}
