package burst.kit.service.impl;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.*;
import burst.kit.entity.response.*;
import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.grpc.BrsApiServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;
import java.util.Set;

public class GrpcBurstNodeService implements BurstNodeService {

    private final BrsApiServiceGrpc.BrsApiServiceBlockingStub brsGrpc;
    private final BurstCrypto burstCrypto = BurstCrypto.getInstance();

    public GrpcBurstNodeService(String nodeAddress) {
        // TODO don't use plaintext
        this.brsGrpc = BrsApiServiceGrpc.newBlockingStub(ManagedChannelBuilder.forTarget(nodeAddress).usePlaintext().build());
    }

    @Override
    public Single<Block> getBlock(BurstID block) {
        return null;
    }

    @Override
    public Single<Block> getBlock(long height) {
        return null;
    }

    @Override
    public Single<Block> getBlock(BurstTimestamp timestamp) {
        return null;
    }

    @Override
    public Single<BurstID> getBlockId(long height) {
        return null;
    }

    @Override
    public Single<Block[]> getBlocks(long firstIndex, long lastIndex) {
        return null;
    }

    @Override
    public Single<Constants> getConstants() {
        return null;
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<AT[]> getAccountATs(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<BurstID[]> getAccountBlockIDs(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<Block[]> getAccountBlocks(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<BurstID[]> getAccountTransactionIDs(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<BurstAddress[]> getAccountsWithRewardRecipient(BurstAddress accountId) {
        return null;
    }

    @Override
    public Single<AT> getAt(BurstID atId) {
        return null;
    }

    @Override
    public Single<BurstID[]> getAtIds() {
        return null;
    }

    @Override
    public Single<Transaction> getTransaction(BurstID transactionId) {
        return null;
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return null;
    }

    @Override
    public Single<byte[]> getTransactionBytes(BurstID transactionId) {
        return null;
    }

    @Override
    public Single<byte[]> generateTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline) {
        return null;
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message) {
        return null;
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, byte[] message) {
        return null;
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return null;
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return null;
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return null;
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
        return null;
    }

    @Override
    public Single<Integer> broadcastTransaction(byte[] transactionBytes) {
        return null;
    }

    @Override
    public Single<BurstAddress> getRewardRecipient(BurstAddress account) {
        return null;
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, BurstID accountId) {
        return null;
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, Map<BurstAddress, BurstValue> recipients) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Set<BurstAddress> recipients) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, String name, String description, byte[] creationBytes, byte[] code, byte[] data, int dpages, int cspages, int uspages, BurstValue minActivationAmount) {
        return null;
    }
}
