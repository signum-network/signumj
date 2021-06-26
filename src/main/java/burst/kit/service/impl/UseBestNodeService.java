package burst.kit.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.AT;
import burst.kit.entity.response.Account;
import burst.kit.entity.response.Asset;
import burst.kit.entity.response.AssetBalance;
import burst.kit.entity.response.AssetOrder;
import burst.kit.entity.response.AssetTrade;
import burst.kit.entity.response.Block;
import burst.kit.entity.response.BlockchainStatus;
import burst.kit.entity.response.Constants;
import burst.kit.entity.response.FeeSuggestion;
import burst.kit.entity.response.MiningInfo;
import burst.kit.entity.response.Transaction;
import burst.kit.entity.response.TransactionBroadcast;
import burst.kit.service.BurstNodeService;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;

/**
 * A node server using the 'best' of a list of nodes.
 * 
 * The best one is the one responding faster and that is up-to-date.
 * 
 * The best node is updated from time to time or when a call to the
 * current best one fails.
 * 
 */
public class UseBestNodeService implements BurstNodeService {
	
    private final List<BurstNodeService> burstNodeServices;
    private BurstNodeService firstWorkingNode;
    private AtomicReference<BurstNodeService> bestNode = new AtomicReference<>();
    private AtomicLong lastCheck = new AtomicLong(0L);
	private boolean checkUpToDate;
    
    private static final int LATE_BLOCK_MINUTES = 8;
    private final static long NODE_CHECK_INTERVAL_MILLIS = 4 * 60_000L;

    /**
     * A node server using a list of nodes, priority is given to the one responding faster and that is up-to-date.
     * 
     * @param burstNodeServices The burst node services this will wrap
     * @param checkUpToDate if true nodes that are not up-to-date have a lower priority
     */
    public UseBestNodeService(boolean checkUpToDate, List<BurstNodeService> burstNodeServices) {
        if (burstNodeServices == null || burstNodeServices.size() == 0)
        	throw new IllegalArgumentException("No Node Services Provided");
        this.burstNodeServices = burstNodeServices;
        this.checkUpToDate = checkUpToDate;
        this.bestNode.set(burstNodeServices.get(0));
        
        // Keep checking on nodes and adjusting the list
        Thread nodeChecker = new Thread("node-checker") {
        	public void run() {
        		checkBestNode();
        	};
        };
        nodeChecker.start();
    }
    
	@Override
	public String getAddress() {
		return bestNode.get().getAddress();
	}

    private void checkBestNode() {
    	while(true) {
    		if(System.currentTimeMillis() - lastCheck.get() > NODE_CHECK_INTERVAL_MILLIS) {
    			Date now = new Date();
    			lastCheck.set(System.currentTimeMillis());

    			// Initialize the list just with the first node, just to have at least one node all times
    			BurstNodeService newNode = burstNodeServices.get(0);
    			long bestElapsed = Long.MAX_VALUE;

    			for (BurstNodeService node : burstNodeServices) {
    				long start = System.currentTimeMillis();
    				try {
    					// TODO: use the blockchain status in the future
//    					BurstTimestamp lastBlockTimestamp = node.getBlockChainStatus().blockingGet().getLastBlockTimestamp();
    					BurstTimestamp lastBlockTimestamp = node.getBlocks(0, 1).blockingGet()[0].getTimestamp();

    					long elapsed = System.currentTimeMillis() - start;
    					int latestBlockMinutes = (int) ((now.getTime() - lastBlockTimestamp.getAsDate().getTime())/1000 / 60);

    					if(checkUpToDate && latestBlockMinutes > LATE_BLOCK_MINUTES) {
    						// lowest priority for late nodes
    						elapsed += 1000;
    					}
    					
    					if(elapsed < bestElapsed) {
    						newNode = node;
    						bestElapsed = elapsed;
    					}
    					
    					if(firstWorkingNode == null) {
    						// let's have at least a working node as soon as possible
    						firstWorkingNode = node;
    						bestNode.set(node);
    					}
    				}
    				catch (Exception ignored) {
    				}
    			}
    			bestNode.set(newNode);
    		}
    		
    		try {
				Thread.sleep(10);
			} catch (InterruptedException ignored) {
				break;
			}
    	}
    }
    
    private <T> Single<T> performOnBest(Function<BurstNodeService, Single<T>> function) {
    	return function.apply(bestNode.get()).doOnError(
    			// if this node fails, we reset the timer so we re-evaluate the nodes ASAP
    			throwable -> lastCheck.set(0L)
    			);
    }

    private <T> Observable<T> performOnBestObservable(Function<BurstNodeService, Observable<T>> function) {
    	Observable<T> obs = Observable.create((ObservableEmitter<T> emitter) -> {
    		emitter.onNext(function.apply(bestNode.get())
    				.doOnError(throwable -> lastCheck.set(0L))
    				.blockingFirst());
    	});

    	return obs;
    }

    @Override
    public Single<Block> getBlock(BurstID block) {
        return performOnBest(service -> service.getBlock(block));
    }

    @Override
    public Single<Block> getBlock(int height) {
        return performOnBest(service -> service.getBlock(height));
    }

    @Override
    public Single<Block> getBlock(BurstTimestamp timestamp) {
        return performOnBest(service -> service.getBlock(timestamp));
    }

    @Override
    public Single<Block[]> getBlocks(int firstIndex, int lastIndex) {
        return performOnBest(service -> service.getBlocks(firstIndex, lastIndex));
    }

    @Override
    public Single<Constants> getConstants() {
        return performOnBest(BurstNodeService::getConstants);
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId) {
        return getAccount(accountId, null, null, null);
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId, Integer height, Boolean estimateCommitment, Boolean getCommittedAmount) {
        return performOnBest(service -> service.getAccount(accountId, height, estimateCommitment, getCommittedAmount));
    }

    @Override
    public Single<AT[]> getAccountATs(BurstAddress accountId) {
        return performOnBest(service -> service.getAccountATs(accountId));
    }

    @Override
    public Single<Block[]> getAccountBlocks(BurstAddress accountId) {
        return performOnBest(service -> service.getAccountBlocks(accountId));
    }

    @Override
    public Single<BurstID[]> getAccountTransactionIDs(BurstAddress accountId) {
        return performOnBest(service -> service.getAccountTransactionIDs(accountId));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(BurstAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect) {
        return performOnBest(service -> service.getAccountTransactions(accountId, firstIndex, lastIndex, includeIndirect));
    }

    @Override
    public Single<Transaction[]> getUnconfirmedTransactions(BurstAddress accountId) {
        return performOnBest(service -> service.getUnconfirmedTransactions(accountId));
    }

    @Override
    public Single<BurstAddress[]> getAccountsWithRewardRecipient(BurstAddress accountId) {
        return performOnBest(service -> service.getAccountsWithRewardRecipient(accountId));
    }

    @Override
    public Single<AssetBalance[]> getAssetBalances(BurstID assetId, Integer firstIndex, Integer lastIndex) {
        return performOnBest(service -> service.getAssetBalances(assetId, firstIndex, lastIndex));
    }

    @Override
    public Single<Asset> getAsset(BurstID assetId) {
        return performOnBest(service -> service.getAsset(assetId));
    }

    @Override
    public Single<AssetTrade[]> getAssetTrades(BurstID assetId, BurstAddress account, Integer firstIndex, Integer lastIndex) {
        return performOnBest(service -> service.getAssetTrades(assetId, account, firstIndex, lastIndex));
    }

    @Override
    public Single<AssetOrder[]> getAskOrders(BurstID assetId) {
        return performOnBest(service -> service.getAskOrders(assetId));
    }

    @Override
    public Single<AssetOrder[]> getBidOrders(BurstID assetId) {
        return performOnBest(service -> service.getBidOrders(assetId));
    }

    @Override
    public Single<AT> getAt(BurstAddress at) {
    	return getAt(at, true);
    }

	@Override
	public Single<AT> getAt(BurstAddress at, boolean includeDetails) {
        return performOnBest(service -> service.getAt(at, includeDetails));
	}

    @Override
    public Single<BurstAddress[]> getAtIds() {
        return performOnBest(service -> service.getAtIds());
    }

    @Override
    public Single<Transaction> getTransaction(BurstID transactionId) {
        return performOnBest(service -> service.getTransaction(transactionId));
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return performOnBest(service -> service.getTransaction(fullHash));
    }

    @Override
    public Single<byte[]> getTransactionBytes(BurstID transactionId) {
        return performOnBest(service -> service.getTransactionBytes(transactionId));
    }

    @Override
    public Single<byte[]> generateTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransaction(recipient, senderPublicKey, amount, fee, deadline, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipientAddress, recipientPublicKey, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipientAddress, recipientPublicKey, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessageToSelf(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessageToSelf(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return performOnBest(BurstNodeService::suggestFee);
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
        return performOnBestObservable(BurstNodeService::getMiningInfo);
    }

    @Override
    public Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes) {
        return performOnBest(service -> service.broadcastTransaction(transactionBytes));
    }

    @Override
    public Single<BurstAddress> getRewardRecipient(BurstAddress account) {
        return performOnBest(service -> service.getRewardRecipient(account));
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, BurstID accountId) {
        return performOnBest(service -> service.submitNonce(passphrase, nonce, accountId));
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, Map<BurstAddress, BurstValue> recipients) throws IllegalArgumentException {
        return performOnBest(service -> service.generateMultiOutTransaction(senderPublicKey, fee, deadline, recipients));
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Set<BurstAddress> recipients) throws IllegalArgumentException {
        return performOnBest(service -> service.generateMultiOutSameTransaction(senderPublicKey, amount, fee, deadline, recipients));
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, String name, String description, byte[] creationBytes) {
        return performOnBest(service -> service.generateCreateATTransaction(senderPublicKey, fee, deadline, name, description, creationBytes));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generateTransferAssetTransaction(senderPublicKey, recipient, assetId, quantity, fee, deadline));
    }

    @Override
    public Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, BurstValue quantity, int decimals, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generateIssueAssetTransaction(senderPublicKey, name, description, quantity, decimals, fee, deadline));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline, String message) {
        return performOnBest(service -> service.generateTransferAssetTransactionWithMessage(senderPublicKey, recipient, assetId, quantity, fee, deadline, message));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return performOnBest(service -> service.generateTransferAssetTransactionWithEncryptedMessage(senderPublicKey, recipient, assetId, quantity, fee, deadline, message));
    }

    @Override
    public Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, BurstID assetId, BurstValue quantity, BurstValue price, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generatePlaceAskOrderTransaction(senderPublicKey, assetId, quantity, price, fee, deadline));
    }

    @Override
    public Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, BurstID assetId, BurstValue quantity, BurstValue price, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generatePlaceBidOrderTransaction(senderPublicKey, assetId, quantity, price, fee, deadline));
    }

    @Override
    public Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, BurstID orderId, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generateCancelAskOrderTransaction(senderPublicKey, orderId, fee, deadline));
    }
    
    @Override
    public Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, BurstID orderId, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generateCancelBidOrderTransaction(senderPublicKey, orderId, fee, deadline));
    }

    @Override
    public Single<byte[]> generateSubscriptionCreationTransaction(byte[] senderPublicKey, BurstValue amount, int frequency, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generateSubscriptionCreationTransaction(senderPublicKey, amount, frequency, fee, deadline));
    }

    @Override
    public Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, BurstID subscription, BurstValue fee, int deadline) {
        return performOnBest(service -> service.generateSubscriptionCancelTransaction(senderPublicKey, subscription, fee, deadline));
    }

    @Override
    public void close() throws Exception {
        for (BurstNodeService burstNodeService : burstNodeServices) {
            burstNodeService.close();
        }
    }

	@Override
	public Single<byte[]> generateTransactionSetRewardRecipient(BurstAddress recipient, byte[] senderPublicKey,
			BurstValue fee, int deadline) {
		return performOnBest(service -> service.generateTransactionSetRewardRecipient(recipient, senderPublicKey, fee, deadline));
	}

	@Override
	public Single<byte[]> generateTransactionAddCommitment(byte[] senderPublicKey, BurstValue amount, BurstValue fee,
			int deadline) {
		return performOnBest(service -> service.generateTransactionAddCommitment(senderPublicKey, amount, fee, deadline));
	}

	@Override
	public Single<byte[]> generateTransactionRemoveCommitment(byte[] senderPublicKey, BurstValue amount, BurstValue fee,
			int deadline) {
		return performOnBest(service -> service.generateTransactionRemoveCommitment(senderPublicKey, amount, fee, deadline));
	}

	@Override
	public Single<BlockchainStatus> getBlockChainStatus() {
		return performOnBest(service -> service.getBlockChainStatus());
	}
}
