package burst.kit.entity;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;

public final class HexStringByteArray {

    /**
     * GSON Serializer.
     */
    public static final JsonSerializer<HexStringByteArray> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.toString());

    /**
     * GSON Deserializer.
     */
    public static final JsonDeserializer<HexStringByteArray> DESERIALIZER = (json, typeOfT, context) -> new HexStringByteArray(json.getAsString());

    private final byte[] bytes;

    /**
     * @param stringRepresentation The string representation of the byte array eg. 01AB23
     */
    public HexStringByteArray(String stringRepresentation) {
        if (stringRepresentation.startsWith("0x")) {
            stringRepresentation = stringRepresentation.substring(2);
        }
        this.bytes = Hex.decode(stringRepresentation);
    }

    /**
     * Wrap a byte array in a HexStringByteArray
     * @param bytes The raw byte array
     */
    public HexStringByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * @return The raw bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @return The hex encoded string eg. 01AB23
     */
    public String toHexString() {
        return Hex.toHexString(bytes);
    }

    @Override
    public String toString() {
        return toHexString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HexStringByteArray && Arrays.equals(bytes, ((HexStringByteArray) obj).bytes);
    }
}
