package burst.kit.entity;

import burst.kit.burst.BurstCrypto;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public final class BurstAddress {

    public static final JsonDeserializer<BurstAddress> DESERIALIZER = (json, typeOfT, context) -> fromEither(json.getAsString());
    public static final JsonSerializer<BurstAddress> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.getNumericID());

    /**
     * Stored without "BURST-" prefix.
     */
    private final String address;
    private final BurstID numericID;

    private BurstAddress(BurstID numericID) {
        this.numericID = numericID;
        this.address = BurstCrypto.getInstance().rsEncode(numericID);
    }

    /**
     * @param numericId The numeric id that represents this Burst Address
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static BurstAddress fromNumericId(BurstID numericId) {
        return new BurstAddress(numericId);
    }

    public static BurstAddress fromRs(String RS) throws IllegalArgumentException {
        if (RS.startsWith("BURST-")) {
            RS = RS.substring(6);
        }
        return new BurstAddress(BurstCrypto.getInstance().rsDecode(RS));
    }

    /**
     * Try to parse an input as either a numeric ID or an RS address.
     *
     * @param input the numeric ID or RS address of the Burst address
     * @return a BurstAddress if one could be parsed from the input, null otherwise
     */
    public static BurstAddress fromEither(String input) {
        try {
            return BurstAddress.fromNumericId(new BurstID(input));
        } catch (IllegalArgumentException e1) {
            try {
                return BurstAddress.fromRs(input);
            } catch (IllegalArgumentException e2) {
                return null;
            }
        }
    }

    public String getNumericID() {
        return numericID.getID();
    }

    public String getRawAddress() {
        return address;
    }

    public String getFullAddress() {
        if (address == null || address.length() == 0) {
            return "";
        } else {
            return "BURST-" + address;
        }
    }

    @Override
    public String toString() {
        return getFullAddress();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BurstAddress && Objects.equals(numericID, ((BurstAddress) obj).numericID);
    }

    @Override
    public int hashCode() {
        return numericID.hashCode();
    }
}
