package burst.kit.service.impl;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.*;
import burst.kit.entity.response.*;
import burst.kit.service.BurstApiException;
import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.grpc.BrsApi;
import burst.kit.service.impl.grpc.BrsApiServiceGrpc;
import burst.kit.util.BurstKitUtils;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class GrpcBurstNodeService implements BurstNodeService {
    private final BrsApiServiceGrpc.BrsApiServiceBlockingStub brsGrpc;
    private final ManagedChannel connection;

    public GrpcBurstNodeService(String nodeAddress, String userAgent) {
        if (nodeAddress.startsWith("grpc://")) nodeAddress = nodeAddress.substring(7);
        this.connection = ManagedChannelBuilder
                .forTarget(nodeAddress)
                .userAgent(userAgent)
                .usePlaintext()
                .build();
        this.brsGrpc = BrsApiServiceGrpc.newBlockingStub(connection);
    }

    private <T> Observable<T> assign(Iterator<T> iterable) {
        return Observable.fromIterable(() -> iterable)
                .onErrorResumeNext(t -> {
                    if (t instanceof StatusRuntimeException && ((StatusRuntimeException) t).getStatus().getCode() == Status.Code.ABORTED) {
                        return Observable.error(new BurstApiException(((StatusRuntimeException) t).getStatus().getDescription()));
                    } else {
                        return Observable.error(t);
                    }
                });
    }

    private <T> Single<T> assign(Callable<T> callable) {
        return assign(Single.fromCallable(callable))
                .onErrorResumeNext(t -> {
                    if (t instanceof StatusRuntimeException && ((StatusRuntimeException) t).getStatus().getCode() == Status.Code.ABORTED) {
                        return Single.error(new BurstApiException(((StatusRuntimeException) t).getStatus().getDescription()));
                    } else {
                        return Single.error(t);
                    }
                });
    }

    private <T> Single<T> assign(Single<T> single) {
        return single.subscribeOn(BurstKitUtils.defaultBurstNodeServiceScheduler());
    }

    private final BrsApi.GetAccountRequest getAccountRequestFromId(BurstAddress id) {
        return BrsApi.GetAccountRequest.newBuilder()
                .setAccountId(id.getBurstID().getSignedLongId())
                .build();
    }

    private final BrsApi.GetByIdRequest getByIdRequestFromId(BurstID id) {
        return BrsApi.GetByIdRequest.newBuilder()
                .setId(id.getSignedLongId())
                .build();
    }

    @Override
    public Single<Block> getBlock(BurstID block) {
        return assign(() -> brsGrpc.getBlock(
                BrsApi.GetBlockRequest.newBuilder()
                        .setBlockId(block.getSignedLongId())
                        .build()))
                .map(Block::new);
    }

    @Override
    public Single<Block> getBlock(int height) {
        return assign(() -> brsGrpc.getBlock(
                BrsApi.GetBlockRequest.newBuilder()
                        .setHeight(height)
                        .build()))
                .map(Block::new);
    }

    @Override
    public Single<Block> getBlock(BurstTimestamp timestamp) {
        return assign(() -> brsGrpc.getBlock(
                BrsApi.GetBlockRequest.newBuilder()
                        .setTimestamp(timestamp.getTimestamp())
                        .build()))
                .map(Block::new);
    }

    @Override
    public Single<Block[]> getBlocks(int firstIndex, int lastIndex) {
        return assign(() -> brsGrpc.getBlocks(
                BrsApi.GetBlocksRequest.newBuilder()
                        .setIncludeTransactions(false)
                        .setIndexRange(
                                BrsApi.IndexRange.newBuilder()
                                        .setFirstIndex(firstIndex)
                                        .setLastIndex(lastIndex)
                                        .build())
                        .build()))
                .map(blocks -> blocks.getBlocksList()
                        .stream()
                        .map(Block::new)
                        .toArray(Block[]::new));
    }

    @Override
    public Single<Constants> getConstants() {
        return assign(() -> brsGrpc.getConstants(Empty.getDefaultInstance()))
                .map(Constants::new);
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId, Integer height, Boolean calculateCommitment) {
    	// FIXME: adjust grpc arguments
        return assign(() -> brsGrpc.getAccount(getAccountRequestFromId(accountId)))
                .map(Account::new);
    }

    @Override
    public Single<AT[]> getAccountATs(BurstAddress accountId) {
        return assign(() -> brsGrpc.getAccountATs(getAccountRequestFromId(accountId)))
                .map(accountATs -> accountATs.getAtsList()
                        .stream()
                        .map(AT::new)
                        .toArray(AT[]::new));
    }

    @Override
    public Single<Block[]> getAccountBlocks(BurstAddress accountId) {
        return assign(() -> brsGrpc.getAccountBlocks(
                BrsApi.GetAccountBlocksRequest.newBuilder()
                        .setAccountId(accountId.getBurstID().getSignedLongId())
                        .setIncludeTransactions(false)
                        .build()))
                .map(accountATs -> accountATs.getBlocksList()
                        .stream()
                        .map(Block::new)
                        .toArray(Block[]::new));
    }

    @Override
    public Single<BurstID[]> getAccountTransactionIDs(BurstAddress accountId) { // TODO should this be deprecated?
        return getAccountTransactions(accountId, null, null, null)
                .map(transactions -> Arrays.stream(transactions)
                        .map(Transaction::getId)
                        .toArray(BurstID[]::new));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(BurstAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect) {
    	// TODO includeIndirect is not taken into account
        return assign(() -> brsGrpc.getAccountTransactions(
                BrsApi.GetAccountTransactionsRequest.newBuilder()
                        .setAccountId(accountId.getBurstID().getSignedLongId())
                        .setIndexRange((firstIndex == null || lastIndex == null) ? BrsApi.IndexRange.getDefaultInstance() : BrsApi.IndexRange.newBuilder().setFirstIndex(firstIndex).setLastIndex(lastIndex).build())
                        .build()))
                .map(transactions -> transactions.getTransactionsList()
                        .stream()
                        .map(Transaction::new)
                        .toArray(Transaction[]::new));
    }

    @Override
    public Single<Transaction[]> getUnconfirmedTransactions(BurstAddress accountId) {
        return assign(() -> brsGrpc.getUnconfirmedTransactions(
                BrsApi.GetAccountRequest.newBuilder()
                        .setAccountId(accountId == null ? 0 : accountId.getBurstID().getSignedLongId())
                        .build()))
                .map(transactions -> transactions.getUnconfirmedTransactionsList()
                        .stream()
                        .map(Transaction::new)
                        .toArray(Transaction[]::new));
    }

    @Override
    public Single<BurstAddress[]> getAccountsWithRewardRecipient(BurstAddress accountId) {
        return assign(() -> brsGrpc.getAccounts(
                BrsApi.GetAccountsRequest.newBuilder()
                        .setRewardRecipient(accountId.getBurstID().getSignedLongId())
                        .setIncludeAccounts(false)
                        .build()))
                .map(accounts -> accounts.getIdsList()
                        .stream()
                        .map(BurstAddress::fromId)
                        .toArray(BurstAddress[]::new));
    }

    @Override
    public Single<AssetBalance[]> getAssetBalances(BurstID assetId, Integer firstIndex, Integer lastIndex) {
        return assign(() -> brsGrpc.getAssetBalances(
                BrsApi.GetAssetBalancesRequest.newBuilder()
                        .setAsset(assetId.getSignedLongId())
                        .setIndexRange((firstIndex == null || lastIndex == null) ? BrsApi.IndexRange.getDefaultInstance() : BrsApi.IndexRange.newBuilder().setFirstIndex(firstIndex).setLastIndex(lastIndex).build())
                        .build()))
                .map(assetBalances -> assetBalances.getAssetBalancesList()
                        .stream()
                        .map(AssetBalance::new)
                        .toArray(AssetBalance[]::new));
    }

    @Override
    public Single<AssetTrade[]> getAssetTrades(BurstID assetId, BurstAddress account, Integer firstIndex, Integer lastIndex) {
        return assign(() -> brsGrpc.getAssetTrades(
                BrsApi.GetAssetTransfersRequest.newBuilder()
                        .setAsset(assetId.getSignedLongId())
                        .setAccount(account.getSignedLongId())
                        .setIndexRange((firstIndex == null || lastIndex == null) ? BrsApi.IndexRange.getDefaultInstance() : BrsApi.IndexRange.newBuilder().setFirstIndex(firstIndex).setLastIndex(lastIndex).build())
                        .build()))
                .map(assetTrades -> assetTrades.getTradesList()
                        .stream()
                        .map(AssetTrade::new)
                        .toArray(AssetTrade[]::new));
    }

    @Override
    public Single<AssetOrder[]> getAskOrders(BurstID assetId) {
        return assign(() -> brsGrpc.getOrders(
                BrsApi.GetOrdersRequest.newBuilder()
                        .setAsset(assetId.getSignedLongId())
                        .setOrderType(BrsApi.AssetOrderType.ASK)
                        .build()))
                .map(orders -> orders.getOrdersList()
                        .stream()
                        .map(AssetOrder::new)
                        .toArray(AssetOrder[]::new));
    }

    @Override
    public Single<AssetOrder[]> getBidOrders(BurstID assetId) {
        return assign(() -> brsGrpc.getOrders(
                BrsApi.GetOrdersRequest.newBuilder()
                        .setAsset(assetId.getSignedLongId())
                        .setOrderType(BrsApi.AssetOrderType.BID)
                        .build()))
                .map(orders -> orders.getOrdersList()
                        .stream()
                        .map(AssetOrder::new)
                        .toArray(AssetOrder[]::new));
    }

    @Override
    public Single<AT> getAt(BurstAddress atId) {
        return assign(() -> brsGrpc.getAT(getByIdRequestFromId(atId.getBurstID())))
                .map(AT::new);
    }

    @Override
    public Single<BurstAddress[]> getAtIds() {
        return assign(() -> brsGrpc.getATIds(Empty.getDefaultInstance()))
                .map(atIds -> atIds.getIdsList()
                        .stream()
                        .map(BurstAddress::fromId)
                        .toArray(BurstAddress[]::new));
    }

    @Override
    public Single<Transaction> getTransaction(BurstID transactionId) {
        return assign(() -> brsGrpc.getTransaction(
                BrsApi.GetTransactionRequest.newBuilder()
                        .setTransactionId(transactionId.getSignedLongId())
                        .build()))
                .map(Transaction::new);
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return assign(() -> brsGrpc.getTransaction(
                BrsApi.GetTransactionRequest.newBuilder()
                        .setFullHash(ByteString.copyFrom(fullHash))
                        .build()))
                .map(Transaction::new);
    }

    @Override
    public Single<byte[]> getTransactionBytes(BurstID transactionId) { // TODO should this be deprecated?
        return assign(() -> brsGrpc.getTransaction(
                BrsApi.GetTransactionRequest.newBuilder()
                        .setTransactionId(transactionId.getSignedLongId())
                        .build()))
                .map(transaction -> brsGrpc.getTransactionBytes(transaction.getTransaction()))
                .map(transactionBytes -> transactionBytes.getTransactionBytes().toByteArray());
    }

    private Single<Any> ordinaryTransactionOrArbitraryMessage(BurstValue amount) {
        return Single.fromCallable(() -> {
            if (amount.equals(BurstValue.ZERO)) {
                return Any.pack(BrsApi.ArbitraryMessageAttachment.getDefaultInstance());
            } else {
                return Any.pack(BrsApi.OrdinaryPaymentAttachment.getDefaultInstance());
            }
        });
    }

    private BrsApi.BasicTransaction.Builder basicTransaction(byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Any attachment) {
        BurstCrypto burstCrypto = BurstCrypto.getInstance();
        return BrsApi.BasicTransaction.newBuilder()
                .setSenderPublicKey(ByteString.copyFrom(senderPublicKey))
                .setSenderId(burstCrypto.getBurstAddressFromPublic(senderPublicKey).getSignedLongId())
                .setAmount(amount.toPlanck().longValueExact())
                .setFee(fee.toPlanck().longValueExact())
                .setDeadline(deadline)
                .setAttachment(attachment);
    }

    private BrsApi.BasicTransaction.Builder basicTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Any attachment) {
        return basicTransaction(senderPublicKey, amount, fee, deadline, attachment)
                .setRecipient(recipient.getSignedLongId());
    }

    @Override
    public Single<byte[]> generateTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline) {
        return ordinaryTransactionOrArbitraryMessage(amount)
                .map(attachment -> basicTransaction(recipient, senderPublicKey, amount, fee, deadline, attachment)
                        .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message) {
        return ordinaryTransactionOrArbitraryMessage(amount)
                .map(attachment -> basicTransaction(recipient, senderPublicKey, amount, fee, deadline, attachment)
                        .addAppendages(Any.pack(BrsApi.MessageAppendix.newBuilder()
                                .setMessage(ByteString.copyFrom(message.getBytes(StandardCharsets.UTF_8)))
                                .setIsText(true)
                                .build()))
                        .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, String message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message) {
        return ordinaryTransactionOrArbitraryMessage(amount)
                .map(attachment -> basicTransaction(recipientAddress, senderPublicKey, amount, fee, deadline, attachment)
                        .addAppendages(Any.pack(BrsApi.MessageAppendix.newBuilder()
                                .setMessage(ByteString.copyFrom(message.getBytes(StandardCharsets.UTF_8)))
                                .setIsText(true)
                                .build()))
                        .addAppendages(Any.pack(BrsApi.PublicKeyAnnouncementAppendix.newBuilder()
                                .setRecipientPublicKey(ByteString.copyFrom(recipientPublicKey))
                                .build()))
                        .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, BurstValue fee, int deadline, String message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, byte[] message) {
        return ordinaryTransactionOrArbitraryMessage(amount)
                .map(attachment -> basicTransaction(recipient, senderPublicKey, amount, fee, deadline, attachment)
                        .addAppendages(Any.pack(BrsApi.MessageAppendix.newBuilder()
                                .setMessage(ByteString.copyFrom(message))
                                .setIsText(false)
                                .build()))
                        .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, byte[] message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return ordinaryTransactionOrArbitraryMessage(amount)
                .map(attachment -> basicTransaction(recipient, senderPublicKey, amount, fee, deadline, attachment)
                        .addAppendages(Any.pack(BrsApi.EncryptedMessageAppendix.newBuilder()
                                .setEncryptedData(BrsApi.EncryptedData.newBuilder()
                                        .setData(ByteString.copyFrom(message.getData()))
                                        .setNonce(ByteString.copyFrom(message.getNonce()))
                                        .build())
                                .setIsText(message.isText())
                                .setType(BrsApi.EncryptedMessageAppendix.Type.TO_RECIPIENT)
                                .build()))
                        .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO

    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return ordinaryTransactionOrArbitraryMessage(amount)
                .map(attachment -> basicTransaction(recipient, senderPublicKey, amount, fee, deadline, attachment)
                        .addAppendages(Any.pack(BrsApi.EncryptedMessageAppendix.newBuilder()
                                .setEncryptedData(BrsApi.EncryptedData.newBuilder()
                                        .setData(ByteString.copyFrom(message.getData()))
                                        .setNonce(ByteString.copyFrom(message.getNonce()))
                                        .build())
                                .setIsText(message.isText())
                                .setType(BrsApi.EncryptedMessageAppendix.Type.TO_SELF)
                                .build()))
                        .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return assign(() -> brsGrpc.suggestFee(Empty.getDefaultInstance()))
                .map(FeeSuggestion::new);
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
        return assign(brsGrpc.getMiningInfo(Empty.getDefaultInstance()))
                .map(MiningInfo::new);
    }

    @Override
    public Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes) {
        return assign(() -> brsGrpc.broadcastTransactionBytes(BrsApi.TransactionBytes.newBuilder()
                .setTransactionBytes(ByteString.copyFrom(transactionBytes))
                .build()))
                .map(result -> new TransactionBroadcast(result, transactionBytes));
    }

    @Override
    public Single<BurstAddress> getRewardRecipient(BurstAddress address) {
        return assign(() -> brsGrpc.getAccount(getAccountRequestFromId(address)))
                .map(account -> BurstAddress.fromId(account.getRewardRecipient()));
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, BurstID accountId) {
        return assign(() -> brsGrpc.submitNonce(BrsApi.SubmitNonceRequest.newBuilder()
                .setSecretPhrase(passphrase)
                .setAccount(accountId == null ? 0 : accountId.getSignedLongId())
                .setNonce(Long.parseUnsignedLong(nonce))
                .build()))
                .map(BrsApi.SubmitNonceResponse::getDeadline);
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, Map<BurstAddress, BurstValue> recipients) throws IllegalArgumentException {
        return Single.fromCallable(() -> {
            BurstValue totalValue = BurstValue.ZERO;
            for (BurstValue value : recipients.values()) {
                totalValue = totalValue.add(value);
            }
            return totalValue;
        }).map(totalValue -> basicTransaction(senderPublicKey, totalValue, fee, deadline, Any.pack(BrsApi.MultiOutAttachment.newBuilder()
                .setVersion(1)
                .addAllRecipients(() -> recipients.entrySet().stream()
                        .map(entry -> BrsApi.MultiOutAttachment.MultiOutRecipient.newBuilder()
                                .setRecipient(entry.getKey().getSignedLongId())
                                .setAmount(entry.getValue().toPlanck().longValueExact())
                                .build())
                        .iterator()).build()))
                .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Set<BurstAddress> recipients) throws IllegalArgumentException {
        return Single.fromCallable(() -> basicTransaction(senderPublicKey, amount, fee, deadline, Any.pack(BrsApi.MultiOutSameAttachment.newBuilder()
                .setVersion(1)
                .addAllRecipients(() -> recipients.stream()
                        .map(BurstAddress::getSignedLongId)
                        .iterator()).build()))
                .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, String name, String description, byte[] creationBytes) {
        return Single.fromCallable(() -> basicTransaction(senderPublicKey, BurstValue.ZERO, fee, deadline, Any.pack(BrsApi.ATCreationAttachment.newBuilder()
                .setVersion(1)
                .setName(name)
                .setDescription(description)
                .setCreationBytes(ByteString.copyFrom(creationBytes))
                .build()))
                .build())
                .map(brsGrpc::completeBasicTransaction)
                .map(brsGrpc::getTransactionBytes)
                .map(bytes -> bytes.getTransactionBytes().toByteArray());
    }

    @Override
    public Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, BurstValue quantity, int decimals, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline, String message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, BurstID assetId, BurstValue quantity, BurstValue price, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, BurstID assetId, BurstValue quantity, BurstValue price, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, BurstID orderID, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, BurstID orderID, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateSubscriptionCreationTransaction(byte[] senderPublicKey, BurstValue amount, int frequency, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, BurstID subscription, BurstValue fee, int deadline) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public Single<Asset> getAsset(BurstID assetID) {
        return Single.error(new UnsupportedOperationException("GRPC Client does not support this API call yet")); // TODO
    }

    @Override
    public void close() throws Exception {
        connection.shutdown();
        connection.awaitTermination(10, TimeUnit.SECONDS);
        while (!connection.isTerminated()) {
            connection.shutdownNow();
        }
    }
}
