package signumj.entity;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import signumj.crypto.SignumCrypto;

public final class SignumTimestamp {
    private final int timestamp;
    private final Date date;

    /**
     * @param timestamp The Burst Epoch Time (number of seconds since Burst epoch)
     */
    private SignumTimestamp(int timestamp) {
        this.timestamp = timestamp;
        this.date = SignumCrypto.getInstance().fromBurstTimeToDate(timestamp);
    }

    /**
     * @param date The Java Date object to be represented
     */
    private SignumTimestamp(Date date) {
        this.timestamp = SignumCrypto.getInstance().toBurstTime(date);
        this.date = date;
    }

    /**
     * @param secondsSinceEpoch The Burst Epoch Time (number of seconds since Burst epoch)
     */
    public static SignumTimestamp fromBurstTimestamp(int secondsSinceEpoch) {
        return new SignumTimestamp(secondsSinceEpoch);
    }

    /**
     * @param date The Java Date object to be represented
     */
    public static SignumTimestamp fromDate(Date date) {
        return new SignumTimestamp(date);
    }

    public static SignumTimestamp now() {
        return new SignumTimestamp(SignumCrypto.getInstance().currentBurstTime());
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
        return obj instanceof SignumTimestamp && Objects.equals(timestamp, ((SignumTimestamp)obj).timestamp);
    }

    @Override
    public String toString() {
        return String.valueOf(getTimestamp());
    }
}
