package signumj.examples;


import java.util.ArrayList;

import signumj.Constants;
import signumj.entity.SignumID;
import signumj.entity.SignumValue;
import signumj.entity.response.AssetBalance;
import signumj.service.NodeService;

/**
 * List all token holders of a given token.
 * 
 */
public class ListTokenHolders {
	
	/**
	 * Adjust with your own token ID
	 */
	static final SignumID TOKEN_ID = SignumID.fromLong("12402415494995249540");
	
	public static void main(String[] args) {
		
		NodeService node = NodeService.getInstance(Constants.HTTP_NODE_EUROPE1);
		
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
				tokenOnHolders = tokenOnHolders.add(ac.getBalance());
				holders.add(ac);
				System.out.println(ac.getAccountAddress().getFullAddress() + " " + ac.getBalance().longValue());
			}
			first += delta;
		}
		
	}
}
