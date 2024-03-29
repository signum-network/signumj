package signumj.examples;

import signumj.Constants;
import signumj.crypto.SignumCrypto;
import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.service.NodeService;
import signumj.service.TransactionBuilder;

/**
 * Example which sends 1 SIGNA to another account with a fee of 0.1 SIGNA.
 * 
 * Performs the cryptographic signature locally so does not send the passphrase
 * to over the wire.
 * 
 */
public class SendTransactionBlockingGet {

	public static void main(String[] args) {
        NodeService node = NodeService.getInstance(Constants.HTTP_NODE_EUROPE2);

        String passphrase = "YOUR SENDING ACCOUNT PASSPHRASE, USED TO SIGN MESSAGES LOCALLY";
        
        SignumAddress recipient = SignumAddress.fromRs("S-JJQS-MMA4-GHB4-4ZNZU");
        SignumValue amountToSend = SignumValue.fromSigna(1);
        SignumValue fee = SignumValue.fromSigna(0.1);
        int deadline = 1440; // deadline in minutes before this transaction becomes invalid
        
        TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MONEY,
        		SignumCrypto.getInstance().getPublicKey(passphrase), fee, deadline)
        		.recipient(recipient)
        		.amount(amountToSend);

        // Generate the transaction without signing it
        byte[] unsignedTransactionBytes = node.generateTransaction(tb).blockingGet();
        if(!tb.verify(unsignedTransactionBytes)) {
        	System.err.println("Transaction bytes mismatch!");
        	return;
        }
        
        // Locally sign the transaction using our passphrase
        byte[] signedTransactionBytes = SignumCrypto.getInstance().signTransaction(passphrase, unsignedTransactionBytes);
        
        // Broadcast the transaction through the node, still not sending it any sensitive information.
        node.broadcastTransaction(signedTransactionBytes).blockingGet();
    }
}
