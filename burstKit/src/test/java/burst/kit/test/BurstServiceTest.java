package burst.kit.test;

import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import burst.kit.entity.response.*;
import burst.kit.service.BurstService;
import burst.kit.service.impl.BurstServiceImpl;
import io.reactivex.Single;
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
        burstService = new BurstServiceImpl();
    }

    @Test
    public void testBurstServiceGetBlock() {
        BlockResponse blockIDResponse = SingleTestUtils.testSingle(burstService.getBlock(TestVariables.EXAMPLE_BLOCK_ID));
        BlockResponse blockHeightResponse = SingleTestUtils.testSingle(burstService.getBlock(TestVariables.EXAMPLE_BLOCK_HEIGHT));
        BlockResponse blockTimestampResponse = SingleTestUtils.testSingle(burstService.getBlock(TestVariables.EXAMPLE_TIMESTAMP));
        BlockResponse blockTransactionResponse = SingleTestUtils.testSingle(burstService.getBlock(new BurstID[]{TestVariables.EXAMPLE_TRANSACTION_ID}));
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
}
