package burst.kit.entity;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.util.Objects;

import brs.util.Convert;

public class BurstID {

    public static final JsonDeserializer<BurstID> DESERIALIZER = (json, typeOfT, context) -> new BurstID(json.getAsString());
    public static final JsonSerializer<BurstID> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getID());

    /**
     * Stored as a signed long (because java) but should be used as an unsigned long
     * using Convert.toUnsignedLong and Convert.parseUnsignedLong (BRS methods)
     */
    private final long id;

    public BurstID(String unsignedLongID) {
        this.id = Convert.parseUnsignedLong(unsignedLongID);
    }

    public BurstID(long signedLongID) {
        this.id = signedLongID;
    }

    public String getID() {
        return Convert.toUnsignedLong(id);
    }

    public long getSignedLongId() {
        return id;
    }

    @Override
    public String toString() {
        return getID();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BurstID && Objects.equals(getID(), ((BurstID) obj).getID());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
