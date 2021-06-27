package signumj.entity.response.http.attachment;

import signumj.entity.SignumAddress;
import signumj.entity.response.TransactionAttachment;
import signumj.response.attachment.MultiOutSameAttachment;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public final class MultiOutSameAttachmentResponse extends TransactionAttachmentResponse {
    @SerializedName("version.MultiSameOutCreation")
    private final int version;
    private final String[] recipients;

    public MultiOutSameAttachmentResponse(int version, String[] recipients) {
        this.version = version;
        this.recipients = recipients;
    }

    public int getVersion() {
        return version;
    }

    public String[] getRecipients() {
        return recipients;
    }

    @Override
    public TransactionAttachment toAttachment() {
        return new MultiOutSameAttachment(version, Arrays.stream(recipients)
                .map(SignumAddress::fromId)
                .toArray(SignumAddress[]::new));
    }
}
