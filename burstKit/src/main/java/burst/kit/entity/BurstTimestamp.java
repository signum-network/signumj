package burst.kit.entity;

import brs.util.Convert;
import burst.kit.burst.BurstCrypto;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public final class BurstTimestamp {

    public static final JsonDeserializer<BurstTimestamp> DESERIALIZER = (json, typeOfT, context) -> new BurstTimestamp(json.getAsInt());
    public static final JsonSerializer<BurstTimestamp> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getTimestamp());

    private final int timestamp;
    private final Date date;

    public BurstTimestamp(int timestamp) {
        this.timestamp = timestamp;
        this.date = BurstCrypto.getInstance().fromEpochTime(timestamp + 1);
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

    @Override
    public int hashCode() {
        return Integer.hashCode(timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BurstTimestamp && Objects.equals(timestamp, ((BurstTimestamp)obj).timestamp);
    }

    @Override
    public String toString() {
        return String.valueOf(getTimestamp());
    }
}
