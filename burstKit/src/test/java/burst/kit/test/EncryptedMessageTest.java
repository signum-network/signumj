package burst.kit.test;

import burst.kit.burst.BurstCrypto;
import burst.kit.entity.BurstEncryptedMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EncryptedMessageTest {
    @Test
    public void TestEncryptedMessage() {
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
}
