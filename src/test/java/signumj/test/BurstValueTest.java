package signumj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import signumj.entity.SignumValue;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstValueTest {
    @Test
    public void testConstructors() {
        assertEquals("123456789", SignumValue.fromBurst("1.23456789").toPlanck().toString());
        assertEquals("123456789", SignumValue.fromBurst("1.23456789 burst").toPlanck().toString());
        assertEquals("123456789", SignumValue.fromBurst("1.23456789 BURST").toPlanck().toString());
        assertEquals("123456789", SignumValue.fromBurst(1.23456789).toPlanck().toString());
        assertEquals("123456789", SignumValue.fromBurst(new BigDecimal("1.23456789")).toPlanck().toString());
        assertEquals("123456789", SignumValue.fromPlanck("123456789").toPlanck().toString());
        assertEquals("123456789", SignumValue.fromPlanck("123456789 planck").toPlanck().toString());
        assertEquals("123456789", SignumValue.fromPlanck("123456789 PLANCK").toPlanck().toString());
        assertEquals("123456789", SignumValue.fromPlanck(123456789).toPlanck().toString());
        assertEquals("123456789", SignumValue.fromPlanck(new BigInteger("123456789")).toPlanck().toString());

        // Test null -> 0
        assertEquals(SignumValue.ZERO, SignumValue.fromPlanck((String) null));
        assertEquals(SignumValue.ZERO, SignumValue.fromPlanck((BigInteger) null));
        assertEquals(SignumValue.ZERO, SignumValue.fromBurst((String) null));
        assertEquals(SignumValue.ZERO, SignumValue.fromBurst((BigDecimal) null));
    }

    @Test
    public void testToString() {
        // Positive
        assertEquals("1 BURST", SignumValue.fromBurst(1).toString());
        assertEquals("1 BURST", SignumValue.fromBurst(1).toFormattedString());
        assertEquals("1", SignumValue.fromBurst(1).toUnformattedString());
        assertEquals("1 BURST", SignumValue.fromBurst(1.00000001).toString());
        assertEquals("1 BURST", SignumValue.fromBurst(1.00000001).toFormattedString());
        assertEquals("1.00000001", SignumValue.fromBurst(1.00000001).toUnformattedString());
        assertEquals("1.235 BURST", SignumValue.fromPlanck(123456789).toString());
        // Negative
        assertEquals("-1 BURST", SignumValue.fromBurst(-1).toString());
        assertEquals("-1 BURST", SignumValue.fromBurst(-1).toFormattedString());
        assertEquals("-1", SignumValue.fromBurst(-1).toUnformattedString());
        assertEquals("-1 BURST", SignumValue.fromBurst(-1.00000001).toString());
        assertEquals("-1 BURST", SignumValue.fromBurst(-1.00000001).toFormattedString());
        assertEquals("-1.00000001", SignumValue.fromBurst(-1.00000001).toUnformattedString());
        assertEquals("-1.235 BURST", SignumValue.fromPlanck(-123456789).toString());
    }

    @Test
    public void testToBurst() {
        assertEquals(BigDecimal.valueOf(100000000, 8), SignumValue.fromBurst(1).toBurst());
        assertEquals(BigDecimal.valueOf(-100000000, 8), SignumValue.fromBurst(-1).toBurst());
    }

    @Test
    public void testAdd() {
        assertEquals(SignumValue.fromBurst(1), SignumValue.fromBurst(0.5).add(SignumValue.fromBurst(0.5)));
        assertEquals(SignumValue.fromBurst(0), SignumValue.fromBurst(-0.5).add(SignumValue.fromBurst(0.5)));
        assertEquals(SignumValue.fromBurst(-1), SignumValue.fromBurst(-0.5).add(SignumValue.fromBurst(-0.5)));
    }

    @Test
    public void testSubtract() {
        assertEquals(SignumValue.fromBurst(1), SignumValue.fromBurst(1.5).subtract(SignumValue.fromBurst(0.5)));
        assertEquals(SignumValue.fromBurst(0), SignumValue.fromBurst(0.5).subtract(SignumValue.fromBurst(0.5)));
        assertEquals(SignumValue.fromBurst(-1), SignumValue.fromBurst(-0.5).subtract(SignumValue.fromBurst(0.5)));
    }

    @Test
    public void testMultiply() {
        // Positive + positive
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(2).multiply(5));
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(4).multiply(2.5));
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(2).multiply(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(4).multiply(BigDecimal.valueOf(2.5)));

        // Positive + negative
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(2).multiply(-5));
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(4).multiply(-2.5));
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(2).multiply(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(4).multiply(BigDecimal.valueOf(-2.5)));

        // Negative + positive
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(-2).multiply(5));
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(-4).multiply(2.5));
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(-2).multiply(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromBurst(-10), SignumValue.fromBurst(-4).multiply(BigDecimal.valueOf(2.5)));

        // Negative + negative
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(-2).multiply(-5));
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(-4).multiply(-2.5));
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(-2).multiply(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromBurst(10), SignumValue.fromBurst(-4).multiply(BigDecimal.valueOf(-2.5)));
    }
    
    @Test
    public void testDivide() {
        // Positive + positive
        assertEquals(SignumValue.fromBurst(0.4), SignumValue.fromBurst(2).divide(5));
        assertEquals(SignumValue.fromBurst(1.6), SignumValue.fromBurst(4).divide(2.5));
        assertEquals(SignumValue.fromBurst(0.4), SignumValue.fromBurst(2).divide(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromBurst(1.6), SignumValue.fromBurst(4).divide(BigDecimal.valueOf(2.5)));

        // Positive + negative
        assertEquals(SignumValue.fromBurst(-0.4), SignumValue.fromBurst(2).divide(-5));
        assertEquals(SignumValue.fromBurst(-1.6), SignumValue.fromBurst(4).divide(-2.5));
        assertEquals(SignumValue.fromBurst(-0.4), SignumValue.fromBurst(2).divide(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromBurst(-1.6), SignumValue.fromBurst(4).divide(BigDecimal.valueOf(-2.5)));

        // Negative + positive
        assertEquals(SignumValue.fromBurst(-0.4), SignumValue.fromBurst(-2).divide(5));
        assertEquals(SignumValue.fromBurst(-1.6), SignumValue.fromBurst(-4).divide(2.5));
        assertEquals(SignumValue.fromBurst(-0.4), SignumValue.fromBurst(-2).divide(BigInteger.valueOf(5)));
        assertEquals(SignumValue.fromBurst(-1.6), SignumValue.fromBurst(-4).divide(BigDecimal.valueOf(2.5)));

        // Negative + negative
        assertEquals(SignumValue.fromBurst(0.4), SignumValue.fromBurst(-2).divide(-5));
        assertEquals(SignumValue.fromBurst(1.6), SignumValue.fromBurst(-4).divide(-2.5));
        assertEquals(SignumValue.fromBurst(0.4), SignumValue.fromBurst(-2).divide(BigInteger.valueOf(-5)));
        assertEquals(SignumValue.fromBurst(1.6), SignumValue.fromBurst(-4).divide(BigDecimal.valueOf(-2.5)));

        // Recurring divisions
        assertEquals(SignumValue.fromPlanck(33333333), SignumValue.fromBurst(1).divide(3));
        assertEquals(SignumValue.fromPlanck(66666666), SignumValue.fromBurst(2).divide(3));

        // Divisor < 1
        assertEquals(SignumValue.fromBurst(3), SignumValue.fromBurst(1).divide(1.0/3.0));
    }

    @Test
    public void testAbs() {
        assertEquals(SignumValue.fromBurst(1), SignumValue.fromBurst(1).abs());
        assertEquals(SignumValue.fromBurst(1), SignumValue.fromBurst(-1).abs());
        assertEquals(SignumValue.fromBurst(0), SignumValue.fromBurst(0).abs());
    }

    @Test
    public void testMin() {
        assertEquals(SignumValue.fromBurst(1), SignumValue.min(SignumValue.fromBurst(1), SignumValue.fromBurst(2)));
        assertEquals(SignumValue.fromBurst(-2), SignumValue.min(SignumValue.fromBurst(-1), SignumValue.fromBurst(-2)));
    }

    @Test
    public void testMax() {
        assertEquals(SignumValue.fromBurst(2), SignumValue.max(SignumValue.fromBurst(1), SignumValue.fromBurst(2)));
        assertEquals(SignumValue.fromBurst(-1), SignumValue.max(SignumValue.fromBurst(-1), SignumValue.fromBurst(-2)));
    }
}
