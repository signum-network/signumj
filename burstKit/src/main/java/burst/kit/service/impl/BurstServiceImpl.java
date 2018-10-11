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
import retrofit2.http.Query;

public class BurstServiceImpl implements BurstService {

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

    @Override
    public void updateNodeAddress(String newNodeAddress) {
        buildServices(newNodeAddress);
    }

    @Override
    public Single<BlockResponse> getBlock(BurstID block) {
        return schedulerAssigner.assignSchedulers(blockchainService.getBlock(block.getID(), null, null, null));
    }

    @Override
    public Single<BlockResponse> getBlock(long height) {
        return schedulerAssigner.assignSchedulers(blockchainService.getBlock(null, String.valueOf(height), null, null));
    }

    @Override
    public Single<BlockResponse> getBlock(BurstTimestamp timestamp) {
        return schedulerAssigner.assignSchedulers(blockchainService.getBlock(null, null, String.valueOf(timestamp.getTimestamp()), null));
    }

    @Override
    public Single<BlockResponse> getBlock(BurstID[] includedTransactions) {
        String[] transactions = new String[includedTransactions.length];
        for(int i = 0; i < includedTransactions.length; i++) {
            transactions[i] = includedTransactions[i].getID();
        }
        return schedulerAssigner.assignSchedulers(blockchainService.getBlock(null, null, null, transactions));
    }

    @Override
    public Single<BlockIDResponse> getBlockId(long height) {
        return schedulerAssigner.assignSchedulers(blockchainService.getBlockID(String.valueOf(height)));
    }

    @Override
    public Single<BlockchainStatusResponse> getBlockchainStatus() {
        return schedulerAssigner.assignSchedulers(blockchainService.getBlockchainStatus());
    }

    @Override
    public Single<BlocksResponse> getBlocks(long firstIndex, long lastIndex) {
        return schedulerAssigner.assignSchedulers(blockchainService.getBlocks(String.valueOf(firstIndex), String.valueOf(lastIndex), null));
    }

    @Override
    public Single<ConstantsResponse> getConstants() {
        return schedulerAssigner.assignSchedulers(blockchainService.getConstants());
    }

    @Override
    public Single<AccountResponse> getAccount(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccount(accountId.getNumericID()));
    }

    @Override
    public Single<AccountATsResponse> getAccountATs(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccountATs(accountId.getNumericID()));
    }

    @Override
    public Single<AccountBlockIDsResponse> getAccountBlockIDs(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccountBlockIDs(accountId.getNumericID(), null, null, null));
    }

    @Override
    public Single<AccountBlocksResponse> getAccountBlocks(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccountBlocks(accountId.getNumericID(), null, null, null, null));
    }

    @Override
    public Single<AccountPublicKeyResponse> getAccountPublicKey(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccountPublicKey(accountId.getNumericID()));
    }

    @Override
    public Single<ATResponse> getAt(BurstID atId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAt(atId.getID()));
    }

    @Override
    public Single<AtIDsResponse> getAtIds() {
        return schedulerAssigner.assignSchedulers(blockchainService.getAtIds());
    }

    @Override
    public Single<AtLongResponse> getAtLong(HexStringByteArray hex) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAtLong(hex.toHexString()));
    }

    @Override
    public Single<TransactionResponse> getTransaction(BurstID transactionId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getTransaction(transactionId.getID(), null));
    }

    @Override
    public Single<TransactionResponse> getTransaction(HexStringByteArray fullHash) {
        return schedulerAssigner.assignSchedulers(blockchainService.getTransaction(null, fullHash.toHexString()));
    }

    @Override
    public Single<TransactionBytesResponse> getTransactionBytes(BurstID transactionId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getTransactionBytes(transactionId.getID()));
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
    }
}
