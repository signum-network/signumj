package signumj.test;

import org.bouncycastle.util.encoders.Hex;

import signumj.Constants;
import signumj.entity.SignumAddress;
import signumj.entity.SignumID;
import signumj.entity.SignumTimestamp;

public class TestVariables {
    public static final int EXAMPLE_BLOCK_HEIGHT = 470000;
    public static final SignumTimestamp EXAMPLE_TIMESTAMP = SignumTimestamp.fromBurstTimestamp(126144000); // 4 years
    public static final SignumID EXAMPLE_BLOCK_ID = SignumID.fromLong("9466704733664017405");
    public static final SignumID EXAMPLE_ASSET_ID = SignumID.fromLong("2864220132438361643");
    public static final SignumAddress EXAMPLE_ACCOUNT_ID = SignumAddress.fromId(SignumID.fromLong("7009665667967103287"));
    public static final SignumAddress EXAMPLE_POOL_ACCOUNT_ID = SignumAddress.fromId(SignumID.fromLong("888561138747819634"));
    public static final byte[] EXAMPLE_ACCOUNT_PUBKEY = Hex.decode("34d010e80c0d6dc409f8d7a99d0815bfbff9387909a9fca4c65253ec44fad360");
    public static final String EXAMPLE_ACCOUNT_RS = "BURST-W5YR-ZZQC-KUBJ-G78KB";

    public static final SignumID EXAMPLE_TRANSACTION_ID = SignumID.fromLong("10489995701880641892");
    public static final SignumID EXAMPLE_AT_CREATION_TRANSACTION_ID = SignumID.fromLong("3474457271106823767");
    public static final SignumID EXAMPLE_MULTI_OUT_TRANSACTION_ID = SignumID.fromLong("3631659512270044993");
    public static final SignumID EXAMPLE_MULTI_OUT_SAME_TRANSACTION_ID = SignumID.fromLong("5032020914938737522");
    public static final SignumID EXAMPLE_ASSET_TRANSFER_TRANSACTION_ID = SignumID.fromLong("3725433067123077435");
    public static final SignumID EXAMPLE_ASSET_TRANSFER_WITH_MESSAGE_TRANSACTION_ID = SignumID.fromLong("5878005582089732590");
    public static final SignumID EXAMPLE_ASSET_TRANSFER_WITH_ENCRYPTED_MESSAGE_TRANSACTION_ID = SignumID.fromLong("1625808172352359006");
    public static final SignumID EXAMPLE_BID_ORDER_PLACEMENT_TRANSACTION_ID = SignumID.fromLong("10996076067714048792");
    public static final SignumID EXAMPLE_BID_ORDER_CANCELLATION_TRANSACTION_ID = SignumID.fromLong("17400712483103268921");
    public static final SignumID EXAMPLE_ASK_ORDER_PLACEMENT_TRANSACTION_ID = SignumID.fromLong("12656175013072880629");
    public static final SignumID EXAMPLE_ASK_ORDER_CANCELLATION_TRANSACTION_ID = SignumID.fromLong("2146184374862030896");
    public static final SignumID EXAMPLE_ASSET_ISSUANCE_TRANSACTION_ID = SignumID.fromLong("12402415494995249540");
    public static final byte[] EXAMPLE_TRANSACTION_FULL_HASH = Hex.decode("e475946429c220d33f414a9d6106452547abe23ee1379fc38f571cac1c037c6f");

    public static final SignumAddress EXAMPLE_AT_ID = SignumAddress.fromId("3474457271106823767");

    public static final String HTTP_NODE = Constants.HTTP_NODE_EUROPE1;

    public static final String TEST_USER_AGENT = "signumJ-TEST";
}
