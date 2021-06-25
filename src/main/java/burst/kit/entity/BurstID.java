package burst.kit.entity;

import java.util.Objects;

public final class BurstID {
    /**
     * Stored as a signed long (because java) but should be used as an unsigned long
     * using Convert.toUnsignedLong and Convert.parseUnsignedLong (BRS methods)
     */
    private final long id;

    /**
     * @param unsignedLongId The numeric ID of the transaction, account, block, etc.
     */
    private BurstID(String unsignedLongId) {
        this.id = Long.parseUnsignedLong(unsignedLongId);
    }

    /**
     * @param signedLongID The numeric ID of the transaction, account, block, etc as a signed long - they are normally expressed as an unsigned long.
     */
    private BurstID(long signedLongID) {
        this.id = signedLongID;
    }

    /**
     * @param unsignedLongId The numeric ID of the transaction, account, block, etc.
     */
    public static BurstID fromLong(String unsignedLongId) {
        if (unsignedLongId == null) return null;
        return new BurstID(unsignedLongId);
    }

    /**
     * @param signedLongId The numeric ID of the transaction, account, block, etc.
     */
    public static BurstID fromLong(long signedLongId) {
        return new BurstID(signedLongId);
    }

    /**
     * @return The unsigned long numeric ID
     */
    public String getID() {
        return Long.toUnsignedString(id);
    }

    /**
     * @return The signed long ID
     */
    public long getSignedLongId() {
        return id;
    }

    @Override
    public String toString() {
        return getID();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BurstID && Objects.equals(getID(), ((BurstID) obj).getID());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
