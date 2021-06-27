package signumj.entity;

import java.util.Objects;

public final class SignumID {
    /**
     * Stored as a signed long (because java) but should be used as an unsigned long
     * using Convert.toUnsignedLong and Convert.parseUnsignedLong (BRS methods)
     */
    private final long id;

    /**
     * @param unsignedLongId The numeric ID of the transaction, account, block, etc.
     */
    private SignumID(String unsignedLongId) {
        this.id = Long.parseUnsignedLong(unsignedLongId);
    }

    /**
     * @param signedLongID The numeric ID of the transaction, account, block, etc as a signed long - they are normally expressed as an unsigned long.
     */
    private SignumID(long signedLongID) {
        this.id = signedLongID;
    }

    /**
     * @param unsignedLongId The numeric ID of the transaction, account, block, etc.
     */
    public static SignumID fromLong(String unsignedLongId) {
        if (unsignedLongId == null) return null;
        return new SignumID(unsignedLongId);
    }

    /**
     * @param signedLongId The numeric ID of the transaction, account, block, etc.
     */
    public static SignumID fromLong(long signedLongId) {
        return new SignumID(signedLongId);
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
        return obj instanceof SignumID && Objects.equals(getID(), ((SignumID) obj).getID());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
