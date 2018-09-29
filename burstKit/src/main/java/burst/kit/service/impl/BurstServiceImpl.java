package burst.kit.service.impl;

import burst.kit.entity.response.*;
import burst.kit.util.SchedulerAssigner;
import com.google.gson.GsonBuilder;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.service.BurstService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BurstServiceImpl implements BurstService {

    private final SchedulerAssigner schedulerAssigner;

    private BlockchainService blockchainService;

    public BurstServiceImpl(SchedulerAssigner schedulerAssigner) {
        this.schedulerAssigner = schedulerAssigner;
        buildServices("https://wallet.burst.cryptoguru.org");
    }

    public BurstServiceImpl() {
        this(new SchedulerAssigner() {
            @Override
            public <T> Single<T> assignSchedulers(Single<T> source) {
                return source.subscribeOn(Schedulers.io());
            }
        });
    }

    private void buildServices(String baseUrl) {
        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(BurstAddress.class, BurstAddress.DESERIALIZER)
                        .registerTypeAdapter(BurstAddress.class, BurstAddress.SERIALIZER)
                        .registerTypeAdapter(BurstID.class, BurstID.DESERIALIZER)
                        .registerTypeAdapter(BurstID.class, BurstID.SERIALIZER)
                        .registerTypeAdapter(BurstTimestamp.class, BurstTimestamp.SERIALIZER)
                        .registerTypeAdapter(BurstTimestamp.class, BurstTimestamp.DESERIALIZER)
                        .registerTypeAdapter(BurstValue.class, BurstValue.DESERIALIZER)
                        .registerTypeAdapter(BurstValue.class, BurstValue.SERIALIZER)
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(client)
                .build();

        blockchainService = retrofit.create(BlockchainService.class);
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
    public Single<AccountResponse> getAccount(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccount(accountId.getNumericID()));
    }

    @Override
    public Single<AccountATsResponse> getAccountATs(BurstAddress accountId) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAccountATs(accountId.getNumericID()));
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
    public Single<AtLongResponse> getAtLong(String hexString) {
        return schedulerAssigner.assignSchedulers(blockchainService.getAtLong(hexString));
    }

    private interface BlockchainService {
        @GET("burst?requestType=getBlock")
        Single<BlockResponse> getBlock(@Query("block") String blockId, @Query("height") String blockHeight, @Query("timestamp") String timestamp, @Query("includeTransactions") String[] transactions); // TODO Array of transactions

        @GET("burst?requestType=getBlockId")
        Single<BlockIDResponse> getBlockID(@Query("height") String blockHeight);

        @GET("burst?requestType=getAccount")
        Single<AccountResponse> getAccount(@Query("account") String accountId);

        @GET("burst?requestType=getAccountATs")
        Single<AccountATsResponse> getAccountATs(@Query("account") String accountId);

        @GET("burst?requestType=getAt")
        Single<ATResponse> getAt(@Query("at") String atId);

        @GET("burst?requestType=getATIds")
        Single<AtIDsResponse> getAtIds();

        @GET("burst?requestType=getATLong")
        Single<AtLongResponse> getAtLong(@Query("hexString") String hexString);
    }
}
