package signumj.entity.response.http;

public class RewardRecipientResponse extends BRSResponse {
    private final String rewardRecipient;

    public RewardRecipientResponse(String rewardRecipient) {
        this.rewardRecipient = rewardRecipient;
    }

    public String getRewardRecipient() {
        return rewardRecipient;
    }
}
