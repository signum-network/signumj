package burst.kit.entity.response.attachment;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("WeakerAccess")
public final class RewardRecipientAssignmentAttachment extends TransactionAttachment {
    @SerializedName("version.RewardRecipientAssignment")
    private int version;

    private RewardRecipientAssignmentAttachment() {}

    public int getVersion() {
        return version;
    }
}
