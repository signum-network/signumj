package burst.kit.test;

import burst.kit.entity.BurstID;
import burst.kit.entity.response.*;
import burst.kit.service.BurstService;
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
    public void testBurstServiceGetAT() {
        ATResponse accountATsResponse = SingleTestUtils.testSingle(burstService.getAt(TestVariables.EXAMPLE_AT_ID));
    }

    @Test
    public void testBurstServiceGetAtIDs() {
        AtIDsResponse atIDsResponse = SingleTestUtils.testSingle(burstService.getAtIds());
    }

    @Test
    public void testBurstServiceGetAtLong() {
        AtLongResponse atLongResponse = SingleTestUtils.testSingle(burstService.getAtLong(TestVariables.EXAMPLE_AT_LONG));
        assertEquals(TestVariables.EXAMPLE_AT_LONG_HEX2LONG, atLongResponse.getHex2long());
    }

    @Test
    public void testBurstServiceGetTransaction() {
        TransactionResponse transactionIdTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_ID));
        TransactionResponse fullHashTransactionResponse = SingleTestUtils.testSingle(burstService.getTransaction(TestVariables.EXAMPLE_TRANSACTION_FULL_HASH));
    }

    @Test
    public void testBurstServiceGetTransactionBytes() {
        TransactionBytesResponse transactionBytesResponse = SingleTestUtils.testSingle(burstService.getTransactionBytes(TestVariables.EXAMPLE_TRANSACTION_ID));
    }
}
