package burst.kit.entity.response.http.attachment;

import burst.kit.util.BurstKitUtils;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionAttachmentAndAppendagesResponse {
    public static final JsonDeserializer<? extends TransactionAttachmentAndAppendagesResponse> DESERIALIZER = (json, typeOfT, context) -> deserialize(json.getAsJsonObject());
    public static final JsonSerializer<? extends TransactionAttachmentAndAppendagesResponse> SERIALIZER = (src, typeOfSrc, context) -> serialize(src);

    private final TransactionAttachmentResponse attachment;
    private final TransactionAppendixResponse[] appendages;

    private TransactionAttachmentAndAppendagesResponse(TransactionAttachmentResponse attachment, TransactionAppendixResponse[] appendages) {
        this.attachment = attachment;
        this.appendages = appendages;
    }

    public TransactionAttachmentResponse getAttachment() {
        return attachment;
    }

    public TransactionAppendixResponse[] getAppendages() {
        return appendages;
    }

    private static TransactionAttachmentAndAppendagesResponse deserialize(JsonObject source) {
        Gson gson = BurstKitUtils.buildGson().create();

        List<TransactionAppendixResponse> appendages = new ArrayList<>();
        TransactionAttachmentResponse attachment = null;

        if (source.has("version.Message")) {
            appendages.add(gson.fromJson(source, MessageAttachmentResponse.class));
        }
        if (source.has("version.EncryptedMessage")) {
            appendages.add(gson.fromJson(source, EncryptedMessageAttachmentResponse.class));
        }
        if (source.has("version.EncryptToSelfMessage")) {
            appendages.add(gson.fromJson(source, EncryptToSelfMessageAttachmentResponse.class));
        }
        if (source.has("version.AccountInfo")) {
            attachment = gson.fromJson(source, AccountInfoAttachmentResponse.class);
        } else if (source.has("version.MultiOutCreation")) {
            attachment = gson.fromJson(source, MultiOutAttachmentResponse.class);
        } else if (source.has("version.MultiSameOutCreation")) {
            attachment = gson.fromJson(source, MultiOutSameAttachmentResponse.class);
        } else if (source.has("version.RewardRecipientAssignment")) {
            attachment = gson.fromJson(source, RewardRecipientAssignmentAttachmentResponse.class);
        } else if (source.has("version.AutomatedTransactionsCreation")) {
            attachment = gson.fromJson(source, ATCreationAttachmentResponse.class);
        }
        if (attachment == null) attachment = new OrdinaryPaymentAttachmentResponse();

        return new TransactionAttachmentAndAppendagesResponse(attachment, appendages.toArray(new TransactionAppendixResponse[0]));
    }

    private static JsonElement serialize(TransactionAttachmentAndAppendagesResponse source) {
        return BurstKitUtils.buildGson().create().toJsonTree(source); // TODO this won't work...
    }
}
