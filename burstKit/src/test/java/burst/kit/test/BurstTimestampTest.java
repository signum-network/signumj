package burst.kit.test;

import burst.kit.entity.BurstTimestamp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstTimestampTest {
    @Test
    public void testBurstTimestampFromEpochTime() {
        BurstTimestamp burstTimestamp = BurstTimestamp.fromBurstTimestamp(123456789);
        assertEquals(123456789, burstTimestamp.getTimestamp());
        assertEquals(1531179189000L, burstTimestamp.getAsDate().toInstant().toEpochMilli());
    }

    @Test
    public void testBurstTimestampFromDate() {
        BurstTimestamp burstTimestamp = BurstTimestamp.fromDate(Date.from(Instant.ofEpochMilli(1531179189000L)));
        assertEquals(123456789, burstTimestamp.getTimestamp());
        assertEquals(1531179189000L, burstTimestamp.getAsDate().toInstant().toEpochMilli());
    }
}
