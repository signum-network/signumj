package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import com.google.gson.annotations.SerializedName;

public final class MultiOutSameAttachment extends TransactionAttachment {
    @SerializedName("version.MultiSameOutCreation")
    private final int version;
    private final BurstAddress[] recipients;

    public MultiOutSameAttachment(int version, BurstAddress[] recipients) {
        this.version = version;
        this.recipients = recipients;
    }

    public int getVersion() {
        return version;
    }

    public BurstAddress[] getRecipients() {
        return recipients;
    }
}
