package signumj.entity;

import signumj.crypto.SignumCrypto;
import signumj.util.SignumUtils;

import java.util.Objects;

import org.bouncycastle.util.encoders.Hex;

public final class SignumAddress {

    /**
     * Stored without "BURST-" prefix.
     */
    private final String address;
    private final SignumID numericID;
    private byte[] publicKey;

    private SignumAddress(SignumID burstID) {
        this.numericID = burstID;
        this.address = SignumCrypto.getInstance().rsEncode(numericID);
    }

    /**
     * @param burstID The numeric id that represents this Burst Address
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static SignumAddress fromId(SignumID burstID) {
        return new SignumAddress(burstID);
    }

    /**
     * @param signedLongId The numeric id that represents this Burst Address, as a signed long
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static SignumAddress fromId(long signedLongId) {
        return new SignumAddress(SignumID.fromLong(signedLongId));
    }

    /**
     * @param unsignedLongId The numeric id that represents this Burst Address
     * @return A BurstAddress object that represents the specified numericId
     * @throws NumberFormatException if the numericId is not a valid number
     * @throws IllegalArgumentException if the numericId is outside the range of accepted numbers (less than 0 or greater than / equal to 2^64)
     */
    public static SignumAddress fromId(String unsignedLongId) {
        return new SignumAddress(SignumID.fromLong(unsignedLongId));
    }

    public static SignumAddress fromRs(String RS) throws IllegalArgumentException {
        for(String addressPrefix : SignumUtils.getValidAddressPrefixes()) {
            if (RS.startsWith(addressPrefix+"-")) {
                RS = RS.substring(addressPrefix.length() + 1);
                break;
            }
        }
        // Remove the public key if present
        String shortRS = RS.substring(0, 20);
        SignumAddress address = new SignumAddress(SignumCrypto.getInstance().rsDecode(shortRS));
        
        if(RS.length() > 21) {
            // check if there is a public key
            String publicKeyBase36 = RS.substring(21);
            byte []publicKey = SignumCrypto.getInstance().parseBase36String(publicKeyBase36);
            
            SignumAddress addressCheck = SignumCrypto.getInstance().getAddressFromPublic(publicKey);
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
    public static SignumAddress fromEither(String input) {
        if (input == null) return null;
        try {
            return SignumAddress.fromId(SignumID.fromLong(input));
        } catch (IllegalArgumentException e1) {
            try {
                return SignumAddress.fromRs(input);
            } catch (IllegalArgumentException e2) {
                return null;
            }
        }
    }

    /**
     * @return The BurstID of this address
     */
    public SignumID getBurstID() {
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
    
    /**
     * @return The public key or null if not set 
     */
    public String getPublicKeyString() {
        return publicKey == null ? null : Hex.toHexString(publicKey);
    }

    /**
     * @param publicKey
     */
    public void setPublicKey(byte []publicKey) {
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
            return SignumUtils.getAddressPrefix() +  "-" + address;
        }
    }
    
    /**
     * @return The ReedSolomon encoded address, with the prefix and base36 encoded public key suffix
     */
    public String getExtendedAddress() {
    	String extendedAddress = getFullAddress();
    	if(publicKey != null) {
    		extendedAddress += "-" + SignumCrypto.getInstance().toBase36String(publicKey);
    	}
    	
    	return extendedAddress;
    }

    @Override
    public String toString() {
        return getFullAddress();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SignumAddress && Objects.equals(numericID, ((SignumAddress) obj).numericID);
    }

    @Override
    public int hashCode() {
        return numericID.hashCode();
    }
}
