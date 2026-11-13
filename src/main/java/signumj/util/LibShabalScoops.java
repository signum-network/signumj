package signumj.util;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 * Batched scoop creation from libshabal 1.5+.
 * <p>
 * Registered separately from {@link LibShabal}: JNA binds all native methods of a class at once, so
 * an older libshabal without this function would otherwise disable every native function.
 */
public final class LibShabalScoops {
    public static final Throwable LOAD_ERROR;

    static {
        Throwable loadError = LibShabal.LOAD_ERROR; // also runs shabal_init()
        if (loadError == null) {
            try {
                Native.register(LibShabalScoops.class, NativeLibrary.getInstance("shabal"));
            } catch (Throwable error) {
                // must not escape, callers fall back to create_scoop
                loadError = error;
            }
        }
        LOAD_ERROR = loadError;
    }

    private LibShabalScoops() {
    }

    /**
     * Writes scoop {@code scoops[i]} of nonce {@code nonces[i]} of account {@code accountIds[i]} to
     * {@code scoopBuffer[scoopBufferOffset + i * 64]}, using SIMD instructions where available.
     *
     * @return 0 on success, -1 if a scoop is not below 4096, -2 if the SIMD code failed (memory
     *     allocation), -3 for null pointers
     */
    public static native int create_scoops(long[] accountIds, long[] nonces, int[] scoops, int count,
                                           byte pocVersion, byte[] scoopBuffer, int scoopBufferOffset);
}
