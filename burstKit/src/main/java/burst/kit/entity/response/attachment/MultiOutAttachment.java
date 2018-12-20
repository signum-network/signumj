package burst.kit.entity.response.attachment;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public final class MultiOutAttachment extends TransactionAttachment {

    private MultiOutRecipient[] recipients;
    @SerializedName("version.MultiOutCreation")
    private int version;

    private MultiOutAttachment() {}

    public MultiOutRecipient[] getRecipients() {
        return recipients;
    }

    public int getVersion() {
        return version;
    }

    public static class MultiOutRecipient {
        public static final JsonDeserializer<MultiOutRecipient> DESERIALIZER = (json, typeOfT, context) -> deserialize(json.getAsJsonArray());
        public static final JsonSerializer<MultiOutRecipient> SERIALIZER = (src, typeOfSrc, context) -> serialize(src);

        private static MultiOutRecipient deserialize(JsonArray source) {
            return new MultiOutRecipient(BurstAddress.fromId(new BurstID(source.get(0).getAsString())), BurstValue.fromPlanck(source.get(1).getAsString()));
        }

        private static JsonArray serialize(MultiOutRecipient source) {
            JsonArray array = new JsonArray(2);
            array.add(source.getRecipient().getID());
            array.add(source.getAmount().toPlanck());
            return array;
        }

        private final BurstAddress recipient;
        private final BurstValue amount;

        private MultiOutRecipient(BurstAddress recipient, BurstValue amount) {
            this.recipient = recipient;
            this.amount = amount;
        }

        public BurstAddress getRecipient() {
            return recipient;
        }

        public BurstValue getAmount() {
            return amount;
        }
    }
}
