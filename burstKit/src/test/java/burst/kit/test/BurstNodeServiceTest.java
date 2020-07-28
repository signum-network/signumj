package burst.kit.test;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.*;
import burst.kit.entity.response.appendix.EncryptedMessageAppendix;
import burst.kit.entity.response.appendix.PlaintextMessageAppendix;
import burst.kit.entity.response.attachment.*;
import burst.kit.service.BurstNodeService;
import org.bouncycastle.util.encoders.Hex;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class BurstNodeServiceTest {
    private BurstNodeService burstNodeService;
    private final BurstCrypto burstCrypto = BurstCrypto.getInstance();

    @Before
    public void setUp() {
        burstNodeService = getBurstNodeService();
    }

    @After
    public void tearDown() throws Exception {
        burstNodeService.close();
    }

    protected abstract BurstNodeService getBurstNodeService();

    @Test
    public void testBurstServiceGetBlock() {
        Block blockIDResponse = RxTestUtils.testSingle(burstNodeService.getBlock(TestVariables.EXAMPLE_BLOCK_ID));
        assertEquals(TestVariables.EXAMPLE_BLOCK_ID, blockIDResponse.getId());
        Block blockHeightResponse = RxTestUtils.testSingle(burstNodeService.getBlock(TestVariables.EXAMPLE_BLOCK_HEIGHT));
        assertEquals(TestVariables.EXAMPLE_BLOCK_HEIGHT, blockHeightResponse.getHeight());
        Block blockTimestampResponse = RxTestUtils.testSingle(burstNodeService.getBlock(TestVariables.EXAMPLE_TIMESTAMP));
        assertEquals(BurstTimestamp.fromBurstTimestamp(126143826), blockTimestampResponse.getTimestamp());
    }

    @Test
    public void testBurstServiceGetBlockID() {
        BurstID blockIDResponse = RxTestUtils.testSingle(burstNodeService.getBlockId(TestVariables.EXAMPLE_BLOCK_HEIGHT));
    }

    @Test
    public void testBurstServiceGetBlocks() {
        Block[] blocksResponse = RxTestUtils.testSingle(burstNodeService.getBlocks(0, 99)); // BRS caps this call at 99 blocks.
        assertEquals(100, blocksResponse.length);
    }

    @Test
    public void testBurstServiceGetConstants() {
        Constants constantsResponse = RxTestUtils.testSingle(burstNodeService.getConstants());
        assertEquals(Constants.class, constantsResponse.getClass());
    }

    @Test
    public void testBurstServiceGetAccount() {
        Account accountResponse = RxTestUtils.testSingle(burstNodeService.getAccount(TestVariables.EXAMPLE_ACCOUNT_ID));
        assertEquals(TestVariables.EXAMPLE_ACCOUNT_ID, accountResponse.getId());
    }

    @Test
    public void testBurstServiceGetAccountATs() {
        AT[] accountATsResponse = RxTestUtils.testSingle(burstNodeService.getAccountATs(TestVariables.EXAMPLE_ACCOUNT_ID));
        assertEquals(0, accountATsResponse.length);
    }

    @Test
    public void testBurstServiceGetAccountBlockIDs() {
        BurstID[] accountBlockIDsResponse = RxTestUtils.testSingle(burstNodeService.getAccountBlockIDs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountBlocks() {
        Block[] accountBlocksResponse = RxTestUtils.testSingle(burstNodeService.getAccountBlocks(TestVariables.EXAMPLE_ACCOUNT_ID));
        assertEquals(3, accountBlocksResponse.length);
    }

    @Test
    public void testBurstServiceGetAccountTransactionIDs() {
        BurstID[] accountTransactionIDsResponse = RxTestUtils.testSingle(burstNodeService.getAccountTransactionIDs(TestVariables.EXAMPLE_ACCOUNT_ID));
        assertEquals(37, accountTransactionIDsResponse.length);
    }

    @Test
    public void testBurstServiceGetAccountTransactions() {
        Transaction[] accountTransactionsResponse = RxTestUtils.testSingle(burstNodeService.getAccountTransactions(TestVariables.EXAMPLE_ACCOUNT_ID, null, null, null));
        assertEquals(37, accountTransactionsResponse.length);
    }

    @Test
    @Ignore // TODO this is due to a bug that is fixed in 3.0, remove once server uses this version
    public void testBurstServiceGetAssetAccounts() {
        AssetBalance[] assetBalancesResponse = RxTestUtils.testSingle(burstNodeService.getAssetBalances(TestVariables.EXAMPLE_ASSET_ID, 0, 100));
    }

    @Test
    public void testBurstServiceGetAskOrders() {
        AssetOrder[] ordersResponse = RxTestUtils.testSingle(burstNodeService.getAskOrders(TestVariables.EXAMPLE_ASSET_ID));
        assertEquals(0, ordersResponse.length);
    }

    @Test
    public void testBurstServiceGetBidOrders() {
        AssetOrder[] ordersResponse = RxTestUtils.testSingle(burstNodeService.getBidOrders(TestVariables.EXAMPLE_ASSET_ID));
        assertEquals(0, ordersResponse.length);
    }

    @Test
    public void testBurstServiceGetAccountsWithRewardRecipient() {
        BurstAddress[] accountsWithRewardRecipientResponse = RxTestUtils.testSingle(burstNodeService.getAccountsWithRewardRecipient(TestVariables.EXAMPLE_POOL_ACCOUNT_ID));
        assertEquals(BurstAddress[].class, accountsWithRewardRecipientResponse.getClass());
    }

    @Test
    public void testBurstServiceGetAT() {
        AT accountATsResponse = RxTestUtils.testSingle(burstNodeService.getAt(TestVariables.EXAMPLE_AT_ID));
        assertEquals(TestVariables.EXAMPLE_AT_ID, accountATsResponse.getId());
    }

    @Test
    public void testBurstServiceGetAtIDs() {
        BurstAddress[] atIDsResponse = RxTestUtils.testSingle(burstNodeService.getAtIds());
        assertEquals(BurstAddress[].class, atIDsResponse.getClass());
    }

    @Test
    public void testBurstServiceGetUnconfirmedTransactions() {
        Transaction[] unconfReponse = RxTestUtils.testSingle(burstNodeService.getUnconfirmedTransactions(null));
        assertEquals(Transaction[].class, unconfReponse.getClass());
    }

    @Test
    public void testBurstServiceGetTransaction() {
        Transaction transactionIdTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_ID));
        assertEquals(TestVariables.EXAMPLE_TRANSACTION_ID, transactionIdTransactionResponse.getId());
        Transaction fullHashTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_FULL_HASH));

        Transaction multiOutTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_MULTI_OUT_TRANSACTION_ID));
        assertEquals(MultiOutAttachment.class, multiOutTransactionResponse.getAttachment().getClass());
        assertEquals(22, ((MultiOutAttachment) multiOutTransactionResponse.getAttachment()).getOutputs().size());

        Transaction multiOutSameTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_MULTI_OUT_SAME_TRANSACTION_ID));
        assertEquals(MultiOutSameAttachment.class, multiOutSameTransactionResponse.getAttachment().getClass());
        assertEquals(128, ((MultiOutSameAttachment) multiOutSameTransactionResponse.getAttachment()).getRecipients().length);

        Transaction atCreationTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_AT_CREATION_TRANSACTION_ID));
        assertEquals(ATCreationAttachment.class, atCreationTransactionResponse.getAttachment().getClass());

        Transaction assetTransferTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_ASSET_TRANSFER_TRANSACTION_ID));
        assertEquals(AssetTransferAttachment.class, assetTransferTransactionResponse.getAttachment().getClass());
        assertEquals("12402415494995249540",((AssetTransferAttachment) assetTransferTransactionResponse.getAttachment()).getAsset());
        assertEquals("431762560000", ((AssetTransferAttachment) assetTransferTransactionResponse.getAttachment()).getQuantityQNT());

        Transaction assetTransferTransactionWithMessageResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_ASSET_TRANSFER_WITH_MESSAGE_TRANSACTION_ID));
        assertEquals(AssetTransferAttachment.class, assetTransferTransactionWithMessageResponse.getAttachment().getClass());
        assertEquals("12402415494995249540",((AssetTransferAttachment) assetTransferTransactionResponse.getAttachment()).getAsset());
        assertEquals("1000", ((AssetTransferAttachment) assetTransferTransactionWithMessageResponse.getAttachment()).getQuantityQNT());
        assertEquals(PlaintextMessageAppendix.class, assetTransferTransactionWithMessageResponse.getAppendages()[0].getClass());
        assertEquals(48, ((PlaintextMessageAppendix) assetTransferTransactionWithMessageResponse.getAppendages()[0]).getMessage().length());

        Transaction assetTransferTransactionWithEncryptedMessageResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_ASSET_TRANSFER_WITH_ENCRYPTED_MESSAGE_TRANSACTION_ID));
        assertEquals(AssetTransferAttachment.class, assetTransferTransactionWithEncryptedMessageResponse.getAttachment().getClass());
        assertEquals("12402415494995249540",((AssetTransferAttachment) assetTransferTransactionWithEncryptedMessageResponse.getAttachment()).getAsset());
        assertEquals(96, ((EncryptedMessageAppendix) assetTransferTransactionWithEncryptedMessageResponse.getAppendages()[0]).getEncryptedMessage().getSize());


        Transaction bidOrderPlacementTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_BID_ORDER_PLACEMENT_TRANSACTION_ID));
        assertEquals(BidOrderPlacementAttachment.class, bidOrderPlacementTransactionResponse.getAttachment().getClass());
        assertEquals("12402415494995249540", ((BidOrderPlacementAttachment) bidOrderPlacementTransactionResponse.getAttachment()).getAsset());
        assertEquals("99990000", ((BidOrderPlacementAttachment) bidOrderPlacementTransactionResponse.getAttachment()).getQuantityQNT());
        assertEquals("1000", ((BidOrderPlacementAttachment) bidOrderPlacementTransactionResponse.getAttachment()).getPriceNQT());

        Transaction askOrderPlacementTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_ASK_ORDER_PLACEMENT_TRANSACTION_ID));
        assertEquals(AskOrderPlacementAttachment.class, askOrderPlacementTransactionResponse.getAttachment().getClass());
        assertEquals("12402415494995249540", ((AskOrderPlacementAttachment) askOrderPlacementTransactionResponse.getAttachment()).getAsset());
        assertEquals("10000", ((AskOrderPlacementAttachment) askOrderPlacementTransactionResponse.getAttachment()).getQuantityQNT());
        assertEquals("1960", ((AskOrderPlacementAttachment) askOrderPlacementTransactionResponse.getAttachment()).getPriceNQT());

        Transaction bidOrderCancellationTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_BID_ORDER_CANCELLATION_TRANSACTION_ID));
        assertEquals(BidOrderCancellationAttachment.class, bidOrderCancellationTransactionResponse.getAttachment().getClass());
        assertEquals("8067384309114314901", ((BidOrderCancellationAttachment) bidOrderCancellationTransactionResponse.getAttachment()).getOrder());

        Transaction askOrderCancellationTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_ASK_ORDER_CANCELLATION_TRANSACTION_ID));
        assertEquals(AskOrderCancellationAttachment.class, askOrderCancellationTransactionResponse.getAttachment().getClass());
        assertEquals("12656175013072880629", ((AskOrderCancellationAttachment) askOrderCancellationTransactionResponse.getAttachment()).getOrder());

        Transaction assetIssuanceTransactionResponse = RxTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_ASSET_ISSUANCE_TRANSACTION_ID));
        assertEquals(AssetIssuanceAttachment.class, assetIssuanceTransactionResponse.getAttachment().getClass());
        assertEquals("TRT", ((AssetIssuanceAttachment) assetIssuanceTransactionResponse.getAttachment()).getName());
        assertEquals(159, ((AssetIssuanceAttachment) assetIssuanceTransactionResponse.getAttachment()).getDescription().length());
        assertEquals("21588128000000", ((AssetIssuanceAttachment) assetIssuanceTransactionResponse.getAttachment()).getQuantityQNT());
        assertEquals(4, ((AssetIssuanceAttachment) assetIssuanceTransactionResponse.getAttachment()).getDecimals());

    }

    @Test
    public void testBurstServiceGetTransactionBytes() {
        byte[] transactionBytesResponse = RxTestUtils.testSingle(burstNodeService.getTransactionBytes(TestVariables.EXAMPLE_TRANSACTION_ID));
    }

    @Test
    public void testBurstServiceGenerateTransaction() {
        // TODO test with zero amounts
        byte[] withoutMessageAmount = RxTestUtils.testSingle(burstNodeService.generateTransaction(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440));
        byte[] withStringMessage = RxTestUtils.testSingle(burstNodeService.generateTransactionWithMessage(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, "Test Transaction"));
        byte[] withBytesMessage = RxTestUtils.testSingle(burstNodeService.generateTransactionWithMessage(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, TestVariables.EXAMPLE_ACCOUNT_PUBKEY));
    }

    @Test
    public void testBurstServiceSuggestFee() {
        FeeSuggestion suggestFeeResponse = RxTestUtils.testSingle(burstNodeService.suggestFee());
        assertTrue(suggestFeeResponse.getPriorityFee().compareTo(suggestFeeResponse.getStandardFee()) >= 0);
        assertTrue(suggestFeeResponse.getStandardFee().compareTo(suggestFeeResponse.getCheapFee()) >= 0);
    }

    @Test
    public void testBurstServiceGetMiningInfo() {
        MiningInfo miningInfoResponse = RxTestUtils.testObservable(burstNodeService.getMiningInfo(), 1).get(0);
    }

    @Test
    public void testBurstServiceGetRewardRecipient() {
        BurstAddress rewardRecipientResponse = RxTestUtils.testSingle(burstNodeService.getRewardRecipient(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceSubmitNonce() {
        Long submitNonceResponse = RxTestUtils.testSingle(burstNodeService.submitNonce("example", "0", null));
    }

    @Test
    public void testBurstServiceGenerateMultiOut() {
        Map<BurstAddress, BurstValue> recipients = new HashMap<>();
        recipients.put(burstCrypto.getBurstAddressFromPassphrase("example1"), BurstValue.fromBurst(1));
        recipients.put(burstCrypto.getBurstAddressFromPassphrase("example2"), BurstValue.fromBurst(2));
        byte[] multiOutResponse = RxTestUtils.testSingle(burstNodeService.generateMultiOutTransaction(TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromPlanck(753000), 1440, recipients));
    }

    @Test
    public void testBurstServiceGenerateMultiOutSame() {
        Set<BurstAddress> recipients = new HashSet<>();
        recipients.add(burstCrypto.getBurstAddressFromPassphrase("example1"));
        recipients.add(burstCrypto.getBurstAddressFromPassphrase("example2"));
        byte[] multiOutSameResponse = RxTestUtils.testSingle(burstNodeService.generateMultiOutSameTransaction(TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(1), BurstValue.fromPlanck(753000), 1440, recipients));
    }

    @Test
    public void testBurstServiceGenerateCreateATTransaction() {
        byte[] lotteryAtCode = Hex.decode("1e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a000000");
        byte[] lotteryAtCreationBytes = BurstCrypto.getInstance().getATCreationBytes((short) 1, lotteryAtCode, new byte[0], (short) 1, (short) 1, (short) 1, BurstValue.fromBurst(2));
        assertEquals("01000000020001000100010000c2eb0b0000000044011e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a00000000", Hex.toHexString(lotteryAtCreationBytes));
        byte[] createATResponse = RxTestUtils.testSingle(burstNodeService.generateCreateATTransaction(TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(5), 1440, "TestAT", "An AT For Testing", lotteryAtCreationBytes));

        byte[] fatLotteryAtCreationBytes = BurstCrypto.getInstance().getATCreationBytes((short) 1, lotteryAtCode, new byte[0], (short) 10, (short) 10, (short) 10, BurstValue.fromBurst(2));
        assertEquals("0100000002000a000a000a0000c2eb0b0000000044011e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a0000000000", Hex.toHexString(fatLotteryAtCreationBytes));
        byte[] fatCreateATResponse = RxTestUtils.testSingle(burstNodeService.generateCreateATTransaction(TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(5), 1440, "TestAT", "An AT For Testing", lotteryAtCreationBytes));
    }
}
