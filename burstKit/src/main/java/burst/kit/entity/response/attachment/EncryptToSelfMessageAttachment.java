package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstEncryptedMessage;
import com.google.gson.annotations.SerializedName;

public class EncryptToSelfMessageAttachment extends TransactionAttachment {
    private final BurstEncryptedMessage encryptToSelfMessage;
    @SerializedName("version.EncryptToSelfMessage")
    private final int version;

    public EncryptToSelfMessageAttachment(BurstEncryptedMessage encryptToSelfMessage, int version) {
        this.encryptToSelfMessage = encryptToSelfMessage;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public BurstEncryptedMessage getEncryptToSelfMessage() {
        return encryptToSelfMessage;
    }
}
