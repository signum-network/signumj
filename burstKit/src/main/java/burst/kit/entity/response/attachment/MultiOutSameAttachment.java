package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import com.google.gson.annotations.SerializedName;

public final class MultiOutSameAttachment extends TransactionAttachment {
    @SerializedName("version.MultiSameOutCreation")
    private int version;
    private BurstAddress[] recipients;

    private MultiOutSameAttachment() {}

    public int getVersion() {
        return version;
    }

    public BurstAddress[] getRecipients() {
        return recipients;
    }
}
