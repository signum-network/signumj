package signumj.examples;

import signumj.Constants;
import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.entity.response.Transaction;
import signumj.response.appendix.PlaintextMessageAppendix;
import signumj.service.NodeService;

/**
 * Listing all recent transactions for a given account and public messages if some.
 * 
 */
public class RecentTransactionsWithPublicMessages {

	public static void main(String[] args) {
        NodeService node = NodeService.getInstance(Constants.HTTP_NODE_EUROPE2);

        SignumAddress account = SignumAddress.fromRs("S-JJQS-MMA4-GHB4-4ZNZU");
        
        // Use the indices to get more transactions if you want.
        Transaction[] txs = node.getAccountTransactions(account, 0, 100, true).blockingGet();

        for(Transaction tx : txs) {
        	SignumValue amount = tx.getAmount();
        	if(tx.getSender().equals(account))
        		amount = amount.multiply(-1d);
        	
        	String message = null;
        	if(tx.getAppendages()!=null && tx.getAppendages().length > 0
        			&& tx.getAppendages()[0] instanceof PlaintextMessageAppendix) {
        		message = ((PlaintextMessageAppendix) tx.getAppendages()[0]).getMessage();
        	}
        	
        	System.out.println("tx id: " + tx.getId().getID()+ ", amount: " + amount.toFormattedString() +
        			(message != null ? ", message: " + message : ""));
        }
    }
}
