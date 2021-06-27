package signumj.crypto.plot.impl;

import java.math.BigInteger;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.Arrays;

import signumj.crypto.SignumCrypto;

public class MiningPlot {
    public static final int HASH_SIZE = 32;
    public static final int HASHES_PER_SCOOP = 2;
    public static final int SCOOP_SIZE = HASHES_PER_SCOOP * HASH_SIZE;
    public static final int SCOOPS_PER_PLOT = 4096;
    public static final BigInteger SCOOPS_PER_PLOT_BIGINT = BigInteger.valueOf(SCOOPS_PER_PLOT);
    public static final int PLOT_SIZE = SCOOPS_PER_PLOT * SCOOP_SIZE;
    public static final int BASE_LENGTH = 16;

    private static final int HASH_CAP = 4096;

    private final byte[] data;
    private final int dataOffset;

    /**
     * No length checks are made on the buffer
     * @throws IndexOutOfBoundsException if the buffer is not big enough
     */
    public MiningPlot(MessageDigest shabal256, long addr, long nonce, int pocVersion, byte[] buffer, int bufferOffset) {
        this.data = buffer;
        this.dataOffset = bufferOffset;
        byte[] baseData = new byte[BASE_LENGTH];
        SignumCrypto.getInstance().longToBytesBE(addr, baseData, 0);
        SignumCrypto.getInstance().longToBytesBE(nonce, baseData, 8);

        try {
            final int limit = PLOT_SIZE - 128 * HASH_SIZE;
            for (int i = PLOT_SIZE; i > limit; i -= HASH_SIZE) {
                shabal256.update(data, i + bufferOffset, PLOT_SIZE - i);
                shabal256.update(baseData);
                shabal256.digest(data, i - HASH_SIZE + bufferOffset, HASH_SIZE);
            }
            for (int i = limit; i > 0; i -= HASH_SIZE) {
                shabal256.update(data, i + bufferOffset, HASH_CAP);
                shabal256.digest(data, i - HASH_SIZE + bufferOffset, HASH_SIZE);
            }
        } catch (DigestException e) {
            throw new RuntimeException(e);
        }
        shabal256.update(data, bufferOffset, PLOT_SIZE);
        shabal256.update(baseData);
        byte[] finalHash = shabal256.digest();
        for (int i = 0, j = 0; i < PLOT_SIZE; i++, j++) {
            if (j == 32) j = 0;
            data[bufferOffset + i] = (byte) (data[bufferOffset + i] ^ finalHash[j]);
        }

        // PoC2 Rearrangement
        if (pocVersion == 2) {
            byte[] hashBuffer = new byte[HASH_SIZE];
            int revPos = PLOT_SIZE - HASH_SIZE; // Start at second hash in last scoop
            for (int pos = 32; pos < (PLOT_SIZE / 2); pos += 64) { // Start at second hash in first scoop
                System.arraycopy(data, pos + bufferOffset, hashBuffer, 0, HASH_SIZE); // Copy low scoop second hash to buffer
                System.arraycopy(data, revPos + bufferOffset, data, pos + bufferOffset, HASH_SIZE); // Copy high scoop second hash to low scoop second hash
                System.arraycopy(hashBuffer, 0, data, revPos + bufferOffset, HASH_SIZE); // Copy buffer to high scoop second hash
                revPos -= 64; // move backwards
            }
        }
    }

    public MiningPlot(MessageDigest shabal256, long addr, long nonce, int pocVersion) {
        this(shabal256, addr, nonce, pocVersion, new byte[PLOT_SIZE], 0);
    }

    public byte[] getScoop(int pos) {
        return Arrays.copyOfRange(data, pos * SCOOP_SIZE + dataOffset, (pos + 1) * SCOOP_SIZE + dataOffset);
    }

    public void hashScoop(MessageDigest shabal256, int pos) {
        shabal256.update(data, pos * SCOOP_SIZE + dataOffset, SCOOP_SIZE);
    }
}
