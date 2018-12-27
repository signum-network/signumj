package burst.kit.test;

import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.*;
import burst.kit.entity.response.attachment.ATCreationAttachment;
import burst.kit.entity.response.attachment.MultiOutAttachment;
import burst.kit.entity.response.attachment.MultiOutSameAttachment;
import burst.kit.service.BurstNodeService;
import burst.kit.util.BurstKitUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class BurstNodeServiceTest {

    private BurstNodeService burstNodeService;

    @Before
    public void setUpBurstServiceTest() {
        burstNodeService = BurstNodeService.getInstance("https://wallet1.burst-team.us:2083");
    }

    @Test
    public void testBurstServiceGetBlock() {
        BlockResponse blockIDResponse = SingleTestUtils.testSingle(burstNodeService.getBlock(TestVariables.EXAMPLE_BLOCK_ID));
        BlockResponse blockHeightResponse = SingleTestUtils.testSingle(burstNodeService.getBlock(TestVariables.EXAMPLE_BLOCK_HEIGHT));
        BlockResponse blockTimestampResponse = SingleTestUtils.testSingle(burstNodeService.getBlock(TestVariables.EXAMPLE_TIMESTAMP));
        BlockResponse blockTransactionResponse = SingleTestUtils.testSingle(burstNodeService.getBlock(new BurstID[]{TestVariables.EXAMPLE_TRANSACTION_ID}));
    }

    @Test
    public void testBurstServiceGetBlockID() {
        BlockIDResponse blockIDResponse = SingleTestUtils.testSingle(burstNodeService.getBlockId(TestVariables.EXAMPLE_BLOCK_HEIGHT));
    }

    @Test
    public void testBurstServiceGetBlockchainStatus() {
        BlockchainStatusResponse blockchainStatusResponse = SingleTestUtils.testSingle(burstNodeService.getBlockchainStatus());
    }

    @Test
    public void testBurstServiceGetBlocks() {
        BlocksResponse blocksResponse = SingleTestUtils.testSingle(burstNodeService.getBlocks(0, 99)); // BRS caps this call at 99 blocks.
        //assertEquals(100, blocksResponse.getBlocks().length);
    }

    @Test
    public void testBurstServiceGetConstants() {
        ConstantsResponse constantsResponse = SingleTestUtils.testSingle(burstNodeService.getConstants());
    }

    @Test
    public void testBurstServiceGetAccount() {
        AccountResponse accountResponse = SingleTestUtils.testSingle(burstNodeService.getAccount(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountATs() {
        AccountATsResponse accountATsResponse = SingleTestUtils.testSingle(burstNodeService.getAccountATs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountBlockIDs() {
        AccountBlockIDsResponse accountBlockIDsResponse = SingleTestUtils.testSingle(burstNodeService.getAccountBlockIDs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountBlocks() {
        AccountBlocksResponse accountBlocksResponse = SingleTestUtils.testSingle(burstNodeService.getAccountBlocks(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountPublicKey() {
        AccountPublicKeyResponse accountPublicKeyResponse = SingleTestUtils.testSingle(burstNodeService.getAccountPublicKey(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountTransactionIDs() {
        AccountTransactionIDsResponse accountTransactionIDsResponse = SingleTestUtils.testSingle(burstNodeService.getAccountTransactionIDs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountTransactions() {
        AccountTransactionsResponse accountTransactionsResponse = SingleTestUtils.testSingle(burstNodeService.getAccountTransactions(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountWithRewardRecipient() {
        AccountsWithRewardRecipientResponse accountsWithRewardRecipientResponse = SingleTestUtils.testSingle(burstNodeService.getAccountsWithRewardRecipient(TestVariables.EXAMPLE_POOL_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAT() {
        ATResponse accountATsResponse = SingleTestUtils.testSingle(burstNodeService.getAt(TestVariables.EXAMPLE_AT_ID));
    }

    @Test
    public void testBurstServiceGetAtIDs() {
        AtIDsResponse atIDsResponse = SingleTestUtils.testSingle(burstNodeService.getAtIds());
    }

    @Test
    public void testBurstServiceGetTransaction() {
        TransactionResponse transactionIdTransactionResponse = SingleTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_ID));
        TransactionResponse fullHashTransactionResponse = SingleTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_FULL_HASH.getBytes()));

        TransactionResponse multiOutTransactionResponse = SingleTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_MULTI_OUT_TRANSACTION_ID));
        assertEquals(MultiOutAttachment.class, multiOutTransactionResponse.getAttachment().getType());
        assertEquals(22, ((MultiOutAttachment) multiOutTransactionResponse.getAttachment()).getRecipients().length);

        TransactionResponse multiOutSameTransactionResponse = SingleTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_MULTI_OUT_SAME_TRANSACTION_ID));
        assertEquals(MultiOutSameAttachment.class, multiOutSameTransactionResponse.getAttachment().getType());
        assertEquals(128, ((MultiOutSameAttachment) multiOutSameTransactionResponse.getAttachment()).getRecipients().length);

        TransactionResponse atCreationTransactionResponse = SingleTestUtils.testSingle(burstNodeService.getTransaction(TestVariables.EXAMPLE_AT_CREATION_TRANSACTION_ID));
        assertEquals(ATCreationAttachment.class, atCreationTransactionResponse.getAttachment().getType());
    }

    @Test
    public void testBurstServiceGetTransactionBytes() {
        TransactionBytesResponse transactionBytesResponse = SingleTestUtils.testSingle(burstNodeService.getTransactionBytes(TestVariables.EXAMPLE_TRANSACTION_ID));
    }

    @Test
    public void testBurstServiceGenerateTransaction() {
        GenerateTransactionResponse withoutMessage = SingleTestUtils.testSingle(burstNodeService.generateTransaction(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes(), BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440));
        GenerateTransactionResponse withStringMessage = SingleTestUtils.testSingle(burstNodeService.generateTransactionWithMessage(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes(), BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, "Test Transaction"));
        GenerateTransactionResponse withBytesMessage = SingleTestUtils.testSingle(burstNodeService.generateTransactionWithMessage(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes(), BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes()));
    }

    @Test
    public void testBurstServiceSuggestFee() {
        SuggestFeeResponse suggestFeeResponse = SingleTestUtils.testSingle(burstNodeService.suggestFee());
        assertTrue(suggestFeeResponse.getPriority().compareTo(suggestFeeResponse.getStandard()) >= 0);
        assertTrue(suggestFeeResponse.getStandard().compareTo(suggestFeeResponse.getCheap()) >= 0);
    }

    @Test
    public void testBurstServiceGetMiningInfo() {
        MiningInfoResponse miningInfoResponse = SingleTestUtils.testSingle(burstNodeService.getMiningInfo());
    }

    @Test
    public void testBurstServiceGetMyInfo() {
        MyInfoResponse myInfoResponse = SingleTestUtils.testSingle(burstNodeService.getMyInfo());
    }
}
