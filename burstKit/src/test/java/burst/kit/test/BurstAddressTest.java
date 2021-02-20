package burst.kit.test;

import burst.kit.crypto.BurstCrypto;
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
        
        assertEquals("BURST-JJQS-MMA4-GHB4-4ZNZU", BurstAddress.fromEither("BURST-JJQS-MMA4-GHB4-4ZNZU").getFullAddress());
        assertEquals("BURST-JJQS-MMA4-GHB4-4ZNZU", BurstAddress.fromEither("BURST-JJQS-MMA4-GHB4-4ZNZU-1UQDBIWHL54TH1HBIK1ZEQWQ3DR4MW6E5B3SNDK1HM9TMLL1T2").getFullAddress());
        assertEquals("4A5FA0EE4E40BA8D322EF75963D6B8E71BD206897326CC9683960C9D2FF25966",
                BurstCrypto.getInstance().toHexString(BurstAddress.fromEither("BURST-JJQS-MMA4-GHB4-4ZNZU-1UQDBIWHL54TH1HBIK1ZEQWQ3DR4MW6E5B3SNDK1HM9TMLL1T2").getPublicKey()).toUpperCase());

        assertEquals("BURST-NMXS-ZZS2-3W9L-9UKJP", BurstAddress.fromEither("BURST-NMXS-ZZS2-3W9L-9UKJP").getFullAddress());
        
        assertEquals("CB85806964BB888E9AE1C2F27A3DB85D448B84FE9CB2BCCEAFBC49729A03AC16",
                BurstCrypto.getInstance().toHexString(BurstAddress.fromEither("BURST-NMXS-ZZS2-3W9L-9UKJP-52M12IVV2RO5B03A568CVA5AO59D0Z1IF8ILFD11CS8M7N3AQE").getPublicKey()).toUpperCase());

        BurstKitUtils.setAddressPrefix("BAT");
        assertEquals(7009665667967103287L, BurstAddress.fromEither("BAT-WEBR-T74Q-HQJY-8PUK4").getSignedLongId());
        assertEquals("BAT-WEBR-T74Q-HQJY-8PUK4", BurstAddress.fromEither("7009665667967103287").getFullAddress());
        assertEquals("BAT-WEBR-T74Q-HQJY-8PUK4", BurstAddress.fromEither("BAT-WEBR-T74Q-HQJY-8PUK4").getFullAddress());
    }
}
