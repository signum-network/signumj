package burst.kit.test;

import burst.kit.entity.BurstAddress;
import burst.kit.util.BurstKitUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstAddressTest {
    @Test
    public void testBurstAddressFromEither() {
        BurstKitUtils.setAddressPrefix("BURST");
        assertEquals(7009665667967103287L, BurstAddress.fromEither("BURST-WEBR-T74Q-HQJY-8PUK4").getSignedLongId());
        assertEquals("BURST-WEBR-T74Q-HQJY-8PUK4", BurstAddress.fromEither("7009665667967103287").getFullAddress());
        assertEquals("BURST-WEBR-T74Q-HQJY-8PUK4", BurstAddress.fromEither("BURST-WEBR-T74Q-HQJY-8PUK4").getFullAddress());
        BurstKitUtils.setAddressPrefix("BAT");
        assertEquals(7009665667967103287L, BurstAddress.fromEither("BAT-WEBR-T74Q-HQJY-8PUK4").getSignedLongId());
        assertEquals("BAT-WEBR-T74Q-HQJY-8PUK4", BurstAddress.fromEither("7009665667967103287").getFullAddress());
        assertEquals("BAT-WEBR-T74Q-HQJY-8PUK4", BurstAddress.fromEither("BAT-WEBR-T74Q-HQJY-8PUK4").getFullAddress());
    }
}
