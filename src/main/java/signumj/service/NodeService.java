package signumj.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import signumj.entity.*;
import signumj.entity.response.*;
import signumj.service.impl.CompositeBurstNodeService;
import signumj.service.impl.HttpBurstNodeService;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public interface NodeService extends AutoCloseable {
	
	/**
	 * @return the address of this node service
	 */
	String getAddress();
	
    /**
     * Get a block via a block ID
     * @param block The block ID of the requested block
     * @return The block details, wrapped in a Single
     */
    Single<Block> getBlock(SignumID block);
    
    /**
     * @return the blockchain status
     */
    Single<BlockchainStatus> getBlockChainStatus();

    /**
     * Get the block at a specific height
     * @param height The height of the block
     * @return The block details, wrapped in a Single
     */
    Single<Block> getBlock(int height);

    /**
     * Get the block at the specified timestamp
     * @param timestamp The timestamp of the block
     * @return The block details, wrapped in a Single
     */
    Single<Block> getBlock(SignumTimestamp timestamp);

    /**
     * Gets all the blocks between the first index and last index.
     * @param firstIndex The index from the most recent blocks (0 would be the most recent block)
     * @param lastIndex The end index from the most recent blocks
     * @return The blocks, wrapped in a single
     */
    Single<Block[]> getBlocks(int firstIndex, int lastIndex);

    /**
     * Get the Constants in use by the node
     * @return The constants, wrapped in a single
     */
    Single<Constants> getConstants();
    
    /**
     * Get the account details of the specified account
     * @param accountId The address of the account
     * @param height
     * @param estimateCommitment
     * @param getCommittedAmount
     * @return The account details, wrapped in a single
     */
    Single<Account> getAccount(SignumAddress accountId, Integer height, Boolean estimateCommitment, Boolean getCommittedAmount);
    
    /**
     * Get the account details of the specified account
     * @param accountId The address of the account
     * @return The account details, wrapped in a single
     */
    Single<Account> getAccount(SignumAddress accountId);

    /**
     * Get the ATs created by the account
     * @param accountId The address of the account
     * @return A list of the ATs, wrapped in a single
     */
    Single<AT[]> getAccountATs(SignumAddress accountId);

    /**
     * Get the blocks forged by an account
     * @param accountId The address of the account
     * @return The blocks, wrapped in a single
     */
    Single<Block[]> getAccountBlocks(SignumAddress accountId); // TODO timestamp, firstIndex, lastIndex

    /**
     * Get the transaction IDs of an account
     * @param accountId The address of the account
     * @return The account's transaction IDs, wrapped in a single
     */
    Single<SignumID[]> getAccountTransactionIDs(SignumAddress accountId); // TODO filtering

    /**
     * Get the transactions of an account
     * @param accountId The address of the account
     * @return The account's transactions, wrapped in a single
     */
    Single<Transaction[]> getAccountTransactions(SignumAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect);

    /**
     * Get the unconfirmed transactions of an account
     * @param accountId The address of the account
     * @return The account's transactions, wrapped in a single
     */
    Single<Transaction[]> getUnconfirmedTransactions(SignumAddress accountId);

    /**
     * Get the list of accounts which have their reward recipient set to the specified account
     * @param accountId The address of the account
     * @return The list of account IDs with reward recipients set to the account, wrapped in a single
     */
    Single<SignumAddress[]> getAccountsWithRewardRecipient(SignumAddress accountId);

    /**
     * Get the given asset
     * @param assetId The asset id
     * @return The asset, wrapped in a single
     */
    Single<Asset> getAsset(SignumID assetId);

    /**
     * Get the accounts holding the given asset
     * @param assetId The asset id
     * @return The asset balances of accounts holding the asset, wrapped in a single
     */
    Single<AssetBalance[]> getAssetBalances(SignumID assetId, Integer firstIndex, Integer lastIndex);

    /**
     * Get the trades for a given asset
     * 
     * @param assetId The asset id
     * @param account The account of interest (optional)
     * @param firstIndex The first index (optional for pagination)
     * @param lastIndex The last index (optional for pagination)
     * 
     * @return A list trades, wrapped in a single
     */
    // TODO TEST
    Single<AssetTrade[]> getAssetTrades(SignumID assetId, SignumAddress account, Integer firstIndex, Integer lastIndex);

    /**
     * Get the ask orders for a given asset
     * @param assetId The asset id
     * 
     * @return A list trades, wrapped in a single
     */
    Single<AssetOrder[]> getAskOrders(SignumID assetId);

    /**
     * Get the bid orders for a given asset
     * @param assetId The asset id
     * 
     * @return A list trades, wrapped in a single
     */
    Single<AssetOrder[]> getBidOrders(SignumID assetId);

    /**
     * Get the details of an AT
     * @param at The address of the AT
     * @return The details of the AT, wrapped in a single
     */
    Single<AT> getAt(SignumAddress at);
    
    /**
     * Get the details of an AT
     * @param at The address of the AT
     * @param includeDetails If the immutable details should also be included
     * @return The details of the AT, wrapped in a single
     */
    Single<AT> getAt(SignumAddress at, Boolean includeDetails);

    /**
     * Get the list of addresses of all ATs
     * @return The list of AT addresses, wrapped in a single
     */
    Single<SignumAddress[]> getAtIds();

    /**
     * Get the details of a transaction
     * @param transactionId The ID of the transaction
     * @return The transaction details, wrapped in a single
     */
    Single<Transaction> getTransaction(SignumID transactionId);

    /**
     * Get the details of a transaction
     * @param fullHash The full hash of the transaction
     * @return The transaction details, wrapped in a single
     */
    Single<Transaction> getTransaction(byte[] fullHash);

    /**
     * Get the transaction bytes
     * @param transactionId The ID of the transaction
     * @return The transaction bytes, wrapped in a single
     */
    Single<byte[]> getTransactionBytes(SignumID transactionId);

    /**
     * Generate a add commitment transaction
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to commit
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionAddCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline);

    /**
     * Generate a add commitment transaction
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to remove
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionRemoveCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline);

    /**
     * Generate a simple transaction (only sending money)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String referencedTransactionFullHash);

    /**
     * Generate a transaction to set the reward recipient
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionSetRewardRecipient(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline);

    /**
     * Generate a transaction with a plaintext message
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message which also sets the recipient's public key in the chain.
     * @param recipientAddress The recipient's address
     * @param recipientPublicKey The public key of the recipient, to be set in the chain
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message which also sets the recipient's public key in the chain.
     * @param recipientAddress The recipient's address
     * @param recipientPublicKey The public key of the recipient, to be set in the chain
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted message (can be read by sender and recipient)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted message (can be read by sender and recipient)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted-to-self message (can be read by only sender)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction (Use sender public key and sender private key to encrypt)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted-to-self message (can be read by only sender)
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction (Use sender public key and sender private key to encrypt)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Get the currently suggested transaction fees, which are calculated based on current network congestion -
     * @return Suggested transaction fees - Priority, standard and cheap in descending speed and cost, wrapped in a single
     */
    Single<FeeSuggestion> suggestFee();

    /**
     * Get the current mining info
     * @return An observable that returns the current mining info when it changes.
     */
    Observable<MiningInfo> getMiningInfo();

    /**
     * Broadcast a transaction on the network
     * @param transactionBytes The signed transaction bytes
     * @return The number of peers this transaction was broadcast to, wrapped in a single
     */
    Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes);

    /**
     * Get the reward recipient of the account
     * @param account The account
     * @return The reward recipient, wrapped in a single
     */
    Single<SignumAddress> getRewardRecipient(SignumAddress account);

    /**
     * Submit a nonce for mining
     * @param passphrase The passphrase of the miner (if solo mining) or null if pool mining
     * @param nonce The nonce that results in the deadline you want to submit
     * @param accountId The account ID of the miner
     * @return The calculated deadline, wrapped in a single
     */
    Single<Long> submitNonce(String passphrase, String nonce, SignumID accountId);

    /**
     * Generate a multi-out transaction
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param recipients A map of recipients and how much they get. Length must be 2-64 inclusive
     * @return The unsigned transaction bytes, wrapped in a single
     * @throws IllegalArgumentException If the number of recipients is not in the range of 2-64 inclusive
     */
    Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, Map<SignumAddress, SignumValue> recipients) throws IllegalArgumentException;

    /**
     * Generate a multi-out transaction
     * @param senderPublicKey The public key of the sender
     * @param amount The amount each recipient gets
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param recipients A list of recipients. Each will get the amount specified. Length must be 2-128 inclusive
     * @return The unsigned transaction bytes, wrapped in a single
     * @throws IllegalArgumentException If the number of recipients is not in the range of 2-128 inclusive
     */
    Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, Set<SignumAddress> recipients);

    /**
     * Generate the transaction for creating an AT
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param name The name of the AT
     * @param description The description of the AT
     * @param creationBytes The creation bytes of the AT (if pre-calculated and not using the following fields)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, String name, String description, byte[] creationBytes);

    /**
     * Generate the transaction for transfering assets
     * @param senderPublicKey The public key of the sender
     * @param name The token name
     * @param description The token description
     * @param quantity The total token supply
     * @param decimals The number of decimals
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, SignumValue quantity, int decimals, SignumValue fee, int deadline);

    /**
     * Generate the transaction for transferring assets
     * @param senderPublicKey The public key of the sender
     * @param recipient The recipient
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param assetId The ID of the asset being transfered
     * @param quantity The quantity to transfer
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue fee, int deadline);

    /**
     * Generate the transaction for an ask order
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue fee, int deadline, String message);

    /**
     * Generate the transaction for an ask order
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue fee, int deadline, EncryptedMessage message);

    /**
     * Generate the transaction for an ask order
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param price The order price
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline);

    /**
     * Generate the transaction for a bid order
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param price The order price
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline);

    /**
     * Generate the transaction for cancelling an ask order
     * @param senderPublicKey The public key of the sender
     * @param orderID The ID of the asset being transfered
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, SignumID orderID, SignumValue fee, int deadline);

    /**
     * Generate the transaction for cancelling a bid order
     * @param senderPublicKey The public key of the sender
     * @param orderID The ID of the asset being transfered
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, SignumID orderID, SignumValue fee, int deadline);

    /**
     * Generate the transaction for creating subscription
     * @param senderPublicKey The public key of the sender
     * @param amount Amount of subscription in plancks
     * @param frequency Frequency in which you send amount, seconds
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateSubscriptionCreationTransaction(byte[] senderPublicKey, SignumValue amount, int frequency, SignumValue fee, int deadline);

    /**
     * Generate the transaction for cancelling a subscription
     * @param senderPublicKey The public key of the sender
     * @param subscription The ID of the subscription you want to cancel
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    // TODO TEST
    Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, SignumID subscription, SignumValue fee, int deadline);

    static NodeService getInstance(String nodeAddress) {
        return getInstance(nodeAddress, null);
    }

    static NodeService getInstance(String nodeAddress, String userAgent) {
        if (userAgent == null) userAgent = "burstkit4j/" + signumj.Constants.VERSION;
        return new HttpBurstNodeService(nodeAddress, userAgent);
    }

    static NodeService getCompositeInstance(String... nodeAddresses) {
        return getCompositeInstanceWithUserAgent(null, nodeAddresses);
    }

    static NodeService getCompositeInstanceWithUserAgent(String userAgent, String... nodeAddresses) {
        if (nodeAddresses.length == 0) throw new IllegalArgumentException("No node addresses specified");
        if (nodeAddresses.length == 1) return getInstance(nodeAddresses[0], userAgent);
        return new CompositeBurstNodeService(Arrays.stream(nodeAddresses)
                .map(nodeAddress -> getInstance(nodeAddress, userAgent))
                .toArray(NodeService[]::new));
    }
}
