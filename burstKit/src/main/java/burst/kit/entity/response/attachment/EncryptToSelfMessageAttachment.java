package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstEncryptedMessage;
import com.google.gson.annotations.SerializedName;

public class EncryptToSelfMessageAttachment extends TransactionAttachment {
    private BurstEncryptedMessage encryptToSelfMessage;
    @SerializedName("version.EncryptToSelfMessage")
    private int version;

    private EncryptToSelfMessageAttachment() {}

    public int getVersion() {
        return version;
    }

    public BurstEncryptedMessage getEncryptToSelfMessage() {
        return encryptToSelfMessage;
    }
}
