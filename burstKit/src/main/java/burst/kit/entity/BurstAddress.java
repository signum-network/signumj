package burst.kit.entity;

import burst.kit.crypto.BurstCrypto;
import burst.kit.util.BurstKitUtils;

import java.util.Objects;

public final class BurstAddress {

    /**
     * Stored without "BURST-" prefix.
     */
    private final String address;
    private final BurstID numericID;
    private byte[] publicKey;

    private BurstAddress(BurstID burstID) {
        this.numericID = burstID;
        this.address = BurstCrypto.getInstance().rsEncode(numericID);
    }

    /**
     * @param burstID The numeric id that represents this Burst Address
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static BurstAddress fromId(BurstID burstID) {
        return new BurstAddress(burstID);
    }

    /**
     * @param signedLongId The numeric id that represents this Burst Address, as a signed long
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static BurstAddress fromId(long signedLongId) {
        return new BurstAddress(BurstID.fromLong(signedLongId));
    }

    /**
     * @param unsignedLongId The numeric id that represents this Burst Address
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static BurstAddress fromId(String unsignedLongId) {
        return new BurstAddress(BurstID.fromLong(unsignedLongId));
    }

    public static BurstAddress fromRs(String RS) throws IllegalArgumentException {
        for(String addressPrefix : BurstKitUtils.getValidAddressPrefixes()) {
            if (RS.startsWith(addressPrefix+"-")) {
                RS = RS.substring(addressPrefix.length() + 1);
                break;
            }
        }
        // Remove the public key if present
        String shortRS = RS.substring(0, 20);
        BurstAddress address = new BurstAddress(BurstCrypto.getInstance().rsDecode(shortRS));
        
        if(RS.length() > 21) {
            // check if there is a public key
            String publicKeyBase36 = RS.substring(21);
            byte []publicKey = BurstCrypto.getInstance().parseBase36String(publicKeyBase36);
            
            BurstAddress addressCheck = BurstCrypto.getInstance().getBurstAddressFromPublic(publicKey);
            if(addressCheck.getBurstID().getSignedLongId() != address.getBurstID().getSignedLongId()) {
                throw new IllegalArgumentException("Reed-Solomon address and public key mismatch");
            }
            address.setPublicKey(publicKey);
        }
        
        return address;
    }

    /**
     * Try to parse an input as either a numeric ID or an RS address.
     *
     * @param input the numeric ID or RS address of the Burst address
     * @return a BurstAddress if one could be parsed from the input, null otherwise
     */
    public static BurstAddress fromEither(String input) {
        if (input == null) return null;
        try {
            return BurstAddress.fromId(BurstID.fromLong(input));
        } catch (IllegalArgumentException e1) {
            try {
                return BurstAddress.fromRs(input);
            } catch (IllegalArgumentException e2) {
                return null;
            }
        }
    }

    /**
     * @return The BurstID of this address
     */
    public BurstID getBurstID() {
        return numericID;
    }

    /**
     * @return The unsigned long numeric ID this BurstAddress points to
     */
    public String getID() {
        return numericID.getID();
    }

    /**
     * @return The signed long numeric ID this BurstAddress points to
     */
    public long getSignedLongId() {
        return numericID.getSignedLongId();
    }
    
    /**
     * @return The public key or null if not set 
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    private void setPublicKey(byte []publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * @return The ReedSolomon encoded address, without the "BURST-" prefix
     */
    public String getRawAddress() {
        return address;
    }

    /**
     * @return The ReedSolomon encoded address, with the "BURST-" prefix
     */
    public String getFullAddress() {
        if (address == null || address.length() == 0) {
            return "";
        } else {
            return BurstKitUtils.getAddressPrefix() +  "-" + address;
        }
    }

    @Override
    public String toString() {
        return getFullAddress();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BurstAddress && Objects.equals(numericID, ((BurstAddress) obj).numericID);
    }

    @Override
    public int hashCode() {
        return numericID.hashCode();
    }
}
