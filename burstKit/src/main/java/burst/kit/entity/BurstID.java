package burst.kit.entity;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.math.BigInteger;
import java.util.Objects;

public final class BurstID {

    /**
     * GSON Serializer.
     */
    public static final JsonSerializer<BurstID> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getID());

    /**
     * GSON Deserializer.
     */
    public static final JsonDeserializer<BurstID> DESERIALIZER = (json, typeOfT, context) -> new BurstID(json.getAsString());

    /**
     * Used by unsigned long translation.
     */
    private static final BigInteger two64 = new BigInteger("18446744073709551616");

    /**
     * Stored as a signed long (because java) but should be used as an unsigned long
     * using Convert.toUnsignedLong and Convert.parseUnsignedLong (BRS methods)
     */
    private final long id;

    /**
     * @param unsignedLongID The numeric ID of the transaction, account, block, etc.
     */
    public BurstID(String unsignedLongID) {
        this.id = parseUnsignedLong(unsignedLongID);
    }

    /**
     * @param signedLongID The numeric ID of the transaction, account, block, etc as a signed long - they are normally expressed as an unsigned long.
     */
    public BurstID(long signedLongID) {
        this.id = signedLongID;
    }

    /**
     * @return The unsigned long numeric ID
     */
    public String getID() {
        return toUnsignedLong(id);
    }

    /**
     * @return The signed long ID
     */
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
