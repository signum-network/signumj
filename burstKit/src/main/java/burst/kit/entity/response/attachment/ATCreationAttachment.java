package burst.kit.entity.response.attachment;

import burst.kit.entity.HexStringByteArray;
import com.google.gson.annotations.SerializedName;

public class ATCreationAttachment extends TransactionAttachment {
    private String name;
    private String description;
    private HexStringByteArray creationBytes;
    @SerializedName("version.AutomatedTransactionsCreation")
    private int version;

    private ATCreationAttachment() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HexStringByteArray getCreationBytes() {
        return creationBytes;
    }

    public int getVersion() {
        return version;
    }
}
