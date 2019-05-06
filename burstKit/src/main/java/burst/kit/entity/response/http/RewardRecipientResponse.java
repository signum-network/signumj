package burst.kit.entity.response.http;

@SuppressWarnings("unused")
public class RewardRecipientResponse extends BRSResponse {
    private final String rewardRecipient;

    public RewardRecipientResponse(String rewardRecipient) {
        this.rewardRecipient = rewardRecipient;
    }

    public String getRewardRecipient() {
        return rewardRecipient;
    }
}
