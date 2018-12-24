package burst.kit.service;

import burst.kit.entity.*;
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
    Single<AccountTransactionIDsResponse> getAccountTransactionIDs(BurstAddress accountId); // TODO filtering
    Single<AccountTransactionsResponse> getAccountTransactions(BurstAddress accountId); // TODO filtering
    Single<AccountsWithRewardRecipientResponse> getAccountsWithRewardRecipient(BurstAddress accountId); // TODO finish
    Single<ATResponse> getAt(BurstID atId);
    Single<AtIDsResponse> getAtIds();

    /**
     * Get the details of a transaction
     * @param transactionId The ID of the transaction
     * @return The transaction details, wrapped in a single
     */
    Single<TransactionResponse> getTransaction(BurstID transactionId);
    Single<TransactionResponse> getTransaction(HexStringByteArray fullHash);
    Single<TransactionBytesResponse> getTransactionBytes(BurstID transactionId);
    Single<GenerateTransactionResponse> generateTransaction(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline);
    Single<GenerateTransactionResponse> generateTransactionWithMessage(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message);
    Single<GenerateTransactionResponse> generateTransactionWithMessage(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, HexStringByteArray message);
    Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessage(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message);
    Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, HexStringByteArray senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message);

    static BurstService getInstance(String nodeAddress, SchedulerAssigner schedulerAssigner) {
        return new BurstServiceImpl(nodeAddress, schedulerAssigner);
    }

    static BurstService getInstance(String nodeAddress) {
        return new BurstServiceImpl(nodeAddress, new DefaultSchedulerAssigner());
    }
}
