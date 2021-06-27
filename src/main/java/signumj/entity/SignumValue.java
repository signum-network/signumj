package signumj.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;

import signumj.util.SignumUtils;

public final class SignumValue implements Comparable<SignumValue> {
    private static final int decimals = 8;

    public static final SignumValue ZERO = SignumValue.fromPlanck(0);
    
    private final BigInteger planck;

    private SignumValue(BigInteger planck) {
        this.planck = planck;
    }

    /**
     * @param planck The number of planck
     * @return The BurstValue representing this number of planck, or a BurstValue representing 0 Burst if the string could not be parsed
     */
    public static SignumValue fromPlanck(String planck) {
        if (planck == null) return ZERO;
        if (planck.toLowerCase(Locale.ENGLISH).endsWith(" planck")) {
            planck = planck.substring(0, planck.length() - 7);
        }
        try {
            return fromPlanck(new BigInteger(planck));
        } catch (NumberFormatException e) {
            return fromPlanck(BigInteger.ZERO);
        }
    }

    /**
     * @param planck The number of planck
     * @return The BurstValue representing this number of planck
     */
    public static SignumValue fromPlanck(long planck) {
        return fromPlanck(BigInteger.valueOf(planck));
    }

    public static SignumValue fromPlanck(BigInteger planck) {
        if (planck == null) return ZERO;
        return new SignumValue(planck);
    }

    /**
     * @param burst The number of burst
     * @return The BurstValue representing this number of burst, or a BurstValue representing 0 Burst if the string could not be parsed
     */
    public static SignumValue fromBurst(String burst) {
        if (burst == null) return ZERO;
        if (burst.toLowerCase(Locale.ENGLISH).endsWith(" " + SignumUtils.getValueSuffix().toLowerCase(Locale.ENGLISH))) {
            burst = burst.substring(0, burst.length() - 6);
        }
        try {
            return fromBurst(new BigDecimal(burst));
        } catch (NumberFormatException e) {
            return fromPlanck(BigInteger.ZERO);
        }
    }

    /**
     * @param burst The number of burst
     * @return The BurstValue representing this number of burst
     */
    public static SignumValue fromBurst(double burst) {
        return fromBurst(BigDecimal.valueOf(burst));
    }

    public static SignumValue fromBurst(BigDecimal burst) {
        if (burst == null) return ZERO;
        return new SignumValue(burst.multiply(BigDecimal.TEN.pow(decimals)).toBigInteger());
    }

    private static BigDecimal roundToThreeDP(BigDecimal in) {
        if (in.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        } else {
            return in.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros();
        }
    }

    @Override
    public String toString() {
        return toFormattedString();
    }

    /**
     * @return The value with the "BURST" suffix and rounded to 3 decimal places
     */
    public String toFormattedString() {
        return roundToThreeDP(toBurst()).toPlainString() + " " + SignumUtils.getValueSuffix();
    }

    /**
     * @return The value without the "BURST" suffix and without rounding
     */
    public String toUnformattedString() {
        return toBurst().stripTrailingZeros().toPlainString();
    }

    /**
     * @return A BigInteger representing the number of planck
     */
    public BigInteger toPlanck() {
        return planck;
    }

    public BigDecimal toBurst() {
        return new BigDecimal(planck, decimals);
    }

    public SignumValue add(SignumValue other) {
        return fromPlanck(planck.add(other.planck));
    }

    public SignumValue subtract(SignumValue other) {
        return fromPlanck(planck.subtract(other.planck));
    }

    public SignumValue multiply(long multiplicand) {
        return fromPlanck(planck.multiply(BigInteger.valueOf(multiplicand)));
    }

    public SignumValue multiply(double multiplicand) {
        return fromBurst(toBurst().multiply(BigDecimal.valueOf(multiplicand)));
    }

    public SignumValue multiply(BigInteger multiplicand) {
        return fromPlanck(planck.multiply(multiplicand));
    }

    public SignumValue multiply(BigDecimal multiplicand) {
        return fromBurst(toBurst().multiply(multiplicand));
    }

    public SignumValue divide(long divisor) {
        return fromPlanck(planck.divide(BigInteger.valueOf(divisor)));
    }

    public SignumValue divide(double divisor) {
        return fromBurst(toBurst().divide(BigDecimal.valueOf(divisor), decimals, RoundingMode.HALF_UP));
    }
    
    public SignumValue divide(BigInteger divisor) {
        return fromPlanck(planck.divide(divisor));
    }

    public SignumValue divide(BigDecimal divisor) {
        return fromBurst(toBurst().divide(divisor, decimals, RoundingMode.HALF_UP));
    }

    public SignumValue abs() {
        return fromPlanck(planck.abs());
    }

    @Override
    public int compareTo(SignumValue other) {
        if (other == null) return 1;
        return planck.compareTo(other.planck);
    }

    public static SignumValue min(SignumValue a, SignumValue b) {
        return (a.compareTo(b) <= 0) ? a : b;
    }

    public static SignumValue max(SignumValue a, SignumValue b) {
        return (a.compareTo(b) >= 0) ? a : b;
    }

    /**
     * @return The number of Burst as a double
     */
    public double doubleValue() { // TODO test
        return toBurst().doubleValue();
    }

    /**
     * @return The number of planck as a long
     */
    public long longValue() { // TODO test
        return toPlanck().longValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignumValue that = (SignumValue) o;

        return planck != null ? planck.equals(that.planck) : that.planck == null;
    }

    @Override
    public int hashCode() {
        return planck != null ? planck.hashCode() : 0;
    }
}
