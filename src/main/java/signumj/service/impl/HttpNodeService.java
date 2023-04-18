package signumj.service.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.net.SocketFactory;

import org.bouncycastle.util.encoders.Hex;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import signumj.entity.*;
import signumj.entity.response.*;
import signumj.entity.response.http.*;
import signumj.service.ApiException;
import signumj.service.NodeService;
import signumj.service.TransactionBuilder;
import signumj.util.SignumUtils;

public final class HttpNodeService implements NodeService {
    private SignumAPIService apiService;
    private String nodeAddress;

    public HttpNodeService(String nodeAddress, String userAgent) {
    	this(nodeAddress, userAgent, 10);
    }
    	
    public HttpNodeService(String nodeAddress, String userAgent, int readTimeout) {
    	
    	this.nodeAddress = nodeAddress;
    	SocketFactory socketFactory = SocketFactory.getDefault();
    	Dns dns = Dns.SYSTEM;
    	
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
        		.readTimeout(readTimeout, TimeUnit.SECONDS)
        		.socketFactory(socketFactory)
        		.dns(dns)
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().header("User-Agent", userAgent).build()))
                .build();

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(nodeAddress)
                .addConverterFactory(GsonConverterFactory.create(SignumUtils.buildGson().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        apiService = retrofit.create(SignumAPIService.class);
    }
    
	@Override
	public String getAddress() {
		return nodeAddress;
	}

    private <T> Single<T> assign(Single<T> source) {
        return source.map(this::checkBrsResponse).subscribeOn(SignumUtils.defaultNodeServiceScheduler());
    }

    private <T> Observable<T> assign(Observable<T> source) {
        return source.subscribeOn(SignumUtils.defaultNodeServiceScheduler());
    }

    private <T> T checkBrsResponse(T source) throws BRSError {
        if (source instanceof BRSResponse) {
            ((BRSResponse) source).throwIfError();
        }
        return source;
    }

    @Override
    public Single<Block> getBlock(SignumID block) {
        return assign(apiService.getBlock(SignumUtils.getEndpoint(), block.getID(), null, null, false))
                .map(Block::new);
    }

    @Override
    public Single<Block> getBlock(int height) {
        return assign(apiService.getBlock(SignumUtils.getEndpoint(), null, String.valueOf(height), null, false))
                        .map(Block::new);
    }

    @Override
    public Single<Block> getBlock(SignumTimestamp timestamp) {
        return assign(apiService.getBlock(SignumUtils.getEndpoint(), null, null,
                String.valueOf(timestamp.getTimestamp()), false)).map(Block::new);
    }

    @Override
    public Single<Block[]> getBlocks(int firstIndex, int lastIndex) {
        return assign(apiService.getBlocks(SignumUtils.getEndpoint(), String.valueOf(firstIndex),
                String.valueOf(lastIndex), null))
                        .map(response -> Arrays.stream(response.getBlocks()).map(Block::new)
                                .collect(Collectors.toList()).toArray(new Block[0]));
    }
    
	@Override
	public Single<BlockchainStatus> getBlockChainStatus() {
        return assign(apiService.getBlockchainStatus(SignumUtils.getEndpoint()).map(BlockchainStatus::new));
	}

    @Override
    public Single<Constants> getConstants() {
        return assign(apiService.getConstants(SignumUtils.getEndpoint())).map(Constants::new);
    }

    @Override
    public Single<Account> getAccount(SignumAddress accountId) {
    	return getAccount(accountId, null, null, null);
    }

    @Override
    public Single<Account> getAccount(SignumAddress accountId, Integer height, Boolean estimateCommitment, Boolean getCommittedAmount) {
        return assign(apiService.getAccount(SignumUtils.getEndpoint(), accountId.getID(),
        		height==null ? null : String.valueOf(height), estimateCommitment==null ? null : String.valueOf(estimateCommitment),
        				getCommittedAmount==null ? null : String.valueOf(getCommittedAmount) )).map(Account::new);
    }

    @Override
    public Single<AT[]> getAccountATs(SignumAddress accountId, SignumID machineCodeHashId) {
        return assign(apiService.getAccountATs(SignumUtils.getEndpoint(), accountId.getID(),
        		machineCodeHashId == null ? null : machineCodeHashId.getID()))
                .map(response -> Arrays.stream(response.getATs()).map(AT::new).toArray(AT[]::new));
    }

    @Override
    public Single<Block[]> getAccountBlocks(SignumAddress accountId) {
        return assign(apiService.getAccountBlocks(SignumUtils.getEndpoint(), accountId.getID(), null, null,
                null, null)).map(response -> Arrays.stream(response.getBlocks()).map(Block::new).toArray(Block[]::new));
    }

    @Override
    public Single<SignumID[]> getAccountTransactionIDs(SignumAddress accountId) {
        return assign(apiService.getAccountTransactionIDs(SignumUtils.getEndpoint(), accountId.getID(), null,
                null, null, null, null, null))
                        .map(response -> Arrays.stream(response.getTransactionIds()).map(SignumID::fromLong)
                                .toArray(SignumID[]::new));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(SignumAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect) {
        return assign(apiService.getAccountTransactions(SignumUtils.getEndpoint(), accountId.getID(), null,
                null, null, firstIndex!=null ? firstIndex.toString() : null, lastIndex!=null ? lastIndex.toString() : null, null,
                		includeIndirect!=null && includeIndirect ? "true" : "false"))
                        .map(response -> Arrays.stream(response.getTransactions()).map(Transaction::new)
                                .toArray(Transaction[]::new));
    }

    @Override
    public Single<Transaction[]> getAccountTransactions(SignumAddress accountId, Integer firstIndex, Integer lastIndex, Boolean includeIndirect, int type, int subtype) {
        return assign(apiService.getAccountTransactions(SignumUtils.getEndpoint(), accountId.getID(), null,
                Integer.toString(type), Integer.toString(subtype), firstIndex!=null ? firstIndex.toString() : null, lastIndex!=null ? lastIndex.toString() : null, null,
                		includeIndirect!=null && includeIndirect ? "true" : "false"))
                        .map(response -> Arrays.stream(response.getTransactions()).map(Transaction::new)
                                .toArray(Transaction[]::new));
    }
    
    @Override
    public Single<Transaction[]> getUnconfirmedTransactions(SignumAddress accountId) {
        return assign(apiService.getUnconfirmedTransactions(SignumUtils.getEndpoint(), accountId==null ? null : accountId.getID()))
                .map(response -> Arrays.stream(response.getUnconfirmedTransactions()).map(Transaction::new)
                        .toArray(Transaction[]::new));
    }

    @Override
    public Single<SignumAddress[]> getAccountsWithRewardRecipient(SignumAddress accountId) {
        return assign(apiService.getAccountsWithRewardRecipient(SignumUtils.getEndpoint(), accountId.getID()))
                .map(response -> Arrays.stream(response.getAccounts()).map(SignumAddress::fromEither)
                        .toArray(SignumAddress[]::new));
    }

    @Override
    public Single<Asset> getAsset(SignumID assetId, SignumValue quantityMinimum, Integer heightStart, Integer heightEnd) {
        return assign(apiService.getAsset(SignumUtils.getEndpoint(), assetId.getID(),
        		quantityMinimum == null ? null : quantityMinimum.toNQT().toString(), heightStart, heightEnd)).map(Asset::new);
    }

    @Override
    public Single<AssetBalance[]> getAssetBalances(SignumID assetId, Integer firstIndex, Integer lastIndex) {
        return assign(apiService.getAssetAccounts(SignumUtils.getEndpoint(), assetId.getID(), firstIndex, lastIndex))
                .map(response -> Arrays.stream(response.getAccountsAsset()).map(AssetBalance::new)
                        .toArray(AssetBalance[]::new));
    }

    @Override
    public Single<AssetTrade[]> getAssetTrades(SignumID assetId, SignumAddress account, Integer firstIndex, Integer lastIndex) {
        return assign(apiService.getAssetTrades(SignumUtils.getEndpoint(), assetId.getID(), account!=null ? account.getID() : null, firstIndex, lastIndex))
                .map(response -> Arrays.stream(response.getTrades()).map(AssetTrade::new).toArray(AssetTrade[]::new));
    }

    @Override
    public Single<AssetOrder[]> getAskOrders(SignumID assetId) {
        return assign(apiService.getAskOrders(SignumUtils.getEndpoint(), assetId.getID()))
                .map(response -> Arrays.stream(response.getOrders()).map(AssetOrder::new).toArray(AssetOrder[]::new));
    }

    @Override
    public Single<AssetOrder[]> getBidOrders(SignumID assetId) {
        return assign(apiService.getBidOrders(SignumUtils.getEndpoint(), assetId.getID()))
                .map(response -> Arrays.stream(response.getOrders()).map(AssetOrder::new).toArray(AssetOrder[]::new));
    }
    
    @Override
    public Single<Alias[]> getAliases(SignumAddress account, String aliasName, String tld, SignumTimestamp timestamp, Integer firstIndex,
    		Integer lastIndex) {
        return assign(apiService.getAliases(SignumUtils.getEndpoint(), account.getID(), aliasName, tld,
        		timestamp == null ? null : String.valueOf(timestamp.getTimestamp()), firstIndex, lastIndex))
                .map(response -> Arrays.stream(response.getAliases()).map(Alias::new).toArray(Alias[]::new));
    }
    
	@Override
	public Single<TLD[]> getTLDs(SignumTimestamp timestamp, Integer firstIndex, Integer lastIndex) {
        return assign(apiService.getTLDs(SignumUtils.getEndpoint(),
        		timestamp == null ? null : String.valueOf(timestamp.getTimestamp()), firstIndex, lastIndex))
                .map(response -> Arrays.stream(response.getTLDs()).map(TLD::new).toArray(TLD[]::new));
	}


    @Override
    public Single<AT> getAt(SignumAddress atId) {
        return getAt(atId, null);
    }
    
    @Override
    public Single<AT> getAt(SignumAddress atId, Boolean includeDetails) {
        return assign(apiService.getAt(SignumUtils.getEndpoint(), atId.getID(), includeDetails)).map(AT::new);
    }

    @Override
    public Single<SignumAddress[]> getAtIds(SignumID codeHashId) {
        return assign(apiService.getAtIds(SignumUtils.getEndpoint(), codeHashId == null ? null : codeHashId.getID())).map(
                response -> Arrays.stream(response.getAtIds()).map(SignumAddress::fromId).toArray(SignumAddress[]::new));
    }
    
    @Override
    public Single<AT[]> getAts(SignumID codeHashId, Boolean includeDetails, Integer firstIndex, Integer lastIndex) {
        return assign(apiService.getAts(SignumUtils.getEndpoint(), codeHashId.getID(), includeDetails, firstIndex, lastIndex).map(
        		response -> Arrays.stream(response.getATs()).map(AT::new).toArray(AT[]::new)));
    }

    @Override
    public Single<Transaction> getTransaction(SignumID transactionId) {
        return assign(apiService.getTransaction(SignumUtils.getEndpoint(), transactionId.getID(), null))
                .map(Transaction::new);
    }

    @Override
    public Single<Transaction> getTransaction(byte[] fullHash) {
        return assign(apiService.getTransaction(SignumUtils.getEndpoint(), null, Hex.toHexString(fullHash)))
                .map(Transaction::new);
    }

    @Override
    public Single<IndirectIncoming> getIndirectIncoming(SignumAddress account, SignumID transaction) {
        return assign(apiService.getIndirectIncoming(SignumUtils.getEndpoint(), account.getID(), transaction.getID()))
                .map(IndirectIncoming::new);
    }

    @Override
    public Single<byte[]> getTransactionBytes(SignumID transactionId) {
        return assign(apiService.getTransactionBytes(SignumUtils.getEndpoint(), transactionId.getID()))
                .map(response -> Hex.decode(response.getTransactionBytes()));
    }
    
	@Override
	public Single<byte[]> generateTransaction(TransactionBuilder builder) {
		String url = SignumUtils.getEndpoint() + "?requestType=" + builder.getRequestType();
        return assign(apiService.generateTransaction(url, builder.getParams(), builder.getBody()))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
	}

    @Override
    public Single<byte[]> generateTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String referencedTransactionFullHash) {
        return assign(apiService.sendMoney(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                amount.toNQT().toString(), null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionAddCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline) {
        return assign(apiService.addCommitment(SignumUtils.getEndpoint(),
                amount.toNQT().toString(), null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionRemoveCommitment(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline) {
        return assign(apiService.removeCommitment(SignumUtils.getEndpoint(),
                amount.toNQT().toString(), null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionSetRewardRecipient(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline) {
        return assign(apiService.setRewardRecipient(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return assign(apiService.sendMoney(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                amount != null ? amount.toNQT().toString() : null, null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, message, true, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return assign(apiService.sendMessage(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, message, true, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return assign(apiService.sendMoney(SignumUtils.getEndpoint(), recipientAddress.getID(), Hex.toHexString(recipientPublicKey),
                amount != null ? amount.toNQT().toString() : null, null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, message, true, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipientAddress, byte[] recipientPublicKey, byte[] senderPublicKey, SignumValue fee, int deadline, String message, String referencedTransactionFullHash) {
        return assign(apiService.sendMessage(SignumUtils.getEndpoint(), recipientAddress.getID(), Hex.toHexString(recipientPublicKey),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, message, true, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }


    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return assign(apiService.sendMoney(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                amount != null ? amount.toNQT().toString() : null, null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, Hex.toHexString(message), false, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, byte[] message, String referencedTransactionFullHash) {
        return assign(apiService.sendMessage(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, Hex.toHexString(message), false, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return assign(apiService.sendMoney(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                amount.toNQT().toString(), null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, null, null, null, message.isText(), Hex.toHexString(message.getData()),
                Hex.toHexString(message.getNonce()), null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessage(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return assign(apiService.sendMessage(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, null, null, null, message.isText(), Hex.toHexString(message.getData()),
                Hex.toHexString(message.getNonce()), null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return assign(apiService.sendMoney(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                amount.toNQT().toString(), null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, null, null, null, null, null, null, null, message.isText(),
                Hex.toHexString(message.getData()), Hex.toHexString(message.getNonce())))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransactionWithEncryptedMessageToSelf(SignumAddress recipient, byte[] senderPublicKey, SignumValue fee, int deadline, EncryptedMessage message, String referencedTransactionFullHash) {
        return assign(apiService.sendMessage(SignumUtils.getEndpoint(), recipient.getID(), recipient.getPublicKeyString(),
                 null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(),
                deadline, referencedTransactionFullHash, false, null, null, null, null, null, null, null, message.isText(),
                Hex.toHexString(message.getData()), Hex.toHexString(message.getNonce())))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateIssueAssetTransaction(byte[] senderPublicKey, String name, String description, SignumValue quantity, int decimals, SignumValue fee, int deadline) {
        return assign(apiService.issueAsset(SignumUtils.getEndpoint(), name, description, quantity.toNQT().toString(),
                decimals, null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }
    
    @Override
    public Single<byte[]> generateAddAssetTreasuryAccountTransaction(SignumAddress recipient, byte[] senderPublicKey,
    		String referencedTransactionFullHash, SignumValue fee, int deadline) {
        return assign(apiService.addAssetTreasuryAccount(SignumUtils.getEndpoint(), recipient.getID(), null, Hex.toHexString(senderPublicKey),
        		fee.toNQT().toString(), deadline, referencedTransactionFullHash, false, null, false, null, false, null, null, null, false, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }
    
    @Override
    public Single<byte[]> generateDistributeToAssetHolders(byte[] senderPublicKey, SignumID assetId,
    		SignumValue quantityMinimumQNT, SignumValue amount, SignumID assetToDistribute, SignumValue quantityQNT,
    		SignumValue fee, int deadline) {
        return assign(apiService.distributeToAssetHolders(SignumUtils.getEndpoint(),
        		assetId.getID(), quantityMinimumQNT.toNQT().toString(), amount.toNQT().toString(),
        		assetToDistribute.getID(), quantityQNT.toNQT().toString(),
        		null, Hex.toHexString(senderPublicKey),
        		fee.toNQT().toString(), deadline, null, false, null, false, null, false, null, null, null, false, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransaction(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline) {
        return assign(apiService.transferAsset(SignumUtils.getEndpoint(), recipient.getID(), assetId.getID(), null, quantity.toNQT().toString(), amount == null ? null : amount.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }
    
    @Override
    public Single<byte[]> generateTransferAssetMultiTransaction(byte[] senderPublicKey, SignumAddress recipient, Map<SignumID, SignumValue> assetIdAndQuantity, SignumValue amount, SignumValue fee, int deadline) {
    	
        StringBuilder assetIdAndQuantityString = new StringBuilder();
        if (assetIdAndQuantity.size() > 4 || assetIdAndQuantity.size() < 2) {
            throw new IllegalArgumentException("Must have 2-4 assets, had " + assetIdAndQuantity.size());
        }
        for (Map.Entry<SignumID, SignumValue> entry : assetIdAndQuantity.entrySet()) {
        	assetIdAndQuantityString.append(entry.getKey().getID()).append(":").append(entry.getValue().toNQT())
                    .append(";");
        }
        assetIdAndQuantityString.setLength(assetIdAndQuantityString.length() - 1);
        
        return assign(apiService.transferAssetMulti(SignumUtils.getEndpoint(), recipient.getID(),null, assetIdAndQuantityString.toString(), amount == null ? null : amount.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline, String message) {
        return assign(apiService.transferAsset(SignumUtils.getEndpoint(), recipient.getID(), assetId.getID(), null, quantity.toNQT().toString(), amount == null ? null : amount.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, message, true, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline, byte[] message) {
        return assign(apiService.transferAsset(SignumUtils.getEndpoint(), recipient.getID(), assetId.getID(), null, quantity.toNQT().toString(), amount == null ? null : amount.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, Hex.toHexString(message), false, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateTransferAssetTransactionWithEncryptedMessage(byte[] senderPublicKey, SignumAddress recipient, SignumID assetId, SignumValue quantity, SignumValue amount, SignumValue fee, int deadline, EncryptedMessage message) {
        return assign(apiService.transferAsset(SignumUtils.getEndpoint(), recipient.getID(), assetId.getID(), null, quantity.toNQT().toString(), amount == null ? null : amount.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null,
                 message.isText(), Hex.toHexString(message.getData()), Hex.toHexString(message.getNonce()), null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generatePlaceAskOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline) {
        return assign(apiService.placeAskOrder(SignumUtils.getEndpoint(), assetId.getID(), null, quantity.toNQT().toString(), price.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generatePlaceBidOrderTransaction(byte[] senderPublicKey, SignumID assetId, SignumValue quantity, SignumValue price, SignumValue fee, int deadline) {
        return assign(apiService.placeBidOrder(SignumUtils.getEndpoint(), assetId.getID(), null, quantity.toNQT().toString(), price.toNQT().toString(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateCancelAskOrderTransaction(byte[] senderPublicKey, SignumID orderId, SignumValue fee, int deadline) {
        return assign(apiService.cancelAskOrder(SignumUtils.getEndpoint(), orderId.getID(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateCancelBidOrderTransaction(byte[] senderPublicKey, SignumID orderId, SignumValue fee, int deadline) {
        return assign(apiService.cancelBidOrder(SignumUtils.getEndpoint(), orderId.getID(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateSubscriptionCreationTransaction(SignumAddress recipient, byte[] senderPublicKey, SignumValue amount, int frequency, SignumValue fee, int deadline) {
        return assign(apiService.createSubscription(SignumUtils.getEndpoint(),
                recipient.getID(), null, amount.toNQT().toString(), frequency,
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateSubscriptionCancelTransaction(byte[] senderPublicKey, SignumID subscription, SignumValue fee, int deadline) {
        return assign(apiService.cancelSubscription(SignumUtils.getEndpoint(), subscription.getID(),
                null, Hex.toHexString(senderPublicKey), fee.toNQT().toString(), deadline, null, false, null, null, null, null, null, null, null, null, null, null))
                .map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<FeeSuggestion> suggestFee() {
        return assign(apiService.suggestFee(SignumUtils.getEndpoint())).map(FeeSuggestion::new);
    }

    @Override
    public Observable<MiningInfo> getMiningInfo() {
        AtomicReference<MiningInfo> miningInfo = new AtomicReference<>();
        return assign(Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMapSingle(l -> getMiningInfoSingle())
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
                }));
    }
    
    @Override
    public Single<MiningInfo> getMiningInfoSingle() {
        return apiService.getMiningInfo(SignumUtils.getEndpoint()).map(this::checkBrsResponse).map(MiningInfo::new);
    }

    @Override
    public Single<TransactionBroadcast> broadcastTransaction(byte[] transactionBytes) {
        if (transactionBytes.length >= 2560) {
          return assign(apiService.broadcastTransactionBig(SignumUtils.getEndpoint(), Hex.toHexString(transactionBytes)))
              .map(TransactionBroadcast::new);          
        }
        return assign(apiService.broadcastTransaction(SignumUtils.getEndpoint(), Hex.toHexString(transactionBytes)))
                        .map(TransactionBroadcast::new);
    }

    @Override
    public Single<SignumAddress> getRewardRecipient(SignumAddress account) {
        return assign(apiService.getRewardRecipient(SignumUtils.getEndpoint(), account.getID()))
                .map(response -> SignumAddress.fromEither(response.getRewardRecipient()));
    }

    @Override
    public Single<Long> submitNonce(String passphrase, String nonce, SignumID accountId) {
        return assign(apiService.submitNonce(SignumUtils.getEndpoint(), passphrase, nonce,
                accountId == null ? null : accountId.getID(), "")).map(submitNonceResponse -> {
                    if (!Objects.equals(submitNonceResponse.getResult(), "success")) {
                        throw new ApiException("Failed to submit nonce: " + submitNonceResponse.getResult());
                    }
                    return submitNonceResponse;
                }).map(SubmitNonceResponse::getDeadline);
    }

    @Override
    public Single<byte[]> generateMultiOutTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, Map<SignumAddress, SignumValue> recipients) throws IllegalArgumentException {
        return Single.fromCallable(() -> {
            StringBuilder recipientsString = new StringBuilder();
            if (recipients.size() > 64 || recipients.size() < 2) {
                throw new IllegalArgumentException("Must have 2-64 recipients, had " + recipients.size());
            }
            for (Map.Entry<SignumAddress, SignumValue> recipient : recipients.entrySet()) {
                recipientsString.append(recipient.getKey().getID()).append(":").append(recipient.getValue().toNQT())
                        .append(";");
            }
            recipientsString.setLength(recipientsString.length() - 1);
            return recipientsString;
        }).flatMap(recipientsString -> assign(
                apiService.sendMoneyMulti(SignumUtils.getEndpoint(), null, Hex.toHexString(senderPublicKey),
                        fee.toNQT().toString(), String.valueOf(deadline), null, false, recipientsString.toString()))
                                .map(response -> Hex.decode(response.getUnsignedTransactionBytes())));
    }

    @Override
    public Single<byte[]> generateMultiOutSameTransaction(byte[] senderPublicKey, SignumValue amount, SignumValue fee, int deadline, Set<SignumAddress> recipients) throws IllegalArgumentException {
        return Single.fromCallable(() -> {
            StringBuilder recipientsString = new StringBuilder();
            if (recipients.size() > 128 || recipients.size() < 2) {
                throw new IllegalArgumentException("Must have 2-128 recipients, had " + recipients.size());
            }
            for (SignumAddress recipient : recipients) {
                recipientsString.append(recipient.getID()).append(";");
            }
            recipientsString.setLength(recipientsString.length() - 1);
            return recipientsString;
        }).flatMap(recipientsString -> assign(apiService.sendMoneyMultiSame(SignumUtils.getEndpoint(), null,
                Hex.toHexString(senderPublicKey), fee.toNQT().toString(), String.valueOf(deadline), null, false,
                recipientsString.toString(), amount.toNQT().toString()))
                        .map(response -> Hex.decode(response.getUnsignedTransactionBytes())));
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, SignumValue fee, int deadline, String name, String description, byte[] creationBytes, String referencedTransactionFullHash) {
        // TODO: making it backward compatible for small AT codes
        if (creationBytes.length >= 2560) {
          return assign(apiService.createATProgramBig(SignumUtils.getEndpoint(), Hex.toHexString(senderPublicKey),
              fee.toNQT().toString(), deadline, referencedTransactionFullHash, false, name, description, Hex.toHexString(creationBytes), null,
              null, 0, 0, 0, null)).map(response -> {
                  if (response.getError() != null)
                      throw new IllegalArgumentException(response.getError());
                  return response;
              }).map(response -> Hex.decode(response.getUnsignedTransactionBytes()));          
        }
        return assign(apiService.createATProgram(SignumUtils.getEndpoint(), Hex.toHexString(senderPublicKey),
                fee.toNQT().toString(), deadline, referencedTransactionFullHash, false, name, description, Hex.toHexString(creationBytes), null,
                null, 0, 0, 0, null)).map(response -> {
                    if (response.getError() != null)
                        throw new IllegalArgumentException(response.getError());
                    return response;
                }).map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public Single<byte[]> generateCreateATTransaction(byte[] senderPublicKey, SignumValue fee, SignumValue minActivation, int deadline, String name, String description, byte[] code, byte[] data, int dpages, int cspages, int uspages, String referencedTransactionFullHash) {
        return assign(apiService.createATProgram(SignumUtils.getEndpoint(), Hex.toHexString(senderPublicKey),
                fee.toNQT().toString(), deadline, referencedTransactionFullHash, false, name, description, null,
                code == null ? null : Hex.toHexString(code), Hex.toHexString(data), dpages, cspages, uspages, minActivation.toNQT().toString())).map(response -> {
                    if (response.getError() != null)
                        throw new IllegalArgumentException(response.getError());
                    return response;
                }).map(response -> Hex.decode(response.getUnsignedTransactionBytes()));
    }

    @Override
    public void close() {
        // Nothing to close.
    }

    private interface SignumAPIService {
        @GET("{endpoint}?requestType=getBlock")
        Single<BlockResponse> getBlock(@Path("endpoint") String endpoint, @Query("block") String blockId,
                @Query("height") String blockHeight, @Query("timestamp") String timestamp,
                @Query("includeTransactions") boolean includeTransactions);

        @GET("{endpoint}?requestType=getBlockchainStatus")
        Single<BlockchainStatusResponse> getBlockchainStatus(@Path("endpoint") String endpoint);

        @GET("{endpoint}?requestType=getBlockId")
        Single<BlockIDResponse> getBlockID(@Path("endpoint") String endpoint, @Query("height") String blockHeight);

        @GET("{endpoint}?requestType=getBlocks")
        Single<BlocksResponse> getBlocks(@Path("endpoint") String endpoint, @Query("firstIndex") String firstIndex,
                @Query("lastIndex") String lastIndex, @Query("includeTransactions") String[] transactions);

        @GET("{endpoint}?requestType=getConstants")
        Single<ConstantsResponse> getConstants(@Path("endpoint") String endpoint);

        @GET("{endpoint}?requestType=getAccount")
        Single<AccountResponse> getAccount(@Path("endpoint") String endpoint, @Query("account") String accountId,
        		@Query("height") String height, @Query("estimateCommitment") String calculateCommitment, @Query("getCommittedAmount") String getCommittedAmount);

        @GET("{endpoint}?requestType=getAccountATs")
        Single<ATsResponse> getAccountATs(@Path("endpoint") String endpoint, @Query("account") String accountId, @Query("machineCodeHashId") String machineCodeHashId);

        @GET("{endpoint}?requestType=getAccountBlockIds")
        Single<AccountBlockIDsResponse> getAccountBlockIDs(@Path("endpoint") String endpoint,
                @Query("account") String accountId, @Query("timestamp") String timestamp,
                @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex);

        @GET("{endpoint}?requestType=getAccountBlocks")
        Single<AccountBlocksResponse> getAccountBlocks(@Path("endpoint") String endpoint,
                @Query("account") String accountId, @Query("timestamp") String timestamp,
                @Query("firstIndex") String firstIndex, @Query("lastIndex") String lastIndex,
                @Query("includeTransactions") String[] includedTransactions);

        @GET("{endpoint}?requestType=getAccountTransactionIds")
        Single<AccountTransactionIDsResponse> getAccountTransactionIDs(@Path("endpoint") String endpoint,
                @Query("account") String accountId, @Query("timestamp") String timestamp, @Query("type") String type,
                @Query("subtype") String subtype, @Query("firstIndex") String firstIndex,
                @Query("lastIndex") String lastIndex, @Query("numberOfConfirmations") String numberOfConfirmations);

        @GET("{endpoint}?requestType=getAccountTransactions")
        Single<AccountTransactionsResponse> getAccountTransactions(@Path("endpoint") String endpoint,
                @Query("account") String accountId, @Query("timestamp") String timestamp, @Query("type") String type,
                @Query("subtype") String subtype, @Query("firstIndex") String firstIndex,
                @Query("lastIndex") String lastIndex, @Query("numberOfConfirmations") String numberOfConfirmations,
                @Query("includeIndirect") String includeIndirect);

        @GET("{endpoint}?requestType=getUnconfirmedTransactions")
        Single<AccountUnconfirmedTransactionsResponse> getUnconfirmedTransactions(@Path("endpoint") String endpoint,
                @Query("account") String accountId);

        @GET("{endpoint}?requestType=getAccountsWithRewardRecipient")
        Single<AccountsWithRewardRecipientResponse> getAccountsWithRewardRecipient(@Path("endpoint") String endpoint,
                @Query("account") String accountId);

        @GET("{endpoint}?requestType=getAsset")
        Single<AssetResponse> getAsset(@Path("endpoint") String endpoint, @Query("asset") String assetId,
        		@Query("quantityMinimumQNT") String quantityMinimumQNT, @Query("heightStart") Integer heightStart, @Query("heightEnd") Integer heightEnd);

        @GET("{endpoint}?requestType=getAssetAccounts")
        Single<AccountsAssetResponse> getAssetAccounts(@Path("endpoint") String endpoint,
                @Query("asset") String assetId, @Query("firstIndex") Integer firstIndex, @Query("lastIndex") Integer lastIndex);

        @GET("{endpoint}?requestType=getTrades")
        Single<AssetTradesResponse> getAssetTrades(@Path("endpoint") String endpoint, @Query("asset") String assetId, @Query("account") String account, @Query("firstIndex") Integer firstIndex, @Query("lastIndex") Integer lastIndex);
        
        @GET("{endpoint}?requestType=getAskOrders")
        Single<AskOrdersResponse> getAskOrders(@Path("endpoint") String endpoint, @Query("asset") String assetId);
        
        @GET("{endpoint}?requestType=getBidOrders")
        Single<BidOrdersResponse> getBidOrders(@Path("endpoint") String endpoint, @Query("asset") String assetId);

        @GET("{endpoint}?requestType=getAliases")
        Single<AliasesResponse> getAliases(@Path("endpoint") String endpoint, @Query("account") String accountId,
        		@Query("aliasName") String aliasName, @Query("tld") String tld, @Query("timestamp") String timestamp, @Query("firstIndex") Integer firstIndex, @Query("lastIndex") Integer lastIndex);
        
        @GET("{endpoint}?requestType=getTLDs")
        Single<TLDsResponse> getTLDs(@Path("endpoint") String endpoint,
        		String timestamp, @Query("firstIndex") Integer firstIndex, @Query("lastIndex") Integer lastIndex);
        
        @GET("{endpoint}?requestType=getAT")
        Single<ATResponse> getAt(@Path("endpoint") String endpoint, @Query("at") String atId, @Query("includeDetails") Boolean includeDetails);

        @GET("{endpoint}?requestType=getATIds")
        Single<AtIDsResponse> getAtIds(@Path("endpoint") String endpoint, @Query("machineCodeHashId") String machineCodeHashId);

        @GET("{endpoint}?requestType=getATs")
        Single<ATsResponse> getAts(@Path("endpoint") String endpoint, @Query("machineCodeHashId") String machineCodeHashId,
        		@Query("includeDetails") Boolean includeDetails, @Query("firstIndex") Integer firstIndex, @Query("lastIndex") Integer lastIndex);

        @GET("{endpoint}?requestType=getTransaction")
        Single<TransactionResponse> getTransaction(@Path("endpoint") String endpoint,
                @Query("transaction") String transaction, @Query("fullHash") String fullHash);

        @GET("{endpoint}?requestType=getIndirectIncoming")
        Single<IndirectIncomingResponse> getIndirectIncoming(@Path("endpoint") String endpoint,
        		@Query("account") String account, @Query("transaction") String transaction);

        @GET("{endpoint}?requestType=getTransactionBytes")
        Single<TransactionBytesResponse> getTransactionBytes(@Path("endpoint") String endpoint,
                @Query("transaction") String transaction);
        
        @POST
        Single<GenerateTransactionResponse> generateTransaction(@Url String url, @QueryMap Map<String, String> params, @Body String body);

        @POST("{endpoint}?requestType=sendMoney")
        Single<GenerateTransactionResponse> sendMoney(@Path("endpoint") String endpoint,
                @Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=addCommitment")
        Single<GenerateTransactionResponse> addCommitment(@Path("endpoint") String endpoint,
                @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=removeCommitment")
        Single<GenerateTransactionResponse> removeCommitment(@Path("endpoint") String endpoint,
                @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=setRewardRecipient")
        Single<GenerateTransactionResponse> setRewardRecipient(@Path("endpoint") String endpoint,
                @Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=sendMessage")
        Single<GenerateTransactionResponse> sendMessage(
                @Path("endpoint") String endpoint, @Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=issueAsset")
        Single<GenerateTransactionResponse> issueAsset(@Path("endpoint") String endpoint,
                @Query("name") String name, @Query("description") String description,
                @Query("quantityQNT") String quantity, @Query("decimals") int decimals, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=addAssetTreasuryAccount")
        Single<GenerateTransactionResponse> addAssetTreasuryAccount(@Path("endpoint") String endpoint,
        		@Query("recipient") String recipient,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);
        
        @POST("{endpoint}?requestType=distributeToAssetHolders")
        Single<GenerateTransactionResponse> distributeToAssetHolders(@Path("endpoint") String endpoint,
        		@Query("asset") String asset,
        		@Query("quantityMinimumQNT") String quantityMinimumQNT,
        		@Query("amountNQT") String amountNQT,
        		@Query("assetToDistribute") String assetToDistribute,
        		@Query("quantityQNT") String quantityQNT,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=transferAsset")
        Single<GenerateTransactionResponse> transferAsset(@Path("endpoint") String endpoint,
                @Query("recipient") String recipient, @Query("asset") String asset, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("quantityQNT") String quantity, @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=transferAssetMulti")
        Single<GenerateTransactionResponse> transferAssetMulti(@Path("endpoint") String endpoint,
                @Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("assetIdsAndQuantities") String assetIdsAndQuantities, @Query("amountNQT") String amount, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=placeAskOrder")
        Single<GenerateTransactionResponse> placeAskOrder(@Path("endpoint") String endpoint,
                @Query("asset") String asset, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("quantityQNT") String quantity, @Query("priceNQT") String price, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=placeBidOrder")
        Single<GenerateTransactionResponse> placeBidOrder(@Path("endpoint") String endpoint,
                @Query("asset") String asset, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("quantityQNT") String quantity, @Query("priceNQT") String price, @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=cancelAskOrder")
        Single<GenerateTransactionResponse> cancelAskOrder(@Path("endpoint") String endpoint,
                @Query("order") String order,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);
                
        
        @POST("{endpoint}?requestType=cancelBidOrder")
        Single<GenerateTransactionResponse> cancelBidOrder(@Path("endpoint") String endpoint,
                @Query("order") String order,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=sendMoneySubscription")
        Single<GenerateTransactionResponse> createSubscription(@Path("endpoint") String endpoint,
                @Query("recipient") String recipient, @Query("recipientPublicKey") String recipientPublicKey,
                @Query("amountNQT") String amount, @Query("frequency") int frequency,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @POST("{endpoint}?requestType=subscriptionCancel")
        Single<GenerateTransactionResponse> cancelSubscription(@Path("endpoint") String endpoint,
                @Query("subscription") String subscription,
                @Query("secretPhrase") String secretPhrase,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("message") String message,
                @Query("messageIsText") Boolean messageIsText, @Query("messageToEncrypt") String messageToEncrypt,
                @Query("messageToEncryptIsText") Boolean messageToEncryptIsText,
                @Query("encryptedMessageData") String encryptedMessageData,
                @Query("encryptedMessageNonce") String encryptedMessageNonce,
                @Query("messageToEncryptToSelf") String messageToEncryptToSelf,
                @Query("messageToEncryptToSelfIsText") Boolean messageToEncryptToSelfIsText,
                @Query("encryptedToSelfMessageData") String encryptedToSelfMessageData,
                @Query("encryptedToSelfMessageNonce") String encryptedToSelfMessageNonce);

        @GET("{endpoint}?requestType=suggestFee")
        Single<SuggestFeeResponse> suggestFee(@Path("endpoint") String endpoint);

        @GET("{endpoint}?requestType=getMiningInfo")
        Single<MiningInfoResponse> getMiningInfo(@Path("endpoint") String endpoint);

        @POST("{endpoint}?requestType=broadcastTransaction")
        Single<BroadcastTransactionResponse> broadcastTransaction(@Path("endpoint") String endpoint,
                @Query("transactionBytes") String transactionBytes);

        @POST("{endpoint}?requestType=broadcastTransaction")
        Single<BroadcastTransactionResponse> broadcastTransactionBig(@Path("endpoint") String endpoint,
                @Body String transactionBytes);

        @GET("{endpoint}?requestType=getRewardRecipient")
        Single<RewardRecipientResponse> getRewardRecipient(@Path("endpoint") String endpoint,
                @Query("account") String account);

        @POST("{endpoint}?requestType=submitNonce")
        Single<SubmitNonceResponse> submitNonce(@Path("endpoint") String endpoint,
                @Query("secretPhrase") String passphrase, @Query("nonce") String nonce,
                @Query("accountId") String accountId, @Query("blockheight") String blockheight);

        @POST("{endpoint}?requestType=sendMoneyMulti")
        Single<GenerateTransactionResponse> sendMoneyMulti(@Path("endpoint") String endpoint,
                @Query("secretPhrase") String secretPhrase, @Query("publicKey") String publicKey,
                @Query("feeNQT") String feeNQT, @Query("deadline") String deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("recipients") String recipients);

        @POST("{endpoint}?requestType=sendMoneyMultiSame")
        Single<GenerateTransactionResponse> sendMoneyMultiSame(@Path("endpoint") String endpoint,
                @Query("secretPhrase") String secretPhrase, @Query("publicKey") String publicKey,
                @Query("feeNQT") String feeNQT, @Query("deadline") String deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("recipients") String recipients,
                @Query("amountNQT") String amountNQT);

        @POST("{endpoint}?requestType=createATProgram")
        Single<CreateATResponse> createATProgram(@Path("endpoint") String endpoint,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("name") String name,
                @Query("description") String description, @Query("creationBytes") String creationBytes,
                @Query("code") String code, @Query("data") String data, @Query("dpages") int dpages,
                @Query("cspages") int cspages, @Query("uspages") int uspages,
                @Query("minActivationAmountNQT") String minActivationAmountNQT);
        
        @POST("{endpoint}?requestType=createATProgram")
        Single<CreateATResponse> createATProgramBig(@Path("endpoint") String endpoint,
                @Query("publicKey") String publicKey, @Query("feeNQT") String fee, @Query("deadline") int deadline,
                @Query("referencedTransactionFullHash") String referencedTransactionFullHash,
                @Query("broadcast") boolean broadcast, @Query("name") String name,
                @Query("description") String description, @Body String creationBytes,                
                @Query("code") String code, @Query("data") String data, @Query("dpages") int dpages,
                @Query("cspages") int cspages, @Query("uspages") int uspages,
                @Query("minActivationAmountNQT") String minActivationAmountNQT);
    }
}
