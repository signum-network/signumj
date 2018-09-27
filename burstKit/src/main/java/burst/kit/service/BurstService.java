package burst.kit.service;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.response.ATResponse;
import burst.kit.entity.response.AccountATsResponse;
import burst.kit.entity.response.AccountResponse;
import burst.kit.entity.response.AtIDsResponse;
import burst.kit.entity.response.AtLongResponse;
import burst.kit.entity.response.BlockResponse;
import io.reactivex.Single;

public interface BurstService {
    Single<BlockResponse> getBlock(BurstID block);
    Single<BlockResponse> getBlock(long height);
    Single<BlockResponse> getBlock(BurstTimestamp timestamp);
    Single<BlockResponse> getBlock(BurstID[] includedTransactions); // TODO actually use array
    Single<AccountResponse> getAccount(BurstAddress accountId);
    Single<AccountATsResponse> getAccountATs(BurstAddress accountId);
    Single<ATResponse> getAt(BurstID atId);
    Single<AtIDsResponse> getAtIds();
    Single<AtLongResponse> getAtLong(String hexString);
}
