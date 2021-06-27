package signumj.examples;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import signumj.Constants;
import signumj.crypto.SignumCrypto;
import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.AssetBalance;
import signumj.entity.response.TransactionBroadcast;
import signumj.service.NodeService;

/**
 * Distribute the entire balance of a given account to token holders according to their shares.
 * 
 * There is also a list of addresses that should be ignored in the distribution.
 * 
 */
public class DistributionToTokenHolders {
	
	/**
	 * Addresses that should be ignored when checking for the holdings.
	 * This usually contains the issuer account, treasury accounts, and similar.
	 */
	static final String []IGNORED_ADDRESSES = {
			"S-JJQS-MMA4-GHB4-4ZNZU",
	};
	
	/**
	 * Adjust with your own token ID
	 */
	static final SignumID TOKEN_ID = SignumID.fromLong("12402415494995249540");
	
	/**
	 * Adjust with your passphrase, it is never sent over the wire.
	 */
	static final String PASSPHRASE = "YOUR PASSPHRASE GOES HERE";

	public static void main(String[] args) {
		
		NodeService node = NodeService.getInstance(Constants.HTTP_NODE_EUROPE1);
		SignumCrypto crypto = SignumCrypto.getInstance();
		
		SignumValue fee = node.suggestFee().blockingGet().getStandardFee();
		
		SignumAddress sender = crypto.getAddressFromPassphrase(PASSPHRASE);
		
		SignumValue balance = node.getAccount(sender).blockingGet().getBalance();
		
		System.out.println("Checking all token holders...");

		Integer first = 0;
		Integer delta = 400;
		SignumValue tokenOnHolders = SignumValue.fromSigna(0);
		ArrayList<AssetBalance> holders = new ArrayList<>();
		while(true) {
			AssetBalance[] accounts = node.getAssetBalances(TOKEN_ID, first, first+delta).blockingGet();
			if(accounts == null || accounts.length == 0)
				break;
			
			for(AssetBalance ac : accounts) {
				boolean shouldIgnore = false;
				for(String ignored : IGNORED_ADDRESSES) {
					SignumAddress ignoredAddres = SignumAddress.fromEither(ignored);
					
					if(ignoredAddres.getSignedLongId() == ac.getAccountAddress().getSignedLongId()) {
						shouldIgnore = true;
						break;
					}
				}
				if(shouldIgnore)
					continue;
				
				tokenOnHolders = tokenOnHolders.add(ac.getBalance());
				holders.add(ac);
				System.out.println(ac.getAccountAddress().getFullAddress() + " " + ac.getBalance().longValue());
			}
			first += delta;
		}
		
		SignumValue totalFee = fee.multiply(holders.size()/64 + 1);
		
		System.out.println("Total amount on holders: " + tokenOnHolders);
		System.out.println("Total fee necessary: " + totalFee.toFormattedString());
		System.out.println("Amount to holders: " + balance.toFormattedString());
		System.out.println("Distributor account: " + sender.getFullAddress());
		
		SignumValue amountToSend = balance.subtract(totalFee);
		if(amountToSend.longValue() < 0) {
			System.err.println("Not enough SIGNA to pay for the transactions");
			System.exit(-1);
			return;
		}
		
		// Ask for confirmation
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter 'yes' to confirm and proceed: ");
		String response = scanner.next();
		scanner.close();
		
		if(!"yes".equalsIgnoreCase(response)) {
			System.err.println("Aborted.");
			System.exit(-4);
		}
		
		ArrayList<SignumAddress> paid = new ArrayList<>();
		
        Map<SignumAddress, SignumValue> recipients = new LinkedHashMap<SignumAddress, SignumValue>();
        long txAmount = 0;
		for(AssetBalance rec : holders) {
			if(paid.contains(rec.getAccountAddress()))
				continue;
			SignumValue amount = balance.multiply(rec.getBalance().longValue()).divide(tokenOnHolders.longValue());
			txAmount += amount.longValue();
			
			recipients.put(rec.getAccountAddress(), amount);
			if(recipients.size()==64) {
				sendToRecipients(node, crypto, recipients, fee, txAmount);
				recipients.clear();
				txAmount = 0;
			}
		}
		
		if(recipients.size() > 0) {
			sendToRecipients(node, crypto, recipients, fee, txAmount);
		}
	}
	
	public static void sendToRecipients(NodeService node, SignumCrypto crypto,
			Map<SignumAddress, SignumValue> recipients, SignumValue fee, long txAmount) {
		int deadline = 1440;

		System.out.println("Sending to " + recipients.size() + " recipients a total of " + txAmount);
		byte []utx = null;
		if(recipients.values().size() == 1) {
			utx = node.generateTransaction(recipients.keySet().iterator().next(),
					crypto.getPublicKey(PASSPHRASE), recipients.values().iterator().next(), fee, deadline, null).blockingGet();
		}
		else {
			utx = node.generateMultiOutTransaction(crypto.getPublicKey(PASSPHRASE), fee, deadline, recipients).blockingGet();
		}
		try {
			byte []stx = crypto.signTransaction(PASSPHRASE, utx);
			TransactionBroadcast txBroadcast = node.broadcastTransaction(stx).blockingGet();
			System.out.println("Transaction " + txBroadcast.getTransactionId().getID() + " has been broadcast.");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-5);
			return;
		}
	}
}
