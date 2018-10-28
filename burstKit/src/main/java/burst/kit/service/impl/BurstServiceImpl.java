package burst.kit.service.impl;

import burst.kit.entity.*;
import burst.kit.entity.response.*;
import burst.kit.util.BurstKitUtils;
import burst.kit.util.SchedulerAssigner;

import burst.kit.service.BurstService;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public final class BurstServiceImpl implements BurstService {

    private final SchedulerAssigner schedulerAssigner;

    private BlockchainService blockchainService;

    public BurstServiceImpl(String nodeAddress, SchedulerAssigner schedulerAssigner) {
        this.schedulerAssigner = schedulerAssigner;
        buildServices(nodeAddress);
    }

    private void buildServices(String nodeAddress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(nodeAddress)
                .addConverterFactory(GsonConverterFactory.create(BurstKitUtils.buildGson().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        blockchainService = retrofit.create(BlockchainService.class);
    }
    
    private <T> Single<T> assign(Single<T> source) {
        return schedulerAssigner.assignSchedulers(source);
    }

    @Override
    public void updateNodeAddress(String newNodeAddress) {
        buildServices(newNodeAddress);
    }

    @Override
    public Single<BlockResponse> getBlock(BurstID block) {
        return assign(blockchainService.getBlock(block.getID(), null, null, null));
    }

    @Override
    public Single<BlockResponse> getBlock(long height) {
        return assign(blockchainService.getBlock(null, String.valueOf(height), null, null));
    }

    @Override
    public Single<BlockResponse> getBlock(BurstTimestamp timestamp) {
        return assign(blockchainService.getBlock(null, null, String.valueOf(timestamp.getTimestamp()), null));
    }

    @Override
    public Single<BlockResponse> getBlock(BurstID[] includedTransactions) {
        String[] transactions = new String[includedTransactions.length];
        for(int i = 0; i < includedTransactions.length; i++) {
            transactions[i] = includedTransactions[i].getID();
        }
        return assign(blockchainService.getBlock(null, null, null, transactions));
    }

    @Override
    public Single<BlockIDResponse> getBlockId(long height) {
        return assign(blockchainService.getBlockID(String.valueOf(height)));
    }

    @Override
    public Single<BlockchainStatusResponse> getBlockchainStatus() {
        return assign(blockchainService.getBlockchainStatus());
    }

    @Override
    public Single<BlocksResponse> getBlocks(long firstIndex, long lastIndex) {
        return assign(blockchainService.getBlocks(String.valueOf(firstIndex), String.valueOf(lastIndex), null));
    }

    @Override
    public Single<ConstantsResponse> getConstants() {
        return assign(blockchainService.getConstants());
    }

    @Override
    public Single<AccountResponse> getAccount(BurstAddress accountId) {
        return assign(blockchainService.getAccount(accountId.getNumericID()));
    }

    @Override
    public Single<AccountATsResponse> getAccountATs(BurstAddress accountId) {
        return assign(blockchainService.getAccountATs(accountId.getNumericID()));
    }

    @Override
    public Single<AccountBlockIDsResponse> getAccountBlockIDs(BurstAddress accountId) {
        return assign(blockchainService.getAccountBlockIDs(accountId.getNumericID(), null, null, null));
    }

    @Override
    public Single<AccountBlocksResponse> getAccountBlocks(BurstAddress accountId) {
        return assign(blockchainService.getAccountBlocks(accountId.getNumericID(), null, null, null, null));
    }

    @Override
    public Single<AccountPublicKeyResponse> getAccountPublicKey(BurstAddress accountId) {
        return assign(blockchainService.getAccountPublicKey(accountId.getNumericID()));
    }

    @Override
    public Single<AccountTransactionIDsResponse> getAccountTransactionIDs(BurstAddress accountId) {
        return assign(blockchainService.getAccountTransactionIDs(accountId.getNumericID(), null, null, null, null, null, null));
    }

    @Override
    public Single<AccountTransactionsResponse> getAccountTransactions(BurstAddress accountId) {
        return assign(blockchainService.getAccountTransactions(accountId.getNumericID(), null, null, null, null, null, null));
    }

    @Override
    public Single<AccountsWithRewardRecipientResponse> getAccountsWithRewardRecipient(BurstAddress accountId) {
        return assign(blockchainService.getAccountsWithRewardRecipient(accountId.getNumericID()));
    }

    @Override
    public Single<ATResponse> getAt(BurstID atId) {
        return assign(blockchainService.getAt(atId.getID()));
    }

    @Override
    public Single<AtIDsResponse> getAtIds() {
        return assign(blockchainService.getAtIds());
    }

    @Override
    public Single<AtLongResponse> getAtLong(HexStringByteArray hex) {
        return assign(blockchainService.getAtLong(hex.toHexString()));
    }

    @Override
    public Single<TransactionResponse> getTransaction(BurstID transactionId) {
        return assign(blockchainService.getTransaction(transactionId.getID(), null));
    }

    @Override
    public Single<TransactionResponse> getTransaction(HexStringByteArray fullHash) {
        return assign(blockchainService.getTransaction(null, fullHash.toHexString()));
    }

    @Override
    public Single<TransactionBytesResponse> getTransactionBytes(BurstID transactionId) {
        return assign(blockchainService.getTransactionBytes(transactionId.getID()));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransaction(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), null, senderPublicKey.toHexString(), fee.toPlanck(), deadline, null, false, null, null, null, null, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithMessage(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), null, senderPublicKey.toHexString(), fee.toPlanck(), deadline, null, false, message, true, null, null, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithMessage(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), null, senderPublicKey.toHexString(), fee.toPlanck(), deadline, null, false, message.toHexString(), false, null, null, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, false, null, null, message, true, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, false, null, null, message.toHexString(), false, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessage(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), null, senderPublicKey.toHexString(), fee.toPlanck(), deadline, null, false, null, null, null, message.isText(), message.getData().toHexString(), message.getNonce().toHexString() ,null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, false, null, null, null, null, null, null, message, true, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, false, null, null, null, null, null, null, message.toHexString(), false, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), null, senderPublicKey.toHexString(), fee.toPlanck(), deadline, null, false, null, null, null, null, null, null, null, message.isText(), message.getData().toHexString(), message.getNonce().toHexString()));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoney(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, null, null, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, message, true, null, null, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, message.toHexString(), false, null, null, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithEncryptedMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, message, true, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithEncryptedMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, message.toHexString(), false, null, null, null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithEncryptedMessage(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, null, message.isText(), message.getData().toHexString(), message.getNonce().toHexString() ,null, null, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithEncryptedMessageToSelf(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, String message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, null, null, null, null, message, true, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithEncryptedMessageToSelf(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, null, null, null, null, message.toHexString(), false, null, null));
    }

    @Override
    public Single<GenerateTransactionResponse> sendMoneyWithEncryptedMessageToSelf(BurstAddress recipient, String passphrase, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return assign(blockchainService.sendMoney(recipient.getNumericID(), null, amount.toPlanck(), passphrase, null, fee.toPlanck(), deadline, null, true, null, null, null, null, null, null, null, message.isText(), message.getData().toHexString(), message.getNonce().toHexString()));
    }

    private interface BlockchainService {
        @GET("burst?requestType=getBlock")
        Single<BlockResponse> getBlock(@Query("block") String blockId, @Query("height") String blockHeight, @Query("timestamp") String timestamp, @Query("includeTransactions") String[] transactions); // TODO Array of transactions

        @GET("burst?requestType=getBlockId")
        Single<BlockIDResponse> getBlockID(@Query("height") String blockHeight);

        @GET("burst?requestType=getBlockchainStatus")
        Single<BlockchainStatusResponse> getBlockchainStatus();

        @GET("burst?requestType=getBlocks")
        Single<BlocksResponse> getBlocks(@Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("includeTransactions") String[] transactions);

        @GET("burst?requestType=getConstants")
        Single<ConstantsResponse> getConstants();

        @GET("burst?requestType=getAccount")
        Single<AccountResponse> getAccount(@Query("account") String accountId);

        @GET("burst?requestType=getAccountATs")
        Single<AccountATsResponse> getAccountATs(@Query("account") String accountId);

        @GET("burst?requestType=getAccountBlockIds")
        Single<AccountBlockIDsResponse> getAccountBlockIDs(@Query("account") String accountId, @Query("timestamp") String timestamp, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex);

        @GET("burst?requestType=getAccountBlocks")
        Single<AccountBlocksResponse> getAccountBlocks(@Query("account") String accountId, @Query("timestamp") String timestamp, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("includeTransactions") String[] includedTransactions);

        @GET("burst?requestType=getAccountPublicKey")
        Single<AccountPublicKeyResponse> getAccountPublicKey(@Query("account") String accountId);

        @GET("burst?requestType=getAccountTransactionIds")
        Single<AccountTransactionIDsResponse> getAccountTransactionIDs(@Query("account") String accountId, @Query("timestamp") String timestamp, @Query("type") String type, @Query("subtype") String subtype, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("numberOfConfirmations") String numberOfConfirmations);

        @GET("burst?requestType=getAccountTransactions")
        Single<AccountTransactionsResponse> getAccountTransactions(@Query("account") String accountId, @Query("timestamp") String timestamp, @Query("type") String type, @Query("subtype") String subtype, @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex, @Query("numberOfConfirmations") String numberOfConfirmations);

        @GET("burst?requestType=getAccountsWithRewardRecipient")
        Single<AccountsWithRewardRecipientResponse> getAccountsWithRewardRecipient(@Query("account") String accountId);

        @GET("burst?requestType=getAt")
        Single<ATResponse> getAt(@Query("at") String atId);

        @GET("burst?requestType=getATIds")
        Single<AtIDsResponse> getAtIds();

        @GET("burst?requestType=getATLong")
        Single<AtLongResponse> getAtLong(@Query("hexString") String hexString);

        @GET("burst?requestType=getTransaction")
        Single<TransactionResponse> getTransaction(@Query("transaction") String transaction, @Query("fullHash") String fullHash);

        @GET("burst?requestType=getTransactionBytes")
        Single<TransactionBytesResponse> getTransactionBytes(@Query("transaction") String transaction);

        @POST("burst?requestType=sendMoney")
        Single<GenerateTransactionResponse> sendMoney(@Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey, @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase, @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline, @Query("referencedTransactionFullHash") String referencedTransactionFullHash, @Query("broadcast") boolean broadcast, @Query("message") String message, @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt, @Query("messageToEncryptIsText") Boolean messageToEncryptIsText, @Query("encryptedMessageData") String encryptedMessageData, @Query("encryptedMessageNonce") String encryptedMessageNonce, @Query("messageToEncryptToSelf") String messageToEncryptToSelf, @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText, @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData, @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);
    }
}
