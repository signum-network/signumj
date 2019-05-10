package burst.kit.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class BurstValue implements Comparable<BurstValue> {
    private static final int decimals = 8;

    public static final BurstValue ZERO = BurstValue.fromPlanck(0);
    
    private final BigInteger planck;

    private BurstValue(BigInteger planck) {
        this.planck = planck;
    }

    /**
     * @param planck The number of planck
     * @return The BurstValue representing this number of planck, or a BurstValue representing 0 Burst if the string could not be parsed
     */
    public static BurstValue fromPlanck(String planck) {
        if (planck == null) return ZERO;
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
    public static BurstValue fromPlanck(long planck) {
        return fromPlanck(BigInteger.valueOf(planck));
    }

    public static BurstValue fromPlanck(BigInteger planck) {
        if (planck == null) return ZERO;
        return new BurstValue(planck);
    }

    /**
     * @param burst The number of burst
     * @return The BurstValue representing this number of burst, or a BurstValue representing 0 Burst if the string could not be parsed
     */
    public static BurstValue fromBurst(String burst) {
        if (burst == null) return ZERO;
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
    public static BurstValue fromBurst(double burst) {
        return fromBurst(BigDecimal.valueOf(burst));
    }

    public static BurstValue fromBurst(BigDecimal burst) {
        if (burst == null) return ZERO;
        return new BurstValue(burst.multiply(BigDecimal.TEN.pow(decimals)).toBigInteger());
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
        return roundToThreeDP(toBurst()).toPlainString() + " BURST";
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

    public BurstValue add(BurstValue other) {
        return fromPlanck(planck.add(other.planck));
    }

    public BurstValue subtract(BurstValue other) {
        return fromPlanck(planck.subtract(other.planck));
    }

    public BurstValue multiply(long multiplicand) {
        return fromPlanck(planck.multiply(BigInteger.valueOf(multiplicand)));
    }

    public BurstValue multiply(double multiplicand) {
        return fromBurst(toBurst().multiply(BigDecimal.valueOf(multiplicand)));
    }

    public BurstValue multiply(BigInteger multiplicand) {
        return fromPlanck(planck.multiply(multiplicand));
    }

    public BurstValue multiply(BigDecimal multiplicand) {
        return fromBurst(toBurst().multiply(multiplicand));
    }

    public BurstValue divide(long divisor) {
        return fromPlanck(planck.divide(BigInteger.valueOf(divisor)));
    }

    public BurstValue divide(double divisor) {
        return fromBurst(toBurst().divide(BigDecimal.valueOf(divisor), decimals, RoundingMode.HALF_UP));
    }
    
    public BurstValue divide(BigInteger divisor) {
        return fromPlanck(planck.divide(divisor));
    }

    public BurstValue divide(BigDecimal divisor) {
        return fromBurst(toBurst().divide(divisor, decimals, RoundingMode.HALF_UP));
    }

    public BurstValue abs() {
        return fromPlanck(planck.abs());
    }

    @Override
    public int compareTo(BurstValue other) {
        if (other == null) return 1;
        return planck.compareTo(other.planck);
    }

    public static BurstValue min(BurstValue a, BurstValue b) {
        return (a.compareTo(b) <= 0) ? a : b;
    }

    public static BurstValue max(BurstValue a, BurstValue b) {
        return (a.compareTo(b) >= 0) ? a : b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BurstValue that = (BurstValue) o;

        return planck != null ? planck.equals(that.planck) : that.planck == null;
    }

    @Override
    public int hashCode() {
        return planck != null ? planck.hashCode() : 0;
    }
}
