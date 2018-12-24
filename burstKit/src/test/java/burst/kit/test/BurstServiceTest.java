package burst.kit.test;

import burst.kit.entity.BurstID;
import burst.kit.entity.BurstValue;
import burst.kit.entity.response.*;
import burst.kit.entity.response.attachment.ATCreationAttachment;
import burst.kit.entity.response.attachment.MultiOutAttachment;
import burst.kit.entity.response.attachment.MultiOutSameAttachment;
import burst.kit.service.BurstService;
import burst.kit.util.BurstKitUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstServiceTest {

    private BurstService burstService;

    @Before
    public void setUpBurstServiceTest() {
        burstService = BurstService.getInstance("https://wallet1.burst-team.us:2083");
    }

    @Test
    public void testBurstServiceGetBlock() {
        BlockResponse blockIDResponse = SingleTestUtils.testSingle(burstService.getBlock(TestVariables.EXAMPLE_BLOCK_ID));
        BlockResponse blockHeightResponse = SingleTestUtils.testSingle(burstService.getBlock(TestVariables.EXAMPLE_BLOCK_HEIGHT));
        BlockResponse blockTimestampResponse = SingleTestUtils.testSingle(burstService.getBlock(TestVariables.EXAMPLE_TIMESTAMP));
        BlockResponse blockTransactionResponse = SingleTestUtils.testSingle(burstService.getBlock(new BurstID[]{TestVariables.EXAMPLE_TRANSACTION_ID}));
    }

    @Test
    public void testBurstServiceGetBlockID() {
        BlockIDResponse blockIDResponse = SingleTestUtils.testSingle(burstService.getBlockId(TestVariables.EXAMPLE_BLOCK_HEIGHT));
    }

    @Test
    public void testBurstServiceGetBlockchainStatus() {
        BlockchainStatusResponse blockchainStatusResponse = SingleTestUtils.testSingle(burstService.getBlockchainStatus());
    }

    @Test
    public void testBurstServiceGetBlocks() {
        BlocksResponse blocksResponse = SingleTestUtils.testSingle(burstService.getBlocks(0, 99)); // BRS caps this call at 99 blocks.
        //assertEquals(100, blocksResponse.getBlocks().length);
    }

    @Test
    public void testBurstServiceGetConstants() {
        ConstantsResponse constantsResponse = SingleTestUtils.testSingle(burstService.getConstants());
    }

    @Test
    public void testBurstServiceGetAccount() {
        AccountResponse accountResponse = SingleTestUtils.testSingle(burstService.getAccount(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountATs() {
        AccountATsResponse accountATsResponse = SingleTestUtils.testSingle(burstService.getAccountATs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountBlockIDs() {
        AccountBlockIDsResponse accountBlockIDsResponse = SingleTestUtils.testSingle(burstService.getAccountBlockIDs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountBlocks() {
        AccountBlocksResponse accountBlocksResponse = SingleTestUtils.testSingle(burstService.getAccountBlocks(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountPublicKey() {
        AccountPublicKeyResponse accountPublicKeyResponse = SingleTestUtils.testSingle(burstService.getAccountPublicKey(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountTransactionIDs() {
        AccountTransactionIDsResponse accountTransactionIDsResponse = SingleTestUtils.testSingle(burstService.getAccountTransactionIDs(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountTransactions() {
        AccountTransactionsResponse accountTransactionsResponse = SingleTestUtils.testSingle(burstService.getAccountTransactions(TestVariables.EXAMPLE_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAccountWithRewardRecipient() {
        AccountsWithRewardRecipientResponse accountsWithRewardRecipientResponse = SingleTestUtils.testSingle(burstService.getAccountsWithRewardRecipient(TestVariables.EXAMPLE_POOL_ACCOUNT_ID));
    }

    @Test
    public void testBurstServiceGetAT() {
        ATResponse accountATsResponse = SingleTestUtils.testSingle(burstService.getAt(TestVariables.EXAMPLE_AT_ID));
    }

    @Test
    public void testBurstServiceGetAtIDs() {
        AtIDsResponse atIDsResponse = SingleTestUtils.testSingle(burstService.getAtIds());
    }

    @Test
    public void testBurstServiceGetTransaction() {
        TransactionResponse transactionIdTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_ID));
        TransactionResponse fullHashTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_FULL_HASH.getBytes()));

        TransactionResponse multiOutTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_MULTI_OUT_TRANSACTION_ID));
        assertEquals(MultiOutAttachment.class, multiOutTransactionResponse.getAttachment().getType());
        assertEquals(22, ((MultiOutAttachment) multiOutTransactionResponse.getAttachment()).getRecipients().length);

        TransactionResponse multiOutSameTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_MULTI_OUT_SAME_TRANSACTION_ID));
        assertEquals(MultiOutSameAttachment.class, multiOutSameTransactionResponse.getAttachment().getType());
        assertEquals(128, ((MultiOutSameAttachment) multiOutSameTransactionResponse.getAttachment()).getRecipients().length);

        TransactionResponse atCreationTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_AT_CREATION_TRANSACTION_ID));
        assertEquals(ATCreationAttachment.class, atCreationTransactionResponse.getAttachment().getType());
    }

    @Test
    public void testBurstServiceGetTransactionBytes() {
        TransactionBytesResponse transactionBytesResponse = SingleTestUtils.testSingle(burstService.getTransactionBytes(TestVariables.EXAMPLE_TRANSACTION_ID));
    }

    @Test
    public void testBurstServiceGenerateTransaction() {
        GenerateTransactionResponse withoutMessage = SingleTestUtils.testSingle(burstService.generateTransaction(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes(), BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440));
        GenerateTransactionResponse withStringMessage = SingleTestUtils.testSingle(burstService.generateTransactionWithMessage(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes(), BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, "Test Transaction"));
        GenerateTransactionResponse withBytesMessage = SingleTestUtils.testSingle(burstService.generateTransactionWithMessage(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes(), BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, TestVariables.EXAMPLE_ACCOUNT_PUBKEY.getBytes()));
        // TODO GenerateTransactionResponse enc = SingleTestUtils.testSingle(burstService.generateTransaction(TestVariables.EXAMPLE_ACCOUNT_ID, TestVariables.EXAMPLE_ACCOUNT_PUBKEY, BurstValue.fromBurst(1), BurstValue.fromBurst(1), 1440, TestVariables.EXAMPLE_ACCOUNT_PUBKEY));

        System.out.println(BurstKitUtils.buildGson().setPrettyPrinting().create().toJson(withoutMessage));
    }
}
