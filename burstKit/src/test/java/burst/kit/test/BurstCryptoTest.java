package burst.kit.test;

import burst.kit.crypto.BurstCrypto;
import burst.kit.entity.BurstEncryptedMessage;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstCryptoTest { // TODO more unit tests
    private byte[] stringToBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    private final BurstCrypto burstCrypto = BurstCrypto.getInstance();

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

        assertEquals(message, result1);
        assertEquals(message, result2);
    }

    @Test
    public void TestSignAndVerify() {
        byte[] myMessage = "A Message".getBytes(StandardCharsets.UTF_8);
        byte[] myPrivateKey = BurstCrypto.getInstance().getPrivateKey("example1");
        byte[] myPublic = BurstCrypto.getInstance().getPublicKey(myPrivateKey);
        byte[] signature = BurstCrypto.getInstance().sign(myMessage, myPrivateKey);
        Assert.assertTrue(BurstCrypto.getInstance().verify(signature, myMessage, myPublic, true));
    }

    @Test
    public void testCryptoSha256() {
        MessageDigest sha256 = BurstCrypto.getInstance().getSha256();
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", Hex.toHexString(sha256.digest(stringToBytes(""))));
        assertEquals("e806a291cfc3e61f83b98d344ee57e3e8933cccece4fb45e1481f1f560e70eb1", Hex.toHexString(sha256.digest(stringToBytes("Testing"))));
        assertEquals("6de732f18e99e18ac25c609d6942f06f6ed7ab3f261ca46668d3a0e19fbc9e80", Hex.toHexString(sha256.digest(stringToBytes("Burstcoin!"))));
        assertEquals("d059c5e6b6715f1e1dd83295e804d4f5fbc560cd10befde400434d19afdf4cfe", Hex.toHexString(sha256.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test
    public void testCryptoShabal256() {
        MessageDigest shabal256 = BurstCrypto.getInstance().getShabal256();
        assertEquals("aec750d11feee9f16271922fbaf5a9be142f62019ef8d720f858940070889014", Hex.toHexString(shabal256.digest(stringToBytes(""))));
        assertEquals("10e237979a7233aa6a9377ff6a4b2541f890f67107fe0c89008fdd2c48e4cfe5", Hex.toHexString(shabal256.digest(stringToBytes("Testing"))));
        assertEquals("9beec9e237da7542a045b89c709b5d423b22faa99d5f01abab67261e1a9de6b8", Hex.toHexString(shabal256.digest(stringToBytes("Burstcoin!"))));
        assertEquals("4d92fb90793baaefabf4691cdcf4f1332ccd51c4a74f509a4b9a338eddb39e09", Hex.toHexString(shabal256.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test
    public void testCryptoRipemd160() {
        MessageDigest ripemd160 = BurstCrypto.getInstance().getRipeMD160();
        assertEquals("9c1185a5c5e9fc54612808977ee8f548b2258d31", Hex.toHexString(ripemd160.digest(stringToBytes(""))));
        assertEquals("01743c6e71742ed72d6c51537f1790a462b82c82", Hex.toHexString(ripemd160.digest(stringToBytes("Testing"))));
        assertEquals("9b7e20c53c6e77ed8d9768d8a5a813d02c0a0d6a", Hex.toHexString(ripemd160.digest(stringToBytes("Burstcoin!"))));
        assertEquals("b089c88c2f81e87326c22b2df66dca6857f690a0", Hex.toHexString(ripemd160.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test
    public void testBytesToLongBE() {
        assertEquals(0x0000000000000000L, burstCrypto.bytesToLongBE(new byte[0]));
        assertEquals(0x0100000000000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0100000000000000")));
        assertEquals(0x0123000000000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123000000000000")));
        assertEquals(0x0123450000000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123450000000000")));
        assertEquals(0x0123456700000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456700000000")));
        assertEquals(0x0123456789000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789000000")));
        assertEquals(0x0123456789ab0000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789ab0000")));
        assertEquals(0x0123456789abcd00L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789abcd00")));
        assertEquals(0x0123456789abcdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789abcdef")));
    }

    @Test
    public void testBytesToLongLE() {
        assertEquals(0x0000000000000000L, burstCrypto.bytesToLongLE(new byte[0]));
        assertEquals(0x0000000000000001L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0100000000000000")));
        assertEquals(0x0000000000002301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123000000000000")));
        assertEquals(0x0000000000452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123450000000000")));
        assertEquals(0x0000000067452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456700000000")));
        assertEquals(0x0000008967452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789000000")));
        assertEquals(0x0000ab8967452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789ab0000")));
        assertEquals(0x00cdab8967452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789abcd00")));
        assertEquals(-0x1032547698badcffL, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789abcdef")));
    }

    @Test
    public void testLongToBytesBE() {
        assertEquals("0000000000000001", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x0000000000000001L)));
        assertEquals("0000000000002301", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x0000000000002301L)));
        assertEquals("0000000000452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x0000000000452301L)));
        assertEquals("0000000067452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x0000000067452301L)));
        assertEquals("0000008967452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x0000008967452301L)));
        assertEquals("0000ab8967452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x0000ab8967452301L)));
        assertEquals("00cdab8967452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(0x00cdab8967452301L)));
        assertEquals("efcdab8967452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(-0x1032547698badcffL)));
    }

    @Test
    public void testLongToBytesLE() {
        assertEquals("0100000000000000", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x0000000000000001L)));
        assertEquals("0123000000000000", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x0000000000002301L)));
        assertEquals("0123450000000000", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x0000000000452301L)));
        assertEquals("0123456700000000", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x0000000067452301L)));
        assertEquals("0123456789000000", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x0000008967452301L)));
        assertEquals("0123456789ab0000", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x0000ab8967452301L)));
        assertEquals("0123456789abcd00", burstCrypto.toHexString(burstCrypto.longToBytesLE(0x00cdab8967452301L)));
        assertEquals("0123456789abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(-0x1032547698badcffL)));
    }
}
