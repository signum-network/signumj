package burst.kit.service;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.response.*;
import burst.kit.service.impl.BurstServiceImpl;
import burst.kit.util.SchedulerAssigner;
import io.reactivex.Single;

public interface BurstService {
    Single<BlockResponse> getBlock(BurstID block);
    Single<BlockResponse> getBlock(long height);
    Single<BlockResponse> getBlock(BurstTimestamp timestamp);
    Single<BlockResponse> getBlock(BurstID[] includedTransactions); // TODO actually use array
    Single<BlockIDResponse> getBlockId(long height);
    Single<BlockchainStatusResponse> getBlockchainStatus();
    Single<BlocksResponse> getBlocks(long firstIndex, long lastIndex); // TODO includeTransactions?
    Single<AccountResponse> getAccount(BurstAddress accountId);
    Single<AccountATsResponse> getAccountATs(BurstAddress accountId);
    Single<ATResponse> getAt(BurstID atId);
    Single<AtIDsResponse> getAtIds();
    Single<AtLongResponse> getAtLong(String hexString);

    static BurstService getInstance(SchedulerAssigner schedulerAssigner) {
        return new BurstServiceImpl(schedulerAssigner);
    }

    static BurstService getInstance() {
        return new BurstServiceImpl();
    }
}
