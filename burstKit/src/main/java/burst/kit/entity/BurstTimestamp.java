package burst.kit.entity;

import brs.util.Convert;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.Instant;
import java.util.Date;

public class BurstTimestamp {

    public static final JsonDeserializer<BurstTimestamp> DESERIALIZER = (json, typeOfT, context) -> new BurstTimestamp(json.getAsInt());
    public static final JsonSerializer<BurstTimestamp> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getTimestamp());

    private final int timestamp;
    private final Date date;

    public BurstTimestamp(int rawTimestamp) {
        this.timestamp = rawTimestamp;
        this.date = Convert.fromEpochTime(timestamp + 1);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Date getAsDate() {
        return date;
    }

    public Instant getAsInstant() {
        return date.toInstant();
    }
}
