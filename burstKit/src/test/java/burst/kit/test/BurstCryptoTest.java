package burst.kit.test;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.BurstEncryptedMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;

@RunWith(JUnit4.class)
public class BurstCryptoTest { // TODO more unit tests
    @Test
    public void TestEncryptTextMessage() {
        String message = "Test message";

        byte[] myPrivateKey = BurstCrypto.getInstance().getPrivateKey("example1");
        byte[] myPublicKey = BurstCrypto.getInstance().getPublicKey(myPrivateKey);
        byte[] theirPrivateKey = BurstCrypto.getInstance().getPrivateKey("example2");
        byte[] theirPublicKey = BurstCrypto.getInstance().getPublicKey(theirPrivateKey);

        BurstEncryptedMessage burstEncryptedMessage = BurstCrypto.getInstance().encryptTextMessage(message, myPrivateKey, theirPublicKey);

        String result1 = new String(BurstCrypto.getInstance().decryptMessage(burstEncryptedMessage, myPrivateKey, theirPublicKey));
        String result2 = new String(BurstCrypto.getInstance().decryptMessage(burstEncryptedMessage, theirPrivateKey, myPublicKey));

        Assert.assertEquals(message, result1);
        Assert.assertEquals(message, result2);
    }

    @Test
    public void TestSignAndVerify() {
        byte[] myMessage = "A Message".getBytes(StandardCharsets.UTF_8);
        byte[] myPrivateKey = BurstCrypto.getInstance().getPrivateKey("example1");
        byte[] myPublic = BurstCrypto.getInstance().getPublicKey(myPrivateKey);
        byte[] signature = BurstCrypto.getInstance().sign(myMessage, myPrivateKey);
        Assert.assertTrue(BurstCrypto.getInstance().verify(signature, myMessage, myPublic, true));
    }
}
