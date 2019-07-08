package burst.kit.service.impl;

import burst.kit.entity.*;
import burst.kit.entity.response.*;
import burst.kit.entity.response.http.*;
import burst.kit.service.BurstApiException;
import burst.kit.service.BurstNodeService;
import burst.kit.util.BurstKitUtils;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import org.bouncycastle.util.encoders.Hex;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public final class HttpBurstNodeService implements BurstNodeService {

    private BlockchainService blockchainService;

    public HttpBurstNodeService(String nodeAddress, String providedUserAgent) {
        String userAgent = providedUserAgent == null ? "burstkit4j/"+ burst.kit.Constants.VERSION : providedUserAgent;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                        .header("User-Agent", userAgent)
                        .build()))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(nodeAddress)
                .addConverterFactory(GsonConverterFactory.create(BurstKitUtils.buildGson().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        blockchainService = retrofit.create(BlockchainService.class);
    }
    
    private <T> Single<T> assign(Single<T> source) {
        return source.map(this::checkBrsResponse)
                .subscribeOn(BurstKitUtils.defaultBurstNodeServiceScheduler());
    }

    private <T> Observable<T> assign(Observable<T> source) {
        return source.subscribeOn(BurstKitUtils.defaultBurstNodeServiceScheduler());
    }

    private <T> T checkBrsResponse(T source) throws BRSError {
        if (source instanceof BRSResponse) {
            ((BRSResponse) source).throwIfError();
        }
        return source;
    }

    @Override
    public Single<Block> getBlock(BurstID block) {
        return assign(blockchainService.getBlock(BurstKitUtils.getEndpoint(), block.getID(), null, null, null))
                .map(Block::new);
    }

    @Override
    public Single<Block> getBlock(int height) {
        return assign(blockchainService.getBlock(BurstKitUtils.getEndpoint(), null, String.valueOf(height), null, null))
                .map(Block::new);
    }

    @Override
    public Single<Block> getBlock(BurstTimestamp timestamp) {
        return assign(blockchainService.getBlock(BurstKitUtils.getEndpoint(), null, null, String.valueOf(timestamp.getTimestamp()), null))
                .map(Block::new);
    }

    @Override
    public Single<BurstID> getBlockId(int height) {
        return assign(blockchainService.getBlockID(BurstKitUtils.getEndpoint(), String.valueOf(height)))
                .map(response -> BurstID.fromLong(response.getBlockID()));
    }

    @Override
    public Single<Block[]> getBlocks(int firstIndex, int lastIndex) {
        return assign(blockchainService.getBlocks(BurstKitUtils.getEndpoint(), String.valueOf(firstIndex), String.valueOf(lastIndex), null))
                .map(response -> Arrays.stream(response.getBlocks())
                        .map(Block::new)
                        .collect(Collectors.toList())
                        .toArray(new Block[0]));
    }

    @Override
    public Single<Constants> getConstants() {
        return assign(blockchainService.getConstants(BurstKitUtils.getEndpoint()))
                .map(Constants::new);
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId) {
        return assign(blockchainService.getAccount(BurstKitUtils.getEndpoint(), accountId.getID()))
                .map(Account::new);
    }

    @Override
    public Single<AT[]> getAccountATs(BurstAddress accountId) {
        return assign(blockchainService.getAccountATs(BurstKitUtils.getEndpoint(), accountId.getID()))
                .map(response -> Arrays.stream(response.getATs())
                        .map(AT::new)
                        .toArray(AT[]::new));
    }

    @Override
    public Single<BurstID[]> getAccountBlockIDs(BurstAddress accountId) {
        return assign(blockchainService.getAccountBlockIDs(BurstKitUtils.getEndpoint(), accountId.getID(), null, null, null))
                .map(response -> Arrays.stream(response.getBlockIds())
                        .map(BurstID::fromLong)
                        .toArray(BurstID[]::new));
    }

    @Override
    public Single<Block[]> getAccountBlocks(BurstAddress accountId) {
        return assign(blockchainService.getAccountBlocks(BurstKitUtils.getEndpoint(), accountId.getID(), null, null, null, null))
                .map(response -> Arrays.stream(response.getBlocks())
                        .map(Block::new)
                        .toArray(Block[]::new));
    }

    @Override
    public Single<BurstID[]> getAccountTransactionIDs(BurstAddress accountId) {
        return assign(blockchainService.getAccountTransactionIDs(BurstKitUtils.getEndpoint(), accountId.getID(), null, null, null, null, null, null))
                .map(response -> Arrays.stream(response.getTransactionIds())
                        .map(BurstID::fromLong)
                        .toArray(BurstID[]::new));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(BurstAddress accountId) {
        return assign(blockchainService.getAccountTransactions(BurstKitUtils.getEndpoint(), accountId.getID(), null, null, null, null, null, null))
                .map(response -> Arrays.stream(response.getTransactions())
                        .map(Transaction::new)
                        .toArray(Transaction[]::new));
    }

    @Override
    public Single<BurstAddress[]> getAccountsWithRewardRecipient(BurstAddress accountId) {
        return assign(blockchainService.getAccountsWithRewardRecipient(BurstKitUtils.getEndpoint(), accountId.getID()))
                .map(response -> Arrays.stream(response.getAccounts())
                        .map(BurstAddress::fromEither)
                        .toArray(BurstAddress[]::new));
    }

    @Override
    public Single<AT> getAt(BurstAddress atId) {
        return assign(blockchainService.getAt(BurstKitUtils.getEndpoint(), atId.getID()))
                .map(AT::new);
    }

    @Override
    public Single<BurstAddress[]> getAtIds() {
        return assign(blockchainService.getAtIds(BurstKitUtils.getEndpoint()))
                .map(response -> Arrays.stream(response.getAtIds())
                        .map(BurstAddress::fromId)
                        .toArray(BurstAddress[]::new));
    }

    @Override
    public Single<Transaction> getTransaction(BurstID transactionId) {
        return assign(blockchainService.getTransaction(BurstKitUtils.getEndpoint(), transactionId.getID(), null))
                .map(Transaction::new);
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return assign(blockchainService.getTransaction(BurstKitUtils.getEndpoint(), null, Hex.toHexString(fullHash)))
                .map(Transaction::new);
    }

    @Override
    public Single<byte[]> getTransactionBytes(BurstID transactionId) {
        return assign(blockchainService.getTransactionBytes(BurstKitUtils.getEndpoint(), transactionId.getID()))
                .map(response -> Hex.decode(response.getTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline) {
        return assign(blockchainService.sendMoney(BurstKitUtils.getEndpoint(), recipient.getID(), null, amount.toPlanck().toString(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(BurstKitUtils.getEndpoint(), recipient.getID(), null, amount.toPlanck().toString(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), deadline, null, false, message, true, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, byte[] message) {
        return assign(blockchainService.sendMoney(BurstKitUtils.getEndpoint(), recipient.getID(), null, amount.toPlanck().toString(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), deadline, null, false, Hex.toHexString(message), false, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return assign(blockchainService.sendMoney(BurstKitUtils.getEndpoint(), recipient.getID(), null, amount.toPlanck().toString(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), deadline, null, false, null, null, null, message.isText(), Hex.toHexString(message.getData()), Hex.toHexString(message.getNonce()) ,null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return assign(blockchainService.sendMoney(BurstKitUtils.getEndpoint(), recipient.getID(), null, amount.toPlanck().toString(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), deadline, null, false, null, null, null, null, null, null, null, message.isText(), Hex.toHexString(message.getData()), Hex.toHexString(message.getNonce())))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return assign(blockchainService.suggestFee(BurstKitUtils.getEndpoint()))
                .map(FeeSuggestion::new);
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
        AtomicReference<MiningInfoResponse> miningInfo = new AtomicReference<>();
        return assign(Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMapSingle(l -> blockchainService.getMiningInfo(BurstKitUtils.getEndpoint())
                        .map(this::checkBrsResponse))
                .filter(newMiningInfo -> {
                    synchronized (miningInfo) {
                        if (miningInfo.get() == null || !Objects.equals(miningInfo.get().getGenerationSignature(), newMiningInfo.getGenerationSignature()) || !Objects.equals(miningInfo.get().getHeight(), newMiningInfo.getHeight())) {
                            miningInfo.set(newMiningInfo);
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .map(MiningInfo::new));
    }

    @Override
    public Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes) {
        return assign(blockchainService.broadcastTransaction(BurstKitUtils.getEndpoint(), Hex.toHexString(transactionBytes)))
                .map(TransactionBroadcast::new);
    }

    @Override
    public Single<BurstAddress> getRewardRecipient(BurstAddress account) {
        return assign(blockchainService.getRewardRecipient(BurstKitUtils.getEndpoint(), account.getID()))
                .map(response -> BurstAddress.fromEither(response.getRewardRecipient()));
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, BurstID accountId) {
        return assign(blockchainService.submitNonce(BurstKitUtils.getEndpoint(), passphrase, nonce, accountId == null ? null : accountId.getID(), ""))
                .map(submitNonceResponse -> {
                    if (!Objects.equals(submitNonceResponse.getResult(), "success")) {
                        throw new BurstApiException("Failed to submit nonce: " + submitNonceResponse.getResult());
                    }
                    return submitNonceResponse;
                })
                .map(SubmitNonceResponse::getDeadline);
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, Map<BurstAddress, BurstValue> recipients) throws IllegalArgumentException {
        return Single.fromCallable(() -> {
            StringBuilder recipientsString = new StringBuilder();
            if (recipients.size() > 64 || recipients.size() < 2) {
                throw new IllegalArgumentException("Must have 2-64 recipients, had " + recipients.size());
            }
            for (Map.Entry<BurstAddress, BurstValue> recipient : recipients.entrySet()) {
                recipientsString.append(recipient.getKey().getID()).append(":").append(recipient.getValue().toPlanck()).append(";");
            }
            recipientsString.setLength(recipientsString.length() - 1);
            return recipientsString;
        }).flatMap(recipientsString -> assign(blockchainService.sendMoneyMulti(BurstKitUtils.getEndpoint(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), String.valueOf(deadline), null, false, recipientsString.toString()))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes())));
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Set<BurstAddress> recipients) throws IllegalArgumentException {
        return Single.fromCallable(() -> {
            StringBuilder recipientsString = new StringBuilder();
            if (recipients.size() > 128 || recipients.size() < 2) {
                throw new IllegalArgumentException("Must have 2-128 recipients, had " + recipients.size());
            }
            for (BurstAddress recipient : recipients) {
                recipientsString.append(recipient.getID()).append(";");
            }
            recipientsString.setLength(recipientsString.length() - 1);
            return recipientsString;
        }).flatMap(recipientsString -> assign(blockchainService.sendMoneyMultiSame(BurstKitUtils.getEndpoint(), null, Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), String.valueOf(deadline), null, false, recipientsString.toString(), amount.toPlanck().toString()))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes())));
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, String name, String description, byte[] creationBytes) {
        return assign(blockchainService.createATProgram(BurstKitUtils.getEndpoint(), Hex.toHexString(senderPublicKey), fee.toPlanck().toString(), deadline, false, name, description, Hex.toHexString(creationBytes), null, null, 0, 0, 0, null))
                .map(response -> {
                    if (response.getError() != null) throw new IllegalArgumentException(response.getError());
                    return response;
                })
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    private interface BlockchainService {
        @GET("{endpoint}?requestType=getBlock")
        Single<BlockResponse> getBlock(@Path("endpoint") String endpoint, @Query("block") String blockId, @Query("height") String blockHeight, @Query("timestamp") String timestamp, @Query("includeTransactions") String[] transactions); // TODO Array of transactions

        @GET("{endpoint}?requestType=getBlockId")
        Single<BlockIDResponse> getBlockID(@Path("endpoint") String endpoint, @Query("height") String blockHeight);

        @GET("{endpoint}?requestType=getBlocks")
        Single<BlocksResponse> getBlocks(@Path("endpoint") String endpoint, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("includeTransactions") String[] transactions);

        @GET("{endpoint}?requestType=getConstants")
        Single<ConstantsResponse> getConstants(@Path("endpoint") String endpoint);

        @GET("{endpoint}?requestType=getAccount")
        Single<AccountResponse> getAccount(@Path("endpoint") String endpoint, @Query("account") String accountId);

        @GET("{endpoint}?requestType=getAccountATs")
        Single<AccountATsResponse> getAccountATs(@Path("endpoint") String endpoint, @Query("account") String accountId);

        @GET("{endpoint}?requestType=getAccountBlockIds")
        Single<AccountBlockIDsResponse> getAccountBlockIDs(@Path("endpoint") String endpoint, @Query("account") String accountId, @Query("timestamp") String timestamp, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex);

        @GET("{endpoint}?requestType=getAccountBlocks")
        Single<AccountBlocksResponse> getAccountBlocks(@Path("endpoint") String endpoint, @Query("account") String accountId, @Query("timestamp") String timestamp, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("includeTransactions") String[] includedTransactions);

        @GET("{endpoint}?requestType=getAccountTransactionIds")
        Single<AccountTransactionIDsResponse> getAccountTransactionIDs(@Path("endpoint") String endpoint, @Query("account") String accountId, @Query("timestamp") String timestamp, @Query("type") String type, @Query("subtype") String subtype, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("numberOfConfirmations") String numberOfConfirmations);

        @GET("{endpoint}?requestType=getAccountTransactions")
        Single<AccountTransactionsResponse> getAccountTransactions(@Path("endpoint") String endpoint, @Query("account") String accountId, @Query("timestamp") String timestamp, @Query("type") String type, @Query("subtype") String subtype, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("numberOfConfirmations") String numberOfConfirmations);

        @GET("{endpoint}?requestType=getAccountsWithRewardRecipient")
        Single<AccountsWithRewardRecipientResponse> getAccountsWithRewardRecipient(@Path("endpoint") String endpoint, @Query("account") String accountId);

        @GET("{endpoint}?requestType=getAT")
        Single<ATResponse> getAt(@Path("endpoint") String endpoint, @Query("at") String atId);

        @GET("{endpoint}?requestType=getATIds")
        Single<AtIDsResponse> getAtIds(@Path("endpoint") String endpoint);

        @GET("{endpoint}?requestType=getTransaction")
        Single<TransactionResponse> getTransaction(@Path("endpoint") String endpoint, @Query("transaction") String transaction, @Query("fullHash") String fullHash);

        @GET("{endpoint}?requestType=getTransactionBytes")
        Single<TransactionBytesResponse> getTransactionBytes(@Path("endpoint") String endpoint, @Query("transaction") String transaction);

        @POST("{endpoint}?requestType=sendMoney")
        Single<GenerateTransactionResponse> sendMoney(@Path("endpoint") String endpoint, @Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey, @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase, @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline, @Query("referencedTransactionFullHash") String referencedTransactionFullHash, @Query("broadcast") boolean broadcast, @Query("message") String message, @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt, @Query("messageToEncryptIsText") Boolean messageToEncryptIsText, @Query("encryptedMessageData") String encryptedMessageData, @Query("encryptedMessageNonce") String encryptedMessageNonce, @Query("messageToEncryptToSelf") String messageToEncryptToSelf, @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText, @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData, @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @GET("{endpoint}?requestType=suggestFee")
        Single<SuggestFeeResponse> suggestFee(@Path("endpoint") String endpoint);

        @GET("{endpoint}?requestType=getMiningInfo")
        Single<MiningInfoResponse> getMiningInfo(@Path("endpoint") String endpoint);

        @POST("{endpoint}?requestType=broadcastTransaction")
        Single<BroadcastTransactionResponse> broadcastTransaction(@Path("endpoint") String endpoint, @Query("transactionBytes") String transactionBytes);

        @GET("{endpoint}?requestType=getRewardRecipient")
        Single<RewardRecipientResponse> getRewardRecipient(@Path("endpoint") String endpoint, @Query("account") String account);

        @POST("{endpoint}?requestType=submitNonce")
        Single<SubmitNonceResponse> submitNonce(@Path("endpoint") String endpoint, @Query("secretPhrase") String passphrase, @Query("nonce") String nonce, @Query("accountId") String accountId, @Query("blockheight") String blockheight);

        @POST("{endpoint}?requestType=sendMoneyMulti")
        Single<GenerateTransactionResponse> sendMoneyMulti(@Path("endpoint") String endpoint, @Query("secretPhrase") String secretPhrase, @Query("publicKey") String publicKey, @Query("feeNQT") String feeNQT, @Query("deadline") String deadline, @Query("referencedTransactionFullHash") String referencedTransactionFullHash, @Query("broadcast") boolean broadcast, @Query("recipients") String recipients);

        @POST("{endpoint}?requestType=sendMoneyMultiSame")
        Single<GenerateTransactionResponse> sendMoneyMultiSame(@Path("endpoint") String endpoint, @Query("secretPhrase") String secretPhrase, @Query("publicKey") String publicKey, @Query("feeNQT") String feeNQT, @Query("deadline") String deadline, @Query("referencedTransactionFullHash") String referencedTransactionFullHash, @Query("broadcast") boolean broadcast, @Query("recipients") String recipients, @Query("amountNQT") String amountNQT);

        @POST("{endpoint}?requestType=createATProgram")
        Single<CreateATResponse> createATProgram(@Path("endpoint") String endpoint, @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline, @Query("broadcast") boolean broadcast, @Query("name") String name, @Query("description") String description, @Query("creationBytes") String creationBytes, @Query("code") String code, @Query("data") String data, @Query("dpages") int dpages, @Query("cspages") int cspages, @Query("uspages") int uspages, @Query("minActivationAmountNQT") String minActivationAmountNQT);
    }
}
