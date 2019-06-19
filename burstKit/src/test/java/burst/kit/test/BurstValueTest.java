package burst.kit.test;

import burst.kit.entity.BurstValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstValueTest {
    @Test
    public void testConstructors() {
        assertEquals("123456789", BurstValue.fromBurst("1.23456789").toPlanck().toString());
        assertEquals("123456789", BurstValue.fromBurst("1.23456789 burst").toPlanck().toString());
        assertEquals("123456789", BurstValue.fromBurst("1.23456789 BURST").toPlanck().toString());
        assertEquals("123456789", BurstValue.fromBurst(1.23456789).toPlanck().toString());
        assertEquals("123456789", BurstValue.fromBurst(new BigDecimal("1.23456789")).toPlanck().toString());
        assertEquals("123456789", BurstValue.fromPlanck("123456789").toPlanck().toString());
        assertEquals("123456789", BurstValue.fromPlanck("123456789 planck").toPlanck().toString());
        assertEquals("123456789", BurstValue.fromPlanck("123456789 PLANCK").toPlanck().toString());
        assertEquals("123456789", BurstValue.fromPlanck(123456789).toPlanck().toString());
        assertEquals("123456789", BurstValue.fromPlanck(new BigInteger("123456789")).toPlanck().toString());

        // Test null -> 0
        assertEquals(BurstValue.ZERO, BurstValue.fromPlanck((String) null));
        assertEquals(BurstValue.ZERO, BurstValue.fromPlanck((BigInteger) null));
        assertEquals(BurstValue.ZERO, BurstValue.fromBurst((String) null));
        assertEquals(BurstValue.ZERO, BurstValue.fromBurst((BigDecimal) null));
    }

    @Test
    public void testToString() {
        // Positive
        assertEquals("1 BURST", BurstValue.fromBurst(1).toString());
        assertEquals("1 BURST", BurstValue.fromBurst(1).toFormattedString());
        assertEquals("1", BurstValue.fromBurst(1).toUnformattedString());
        assertEquals("1 BURST", BurstValue.fromBurst(1.00000001).toString());
        assertEquals("1 BURST", BurstValue.fromBurst(1.00000001).toFormattedString());
        assertEquals("1.00000001", BurstValue.fromBurst(1.00000001).toUnformattedString());
        assertEquals("1.235 BURST", BurstValue.fromPlanck(123456789).toString());
        // Negative
        assertEquals("-1 BURST", BurstValue.fromBurst(-1).toString());
        assertEquals("-1 BURST", BurstValue.fromBurst(-1).toFormattedString());
        assertEquals("-1", BurstValue.fromBurst(-1).toUnformattedString());
        assertEquals("-1 BURST", BurstValue.fromBurst(-1.00000001).toString());
        assertEquals("-1 BURST", BurstValue.fromBurst(-1.00000001).toFormattedString());
        assertEquals("-1.00000001", BurstValue.fromBurst(-1.00000001).toUnformattedString());
        assertEquals("-1.235 BURST", BurstValue.fromPlanck(-123456789).toString());
    }

    @Test
    public void testToBurst() {
        assertEquals(BigDecimal.valueOf(100000000, 8), BurstValue.fromBurst(1).toBurst());
        assertEquals(BigDecimal.valueOf(-100000000, 8), BurstValue.fromBurst(-1).toBurst());
    }

    @Test
    public void testAdd() {
        assertEquals(BurstValue.fromBurst(1), BurstValue.fromBurst(0.5).add(BurstValue.fromBurst(0.5)));
        assertEquals(BurstValue.fromBurst(0), BurstValue.fromBurst(-0.5).add(BurstValue.fromBurst(0.5)));
        assertEquals(BurstValue.fromBurst(-1), BurstValue.fromBurst(-0.5).add(BurstValue.fromBurst(-0.5)));
    }

    @Test
    public void testSubtract() {
        assertEquals(BurstValue.fromBurst(1), BurstValue.fromBurst(1.5).subtract(BurstValue.fromBurst(0.5)));
        assertEquals(BurstValue.fromBurst(0), BurstValue.fromBurst(0.5).subtract(BurstValue.fromBurst(0.5)));
        assertEquals(BurstValue.fromBurst(-1), BurstValue.fromBurst(-0.5).subtract(BurstValue.fromBurst(0.5)));
    }

    @Test
    public void testMultiply() {
        // Positive + positive
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(2).multiply(5));
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(4).multiply(2.5));
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(2).multiply(BigInteger.valueOf(5)));
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(4).multiply(BigDecimal.valueOf(2.5)));

        // Positive + negative
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(2).multiply(-5));
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(4).multiply(-2.5));
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(2).multiply(BigInteger.valueOf(-5)));
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(4).multiply(BigDecimal.valueOf(-2.5)));

        // Negative + positive
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(-2).multiply(5));
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(-4).multiply(2.5));
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(-2).multiply(BigInteger.valueOf(5)));
        assertEquals(BurstValue.fromBurst(-10), BurstValue.fromBurst(-4).multiply(BigDecimal.valueOf(2.5)));

        // Negative + negative
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(-2).multiply(-5));
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(-4).multiply(-2.5));
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(-2).multiply(BigInteger.valueOf(-5)));
        assertEquals(BurstValue.fromBurst(10), BurstValue.fromBurst(-4).multiply(BigDecimal.valueOf(-2.5)));
    }
    
    @Test
    public void testDivide() {
        // Positive + positive
        assertEquals(BurstValue.fromBurst(0.4), BurstValue.fromBurst(2).divide(5));
        assertEquals(BurstValue.fromBurst(1.6), BurstValue.fromBurst(4).divide(2.5));
        assertEquals(BurstValue.fromBurst(0.4), BurstValue.fromBurst(2).divide(BigInteger.valueOf(5)));
        assertEquals(BurstValue.fromBurst(1.6), BurstValue.fromBurst(4).divide(BigDecimal.valueOf(2.5)));

        // Positive + negative
        assertEquals(BurstValue.fromBurst(-0.4), BurstValue.fromBurst(2).divide(-5));
        assertEquals(BurstValue.fromBurst(-1.6), BurstValue.fromBurst(4).divide(-2.5));
        assertEquals(BurstValue.fromBurst(-0.4), BurstValue.fromBurst(2).divide(BigInteger.valueOf(-5)));
        assertEquals(BurstValue.fromBurst(-1.6), BurstValue.fromBurst(4).divide(BigDecimal.valueOf(-2.5)));

        // Negative + positive
        assertEquals(BurstValue.fromBurst(-0.4), BurstValue.fromBurst(-2).divide(5));
        assertEquals(BurstValue.fromBurst(-1.6), BurstValue.fromBurst(-4).divide(2.5));
        assertEquals(BurstValue.fromBurst(-0.4), BurstValue.fromBurst(-2).divide(BigInteger.valueOf(5)));
        assertEquals(BurstValue.fromBurst(-1.6), BurstValue.fromBurst(-4).divide(BigDecimal.valueOf(2.5)));

        // Negative + negative
        assertEquals(BurstValue.fromBurst(0.4), BurstValue.fromBurst(-2).divide(-5));
        assertEquals(BurstValue.fromBurst(1.6), BurstValue.fromBurst(-4).divide(-2.5));
        assertEquals(BurstValue.fromBurst(0.4), BurstValue.fromBurst(-2).divide(BigInteger.valueOf(-5)));
        assertEquals(BurstValue.fromBurst(1.6), BurstValue.fromBurst(-4).divide(BigDecimal.valueOf(-2.5)));

        // Recurring divisions
        assertEquals(BurstValue.fromPlanck(33333333), BurstValue.fromBurst(1).divide(3));
        assertEquals(BurstValue.fromPlanck(66666666), BurstValue.fromBurst(2).divide(3));

        // Divisor < 1
        assertEquals(BurstValue.fromBurst(3), BurstValue.fromBurst(1).divide(1.0/3.0));
    }

    @Test
    public void testAbs() {
        assertEquals(BurstValue.fromBurst(1), BurstValue.fromBurst(1).abs());
        assertEquals(BurstValue.fromBurst(1), BurstValue.fromBurst(-1).abs());
        assertEquals(BurstValue.fromBurst(0), BurstValue.fromBurst(0).abs());
    }

    @Test
    public void testMin() {
        assertEquals(BurstValue.fromBurst(1), BurstValue.min(BurstValue.fromBurst(1), BurstValue.fromBurst(2)));
        assertEquals(BurstValue.fromBurst(-2), BurstValue.min(BurstValue.fromBurst(-1), BurstValue.fromBurst(-2)));
    }

    @Test
    public void testMax() {
        assertEquals(BurstValue.fromBurst(2), BurstValue.max(BurstValue.fromBurst(1), BurstValue.fromBurst(2)));
        assertEquals(BurstValue.fromBurst(-1), BurstValue.max(BurstValue.fromBurst(-1), BurstValue.fromBurst(-2)));
    }
}
