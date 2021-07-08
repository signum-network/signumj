package signumj.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.Single;
import signumj.entity.EncryptedMessage;
import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;
import signumj.entity.SignumValue;
import signumj.entity.response.AT;
import signumj.entity.response.Account;
import signumj.entity.response.Asset;
import signumj.entity.response.AssetBalance;
import signumj.entity.response.AssetOrder;
import signumj.entity.response.AssetTrade;
import signumj.entity.response.Block;
import signumj.entity.response.BlockchainStatus;
import signumj.entity.response.Constants;
import signumj.entity.response.FeeSuggestion;
import signumj.entity.response.MiningInfo;
import signumj.entity.response.Transaction;
import signumj.entity.response.TransactionBroadcast;
import signumj.service.NodeService;
import signumj.util.SignumUtils;

/**
 * A node server using the 'best' of a list of nodes.
 * 
 * The best one is the one responding faster and that is up-to-date.
 * 
 * The best node is updated from time to time or when a call to the
 * current best one fails.
 * 
 */
public class UseBestNodeService implements NodeService {
	
    private final List<NodeService> burstNodeServices;
    private NodeService firstWorkingNode;
    private AtomicReference<NodeService> bestNode = new AtomicReference<>();
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
    public UseBestNodeService(boolean checkUpToDate, List<NodeService> burstNodeServices) {
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
    			NodeService newNode = burstNodeServices.get(0);
    			long bestElapsed = Long.MAX_VALUE;

    			for (NodeService node : burstNodeServices) {
    				long start = System.currentTimeMillis();
    				try {
    					// TODO: use the blockchain status in the future
//    					BurstTimestamp lastBlockTimestamp = node.getBlockChainStatus().blockingGet().getLastBlockTimestamp();
    					SignumTimestamp lastBlockTimestamp = node.getBlocks(0, 1).blockingGet()[0].getTimestamp();

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
    
    private <T> Single<T> performOnBest(Function<NodeService, Single<T>> function) {
    	return function.apply(bestNode.get()).doOnError(throwable -> 
    			// if this node fails, we reset the timer so we re-evaluate the nodes ASAP
    			lastCheck.set(0L)
    			);
    }

    @Override
    public Single<Block> getBlock(SignumID block) {
        return performOnBest(service -> service.getBlock(block));
    }

    @Override
    public Single<Block> getBlock(int height) {
        return performOnBest(service -> service.getBlock(height));
    }

    @Override
    public Single<Block> getBlock(SignumTimestamp timestamp) {
        return performOnBest(service -> service.getBlock(timestamp));
    }

    @Override
    public Single<Block[]> getBlocks(int firstIndex, int lastIndex) {
        return performOnBest(service -> service.getBlocks(firstIndex, lastIndex));
    }

    @Override
    public Single<Constants> getConstants() {
        return performOnBest(NodeService::getConstants);
    }

    @Override
    public Single<Account> getAccount(SignumAddress accountId) {
        return getAccount(accountId, null, null, null);
    }

    @Override
    public Single<Account> getAccount(SignumAddress accountId, Integer height, Boolean estimateCommitment, Boolean getCommittedAmount) {
        return performOnBest(service -> service.getAccount(accountId, height, estimateCommitment, getCommittedAmount));
    }

    @Override
    public Single<AT[]> getAccountATs(SignumAddress accountId) {
        return performOnBest(service -> service.getAccountATs(accountId));
    }

    @Override
    public Single<Block[]> getAccountBlocks(SignumAddress accountId) {
        return performOnBest(service -> service.getAccountBlocks(accountId));
    }

    @Override
    public Single<SignumID[]> getAccountTransactionIDs(SignumAddress accountId) {
        return performOnBest(service -> service.getAccountTransactionIDs(accountId));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(SignumAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect) {
        return performOnBest(service -> service.getAccountTransactions(accountId, firstIndex, lastIndex, includeIndirect));
    }

    @Override
    public Single<Transaction[]> getUnconfirmedTransactions(SignumAddress accountId) {
        return performOnBest(service -> service.getUnconfirmedTransactions(accountId));
    }

    @Override
    public Single<SignumAddress[]> getAccountsWithRewardRecipient(SignumAddress accountId) {
        return performOnBest(service -> service.getAccountsWithRewardRecipient(accountId));
    }

    @Override
    public Single<AssetBalance[]> getAssetBalances(SignumID assetId, Integer firstIndex, Integer lastIndex) {
        return performOnBest(service -> service.getAssetBalances(assetId, firstIndex, lastIndex));
    }

    @Override
    public Single<Asset> getAsset(SignumID assetId) {
        return performOnBest(service -> service.getAsset(assetId));
    }

    @Override
    public Single<AssetTrade[]> getAssetTrades(SignumID assetId, SignumAddress account, Integer firstIndex, Integer lastIndex) {
        return performOnBest(service -> service.getAssetTrades(assetId, account, firstIndex, lastIndex));
    }

    @Override
    public Single<AssetOrder[]> getAskOrders(SignumID assetId) {
        return performOnBest(service -> service.getAskOrders(assetId));
    }

    @Override
    public Single<AssetOrder[]> getBidOrders(SignumID assetId) {
        return performOnBest(service -> service.getBidOrders(assetId));
    }

    @Override
    public Single<AT> getAt(SignumAddress at) {
    	return getAt(at, /* the default is to include */ null);
    }

	@Override
	public Single<AT> getAt(SignumAddress at, Boolean includeDetails) {
        return performOnBest(service -> service.getAt(at, includeDetails));
	}

    @Override
    public Single<SignumAddress[]> getAtIds() {
        return performOnBest(service -> service.getAtIds());
    }

    @Override
    public Single<Transaction> getTransaction(SignumID transactionId) {
        return performOnBest(service -> service.getTransaction(transactionId));
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return performOnBest(service -> service.getTransaction(fullHash));
    }

    @Override
    public Single<byte[]> getTransactionBytes(SignumID transactionId) {
        return performOnBest(service -> service.getTransactionBytes(transactionId));
    }

    @Override
    public Single<byte[]> generateTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransaction(recipient, senderPublicKey, amount, fee, deadline, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipientAddress, recipientPublicKey, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipientAddress, recipientPublicKey, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessageToSelf(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return performOnBest(service -> service.generateTransactionWithEncryptedMessageToSelf(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return performOnBest(NodeService::suggestFee);
    }

    @Override
    public Single<MiningInfo> getMiningInfoSingle() {
        return performOnBest(NodeService::getMiningInfoSingle);
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
    	AtomicReference<MiningInfo> miningInfo = new AtomicReference<>();
        Observable<MiningInfo> obs = Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMapSingle(l -> bestNode.get().getMiningInfoSingle())
                .filter(newMiningInfo -> {
                    synchronized (miningInfo) {
                        if (miningInfo.get() == null
                                || !Objects.equals(miningInfo.get().getGenerationSignature(),
                                        newMiningInfo.getGenerationSignature())
                                || !Objects.equals(miningInfo.get().getHeight(), newMiningInfo.getHeight())) {
                            miningInfo.set(newMiningInfo);
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
        
        return obs.subscribeOn(SignumUtils.defaultNodeServiceScheduler());
    }

    @Override
    public Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes) {
        return performOnBest(service -> service.broadcastTransaction(transactionBytes));
    }

    @Override
    public Single<SignumAddress> getRewardRecipient(SignumAddress account) {
        return performOnBest(service -> service.getRewardRecipient(account));
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, SignumID accountId) {
        return performOnBest(service -> service.submitNonce(passphrase, nonce, accountId));
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, Map<SignumAddress, SignumValue> recipients) throws IllegalArgumentException {
        return performOnBest(service -> service.generateMultiOutTransaction(senderPublicKey, fee, deadline, recipients));
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, Set<SignumAddress> recipients) throws IllegalArgumentException {
        return performOnBest(service -> service.generateMultiOutSameTransaction(senderPublicKey, amount, fee, deadline, recipients));
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, String name, String description, byte[] creationBytes) {
        return performOnBest(service -> service.generateCreateATTransaction(senderPublicKey, fee, deadline, name, description, creationBytes));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generateTransferAssetTransaction(senderPublicKey, recipient, assetId, quantity, fee, deadline));
    }

    @Override
    public Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, SignumValue quantity, int decimals, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generateIssueAssetTransaction(senderPublicKey, name, description, quantity, decimals, fee, deadline));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue fee, int deadline, String message) {
        return performOnBest(service -> service.generateTransferAssetTransactionWithMessage(senderPublicKey, recipient, assetId, quantity, fee, deadline, message));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue fee, int deadline, EncryptedMessage message) {
        return performOnBest(service -> service.generateTransferAssetTransactionWithEncryptedMessage(senderPublicKey, recipient, assetId, quantity, fee, deadline, message));
    }

    @Override
    public Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generatePlaceAskOrderTransaction(senderPublicKey, assetId, quantity, price, fee, deadline));
    }

    @Override
    public Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generatePlaceBidOrderTransaction(senderPublicKey, assetId, quantity, price, fee, deadline));
    }

    @Override
    public Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, SignumID orderId, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generateCancelAskOrderTransaction(senderPublicKey, orderId, fee, deadline));
    }
    
    @Override
    public Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, SignumID orderId, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generateCancelBidOrderTransaction(senderPublicKey, orderId, fee, deadline));
    }

    @Override
    public Single<byte[]> generateSubscriptionCreationTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, int frequency, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generateSubscriptionCreationTransaction(recipient, senderPublicKey, amount, frequency, fee, deadline));
    }

    @Override
    public Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, SignumID subscription, SignumValue fee, int deadline) {
        return performOnBest(service -> service.generateSubscriptionCancelTransaction(senderPublicKey, subscription, fee, deadline));
    }

    @Override
    public void close() throws Exception {
        for (NodeService burstNodeService : burstNodeServices) {
            burstNodeService.close();
        }
    }

	@Override
	public Single<byte[]> generateTransactionSetRewardRecipient(SignumAddress recipient, byte[] senderPublicKey,
			SignumValue fee, int deadline) {
		return performOnBest(service -> service.generateTransactionSetRewardRecipient(recipient, senderPublicKey, fee, deadline));
	}

	@Override
	public Single<byte[]> generateTransactionAddCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee,
			int deadline) {
		return performOnBest(service -> service.generateTransactionAddCommitment(senderPublicKey, amount, fee, deadline));
	}

	@Override
	public Single<byte[]> generateTransactionRemoveCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee,
			int deadline) {
		return performOnBest(service -> service.generateTransactionRemoveCommitment(senderPublicKey, amount, fee, deadline));
	}

	@Override
	public Single<BlockchainStatus> getBlockChainStatus() {
		return performOnBest(service -> service.getBlockChainStatus());
	}
}
