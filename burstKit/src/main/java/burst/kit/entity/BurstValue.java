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

    public static BurstValue fromPlanck(String planck) {
        try {
            return new BurstValue(planck);
        } catch (NumberFormatException e) {
            return new BurstValue("0");
        }
    }

    public static BurstValue fromPlanck(long planck) {
        return fromPlanck(String.valueOf(planck));
    }

    public static BurstValue fromBurst(String burst) {
        try {
            return new BurstValue(new BigDecimal(burst).multiply(BigDecimal.TEN.pow(8)).toString());
        } catch (NumberFormatException e) {
            return new BurstValue("0");
        }
    }

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

    public String toUnformattedString() {
        return super.stripTrailingZeros().toPlainString();
    }

    public String toPlanck() {
        return multiply(BigDecimal.TEN.pow(8)).toBigInteger().toString();
    }
}
