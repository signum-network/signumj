package burst.kit.entity.response;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.BurstID;
import burst.kit.entity.response.http.BroadcastTransactionResponse;
import burst.kit.service.impl.grpc.BrsApi;
import org.bouncycastle.util.encoders.Hex;

public class TransactionBroadcast {
    private final byte[] fullHash;
    private final BurstID transactionId;
    private final int numberPeersSentTo;

    public TransactionBroadcast(byte[] fullHash, BurstID transactionId, int numberPeersSentTo) {
        this.fullHash = fullHash;
        this.transactionId = transactionId;
        this.numberPeersSentTo = numberPeersSentTo;
    }

    public TransactionBroadcast(BroadcastTransactionResponse response) {
        this.fullHash = Hex.decode(response.getFullHash());
        this.transactionId = BurstID.fromLong(response.getTransactionID());
        this.numberPeersSentTo = response.getNumberPeersSentTo();
    }

    public TransactionBroadcast(BrsApi.TransactionBroadcastResult transactionBroadcastResult, byte[] transactionBytes) {
        BurstCrypto burstCrypto = BurstCrypto.getInstance();
        this.fullHash = burstCrypto.getSha256().digest(transactionBytes);
        this.transactionId = burstCrypto.hashToId(this.fullHash);
        this.numberPeersSentTo = transactionBroadcastResult.getNumberOfPeersSentTo();
    }

    public byte[] getFullHash() {
        return fullHash;
    }

    public BurstID getTransactionId() {
        return transactionId;
    }

    public int getNumberPeersSentTo() {
        return numberPeersSentTo;
    }
}
