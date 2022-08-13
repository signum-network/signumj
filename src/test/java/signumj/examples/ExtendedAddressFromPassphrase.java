package signumj.examples;


import signumj.crypto.SignumCrypto;
import signumj.entity.SignumAddress;

/**
 * Offline extended address generation from passphrase.
 * 
 */
public class ExtendedAddressFromPassphrase {
	
	public static void main(String[] args) {
		
		String passphrase = "ENTER THE DESIRED PASSPHRASE HERE";
		
		SignumAddress address = SignumCrypto.getInstance().getAddressFromPassphrase(passphrase);
		
		System.out.println("Extended address: " + address.getExtendedAddress());
	}
}
