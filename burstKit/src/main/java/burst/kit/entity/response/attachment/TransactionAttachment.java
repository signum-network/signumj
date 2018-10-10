package burst.kit.entity.response.attachment;

import burst.kit.util.BurstKitUtils;
import com.google.gson.*;

public abstract class TransactionAttachment {

    public static final JsonDeserializer<? extends TransactionAttachment> DESERIALIZER = (json, typeOfT, context) -> deserialize(json.getAsJsonObject());
    public static final JsonSerializer<? extends TransactionAttachment> SERIALIZER = (src, typeOfSrc, context) -> serialize(src);

    private static TransactionAttachment deserialize(JsonObject source) {
        Gson gson = newGson();

        if (source.has("version.AccountInfo")) {
            return gson.fromJson(source, AccountInfoAttachment.class);
        } else if (source.has("version.MultiOutCreation")) {
            return gson.fromJson(source, MultiOutAttachment.class);
        }

        return null;
    }

    private static JsonElement serialize(TransactionAttachment source) {
        return newGson().toJsonTree(source);
    }

    private static Gson newGson() {
        return BurstKitUtils.buildGson().create();
    }

    public abstract AttachmentType getType();
}
