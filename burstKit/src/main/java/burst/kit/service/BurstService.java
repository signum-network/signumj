package burst.kit.service;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.HexStringByteArray;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.response.*;
import burst.kit.service.impl.BurstServiceImpl;
import burst.kit.service.impl.DefaultSchedulerAssigner;
import burst.kit.util.SchedulerAssigner;
import io.reactivex.Single;

public interface BurstService {
    void updateNodeAddress(String newNodeAddress);

    Single<BlockResponse> getBlock(BurstID block);
    Single<BlockResponse> getBlock(long height);
    Single<BlockResponse> getBlock(BurstTimestamp timestamp);
    Single<BlockResponse> getBlock(BurstID[] includedTransactions); // TODO actually use array
    Single<BlockIDResponse> getBlockId(long height);
    Single<BlockchainStatusResponse> getBlockchainStatus();
    Single<BlocksResponse> getBlocks(long firstIndex, long lastIndex); // TODO includeTransactions?
    Single<ConstantsResponse> getConstants();
    Single<AccountResponse> getAccount(BurstAddress accountId);
    Single<AccountATsResponse> getAccountATs(BurstAddress accountId);
    Single<AccountBlockIDsResponse> getAccountBlockIDs(BurstAddress accountId); // TODO timestamp, firstIndex, lastIndex
    Single<AccountBlocksResponse> getAccountBlocks(BurstAddress accountId); // TODO timestamp, firstIndex, lastIndex, includeTransactions
    Single<AccountPublicKeyResponse> getAccountPublicKey(BurstAddress accountId);
    Single<ATResponse> getAt(BurstID atId);
    Single<AtIDsResponse> getAtIds();
    Single<AtLongResponse> getAtLong(HexStringByteArray hex);
    Single<TransactionResponse> getTransaction(BurstID transactionId);
    Single<TransactionResponse> getTransaction(HexStringByteArray fullHash);
    Single<TransactionBytesResponse> getTransactionBytes(BurstID transactionId);

    static BurstService getInstance(String nodeAddress, SchedulerAssigner schedulerAssigner) {
        return new BurstServiceImpl(nodeAddress, schedulerAssigner);
    }

    static BurstService getInstance(String nodeAddress) {
        return new BurstServiceImpl(nodeAddress, new DefaultSchedulerAssigner());
    }
}
