package burst.kit.entity;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BurstValue extends BigDecimal {

    public static final JsonDeserializer<BurstValue> DESERIALIZER = (json, typeOfT, context) -> json.isJsonNull() ? null : BurstValue.fromPlanck(json.getAsString());
    public static final JsonSerializer<BurstValue> SERIALIZER = (src, typeOfSrc, context) -> new JsonPrimitive(src.toPlanck());

    @SuppressWarnings("BigDecimalMethodWithoutRoundingCalled")
    private BurstValue(String val) {
        super(new BigDecimal(val).divide(BigDecimal.TEN.pow(8)).toString());
    }

    /**
     * @param planck The number of planck
     * @return The BurstValue representing this number of planck, or a BurstValue representing 0 Burst if the string could not be parsed
     */
    public static BurstValue fromPlanck(String planck) {
        try {
            return new BurstValue(planck);
        } catch (NumberFormatException e) {
            return new BurstValue("0");
        }
    }

    /**
     * @param planck The number of planck
     * @return The BurstValue representing this number of planck
     */
    public static BurstValue fromPlanck(long planck) {
        return fromPlanck(String.valueOf(planck));
    }

    /**
     * @param burst The number of burst
     * @return The BurstValue representing this number of burst, or a BurstValue representing 0 Burst if the string could not be parsed
     */
    public static BurstValue fromBurst(String burst) {
        try {
            return new BurstValue(new BigDecimal(burst).multiply(BigDecimal.TEN.pow(8)).toString());
        } catch (NumberFormatException e) {
            return new BurstValue("0");
        }
    }

    /**
     * @param burst The number of burst
     * @return The BurstValue representing this number of burst
     */
    public static BurstValue fromBurst(double burst) {
        return fromBurst(String.valueOf(burst));
    }

    @Override
    public String toString() {
        return roundToThreeDP(this).toPlainString() + " BURST";
    }

    private static BigDecimal roundToThreeDP(BigDecimal in) {
        if (in.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        } else {
            return in.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
        }
    }

    /**
     * @return The value with the "BURST" suffix and rounded to 3 decimal places
     */
    public String toFormattedString() {
        return toString();
    }

    /**
     * @return The value without the "BURST" suffix and without rounding
     */
    public String toUnformattedString() {
        return super.stripTrailingZeros().toPlainString();
    }

    /**
     * @return A string representing the number of planck
     */
    public String toPlanck() {
        return multiply(BigDecimal.TEN.pow(8)).toBigInteger().toString();
    }
}
