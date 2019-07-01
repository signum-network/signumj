package burst.kit.crypto.plot.impl;

import burst.kit.crypto.BurstCrypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.function.Supplier;

class MiningPlot {
    private static final int HASH_SIZE = 32;
    private static final int HASHES_PER_SCOOP = 2;
    private static final int SCOOP_SIZE = HASHES_PER_SCOOP * HASH_SIZE;
    private static final int SCOOPS_PER_PLOT = 4096;
    static final BigInteger SCOOPS_PER_PLOT_BIGINT = BigInteger.valueOf(SCOOPS_PER_PLOT);
    private static final int PLOT_SIZE = SCOOPS_PER_PLOT * SCOOP_SIZE;
    private static final int BASE_LENGTH = 16;
    private static final int PLOT_TOTAL_SIZE = PLOT_SIZE + BASE_LENGTH;

    private static final int HASH_CAP = 4096;

    private final byte[] data = new byte[PLOT_TOTAL_SIZE];

    MiningPlot(Supplier<MessageDigest> shabal256Supplier, long addr, long nonce, int pocVersion) {
        System.arraycopy(BurstCrypto.getInstance().longToBytes(addr), 0, data, PLOT_SIZE, 8);
        System.arraycopy(BurstCrypto.getInstance().longToBytes(nonce), 0, data, PLOT_SIZE + 8, 8);
        MessageDigest shabal256 = shabal256Supplier.get();
        int len;
        for (int i = PLOT_SIZE; i > 0; i -= HASH_SIZE) {
            len = PLOT_TOTAL_SIZE - i;
            if (len > HASH_CAP) {
                len = HASH_CAP;
            }
            shabal256.update(data, i, len);
            System.arraycopy(shabal256.digest(), 0, data, i - HASH_SIZE, HASH_SIZE);
        }
        byte[] finalHash = shabal256.digest(data);
        for (int i = 0, j = 0; i < PLOT_SIZE; i++, j++) {
            if (j == 32) j = 0;
            data[i] = (byte) (data[i] ^ finalHash[j]);
        }

        // PoC2 Rearrangement
        if (pocVersion == 2) {
            byte[] hashBuffer = new byte[HASH_SIZE];
            int revPos = PLOT_SIZE - HASH_SIZE; // Start at second hash in last scoop
            for (int pos = 32; pos < (PLOT_SIZE / 2); pos += 64) { // Start at second hash in first scoop
                System.arraycopy(data, pos, hashBuffer, 0, HASH_SIZE); // Copy low scoop second hash to buffer
                System.arraycopy(data, revPos, data, pos, HASH_SIZE); // Copy high scoop second hash to low scoop second hash
                System.arraycopy(hashBuffer, 0, data, revPos, HASH_SIZE); // Copy buffer to high scoop second hash
                revPos -= 64; // move backwards
            }
        }
    }

    void hashScoop(MessageDigest shabal256, int pos) {
        shabal256.update(data, pos * SCOOP_SIZE, SCOOP_SIZE);
    }
}
