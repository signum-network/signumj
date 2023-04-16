package signumj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bouncycastle.util.encoders.Hex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import signumj.Constants;
import signumj.crypto.SignumCrypto;
import signumj.entity.SignumAddress;
import signumj.entity.SignumValue;
import signumj.service.NodeService;
import signumj.service.TransactionBuilder;
import signumj.service.impl.HttpNodeService;

public class TransactionBuilderTest {
    private NodeService nodeService;
    private final SignumCrypto crypto = SignumCrypto.getInstance();

    @Before
    public void setUp() {
        nodeService = getNodeService();
    }

    @After
    public void tearDown() throws Exception {
        nodeService.close();
    }

    protected NodeService getNodeService() {
    	return new HttpNodeService(Constants.HTTP_NODE_TESTNET, TestVariables.TEST_USER_AGENT);
    }

    @Test
    public void testSendMoney() {
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MONEY,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
    			.recipient(TestVariables.EXAMPLE_ACCOUNT_ID)
    			.amount(SignumValue.fromSigna(1));

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

	@Test
    public void testSendMessage() {
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MESSAGE,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
    			.recipient(TestVariables.EXAMPLE_ACCOUNT_ID)
				.message("Test message");

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

	@Test
    public void testSendMessageAnnouncePublicKey() {
		SignumAddress recipient = crypto.getAddressFromPublic(TestVariables.EXAMPLE_ACCOUNT_PUBKEY_FRESH);
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MESSAGE,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
    			.recipient(recipient)
				.message("Account activation");

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

    @Test
    public void testSendMoneyMessage() {
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MONEY,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
    			.recipient(TestVariables.EXAMPLE_ACCOUNT_ID)
    			.amount(SignumValue.fromSigna(1))
    			.message("Test Transaction");

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

    @Test
    public void testSetRewardRecipient() {
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SET_REWARD_RECIPIENT,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
    			.recipient(TestVariables.EXAMPLE_ACCOUNT_ID);

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

	@Test
    public void testAddCommitment() {
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.ADD_COMMITMENT,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
				.amount(SignumValue.fromSigna(1));

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

	@Test
    public void testSetAlias() {
    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SET_ALIAS,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(1), 1440)
				.alias("aNewAlias", null);

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

    @Test
    public void testMultiOut() {
        Map<SignumAddress, SignumValue> recipients = new HashMap<>();
        recipients.put(crypto.getAddressFromPassphrase("example1"), SignumValue.fromSigna(1));
        recipients.put(crypto.getAddressFromPassphrase("example2"), SignumValue.fromSigna(2));

    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MONEY_MULTI,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
    			.recipients(recipients);

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

    @Test
    public void testMultiOutSame() {
        Set<SignumAddress> recipients = new HashSet<>();
        recipients.add(crypto.getAddressFromPassphrase("example1"));
        recipients.add(crypto.getAddressFromPassphrase("example2"));

    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.SEND_MONEY_MULTI_SAME,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(0.01), 1440)
				.amount(SignumValue.fromSigna(2))
    			.recipients(recipients);

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

    @Test
    public void testGenerateCreateATTransaction() {
        byte[] lotteryAtCode = Hex.decode("1e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a000000");
        byte[] lotteryAtCreationBytes = SignumCrypto.getInstance().getATCreationBytes((short) 2, lotteryAtCode, new byte[0], (short) 1, (short) 1, (short) 1, SignumValue.fromSigna(2));
        assertEquals("02000000020001000100010000c2eb0b0000000044011e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a00000000", Hex.toHexString(lotteryAtCreationBytes));

    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.CREATE_AT,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(2), 1440)
				.name("TestAT")
				.description("An AT For Testing")
    			.creationBytes(lotteryAtCreationBytes);

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }

	@Test
    public void testCreateATTransactionWithMessage() {
        byte[] lotteryAtCode = Hex.decode("1e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a000000");
        byte[] lotteryAtCreationBytes = SignumCrypto.getInstance().getATCreationBytes((short) 2, lotteryAtCode, new byte[0], (short) 1, (short) 1, (short) 1, SignumValue.fromSigna(2));
        assertEquals("02000000020001000100010000c2eb0b0000000044011e000000003901090000006400000000000000351400000000000201000000000000000104000000803a0900000000000601000000040000003615000200000000000000260200000036160003000000020000001f030000000100000072361b0008000000020000002308000000090000000f1af3000000361c0004000000020000001e0400000035361700040000000200000026040000007f2004000000050000001e02050000000400000036180006000000020000000200000000030000001a39000000352000070000001b07000000181b0500000012332100060000001a310100000200000000030000001a1a0000003618000a0000000200000020080000000900000023070800000009000000341f00080000000a0000001a78000000341f00080000000a0000001ab800000002000000000400000003050000001a1a00000000", Hex.toHexString(lotteryAtCreationBytes));

    	TransactionBuilder tb = new TransactionBuilder(TransactionBuilder.CREATE_AT,
    			TestVariables.EXAMPLE_ACCOUNT_PUBKEY, SignumValue.fromSigna(2), 1440)
				.name("TestAT")
				.description("An AT For Testing")
    			.creationBytes(lotteryAtCreationBytes)
				.message("Test extra AT message");

    	byte []utx = RxTestUtils.testSingle(nodeService.generateTransaction(tb));
    	assertTrue(tb.verify(utx));
    }
}
