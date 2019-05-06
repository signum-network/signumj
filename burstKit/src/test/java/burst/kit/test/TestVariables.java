package burst.kit.test;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;
import org.bouncycastle.util.encoders.Hex;

public class TestVariables {
    public static final long EXAMPLE_BLOCK_HEIGHT = 470000;
    public static final BurstTimestamp EXAMPLE_TIMESTAMP = new BurstTimestamp(126144000); // 4 years
    public static final BurstID EXAMPLE_BLOCK_ID = BurstID.fromLong("9466704733664017405");
    public static final BurstAddress EXAMPLE_ACCOUNT_ID = BurstAddress.fromId(BurstID.fromLong("7009665667967103287"));
    public static final BurstAddress EXAMPLE_POOL_ACCOUNT_ID = BurstAddress.fromId(BurstID.fromLong("888561138747819634"));
    public static final byte[] EXAMPLE_ACCOUNT_PUBKEY = Hex.decode("34d010e80c0d6dc409f8d7a99d0815bfbff9387909a9fca4c65253ec44fad360");
    public static final String EXAMPLE_ACCOUNT_RS = "BURST-W5YR-ZZQC-KUBJ-G78KB";

    public static final BurstID EXAMPLE_TRANSACTION_ID = BurstID.fromLong("10489995701880641892");
    public static final BurstID EXAMPLE_AT_CREATION_TRANSACTION_ID = BurstID.fromLong("3474457271106823767");
    public static final BurstID EXAMPLE_MULTI_OUT_TRANSACTION_ID = BurstID.fromLong("3631659512270044993");
    public static final BurstID EXAMPLE_MULTI_OUT_SAME_TRANSACTION_ID = BurstID.fromLong("5032020914938737522");
    public static final byte[] EXAMPLE_TRANSACTION_FULL_HASH = Hex.decode("e475946429c220d33f414a9d6106452547abe23ee1379fc38f571cac1c037c6f");

    public static final BurstID EXAMPLE_AT_ID = BurstID.fromLong("3474457271106823767");

    public static final String EXAMPLE_AT_LONG_HEX2LONG = "9900958322455989675";
}
