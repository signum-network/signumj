package signumj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import signumj.entity.SignumTimestamp;

import java.sql.Date;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TimestampTest {
    @Test
    public void testBurstTimestampFromEpochTime() {
        SignumTimestamp burstTimestamp = SignumTimestamp.fromBurstTimestamp(123456789);
        assertEquals(123456789, burstTimestamp.getTimestamp());
        assertEquals(1531179189000L, burstTimestamp.getAsDate().toInstant().toEpochMilli());
    }

    @Test
    public void testBurstTimestampFromDate() {
        SignumTimestamp burstTimestamp = SignumTimestamp.fromDate(Date.from(Instant.ofEpochMilli(1531179189000L)));
        assertEquals(123456789, burstTimestamp.getTimestamp());
        assertEquals(1531179189000L, burstTimestamp.getAsDate().toInstant().toEpochMilli());
    }
}
