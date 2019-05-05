package burst.kit.entity.response.http.attachment;

import burst.kit.util.BurstKitUtils;
import com.google.gson.*;

public abstract class TransactionAttachment {

    public static final JsonDeserializer<? extends TransactionAttachment> DESERIALIZER = (json, typeOfT, context) -> deserialize(json.getAsJsonObject());
    public static final JsonSerializer<? extends TransactionAttachment> SERIALIZER = (src, typeOfSrc, context) -> serialize(src);

    private static TransactionAttachment deserialize(JsonObject source) {
        Gson gson = newGson();

        if (source.has("version.Message")) {
            return gson.fromJson(source, MessageAttachmentResponse.class);
        } else if (source.has("version.EncryptedMessage")) {
            return gson.fromJson(source, EncryptedMessageAttachmentResponse.class);
        } else if (source.has("version.EncryptToSelfMessage")) {
            return gson.fromJson(source, EncryptToSelfMessageAttachmentResponse.class);
        } else if (source.has("version.AccountInfo")) {
            return gson.fromJson(source, AccountInfoAttachmentResponse.class);
        } else if (source.has("version.MultiOutCreation")) {
            return gson.fromJson(source, MultiOutAttachmentResponse.class);
        } else if (source.has("version.MultiSameOutCreation")) {
            return gson.fromJson(source, MultiOutSameAttachmentResponse.class);
        } else if (source.has("version.RewardRecipientAssignment")) {
            return gson.fromJson(source, RewardRecipientAssignmentAttachmentResponse.class);
        } else if (source.has("version.AutomatedTransactionsCreation")) {
            return gson.fromJson(source, ATCreationAttachmentResponse.class);
        } else {
            return new UnsupportedAttachmentResponse();
        }
    }

    private static JsonElement serialize(TransactionAttachment source) {
        return newGson().toJsonTree(source);
    }

    private static Gson newGson() {
        return BurstKitUtils.buildGson().create();
    }
}
