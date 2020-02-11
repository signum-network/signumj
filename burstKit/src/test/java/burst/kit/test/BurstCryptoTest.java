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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BurstCryptoTest { // TODO more unit tests
    private byte[] stringToBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    private final BurstCrypto burstCrypto = BurstCrypto.getInstance();

    @Test
    public void testEncryptTextMessage() {
        String message = "Test message";

        burstCrypto.setNativeEnabled(false);
        byte[] myPrivateKey = burstCrypto.getPrivateKey("example1");
        byte[] myPublicKey = burstCrypto.getPublicKey(myPrivateKey);
        byte[] theirPrivateKey = burstCrypto.getPrivateKey("example2");
        byte[] theirPublicKey = burstCrypto.getPublicKey(theirPrivateKey);

        burstCrypto.setNativeEnabled(true);
        if (burstCrypto.nativeEnabled()) {
            byte[] myPrivateKey_native = burstCrypto.getPrivateKey("example1");
            byte[] myPublicKey_native = burstCrypto.getPublicKey(myPrivateKey);
            byte[] theirPrivateKey_native = burstCrypto.getPrivateKey("example2");
            byte[] theirPublicKey_native = burstCrypto.getPublicKey(theirPrivateKey);

            assertArrayEquals(myPrivateKey, myPrivateKey_native);
            assertArrayEquals(myPublicKey, myPublicKey_native);
            assertArrayEquals(theirPrivateKey, theirPrivateKey_native);
            assertArrayEquals(theirPublicKey, theirPublicKey_native);
        }

        BurstEncryptedMessage burstEncryptedMessage = burstCrypto.encryptTextMessage(message, myPrivateKey, theirPublicKey);

        String result1 = new String(burstCrypto.decryptMessage(burstEncryptedMessage, myPrivateKey, theirPublicKey));
        String result2 = new String(burstCrypto.decryptMessage(burstEncryptedMessage, theirPrivateKey, myPublicKey));

        assertEquals(message, result1);
        assertEquals(message, result2);
    }

    private void reverse(byte[] array) {
        for(int i = 0; i < array.length / 2; i++)
        {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = (byte) temp;
        }
    }

    @Test
    public void testGetSharedKey() {
        byte[] sharedKey = burstCrypto.parseHexString("f057d9854fa4d7cf86a822500dae7b6c3325a21a1f13f5fc98b587bc0569113a");

        byte[] privateKey1 = burstCrypto.getPrivateKey("passphrase1");
        byte[] privateKey2 = burstCrypto.getPrivateKey("passphrase2");
        byte[] publicKey1 = burstCrypto.getPublicKey(privateKey1);
        byte[] publicKey2 = burstCrypto.getPublicKey(privateKey2);

        burstCrypto.setNativeEnabled(false);
        byte[] sharedKey1 = burstCrypto.getSharedSecret(privateKey1, publicKey2);
        byte[] sharedKey2 = burstCrypto.getSharedSecret(privateKey2, publicKey1);
        assertArrayEquals(sharedKey, sharedKey1);
        assertArrayEquals(sharedKey, sharedKey2);

        burstCrypto.setNativeEnabled(true);
        if (burstCrypto.nativeEnabled()) {
            byte[] sharedKey1_native = burstCrypto.getSharedSecret(privateKey1, publicKey2);
            byte[] sharedKey2_native = burstCrypto.getSharedSecret(privateKey2, publicKey1);
            assertArrayEquals(sharedKey1, sharedKey1_native);
            assertArrayEquals(sharedKey2, sharedKey2_native);
        }
    }

    @Test
    public void testSignAndVerify() {
        burstCrypto.setNativeEnabled(false);
        byte[] myMessage = "A Message".getBytes(StandardCharsets.UTF_8);
        byte[] myPrivateKey = burstCrypto.getPrivateKey("example1");
        byte[] myPublic = burstCrypto.getPublicKey(myPrivateKey);
        byte[] signature = burstCrypto.sign(myMessage, myPrivateKey);
        Assert.assertTrue(burstCrypto.verify(signature, myMessage, myPublic, true));
        reverse(signature);
        Assert.assertFalse(burstCrypto.verify(signature, myMessage, myPublic, true));

        burstCrypto.setNativeEnabled(true);
        if (burstCrypto.nativeEnabled()) {
            reverse(signature); // Undo previous reverse
            byte[] nativeSignature = burstCrypto.sign(myMessage, myPrivateKey);
            byte[] nativePublicKey = burstCrypto.getPublicKey(myPrivateKey);
            Assert.assertArrayEquals(myPublic, nativePublicKey);
            Assert.assertArrayEquals(signature, nativeSignature);
            Assert.assertTrue(burstCrypto.verify(signature, myMessage, myPublic, true));
            reverse(signature);
            Assert.assertFalse(burstCrypto.verify(signature, myMessage, myPublic, true));
        }
    }

    @Test
    public void testCryptoSha256() {
        MessageDigest sha256 = burstCrypto.getSha256();
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", Hex.toHexString(sha256.digest(stringToBytes(""))));
        assertEquals("e806a291cfc3e61f83b98d344ee57e3e8933cccece4fb45e1481f1f560e70eb1", Hex.toHexString(sha256.digest(stringToBytes("Testing"))));
        assertEquals("6de732f18e99e18ac25c609d6942f06f6ed7ab3f261ca46668d3a0e19fbc9e80", Hex.toHexString(sha256.digest(stringToBytes("Burstcoin!"))));
        assertEquals("d059c5e6b6715f1e1dd83295e804d4f5fbc560cd10befde400434d19afdf4cfe", Hex.toHexString(sha256.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test
    public void testCryptoShabal256() {
        MessageDigest shabal256 = burstCrypto.getShabal256();
        assertEquals("aec750d11feee9f16271922fbaf5a9be142f62019ef8d720f858940070889014", Hex.toHexString(shabal256.digest(stringToBytes(""))));
        assertEquals("10e237979a7233aa6a9377ff6a4b2541f890f67107fe0c89008fdd2c48e4cfe5", Hex.toHexString(shabal256.digest(stringToBytes("Testing"))));
        assertEquals("9beec9e237da7542a045b89c709b5d423b22faa99d5f01abab67261e1a9de6b8", Hex.toHexString(shabal256.digest(stringToBytes("Burstcoin!"))));
        assertEquals("4d92fb90793baaefabf4691cdcf4f1332ccd51c4a74f509a4b9a338eddb39e09", Hex.toHexString(shabal256.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test
    public void testCryptoRipemd160() {
        MessageDigest ripemd160 = burstCrypto.getRipeMD160();
        assertEquals("9c1185a5c5e9fc54612808977ee8f548b2258d31", Hex.toHexString(ripemd160.digest(stringToBytes(""))));
        assertEquals("01743c6e71742ed72d6c51537f1790a462b82c82", Hex.toHexString(ripemd160.digest(stringToBytes("Testing"))));
        assertEquals("9b7e20c53c6e77ed8d9768d8a5a813d02c0a0d6a", Hex.toHexString(ripemd160.digest(stringToBytes("Burstcoin!"))));
        assertEquals("b089c88c2f81e87326c22b2df66dca6857f690a0", Hex.toHexString(ripemd160.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test
    public void testCryptoMd5() {
        MessageDigest md5 = burstCrypto.getMD5();
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", Hex.toHexString(md5.digest(stringToBytes(""))));
        assertEquals("fa6a5a3224d7da66d9e0bdec25f62cf0", Hex.toHexString(md5.digest(stringToBytes("Testing"))));
        assertEquals("5251a8a65a96abd2bf344f84c64b7d71", Hex.toHexString(md5.digest(stringToBytes("Burstcoin!"))));
        assertEquals("48fe9ca8e71d7334f4566909aef4350d", Hex.toHexString(md5.digest(stringToBytes("Burst Apps Team"))));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testBytesToLongBE_tooShortArray() {
        assertEquals(0x0000000000000000L, burstCrypto.bytesToLongBE(new byte[7]));
    }

    @Test
    public void testBytesToLongBE() {
        assertEquals(0x0100000000000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0100000000000000")));
        assertEquals(0x0123000000000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123000000000000")));
        assertEquals(0x0123450000000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123450000000000")));
        assertEquals(0x0123456700000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456700000000")));
        assertEquals(0x0123456789000000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789000000")));
        assertEquals(0x0123456789ab0000L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789ab0000")));
        assertEquals(0x0123456789abcd00L, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789abcd00")));

        assertEquals(0x00000000000000efL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("00000000000000ef")));
        assertEquals(0x000000000000cdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("000000000000cdef")));
        assertEquals(0x0000000000abcdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0000000000abcdef")));
        assertEquals(0x0000000089abcdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0000000089abcdef")));
        assertEquals(0x0000006789abcdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0000006789abcdef")));
        assertEquals(0x0000456789abcdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0000456789abcdef")));
        assertEquals(0x0023456789abcdefL, burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0023456789abcdef")));

        assertEquals(Long.parseUnsignedLong("0123456789abcdef", 16), burstCrypto.bytesToLongBE(burstCrypto.parseHexString("0123456789abcdef")));
        assertEquals(Long.parseUnsignedLong("efcdab8967452301", 16), burstCrypto.bytesToLongBE(burstCrypto.parseHexString("efcdab8967452301")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testBytesToLongLE_tooShortArray() {
        assertEquals(0x0000000000000000L, burstCrypto.bytesToLongLE(new byte[7]));
    }

    @Test
    public void testBytesToLongLE() {
        assertEquals(0x0000000000000001L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0100000000000000")));
        assertEquals(0x0000000000002301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123000000000000")));
        assertEquals(0x0000000000452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123450000000000")));
        assertEquals(0x0000000067452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456700000000")));
        assertEquals(0x0000008967452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789000000")));
        assertEquals(0x0000ab8967452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789ab0000")));
        assertEquals(0x00cdab8967452301L, burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789abcd00")));

        assertEquals(Long.parseUnsignedLong("ef00000000000000", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("00000000000000ef")));
        assertEquals(Long.parseUnsignedLong("efcd000000000000", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("000000000000cdef")));
        assertEquals(Long.parseUnsignedLong("efcdab0000000000", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0000000000abcdef")));
        assertEquals(Long.parseUnsignedLong("efcdab8900000000", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0000000089abcdef")));
        assertEquals(Long.parseUnsignedLong("efcdab8967000000", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0000006789abcdef")));
        assertEquals(Long.parseUnsignedLong("efcdab8967450000", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0000456789abcdef")));
        assertEquals(Long.parseUnsignedLong("efcdab8967452300", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0023456789abcdef")));

        assertEquals(Long.parseUnsignedLong("efcdab8967452301", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("0123456789abcdef")));
        assertEquals(Long.parseUnsignedLong("0123456789abcdef", 16), burstCrypto.bytesToLongLE(burstCrypto.parseHexString("efcdab8967452301")));
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

        assertEquals("ef00000000000000", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("ef00000000000000", 16))));
        assertEquals("efcd000000000000", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcd000000000000", 16))));
        assertEquals("efcdab0000000000", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcdab0000000000", 16))));
        assertEquals("efcdab8900000000", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcdab8900000000", 16))));
        assertEquals("efcdab8967000000", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcdab8967000000", 16))));
        assertEquals("efcdab8967450000", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcdab8967450000", 16))));
        assertEquals("efcdab8967452300", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcdab8967452300", 16))));

        assertEquals("0123456789abcdef", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("0123456789abcdef", 16))));
        assertEquals("efcdab8967452301", burstCrypto.toHexString(burstCrypto.longToBytesBE(Long.parseUnsignedLong("efcdab8967452301", 16))));
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

        assertEquals("00000000000000ef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("ef00000000000000", 16))));
        assertEquals("000000000000cdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcd000000000000", 16))));
        assertEquals("0000000000abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcdab0000000000", 16))));
        assertEquals("0000000089abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcdab8900000000", 16))));
        assertEquals("0000006789abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcdab8967000000", 16))));
        assertEquals("0000456789abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcdab8967450000", 16))));
        assertEquals("0023456789abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcdab8967452300", 16))));

        assertEquals("efcdab8967452301", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("0123456789abcdef", 16))));
        assertEquals("0123456789abcdef", burstCrypto.toHexString(burstCrypto.longToBytesLE(Long.parseUnsignedLong("efcdab8967452301", 16))));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testBytesToIntBE_tooShortArray() {
        assertEquals(0x00000000, burstCrypto.bytesToIntBE(new byte[3]));
    }

    @Test
    public void testBytesToIntBE() {
        assertEquals(0x89000000, burstCrypto.bytesToIntBE(burstCrypto.parseHexString("89000000")));
        assertEquals(0x89ab0000, burstCrypto.bytesToIntBE(burstCrypto.parseHexString("89ab0000")));
        assertEquals(0x89abcd00, burstCrypto.bytesToIntBE(burstCrypto.parseHexString("89abcd00")));
        assertEquals(0x89abcdef, burstCrypto.bytesToIntBE(burstCrypto.parseHexString("89abcdef")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testBytesToIntLE_tooShortArray() {
        assertEquals(0x00000000, burstCrypto.bytesToIntLE(new byte[3]));
    }

    @Test
    public void testBytesToIntLE() {
        assertEquals(0x00000001, burstCrypto.bytesToIntLE(burstCrypto.parseHexString("01000000")));
        assertEquals(0x00002301, burstCrypto.bytesToIntLE(burstCrypto.parseHexString("01230000")));
        assertEquals(0x00452301, burstCrypto.bytesToIntLE(burstCrypto.parseHexString("01234500")));
        assertEquals(0x67452301, burstCrypto.bytesToIntLE(burstCrypto.parseHexString("01234567")));
    }

    @Test
    public void testIntToBytesBE() {
        assertEquals("00000001", burstCrypto.toHexString(burstCrypto.intToBytesBE(0x00000001)));
        assertEquals("00002301", burstCrypto.toHexString(burstCrypto.intToBytesBE(0x00002301)));
        assertEquals("00452301", burstCrypto.toHexString(burstCrypto.intToBytesBE(0x00452301)));
        assertEquals("67452301", burstCrypto.toHexString(burstCrypto.intToBytesBE(0x67452301)));
    }

    @Test
    public void testIntToBytesLE() {
        assertEquals("01000000", burstCrypto.toHexString(burstCrypto.intToBytesLE(0x00000001)));
        assertEquals("01230000", burstCrypto.toHexString(burstCrypto.intToBytesLE(0x00002301)));
        assertEquals("01234500", burstCrypto.toHexString(burstCrypto.intToBytesLE(0x00452301)));
        assertEquals("01234567", burstCrypto.toHexString(burstCrypto.intToBytesLE(0x67452301)));
    }
}
