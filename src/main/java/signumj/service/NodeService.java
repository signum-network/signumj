package signumj.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import signumj.crypto.SignumCrypto;
import signumj.entity.*;
import signumj.entity.response.*;
import signumj.service.impl.CompositeNodeService;
import signumj.service.impl.HttpNodeService;
import signumj.service.impl.UseBestNodeService;

import java.util.ArrayList;
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
    Single<AT[]> getAccountATs(SignumAddress accountId, SignumID machineCodeHashId);

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
     * Get the transactions of an account
     * @param accountId The address of the account
     * @return The account's transactions, wrapped in a single
     */
    Single<Transaction[]> getAccountTransactions(SignumAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect,
    		int type, int subtype);

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
     * @param quantityMinimum the minimum quantity for the number of accounts count (0 if null)
     * @param heightStart the height start for the volume and price statistics (past 24h if null)
     * @param heightEnd the height start for the volume and price statistics (past 24h if null)
     *
     * @return The asset, wrapped in a single
     */
    Single<Asset> getAsset(SignumID assetId, SignumValue quantityMinimum, Integer heightStart, Integer heightEnd);

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
     * Get the aliases
     *
     * @return A list of aliases, wrapped in a single
     */
    Single<Alias[]> getAliases(SignumAddress account, String aliasName, String tld, SignumTimestamp timestamp, Integer firstIndex, Integer lastIndex);

    /**
     * Get the TLDs.
     *
     * @return all the Top Level Domains/Namespaces of the alias system.
     */
    Single<TLD[]> getTLDs(SignumTimestamp timestamp, Integer firstIndex, Integer lastIndex);

    /**
     * Get the subscriptions of a given account
     *
     * @param account
     *
     * @return A list of subscriptions, wrapped in a single
     */
    Single<Subscription[]> getAccountSubscriptions(SignumAddress account);

    /**
     * Get the details of an AT
     * @param at The address of the AT
     * @return The details of the AT, wrapped in a single
     */
    Single<AT> getAt(SignumAddress at);

    /**
     * Get the details for all ATs matching the given code hash Id
     * @param codeHashId The code hash ID we are filtering for
     * @param includeDetails If all the information should be returned (more expensive)
     * @return The AT array
     */
    Single<AT[]> getAts(SignumID codeHashId, Boolean includeDetails, Integer firstIndex, Integer lastIndex);

    /**
     * Get the details of an AT
     * @param at The address of the AT
     * @param includeDetails If all the information should be returned (more expensive)
     * @return The details of the AT, wrapped in a single
     */
    Single<AT> getAt(SignumAddress at, Boolean includeDetails);

    /**
     * Get the list of addresses of all ATs
     * @param codeHashId The code hash ID to be used as filter criteria
     * @return The list of AT addresses, wrapped in a single
     */
    Single<SignumAddress[]> getAtIds(SignumID codeHashId);

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
     * Get the indirect incoming details for the given account and transaction
     * @param account
     * @param transaction
     * @return the indirect incoming details, wrapped in a single
     */
    Single<IndirectIncoming> getIndirectIncoming(SignumAddress account, SignumID transaction);

    /**
     * Get the transaction bytes
     * @param transactionId The ID of the transaction
     * @return The transaction bytes, wrapped in a single
     */
    Single<byte[]> getTransactionBytes(SignumID transactionId);

    /**
     * Generate a add commitment transaction, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to commit
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionAddCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline);

    /**
     * Generate a add commitment transaction, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to remove
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionRemoveCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline);

    /**
     * Generate a simple transaction (only sending money), please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String referencedTransactionFullHash);

    /**
     * Generate a transaction to set the reward recipient, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionSetRewardRecipient(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline);

    /**
     * Generate a transaction with a plaintext message, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message which also sets the recipient's public key in the chain, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipientAddress The recipient's address
     * @param recipientPublicKey The public key of the recipient, to be set in the chain
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message which also sets the recipient's public key in the chain, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipientAddress The recipient's address
     * @param recipientPublicKey The public key of the recipient, to be set in the chain
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with a plaintext message, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted message (can be read by sender and recipient), please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted message (can be read by sender and recipient), please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted-to-self message (can be read by only sender), please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount The amount to send
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction (Use sender public key and sender private key to encrypt)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Generate a transaction with an encrypted-to-self message (can be read by only sender), please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction (Use sender public key and sender private key to encrypt)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash);

    /**
     * Ask the node service to generate the unsigned bytes for the given transaction builder.
     *
     * To sign the transaction, use {@link SignumCrypto#signTransaction(byte[], byte[])}.
     *
     * @param builder the transaction builder
     * @return the unsigned bytes for the transaction
     */
    Single<byte[]> generateTransaction(TransactionBuilder builder);

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
     * Get the current mining info
     * @return An observable that returns the current mining info when it changes.
     */
    Single<MiningInfo> getMiningInfoSingle();

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
     * Generate the transaction for creating an AT, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param name The name of the AT
     * @param description The description of the AT
     * @param creationBytes The creation bytes of the AT (if pre-calculated and not using the following fields)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, String name, String description, byte[] creationBytes, String referencedTransactionFullHash);

    /**
     * Generate the transaction for creating an AT, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param fee The transaction fee
     * @param minActivation The activation amount
     * @param deadline The deadline for the transaction
     * @param name The name of the AT
     * @param description The description of the AT
     * @param code The contract code
     * @param data The creation data
     * @param dpages Number of pages for data
     * @param cspages Call stack pages
     * @param uspages User stack pages
     * @param referencedTransactionFullHash A reference transaction full hash
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, SignumValue fee, SignumValue minActivation, int deadline, String name, String description, byte[] code, byte[]data, int dpages, int cspages, int uspages, String referencedTransactionFullHash);

    /**
     * Generate the transaction for transfering assets, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param name The token name
     * @param description The token description
     * @param quantity The total token supply
     * @param decimals The number of decimals
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, SignumValue quantity, int decimals, SignumValue fee, int deadline);

    /**
     * Adds the recipient account as a treasury account of the asset issued by the given full hash, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient the treasury account to be added
     * @param senderPublicKey
     * @param referencedTransactionFullHash the full hash of the transaction that created the asset
     * @param fee
     * @param deadline
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateAddAssetTreasuryAccountTransaction(SignumAddress recipient, byte[] senderPublicKey, String referencedTransactionFullHash, SignumValue fee, int deadline);

    /**
     * Adds the recipient account as a treasury account of the asset issued by the given full hash, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey
     * @param assetId the asset ID accounts have to hold to receive
     * @param quantityMinimumQNT minimum quantity to be eligible for the distribution
     * @param amount the amount being distributed
     * @param assetToDistribute the asset being also distributed
     * @param quantityQNT quantity of the asset being distributed
     * @param fee
     * @param deadline
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateDistributeToAssetHolders(byte[] senderPublicKey, SignumID assetId, SignumValue quantityMinimumQNT,
    		SignumValue amount, SignumID assetToDistribute, SignumValue quantityQNT, SignumValue fee, int deadline);

    /**
     * Generate the transaction for transferring assets, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param recipient The recipient
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param assetId The ID of the asset being transfered
     * @param quantity The quantity to transfer
     * @param amount The SIGNA amount to send along with this transfer (optional)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline);

    /**
     * Generate the transaction for transferring assets, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param recipient The recipient
     * @param assetIdsAndQuantities the asset ids and quantities being sent
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param amount The SIGNA amount to send along with this transfer (optional)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransferAssetMultiTransaction(byte[] senderPublicKey, SignumAddress recipient, Map<SignumID, SignumValue> assetIdsAndQuantities, SignumValue amount, SignumValue fee, int deadline);

    /**
     * Generate the transaction for an asset transfer with a message, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @param amount The SIGNA amount to send along with this transfer (optional)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline, String message);

    /**
     * Generate the transaction for an asset transfer with a message, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The message to include in the transaction
     * @param amount The SIGNA amount to send along with this transfer (optional)
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline, byte[] message);

    /**
     * Generate the transaction for an ask order, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @param message The encrypted message to include in the transaction
     * @param amount The SIGNA amount to send along with this transfer (optional)
     *
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message);

    /**
     * Generate the transaction for an ask order, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param price The order price
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline);

    /**
     * Generate the transaction for a bid order, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param assetId The ID of the asset being transfered
     * @param quantity The order quantity
     * @param price The order price
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline);

    /**
     * Generate the transaction for cancelling an ask order, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param orderID The ID of the asset being transfered
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, SignumID orderID, SignumValue fee, int deadline);

    /**
     * Generate the transaction for cancelling a bid order, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param orderID The ID of the asset being transfered
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, SignumID orderID, SignumValue fee, int deadline);

    /**
     * Generate the transaction for creating subscription, please use {@link #generateTransaction(TransactionBuilder)}
     * @param recipient The recipient
     * @param senderPublicKey The public key of the sender
     * @param amount Amount of subscription in plancks
     * @param frequency Frequency in which you send amount, seconds
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateSubscriptionCreationTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, int frequency, SignumValue fee, int deadline);

    /**
     * Generate the transaction for cancelling a subscription, please use {@link #generateTransaction(TransactionBuilder)}
     * @param senderPublicKey The public key of the sender
     * @param subscription The ID of the subscription you want to cancel
     * @param fee The transaction fee
     * @param deadline The deadline for the transaction
     * @return The unsigned transaction bytes, wrapped in a single
     */
    @Deprecated
    Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, SignumID subscription, SignumValue fee, int deadline);

    static NodeService getInstance(String nodeAddress) {
        return getInstance(nodeAddress, null);
    }

    static NodeService getInstance(String nodeAddress, String userAgent) {
        return getInstance(nodeAddress, userAgent, signumj.Constants.HTTP_REQUEST_TIMEOUT);
    }

    static NodeService getInstance(String nodeAddress, String userAgent, int requestTimeoutSecs) {
        if (userAgent == null) userAgent = "burstkit4j/" + signumj.Constants.VERSION;
        return new HttpNodeService(nodeAddress, userAgent, requestTimeoutSecs);
    }

    static NodeService getCompositeInstance(String... nodeAddresses) {
        return getCompositeInstanceWithUserAgent(null, nodeAddresses);
    }

    /**
     * Returns a node service instance that will use the best node of a list.
     *
     * @param checkUpToDate check from time to time if the node is up-to-date or not (recent block)
     * @param userAgent the user agent
     * @param nodeAddresses the list of node addresses to choose the best one
     * @return a new node service instance
     */
    static NodeService getUseBestInstance(boolean checkUpToDate, String userAgent, String... nodeAddresses) {
		return getUseBestInstance(checkUpToDate, userAgent, signumj.Constants.HTTP_REQUEST_TIMEOUT, nodeAddresses);
    }

	/**
	 * Returns a node service instance that will use the best node of a list.
	 *
	 * @param checkUpToDate check from time to time if the node is up-to-date or not (recent block)
	 * @param userAgent the user agent
	 * @param requestTimeoutSecs Timeout for requests to node
	 * @param nodeAddresses the list of node addresses to choose the best one
	 * @return a new node service instance
	 */
	static NodeService getUseBestInstance(boolean checkUpToDate, String userAgent, int requestTimeoutSecs, String... nodeAddresses) {
		ArrayList<NodeService> nodeList = new ArrayList<>();
		for(String address : nodeAddresses) {
			nodeList.add(getInstance(address, userAgent, requestTimeoutSecs));
		}
		return new UseBestNodeService(checkUpToDate, nodeList);
	}

	static NodeService getCompositeInstanceWithUserAgent(String userAgent, String... nodeAddresses) {
        if (nodeAddresses.length == 0) throw new IllegalArgumentException("No node addresses specified");
        if (nodeAddresses.length == 1) return getInstance(nodeAddresses[0], userAgent);
        return new CompositeNodeService(Arrays.stream(nodeAddresses)
                .map(nodeAddress -> getInstance(nodeAddress, userAgent))
                .toArray(NodeService[]::new));
    }
}
