package burst.kit.test.crypto.hash;

import burst.kit.crypto.BurstCrypto;
import burst.kit.crypto.hash.shabal.Shabal256;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * This class is only meant to test specific functionality of the Shabal256 class.
 * For general abstract functionality tests, such as digest result tests, see the BurstCryptoTest
 */
@RunWith(JUnit4.class)
public class Shabal256Test {
    @Test
    public void testClone() {
        Shabal256 shabal256 = new Shabal256();
        shabal256.update("testTestTest".getBytes(StandardCharsets.UTF_8));
        Shabal256 newShabal256 = shabal256.clone();
        String expectedHash = "bed0eb7ff7d5cfd3e91a6001fb5f346581e40c40fde4b9525bcf7fffa305593b";
        assertEquals(expectedHash, BurstCrypto.getInstance().toHexString(shabal256.digest()));
        assertEquals(expectedHash, BurstCrypto.getInstance().toHexString(newShabal256.digest()));
    }
}
