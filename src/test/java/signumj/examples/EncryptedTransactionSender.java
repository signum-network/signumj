package signumj.examples;

import org.bouncycastle.util.encoders.Hex;

import io.reactivex.disposables.Disposable;
import signumj.crypto.SignumCrypto;
import signumj.entity.SignumValue;
import signumj.entity.response.TransactionBroadcast;
import signumj.entity.response.http.BRSError;
import signumj.service.NodeService;

/**
 * Example which sends 1 SIGNA to another account with a fee of 0.1 SIGNA
 * and includes an encrypted message which only the sender or recipient
 * can read. Performs all cryptographic functions (encrypting the message
 * and signing the transaction) offline so does not send the passphrase
 * to the node (which would be a huge security risk!)
 */
public class EncryptedTransactionSender {
	
    public Disposable sendTransactionWithEncryptedMessage(NodeService node, String passphrase, String recipientPublicKey,
    		SignumValue amountToSend, SignumValue fee, int deadline) {

    	SignumCrypto crypto = SignumCrypto.getInstance();
        byte[] recipientPublicKeyBytes = Hex.decode(recipientPublicKey);

        // Generate the transaction without signing it
        Disposable disposable = node.generateTransactionWithEncryptedMessage(
        		crypto.getAddressFromPublic(recipientPublicKeyBytes),
        		crypto.getPublicKey(passphrase), amountToSend, fee, deadline,
        		crypto.encryptTextMessage("Sent from SignumJ!", passphrase, recipientPublicKeyBytes), null)
                .flatMap(response -> {
                    // Now we need to locally sign the transaction.
                    // Get the unsigned transaction bytes from the node's response
                    byte[] unsignedTransactionBytes = response;
                    // Locally sign the transaction using our passphrase
                    byte[] signedTransactionBytes = crypto.signTransaction(passphrase, unsignedTransactionBytes);
                    // Broadcast the transaction through the node, still not sending it any sensitive information. Use this as the result of the flatMap so we do not have to call subscribe() twice
                    return node.broadcastTransaction(signedTransactionBytes);
                })
                .subscribe(this::onTransactionSent, this::handleError);
        
        return disposable;
    }
    
    private void onTransactionSent(TransactionBroadcast tx) {
        // Get the transaction ID of the newly sent transaction!
        System.out.println("Transaction sent! Transaction ID: " + tx.getTransactionId().getID());
    }
    
    private void handleError(Throwable t) {
        if (t instanceof BRSError) {
            System.out.println("Caught BRS Error: " + ((BRSError) t).getDescription());
        } else {
            t.printStackTrace();
        }
    }
}
