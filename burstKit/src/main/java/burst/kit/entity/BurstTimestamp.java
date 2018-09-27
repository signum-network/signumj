package burst.kit.entity;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class BurstTimestamp { // TODO Convert to Java time types

    public static final JsonDeserializer<BurstTimestamp> DESERIALIZER = (json, typeOfT, context) -> new BurstTimestamp(json.getAsLong());
    public static final JsonSerializer<BurstTimestamp> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getTimestamp());

    private long timestamp;

    public BurstTimestamp(long rawTimestamp) {
        this.timestamp = rawTimestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
