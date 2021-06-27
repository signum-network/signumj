package signumj.entity.response;

import signumj.entity.SignumID;
import signumj.entity.response.http.BroadcastTransactionResponse;

import org.bouncycastle.util.encoders.Hex;

public class TransactionBroadcast {
    private final byte[] fullHash;
    private final SignumID transactionId;
    private final int numberPeersSentTo;

    public TransactionBroadcast(byte[] fullHash, SignumID transactionId, int numberPeersSentTo) {
        this.fullHash = fullHash;
        this.transactionId = transactionId;
        this.numberPeersSentTo = numberPeersSentTo;
    }

    public TransactionBroadcast(BroadcastTransactionResponse response) {
        this.fullHash = Hex.decode(response.getFullHash());
        this.transactionId = SignumID.fromLong(response.getTransactionID());
        this.numberPeersSentTo = response.getNumberPeersSentTo();
    }

    public byte[] getFullHash() {
        return fullHash;
    }

    public SignumID getTransactionId() {
        return transactionId;
    }

    public int getNumberPeersSentTo() {
        return numberPeersSentTo;
    }
}
