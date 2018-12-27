package burst.kit.service;

import burst.kit.entity.*;
import burst.kit.entity.response.*;
import burst.kit.service.impl.BurstNodeServiceImpl;
import burst.kit.service.impl.DefaultSchedulerAssigner;
import burst.kit.util.SchedulerAssigner;
import com.google.gson.JsonObject;
import io.reactivex.Single;

public interface BurstNodeService {
    /**
     * Set the node address the the service connects to for future requests
     * @param newNodeAddress The full URL of the node, including port number if different from 80, not including /burst API locator
     */
    void updateNodeAddress(String newNodeAddress);

    /**
     * Get a block via a block ID
     * @param block The block ID of the requested block
     * @return The block details, wrapped in a Single
     */
    Single<BlockResponse> getBlock(BurstID block);

    /**
     * Get the block at a specific height
     * @param height The height of the block
     * @return The block details, wrapped in a Single
     */
    Single<BlockResponse> getBlock(long height);

    /**
     * Get the block at the specified timestamp
     * @param timestamp The timestamp of the block
     * @return The block details, wrapped in a Single
     */
    Single<BlockResponse> getBlock(BurstTimestamp timestamp);

    /**
     * Get the block by specifying transactions included in it
     * WARNING: Not tested.
     * @param includedTransactions The transactions included in the block - NEEDS TESTING
     * @return The block details, wrapped in a Single
     */
    Single<BlockResponse> getBlock(BurstID[] includedTransactions); // TODO actually use array

    /**
     * Get the block ID at a specified height
     * @param height The height of the block
     * @return The Block ID response, wrapped in a single
     */
    Single<BlockIDResponse> getBlockId(long height);

    /**
     * Get the blockchain status
     * @return The blockchain status, wrapped in a single
     */
    Single<BlockchainStatusResponse> getBlockchainStatus();

    /**
     * Gets all the blocks between the first index and last index.
     * @param firstIndex The index from the most recent blocks (0 would be the most recent block)
     * @param lastIndex The end index from the most recent blocks
     * @return The blocks, wrapped in a single
     */
    Single<BlocksResponse> getBlocks(long firstIndex, long lastIndex); // TODO includeTransactions?

    /**
     * Get the Constants in use by the node
     * @return The constants, wrapped in a single
     */
    Single<ConstantsResponse> getConstants();

    /**
     * Get the account details of the specified account
     * @param accountId The address of the account
     * @return The block details, wrapped in a single
     */
    Single<AccountResponse> getAccount(BurstAddress accountId);

    /**
     * Get the ATs created by the account
     * @param accountId The address of the account
     * @return A list of the ATs, wrapped in a single
     */
    Single<AccountATsResponse> getAccountATs(BurstAddress accountId);

    /**
     * Get the IDs of the blocks forged by an account
     * @param accountId The address of the account
     * @return The block IDs, wrapped in a single
     */
    Single<AccountBlockIDsResponse> getAccountBlockIDs(BurstAddress accountId); // TODO timestamp, firstIndex, lastIndex

    /**
     * Get the blocks forged by an account
     * @param accountId The address of the account
     * @return The blocks, wrapped in a single
     */
    Single<AccountBlocksResponse> getAccountBlocks(BurstAddress accountId); // TODO timestamp, firstIndex, lastIndex, includeTransactions

    /**
     * Get the public key of an account
     * @param accountId The address of the account
     * @return The public key, wrapped in a single
     */
    Single<AccountPublicKeyResponse> getAccountPublicKey(BurstAddress accountId);

    /**
     * Get the transaction IDs of an account
     * @param accountId The address of the account
     * @return The account's transaction IDs, wrapped in a single
     */
    Single<AccountTransactionIDsResponse> getAccountTransactionIDs(BurstAddress accountId); // TODO filtering

    /**
     * Get the transactions of an account
     * @param accountId The address of the account
     * @return The account's transactions, wrapped in a single
     */
    Single<AccountTransactionsResponse> getAccountTransactions(BurstAddress accountId); // TODO filtering

    /**
     * Get the list of accounts which have their reward recipient set to the specified account
     * @param accountId The address of the account
     * @return The list of account IDs with reward recipients set to the account, wrapped in a single
     */
    Single<AccountsWithRewardRecipientResponse> getAccountsWithRewardRecipient(BurstAddress accountId); // TODO finish

    /**
     * Get the details of an AT
     * @param atId The ID of the AT
     * @return The details of the AT, wrapped in a single
     */
    Single<ATResponse> getAt(BurstID atId);

    /**
     * Get the list of IDs of all ATs
     * @return The list of AT IDs, wrapped in a single
     */
    Single<AtIDsResponse> getAtIds();

    /**
     * Get the details of a transaction
     * @param transactionId The ID of the transaction
     * @return The transaction details, wrapped in a single
     */
    Single<TransactionResponse> getTransaction(BurstID transactionId);

    /**
     * Get the details of a transaction
     * @param fullHash The full hash of the transaction
     * @return The transaction details, wrapped in a single
     */
    Single<TransactionResponse> getTransaction(byte[] fullHash);

    /**
     * Get the transaction bytes
     * @param transactionId The ID of the transaction
     * @return The transaction bytes, wrapped in a single
     */
    Single<TransactionBytesResponse> getTransactionBytes(BurstID transactionId);

    /**
     * Generate a simple transaction (only sending money)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The generated transaction, wrapped in a single
     */
    Single<GenerateTransactionResponse> generateTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline);

    /**
     * Generate a transaction with a plaintext message
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The generated transaction, wrapped in a single
     */
    Single<GenerateTransactionResponse> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message);

    /**
     * Generate a transaction with a plaintext message
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The generated transaction, wrapped in a single
     */
    Single<GenerateTransactionResponse> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, byte[] message);

    /**
     * Generate a transaction with an encrypted message (can be read by sender and recipient)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @return The generated transaction, wrapped in a single
     */
    Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message);

    /**
     * Generate a transaction with an encrypted-to-self message (can be read by only sender)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction (Use sender public key and sender private key to encrypt)
     * @return The generated transaction, wrapped in a single
     */
    Single<GenerateTransactionResponse> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message);

    /**
     * Get the currently suggested transaction fees, which are calculated based on current network congestion -
     * @return Suggested transaction fees - Priority, standard and cheap in descending speed and cost, wrapped in a single
     */
    Single<SuggestFeeResponse> suggestFee();

    /**
     * Get the current mining info
     * @return The mining info, wrapped in a single
     */
    Single<MiningInfoResponse> getMiningInfo();

    /**
     * Returns info about the host IP and your IP address (? Needs checking)
     * @return Your info, wrapped in a single
     */
    Single<MyInfoResponse> getMyInfo();

    /**
     * Broadcast a transaction on the network
     * @param transactionBytes The signed transaction bytes
     * @return The full hash and transaction ID or an error, wrapped in a single
     */
    Single<BroadcastTransactionResponse> broadcastTransaction(byte[] transactionBytes);

    static BurstNodeService getInstance(String nodeAddress, SchedulerAssigner schedulerAssigner) {
        return new BurstNodeServiceImpl(nodeAddress, schedulerAssigner);
    }

    static BurstNodeService getInstance(String nodeAddress) {
        return new BurstNodeServiceImpl(nodeAddress, new DefaultSchedulerAssigner());
    }
}
