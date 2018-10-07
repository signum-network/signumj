package burst.kit.test;

import burst.kit.entity.BurstAddress;
import burst.kit.entity.BurstID;
import burst.kit.entity.BurstTimestamp;

public class TestVariables {
    public static final long EXAMPLE_BLOCK_HEIGHT = 470000;
    public static final BurstTimestamp EXAMPLE_TIMESTAMP = new BurstTimestamp(126144000); // 4 years
    public static final BurstID EXAMPLE_BLOCK_ID = new BurstID("9466704733664017405");
    //public static final BurstAddress EXAMPLE_ACCOUNT_ID = BurstAddress.fromNumericId(new BurstID("16484518239061020631"));
    public static final BurstAddress EXAMPLE_ACCOUNT_ID = BurstAddress.fromNumericId(new BurstID("7009665667967103287"));
    public static final String EXAMPLE_ACCOUNT_RS = "BURST-W5YR-ZZQC-KUBJ-G78KB";
    public static final BurstID EXAMPLE_TRANSACTION_ID = new BurstID("10489995701880641892");
    public static final BurstID EXAMPLE_AT_ID = new BurstID("3474457271106823767");

    public static final String EXAMPLE_AT_LONG = "abcdef0123456789";
    public static final String EXAMPLE_AT_LONG_HEX2LONG = "9900958322455989675";
}
