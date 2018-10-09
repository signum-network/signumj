package burst.kit.entity;

import brs.util.Convert;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.util.Arrays;

public class HexStringByteArray {

    public static final JsonDeserializer<HexStringByteArray> DESERIALIZER = (json, typeOfT, context) -> new HexStringByteArray(json.getAsString());
    public static final JsonSerializer<HexStringByteArray> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.toString());

    private final byte[] bytes;

    public HexStringByteArray(String stringRepresentation) {
        this.bytes = Convert.parseHexString(stringRepresentation);
    }

    public HexStringByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String toHexString() {
        return Convert.toHexString(bytes);
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
