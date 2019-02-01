package burst.kit.entity.response;

import burst.kit.entity.BurstAddress;

@SuppressWarnings("unused")
public class RewardRecipientResponse extends BRSResponse {
    private final BurstAddress rewardRecipient;

    public RewardRecipientResponse(String errorDescription, Integer errorCode, Integer requestProcessingTime, BurstAddress rewardRecipient) {
        super(errorDescription, errorCode, requestProcessingTime);
        this.rewardRecipient = rewardRecipient;
    }

    public BurstAddress getRewardRecipient() {
        return rewardRecipient;
    }
}
