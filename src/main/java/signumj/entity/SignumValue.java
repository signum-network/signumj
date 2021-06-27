package signumj.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;

import signumj.util.SignumUtils;

public final class SignumValue implements Comparable<SignumValue> {
    private static final int decimals = 8;

    public static final SignumValue ZERO = SignumValue.fromNQT(0);
    
    private final BigInteger planck;

    private SignumValue(BigInteger planck) {
        this.planck = planck;
    }
    
    /**
     * @param planck The number of quant
     * @return The SignumValue representing this number of planck, or a SignumValue representing 0 Burst if the string could not be parsed
     */
    public static SignumValue fromNQT(String planck) {
        if (planck == null) return ZERO;
        if (planck.toLowerCase(Locale.ENGLISH).endsWith(" planck")) {
            planck = planck.substring(0, planck.length() - 7);
        }
        else if (planck.toLowerCase(Locale.ENGLISH).endsWith(" quant")) {
            planck = planck.substring(0, planck.length() - 6);
        }
        else if (planck.toLowerCase(Locale.ENGLISH).endsWith(" nqt")) {
            planck = planck.substring(0, planck.length() - 4);
        }
        try {
            return fromNQT(new BigInteger(planck));
        } catch (NumberFormatException e) {
            return fromNQT(BigInteger.ZERO);
        }
    }
    
    /**
     * @param planck The number of planck
     * @return The SignumValue representing this number of quant
     */
    public static SignumValue fromNQT(long planck) {
        return fromNQT(BigInteger.valueOf(planck));
    }

    public static SignumValue fromNQT(BigInteger planck) {
        if (planck == null) return ZERO;
        return new SignumValue(planck);
    }

    /**
     * @param signa The number of Signa
     * @return The SignumValue representing this number of Signa, or a SignumValue representing 0 Signa if the string could not be parsed
     */
    public static SignumValue fromSigna(String signa) {
        if (signa == null) return ZERO;
        if (signa.toLowerCase(Locale.ENGLISH).endsWith(" " + SignumUtils.getValueSuffix().toLowerCase(Locale.ENGLISH))) {
            signa = signa.substring(0, signa.length() - 6);
        }
        try {
            return fromSigna(new BigDecimal(signa));
        } catch (NumberFormatException e) {
            return fromNQT(BigInteger.ZERO);
        }
    }

    /**
     * @param burst The number of burst
     * @return The SignumValue representing this number of burst
     */
    public static SignumValue fromSigna(double burst) {
        return fromSigna(BigDecimal.valueOf(burst));
    }

    public static SignumValue fromSigna(BigDecimal burst) {
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
     * @return The value with the suffix and rounded to 3 decimal places
     */
    public String toFormattedString() {
        return roundToThreeDP(toSigna()).toPlainString() + " " + SignumUtils.getValueSuffix();
    }

    /**
     * @return The value without the suffix and without rounding
     */
    public String toUnformattedString() {
        return toSigna().stripTrailingZeros().toPlainString();
    }
    
    /**
     * @return A BigInteger representing the number of quant
     */
    public BigInteger toNQT() {
        return planck;
    }

    public BigDecimal toSigna() {
        return new BigDecimal(planck, decimals);
    }

    public SignumValue add(SignumValue other) {
        return fromNQT(planck.add(other.planck));
    }

    public SignumValue subtract(SignumValue other) {
        return fromNQT(planck.subtract(other.planck));
    }

    public SignumValue multiply(long multiplicand) {
        return fromNQT(planck.multiply(BigInteger.valueOf(multiplicand)));
    }

    public SignumValue multiply(double multiplicand) {
        return fromSigna(toSigna().multiply(BigDecimal.valueOf(multiplicand)));
    }

    public SignumValue multiply(BigInteger multiplicand) {
        return fromNQT(planck.multiply(multiplicand));
    }

    public SignumValue multiply(BigDecimal multiplicand) {
        return fromSigna(toSigna().multiply(multiplicand));
    }

    public SignumValue divide(long divisor) {
        return fromNQT(planck.divide(BigInteger.valueOf(divisor)));
    }

    public SignumValue divide(double divisor) {
        return fromSigna(toSigna().divide(BigDecimal.valueOf(divisor), decimals, RoundingMode.HALF_UP));
    }
    
    public SignumValue divide(BigInteger divisor) {
        return fromNQT(planck.divide(divisor));
    }

    public SignumValue divide(BigDecimal divisor) {
        return fromSigna(toSigna().divide(divisor, decimals, RoundingMode.HALF_UP));
    }

    public SignumValue abs() {
        return fromNQT(planck.abs());
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
        return toSigna().doubleValue();
    }

    /**
     * @return The number of planck as a long
     */
    public long longValue() { // TODO test
        return toNQT().longValue();
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
