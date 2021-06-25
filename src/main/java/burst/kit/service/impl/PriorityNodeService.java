package burst.kit.service.impl;

import burst.kit.entity.*;
import burst.kit.entity.response.*;
import burst.kit.service.BurstNodeService;
import burst.kit.util.BurstKitUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A node server using a list of nodes, priority is given to the one responding faster and that is up-to-date.
 * 
 * The list is updated from time to time and priority is re-evaluated. Nodes not responding are removed from the list.
 */
public class PriorityNodeService implements BurstNodeService {
	
    private final BurstNodeService[] burstNodeServices;
    private AtomicReference<Map<Long, BurstNodeService>> nodePriorityList = new AtomicReference<>();
    private long lastCheck;
    private long nodeCheckInterval = 4 * 60_000L;
	private boolean checkUpToDate;
    
    private static final int LATE_BLOCK_MINUTES = 8;

    /**
     * A node server using a list of nodes, priority is given to the one responding faster and that is up-to-date.
     * 
     * @param burstNodeServices The burst node services this will wrap
     * @param checkUpToDate if true nodes that are not up-to-date go to the end of the list
     */
    public PriorityNodeService(boolean checkUpToDate, BurstNodeService... burstNodeServices) {
        if (burstNodeServices == null || burstNodeServices.length == 0) throw new IllegalArgumentException("No Burst Node Services Provided");
        this.burstNodeServices = burstNodeServices;
        this.checkUpToDate = checkUpToDate;
        
        // Keep checking on nodes and adjusting the list
		new java.util.Timer().schedule(
		        new java.util.TimerTask() {
		            public void run() {
		            	updateNodePriorityList();
		            }
		        }, 0, 4 * 60_000);
    }

    private synchronized <T> void doIfUsedObservable(ObservableEmitter<T> emitter, AtomicInteger usedObservable, AtomicReferenceArray<Disposable> disposables, int myI, Runnable runnable) {
        int used = usedObservable.get();
        if (used == -1) {
            // We are the first!
            usedObservable.set(myI);
            runnable.run();
            // Kill all of the others.
            Disposable myDisposable = disposables.get(myI);
            disposables.set(myI, null);
            emitter.setCancellable(() -> {
                if (myDisposable != null) {
                    myDisposable.dispose();
                }
            }); // Calling this calls the previous one, so all of the others get disposed.
        } else if (used == myI) {
            // We are the used observable.
            runnable.run();
        }
    }

    private <T> Observable<T> compositeObservable(List<Observable<T>> observables) {
        return Observable.create((ObservableEmitter<T> emitter) -> {
            AtomicInteger usedObservable = new AtomicInteger(-1);
            AtomicInteger errorCount = new AtomicInteger(0);
            AtomicReferenceArray<Disposable> disposables = new AtomicReferenceArray<>(observables.size());
            emitter.setCancellable(() -> {
                for (int i = 0; i < disposables.length(); i++) {
                    Disposable disposable = disposables.get(i);
                    if (disposable != null) disposable.dispose();
                }
            });
            for (int i = 0; i < observables.size(); i++) {
                final int myI = i;
                Observable<T> observable = observables.get(i);
                disposables.set(myI, observable.subscribe(t -> doIfUsedObservable(emitter, usedObservable, disposables, myI, () -> emitter.onNext(t)),
                        error -> {
                            synchronized (errorCount) {
                                if (errorCount.incrementAndGet() == observables.size() || usedObservable.get() == myI) { // Every single has errored
                                    emitter.tryOnError(error);
                                }
                            }
                        },
                        () -> doIfUsedObservable(emitter, usedObservable, disposables, myI, emitter::onComplete)));
            }
        })
                .subscribeOn(BurstKitUtils.defaultBurstNodeServiceScheduler());
    }
    
    private <T, U> List<U> map(Collection<T> ts, Function<T, U> mapper) {
        return ts.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    private void updateNodePriorityList() {
    	if(nodePriorityList.get() == null || lastCheck - System.currentTimeMillis() > nodeCheckInterval) {
    		Date now = new Date();
    		lastCheck = System.currentTimeMillis();
    		    		
			// Initialize the list just with the first node, just to have at least one node all times
    		Map<Long, BurstNodeService> newList = new TreeMap<>();
    		newList.put(1L, burstNodeServices[0]);
    		nodePriorityList.set(newList);
    		newList.clear();

    		for (BurstNodeService node : burstNodeServices) {    			
				long start = System.currentTimeMillis();
				try {
					BlockchainStatus status = node.getBlockChainStatus().blockingGet();

					long elapsed = System.currentTimeMillis() - start;
					int latestBlockMinutes = (int) ((now.getTime() - status.getLastBlockTimestamp().getAsDate().getTime())/1000 / 60);

					if(checkUpToDate && latestBlockMinutes > LATE_BLOCK_MINUTES) {
						// lowest priority for late nodes
						elapsed += 1000;
					}

					newList.put(elapsed, node);
				}
				catch (Exception ignored) {
				}
			}
    		if(newList.size() > 0)
    			nodePriorityList.set(newList);
    	}
    }
    
    private <T> Single<T> performByPriority(Function<BurstNodeService, Single<T>> function) {
    	List<Single<T>> singles = map(nodePriorityList.get().values(), function);
        for (int i = 0; i < singles.size()-1; i++) {
            singles.set(i, singles.get(i).onErrorResumeNext(singles.get(i+1)));
        }
        return singles.get(0);
    }

    private <T> Observable<T> performFastestObservable(Function<BurstNodeService, Observable<T>> function) {
        return compositeObservable(map(nodePriorityList.get().values(), function));
    }

    @Override
    public Single<Block> getBlock(BurstID block) {
        return performByPriority(service -> service.getBlock(block));
    }

    @Override
    public Single<Block> getBlock(int height) {
        return performByPriority(service -> service.getBlock(height));
    }

    @Override
    public Single<Block> getBlock(BurstTimestamp timestamp) {
        return performByPriority(service -> service.getBlock(timestamp));
    }

    @Override
    public Single<Block[]> getBlocks(int firstIndex, int lastIndex) {
        return performByPriority(service -> service.getBlocks(firstIndex, lastIndex));
    }

    @Override
    public Single<Constants> getConstants() {
        return performByPriority(BurstNodeService::getConstants);
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId) {
        return getAccount(accountId, null, null, null);
    }

    @Override
    public Single<Account> getAccount(BurstAddress accountId, Integer height, Boolean estimateCommitment, Boolean getCommittedAmount) {
        return performByPriority(service -> service.getAccount(accountId, height, estimateCommitment, getCommittedAmount));
    }

    @Override
    public Single<AT[]> getAccountATs(BurstAddress accountId) {
        return performByPriority(service -> service.getAccountATs(accountId));
    }

    @Override
    public Single<Block[]> getAccountBlocks(BurstAddress accountId) {
        return performByPriority(service -> service.getAccountBlocks(accountId));
    }

    @Override
    public Single<BurstID[]> getAccountTransactionIDs(BurstAddress accountId) {
        return performByPriority(service -> service.getAccountTransactionIDs(accountId));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(BurstAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect) {
        return performByPriority(service -> service.getAccountTransactions(accountId, firstIndex, lastIndex, includeIndirect));
    }

    @Override
    public Single<Transaction[]> getUnconfirmedTransactions(BurstAddress accountId) {
        return performByPriority(service -> service.getUnconfirmedTransactions(accountId));
    }

    @Override
    public Single<BurstAddress[]> getAccountsWithRewardRecipient(BurstAddress accountId) {
        return performByPriority(service -> service.getAccountsWithRewardRecipient(accountId));
    }

    @Override
    public Single<AssetBalance[]> getAssetBalances(BurstID assetId, Integer firstIndex, Integer lastIndex) {
        return performByPriority(service -> service.getAssetBalances(assetId, firstIndex, lastIndex));
    }

    @Override
    public Single<Asset> getAsset(BurstID assetId) {
        return performByPriority(service -> service.getAsset(assetId));
    }

    @Override
    public Single<AssetTrade[]> getAssetTrades(BurstID assetId, BurstAddress account, Integer firstIndex, Integer lastIndex) {
        return performByPriority(service -> service.getAssetTrades(assetId, account, firstIndex, lastIndex));
    }

    @Override
    public Single<AssetOrder[]> getAskOrders(BurstID assetId) {
        return performByPriority(service -> service.getAskOrders(assetId));
    }

    @Override
    public Single<AssetOrder[]> getBidOrders(BurstID assetId) {
        return performByPriority(service -> service.getBidOrders(assetId));
    }

    @Override
    public Single<AT> getAt(BurstAddress at) {
    	return getAt(at, true);
    }

	@Override
	public Single<AT> getAt(BurstAddress at, boolean includeDetails) {
        return performByPriority(service -> service.getAt(at, includeDetails));
	}

    @Override
    public Single<BurstAddress[]> getAtIds() {
        return performByPriority(service -> service.getAtIds());
    }

    @Override
    public Single<Transaction> getTransaction(BurstID transactionId) {
        return performByPriority(service -> service.getTransaction(transactionId));
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return performByPriority(service -> service.getTransaction(fullHash));
    }

    @Override
    public Single<byte[]> getTransactionBytes(BurstID transactionId) {
        return performByPriority(service -> service.getTransactionBytes(transactionId));
    }

    @Override
    public Single<byte[]> generateTransaction(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransaction(recipient, senderPublicKey, amount, fee, deadline, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithMessage(recipientAddress, recipientPublicKey, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, BurstValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithMessage(recipientAddress, recipientPublicKey, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithEncryptedMessage(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithEncryptedMessage(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithEncryptedMessageToSelf(recipient, senderPublicKey, amount, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(BurstAddress recipient, byte[] senderPublicKey, BurstValue fee, int deadline, BurstEncryptedMessage message, String referencedTransactionFullHash) {
        return performByPriority(service -> service.generateTransactionWithEncryptedMessageToSelf(recipient, senderPublicKey, fee, deadline, message, referencedTransactionFullHash));
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return performByPriority(BurstNodeService::suggestFee);
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
        return performFastestObservable(BurstNodeService::getMiningInfo);
    }

    @Override
    public Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes) {
        return performByPriority(service -> service.broadcastTransaction(transactionBytes));
    }

    @Override
    public Single<BurstAddress> getRewardRecipient(BurstAddress account) {
        return performByPriority(service -> service.getRewardRecipient(account));
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, BurstID accountId) {
        return performByPriority(service -> service.submitNonce(passphrase, nonce, accountId));
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, Map<BurstAddress, BurstValue> recipients) throws IllegalArgumentException {
        return performByPriority(service -> service.generateMultiOutTransaction(senderPublicKey, fee, deadline, recipients));
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, BurstValue amount, BurstValue fee, int deadline, Set<BurstAddress> recipients) throws IllegalArgumentException {
        return performByPriority(service -> service.generateMultiOutSameTransaction(senderPublicKey, amount, fee, deadline, recipients));
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, BurstValue fee, int deadline, String name, String description, byte[] creationBytes) {
        return performByPriority(service -> service.generateCreateATTransaction(senderPublicKey, fee, deadline, name, description, creationBytes));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generateTransferAssetTransaction(senderPublicKey, recipient, assetId, quantity, fee, deadline));
    }

    @Override
    public Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, BurstValue quantity, int decimals, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generateIssueAssetTransaction(senderPublicKey, name, description, quantity, decimals, fee, deadline));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline, String message) {
        return performByPriority(service -> service.generateTransferAssetTransactionWithMessage(senderPublicKey, recipient, assetId, quantity, fee, deadline, message));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, BurstAddress recipient, BurstID assetId, BurstValue quantity, BurstValue fee, int deadline, BurstEncryptedMessage message) {
        return performByPriority(service -> service.generateTransferAssetTransactionWithEncryptedMessage(senderPublicKey, recipient, assetId, quantity, fee, deadline, message));
    }

    @Override
    public Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, BurstID assetId, BurstValue quantity, BurstValue price, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generatePlaceAskOrderTransaction(senderPublicKey, assetId, quantity, price, fee, deadline));
    }

    @Override
    public Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, BurstID assetId, BurstValue quantity, BurstValue price, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generatePlaceBidOrderTransaction(senderPublicKey, assetId, quantity, price, fee, deadline));
    }

    @Override
    public Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, BurstID orderId, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generateCancelAskOrderTransaction(senderPublicKey, orderId, fee, deadline));
    }
    
    @Override
    public Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, BurstID orderId, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generateCancelBidOrderTransaction(senderPublicKey, orderId, fee, deadline));
    }

    @Override
    public Single<byte[]> generateSubscriptionCreationTransaction(byte[] senderPublicKey, BurstValue amount, int frequency, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generateSubscriptionCreationTransaction(senderPublicKey, amount, frequency, fee, deadline));
    }

    @Override
    public Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, BurstID subscription, BurstValue fee, int deadline) {
        return performByPriority(service -> service.generateSubscriptionCancelTransaction(senderPublicKey, subscription, fee, deadline));
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
		
		return performByPriority(service -> service.generateTransactionSetRewardRecipient(recipient, senderPublicKey, fee, deadline));
	}

	@Override
	public Single<byte[]> generateTransactionAddCommitment(byte[] senderPublicKey, BurstValue amount, BurstValue fee,
			int deadline) {
		return performByPriority(service -> service.generateTransactionAddCommitment(senderPublicKey, amount, fee, deadline));
	}

	@Override
	public Single<byte[]> generateTransactionRemoveCommitment(byte[] senderPublicKey, BurstValue amount, BurstValue fee,
			int deadline) {
		return performByPriority(service -> service.generateTransactionRemoveCommitment(senderPublicKey, amount, fee, deadline));
	}

	@Override
	public Single<BlockchainStatus> getBlockChainStatus() {
		return performByPriority(service -> service.getBlockChainStatus());
	}
}
