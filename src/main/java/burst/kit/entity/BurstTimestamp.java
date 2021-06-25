package burst.kit.entity;

import burst.kit.crypto.BurstCrypto;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public final class BurstTimestamp {
    private final int timestamp;
    private final Date date;

    /**
     * @param timestamp The Burst Epoch Time (number of seconds since Burst epoch)
     */
    private BurstTimestamp(int timestamp) {
        this.timestamp = timestamp;
        this.date = BurstCrypto.getInstance().fromBurstTimeToDate(timestamp);
    }

    /**
     * @param date The Java Date object to be represented
     */
    private BurstTimestamp(Date date) {
        this.timestamp = BurstCrypto.getInstance().toBurstTime(date);
        this.date = date;
    }

    /**
     * @param secondsSinceEpoch The Burst Epoch Time (number of seconds since Burst epoch)
     */
    public static BurstTimestamp fromBurstTimestamp(int secondsSinceEpoch) {
        return new BurstTimestamp(secondsSinceEpoch);
    }

    /**
     * @param date The Java Date object to be represented
     */
    public static BurstTimestamp fromDate(Date date) {
        return new BurstTimestamp(date);
    }

    public static BurstTimestamp now() {
        return new BurstTimestamp(BurstCrypto.getInstance().currentBurstTime());
    }

    /**
     * @return The burst timestamp (number of seconds since Burst epoch)
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * @return The timestamp as a pre Java 8 Date object, mainly intended for Android usage as older versions of Android do not have the Java 8 Time API
     */
    public Date getAsDate() {
        return date;
    }

    /**
     * @return The timestamp as a Java 8 Instant
     */
    public Instant getAsInstant() {
        return date.toInstant();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BurstTimestamp && Objects.equals(timestamp, ((BurstTimestamp)obj).timestamp);
    }

    @Override
    public String toString() {
        return String.valueOf(getTimestamp());
    }
}
