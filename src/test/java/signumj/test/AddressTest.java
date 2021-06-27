package signumj.test;

import signumj.crypto.SignumCrypto;
import signumj.entity.SignumAddress;
import signumj.util.SignumUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

import org.bouncycastle.util.encoders.Hex;

@RunWith(JUnit4.class)
public class AddressTest {
    @Test
    public void testAddressFromEither() {
        SignumUtils.setAddressPrefix("BURST");
        assertEquals(7009665667967103287L, SignumAddress.fromEither("BURST-WEBR-T74Q-HQJY-8PUK4").getSignedLongId());
        assertEquals("BURST-WEBR-T74Q-HQJY-8PUK4", SignumAddress.fromEither("7009665667967103287").getFullAddress());
        assertEquals("BURST-WEBR-T74Q-HQJY-8PUK4", SignumAddress.fromEither("BURST-WEBR-T74Q-HQJY-8PUK4").getFullAddress());
        
        assertEquals("BURST-JJQS-MMA4-GHB4-4ZNZU", SignumAddress.fromEither("BURST-JJQS-MMA4-GHB4-4ZNZU").getFullAddress());
        assertEquals("BURST-JJQS-MMA4-GHB4-4ZNZU", SignumAddress.fromEither("BURST-JJQS-MMA4-GHB4-4ZNZU-1UQDBIWHL54TH1HBIK1ZEQWQ3DR4MW6E5B3SNDK1HM9TMLL1T2").getFullAddress());
        assertEquals("4A5FA0EE4E40BA8D322EF75963D6B8E71BD206897326CC9683960C9D2FF25966",
                SignumCrypto.getInstance().toHexString(SignumAddress.fromEither("BURST-JJQS-MMA4-GHB4-4ZNZU-1UQDBIWHL54TH1HBIK1ZEQWQ3DR4MW6E5B3SNDK1HM9TMLL1T2").getPublicKey()).toUpperCase());

        assertEquals("BURST-NMXS-ZZS2-3W9L-9UKJP", SignumAddress.fromEither("BURST-NMXS-ZZS2-3W9L-9UKJP").getFullAddress());
        
        assertEquals("CB85806964BB888E9AE1C2F27A3DB85D448B84FE9CB2BCCEAFBC49729A03AC16",
                SignumCrypto.getInstance().toHexString(SignumAddress.fromEither("BURST-NMXS-ZZS2-3W9L-9UKJP-52M12IVV2RO5B03A568CVA5AO59D0Z1IF8ILFD11CS8M7N3AQE").getPublicKey()).toUpperCase());
        
        assertEquals("BURST-JJQS-MMA4-GHB4-4ZNZU", SignumCrypto.getInstance().getAddressFromPublic(Hex.decode("4A5FA0EE4E40BA8D322EF75963D6B8E71BD206897326CC9683960C9D2FF25966")).getFullAddress());
        assertEquals("BURST-JJQS-MMA4-GHB4-4ZNZU-1UQDBIWHL54TH1HBIK1ZEQWQ3DR4MW6E5B3SNDK1HM9TMLL1T2", SignumCrypto.getInstance().getAddressFromPublic(Hex.decode("4A5FA0EE4E40BA8D322EF75963D6B8E71BD206897326CC9683960C9D2FF25966")).getExtendedAddress());

        assertEquals("BURST-NMXS-ZZS2-3W9L-9UKJP", SignumCrypto.getInstance().getAddressFromPublic(Hex.decode("CB85806964BB888E9AE1C2F27A3DB85D448B84FE9CB2BCCEAFBC49729A03AC16")).getFullAddress());
        assertEquals("BURST-NMXS-ZZS2-3W9L-9UKJP-52M12IVV2RO5B03A568CVA5AO59D0Z1IF8ILFD11CS8M7N3AQE", SignumCrypto.getInstance().getAddressFromPublic(Hex.decode("CB85806964BB888E9AE1C2F27A3DB85D448B84FE9CB2BCCEAFBC49729A03AC16")).getExtendedAddress());

        SignumUtils.setAddressPrefix("BAT");
        assertEquals(7009665667967103287L, SignumAddress.fromEither("BAT-WEBR-T74Q-HQJY-8PUK4").getSignedLongId());
        assertEquals("BAT-WEBR-T74Q-HQJY-8PUK4", SignumAddress.fromEither("7009665667967103287").getFullAddress());
        assertEquals("BAT-WEBR-T74Q-HQJY-8PUK4", SignumAddress.fromEither("BAT-WEBR-T74Q-HQJY-8PUK4").getFullAddress());
    }
}
