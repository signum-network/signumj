package burst.kit.util;

import burst.kit.entity.*;
import burst.kit.entity.response.http.attachment.MultiOutAttachmentResponse;
import burst.kit.entity.response.http.attachment.TransactionAttachment;
import com.google.gson.GsonBuilder;

@SuppressWarnings("WeakerAccess")
public final class BurstKitUtils {
    public static GsonBuilder buildGson(GsonBuilder builder) {
        return builder
                // Main Burst Entities
                .registerTypeAdapter(BurstAddress.class, BurstAddress.DESERIALIZER)
                .registerTypeAdapter(BurstAddress.class, BurstAddress.SERIALIZER)
                .registerTypeAdapter(HexStringByteArray.class, HexStringByteArray.DESERIALIZER)
                .registerTypeAdapter(HexStringByteArray.class, HexStringByteArray.SERIALIZER)
                .registerTypeAdapter(BurstID.class, BurstID.DESERIALIZER)
                .registerTypeAdapter(BurstID.class, BurstID.SERIALIZER)
                .registerTypeAdapter(BurstTimestamp.class, BurstTimestamp.SERIALIZER)
                .registerTypeAdapter(BurstTimestamp.class, BurstTimestamp.DESERIALIZER)
                .registerTypeAdapter(BurstValue.class, BurstValue.DESERIALIZER)
                .registerTypeAdapter(BurstValue.class, BurstValue.SERIALIZER)

                // Response entities
                .registerTypeAdapter(TransactionAttachment.class, TransactionAttachment.SERIALIZER)
                .registerTypeAdapter(TransactionAttachment.class, TransactionAttachment.DESERIALIZER)
                .registerTypeAdapter(MultiOutAttachmentResponse.MultiOutRecipient.class, MultiOutAttachmentResponse.MultiOutRecipient.SERIALIZER)
                .registerTypeAdapter(MultiOutAttachmentResponse.MultiOutRecipient.class, MultiOutAttachmentResponse.MultiOutRecipient.DESERIALIZER)
                ;
    }

    public static GsonBuilder buildGson() {
        return buildGson(new GsonBuilder());
    }
}
