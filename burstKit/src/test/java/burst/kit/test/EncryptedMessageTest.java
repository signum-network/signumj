package burst.kit.test;

import brs.crypto.Crypto;
import brs.util.Convert;
import burst.kit.burst.BurstCrypto;
import burst.kit.entity.BurstEncryptedMessage;
import burst.kit.entity.HexStringByteArray;
import org.bouncycastle.math.ec.custom.djb.Curve25519;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptedMessageTest {
    @Test
    public void TestEncryptedMessageDecrypt() throws NoSuchAlgorithmException {
        byte[] myPrivateKey = BurstCrypto.getInstance().getPrivateKey("mouth begun jump spill string house depend connection dad spring trace lonely").getBytes();

        byte[] theirPublicKey = new HexStringByteArray("34d010e80c0d6dc409f8d7a99d0815bfbff9387909a9fca4c65253ec44fad360").getBytes();

        byte[] data = Convert.parseHexString("23f18a2d32c3ad77cbf78e1f4bd35d306f5b8c126b5a0427776f996ecc39f732b041bc1dbc4c48233ac60b8a2a7f5d9e");
        byte[] nonce = Convert.parseHexString("00eaee31c3db84f873cb0574194243c8e0c676b486346c52c755639a093590ab");

        BurstEncryptedMessage burstEncryptedMessage = new BurstEncryptedMessage(data, nonce);
        System.out.println(new String(burstEncryptedMessage.decrypt(myPrivateKey, theirPublicKey)));
    }

    @Test
    public void TestEncryptedMessageEncrypt() throws NoSuchAlgorithmException {
        byte[] myPrivateKey = Crypto.getPrivateKey("mouth begun jump spill string house depend connection dad spring trace lonely");
        byte[] myPublicKey = Crypto.privateToPublicKey(myPrivateKey);
        byte[] myPublicKeyHash = Crypto.sha256().digest(myPublicKey);
        long myAccountId = Convert.fullHashToId(myPublicKeyHash);
        String myRs = Crypto.rsEncode(myAccountId);
        
        byte[] theirPublicKey = new HexStringByteArray("34d010e80c0d6dc409f8d7a99d0815bfbff9387909a9fca4c65253ec44fad360").getBytes();
        byte[] theirPublicKeyHash = Crypto.sha256().digest(theirPublicKey);
        long theirAccountId = Convert.fullHashToId(theirPublicKeyHash);
        String theirRs = Crypto.rsEncode(theirAccountId);
        
        BurstEncryptedMessage encryptedMessage = BurstEncryptedMessage.encrypt("smello", myPrivateKey, theirPublicKey);
        System.out.println(encryptedMessage.getData().toHexString());
        System.out.println(encryptedMessage.getNonce().toHexString());
        System.out.println();

        // IT WORKS
    }
}
