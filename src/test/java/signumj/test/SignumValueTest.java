package signumj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import signumj.entity.SignumValue;
import signumj.util.SignumUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SignumValueTest {
    @Test
    public void testConstructors() {
        assertEquals("123456789", SignumValue.fromSigna("1.23456789").toNQT().toString());
        assertEquals("123456789", SignumValue.fromSigna("1.23456789 signa").toNQT().toString());
        assertEquals("123456789", SignumValue.fromSigna("1.23456789 SIGNA").toNQT().toString());
        assertEquals("123456789", SignumValue.fromSigna(1.23456789).toNQT().toString());
        assertEquals("123456789", SignumValue.fromSigna(new BigDecimal("1.23456789")).toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT("123456789").toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT("123456789 planck").toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT("123456789 PLANCK").toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT("123456789 quant").toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT("123456789 NQT").toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT(123456789).toNQT().toString());
        assertEquals("123456789", SignumValue.fromNQT(new BigInteger("123456789")).toNQT().toString());

        // Test null -> 0
        assertEquals(SignumValue.ZERO, SignumValue.fromNQT((String) null));
        assertEquals(SignumValue.ZERO, SignumValue.fromNQT((BigInteger) null));
        assertEquals(SignumValue.ZERO, SignumValue.fromSigna((String) null));
        assertEquals(SignumValue.ZERO, SignumValue.fromSigna((BigDecimal) null));
    }

    @Test
    public void testToString() {
        // Positive
        assertEquals("1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(1).toString());
        assertEquals("1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(1).toFormattedString());
        assertEquals("1", SignumValue.fromSigna(1).toUnformattedString());
        assertEquals("1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(1.00000001).toString());
        assertEquals("1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(1.00000001).toFormattedString());
        assertEquals("1.00000001", SignumValue.fromSigna(1.00000001).toUnformattedString());
        assertEquals("1.235 " + SignumUtils.getValueSuffix(), SignumValue.fromNQT(123456789).toString());
        // Negative
        assertEquals("-1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(-1).toString());
        assertEquals("-1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(-1).toFormattedString());
        assertEquals("-1", SignumValue.fromSigna(-1).toUnformattedString());
        assertEquals("-1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(-1.00000001).toString());
        assertEquals("-1 " + SignumUtils.getValueSuffix(), SignumValue.fromSigna(-1.00000001).toFormattedString());
        assertEquals("-1.00000001", SignumValue.fromSigna(-1.00000001).toUnformattedString());
        assertEquals("-1.235 " + SignumUtils.getValueSuffix(), SignumValue.fromNQT(-123456789).toString());
    }

    @Test
    public void testToBurst() {
        assertEquals(BigDecimal.valueOf(100000000, 8), SignumValue.fromSigna(1).toSigna());
        assertEquals(BigDecimal.valueOf(-100000000, 8), SignumValue.fromSigna(-1).toSigna());
    }

    @Test
    public void testAdd() {
        assertEquals(SignumValue.fromSigna(1), SignumValue.fromSigna(0.5).add(SignumValue.fromSigna(0.5)));
        assertEquals(SignumValue.fromSigna(0), SignumValue.fromSigna(-0.5).add(SignumValue.fromSigna(0.5)));
        assertEquals(SignumValue.fromSigna(-1), SignumValue.fromSigna(-0.5).add(SignumValue.fromSigna(-0.5)));
    }

    @Test
    public void testSubtract() {
        assertEquals(SignumValue.fromSigna(1), SignumValue.fromSigna(1.5).subtract(SignumValue.fromSigna(0.5)));
        assertEquals(SignumValue.fromSigna(0), SignumValue.fromSigna(0.5).subtract(SignumValue.fromSigna(0.5)));
        assertEquals(SignumValue.fromSigna(-1), SignumValue.fromSigna(-0.5).subtract(SignumValue.fromSigna(0.5)));
    }

    @Test
    public void testMultiply() {
        // Positive + positive
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(2).multiply(5));
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(4).multiply(2.5));
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(2).multiply(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(4).multiply(BigDecimal.valueOf(2.5)));

        // Positive + negative
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(2).multiply(-5));
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(4).multiply(-2.5));
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(2).multiply(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(4).multiply(BigDecimal.valueOf(-2.5)));

        // Negative + positive
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(-2).multiply(5));
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(-4).multiply(2.5));
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(-2).multiply(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromSigna(-10), SignumValue.fromSigna(-4).multiply(BigDecimal.valueOf(2.5)));

        // Negative + negative
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(-2).multiply(-5));
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(-4).multiply(-2.5));
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(-2).multiply(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromSigna(10), SignumValue.fromSigna(-4).multiply(BigDecimal.valueOf(-2.5)));
    }
    
    @Test
    public void testDivide() {
        // Positive + positive
        assertEquals(SignumValue.fromSigna(0.4), SignumValue.fromSigna(2).divide(5));
        assertEquals(SignumValue.fromSigna(1.6), SignumValue.fromSigna(4).divide(2.5));
        assertEquals(SignumValue.fromSigna(0.4), SignumValue.fromSigna(2).divide(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromSigna(1.6), SignumValue.fromSigna(4).divide(BigDecimal.valueOf(2.5)));

        // Positive + negative
        assertEquals(SignumValue.fromSigna(-0.4), SignumValue.fromSigna(2).divide(-5));
        assertEquals(SignumValue.fromSigna(-1.6), SignumValue.fromSigna(4).divide(-2.5));
        assertEquals(SignumValue.fromSigna(-0.4), SignumValue.fromSigna(2).divide(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromSigna(-1.6), SignumValue.fromSigna(4).divide(BigDecimal.valueOf(-2.5)));

        // Negative + positive
        assertEquals(SignumValue.fromSigna(-0.4), SignumValue.fromSigna(-2).divide(5));
        assertEquals(SignumValue.fromSigna(-1.6), SignumValue.fromSigna(-4).divide(2.5));
        assertEquals(SignumValue.fromSigna(-0.4), SignumValue.fromSigna(-2).divide(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromSigna(-1.6), SignumValue.fromSigna(-4).divide(BigDecimal.valueOf(2.5)));

        // Negative + negative
        assertEquals(SignumValue.fromSigna(0.4), SignumValue.fromSigna(-2).divide(-5));
        assertEquals(SignumValue.fromSigna(1.6), SignumValue.fromSigna(-4).divide(-2.5));
        assertEquals(SignumValue.fromSigna(0.4), SignumValue.fromSigna(-2).divide(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromSigna(1.6), SignumValue.fromSigna(-4).divide(BigDecimal.valueOf(-2.5)));

        // Recurring divisions
        assertEquals(SignumValue.fromNQT(33333333), SignumValue.fromSigna(1).divide(3));
        assertEquals(SignumValue.fromNQT(66666666), SignumValue.fromSigna(2).divide(3));

        // Divisor < 1
        assertEquals(SignumValue.fromSigna(3), SignumValue.fromSigna(1).divide(1.0/3.0));
    }

    @Test
    public void testAbs() {
        assertEquals(SignumValue.fromSigna(1), SignumValue.fromSigna(1).abs());
        assertEquals(SignumValue.fromSigna(1), SignumValue.fromSigna(-1).abs());
        assertEquals(SignumValue.fromSigna(0), SignumValue.fromSigna(0).abs());
    }

    @Test
    public void testMin() {
        assertEquals(SignumValue.fromSigna(1), SignumValue.min(SignumValue.fromSigna(1), SignumValue.fromSigna(2)));
        assertEquals(SignumValue.fromSigna(-2), SignumValue.min(SignumValue.fromSigna(-1), SignumValue.fromSigna(-2)));
    }

    @Test
    public void testMax() {
        assertEquals(SignumValue.fromSigna(2), SignumValue.max(SignumValue.fromSigna(1), SignumValue.fromSigna(2)));
        assertEquals(SignumValue.fromSigna(-1), SignumValue.max(SignumValue.fromSigna(-1), SignumValue.fromSigna(-2)));
    }
}
