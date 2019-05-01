package burst.kit.entity.response.http.attachment;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("WeakerAccess")
public final class RewardRecipientAssignmentAttachment extends TransactionAttachment {
    @SerializedName("version.RewardRecipientAssignment")
    private final int version;

    public RewardRecipientAssignmentAttachment(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
