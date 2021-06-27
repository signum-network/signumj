package signumj.test.crypto.ec;

import signumj.crypto.SignumCrypto;
import signumj.crypto.ec.Curve25519;
import signumj.crypto.ec.Curve25519Impl;
import signumj.crypto.ec.Curve25519NativeImpl;
import signumj.util.LibShabal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;

@RunWith(JUnit4.class)
public class Curve25519Benchmark {
    private byte[] sha256(String input) {
        return SignumCrypto.getInstance().getSha256().digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private void benchmark(Curve25519 curve25519, boolean isNative) {
        byte[] messageDigest = sha256("May the best curve win!");
        byte[] privateKey = sha256("Super secret passphrase");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            byte[] publicKey = curve25519.getPublicKey(privateKey);
            byte[] signature = curve25519.sign(messageDigest, privateKey);
            boolean verifySuccess = curve25519.verify(messageDigest, signature, publicKey, true);
            if (!verifySuccess) throw new IllegalStateException("Curve failed to verify");
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println((isNative ? "Native" : "Java") + " Curve25519: 10000 iterations of get public key -> sign -> verify took " + duration + "ms");
    }

    @Test
    public void benchmarkJava() {
        benchmark(new Curve25519Impl(() -> SignumCrypto.getInstance().getSha256()), false);
    }

    @Test
    public void benchmarkNative() {
        if (LibShabal.LOAD_ERROR != null) {
            System.out.println("LibShabal not loaded, can't benchmark native implementation");
            LibShabal.LOAD_ERROR.printStackTrace();
            return;
        }
        benchmark(new Curve25519NativeImpl(), true);
    }
}
