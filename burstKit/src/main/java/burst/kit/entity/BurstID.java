package burst.kit.entity;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.math.BigInteger;
import java.util.Objects;

public final class BurstID {

    public static final JsonDeserializer<BurstID> DESERIALIZER = (json, typeOfT, context) -> new BurstID(json.getAsString());
    public static final JsonSerializer<BurstID> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getID());

    private static final BigInteger two64 = new BigInteger("18446744073709551616");

    /**
     * Stored as a signed long (because java) but should be used as an unsigned long
     * using Convert.toUnsignedLong and Convert.parseUnsignedLong (BRS methods)
     */
    private final long id;

    public BurstID(String unsignedLongID) {
        this.id = parseUnsignedLong(unsignedLongID);
    }

    public BurstID(long signedLongID) {
        this.id = signedLongID;
    }

    public String getID() {
        return toUnsignedLong(id);
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

    private String toUnsignedLong(long objectId) {
        if (objectId >= 0) {
            return String.valueOf(objectId);
        }
        BigInteger id = BigInteger.valueOf(objectId).add(two64);
        return id.toString();
    }

    private long parseUnsignedLong(String number) {
        if (number == null) {
            return 0;
        }
        BigInteger bigInt = new BigInteger(number.trim());
        if (bigInt.signum() < 0 || bigInt.compareTo(two64) > -1) {
            throw new NumberFormatException("overflow: " + number);
        }
        return bigInt.longValue();
    }
}
