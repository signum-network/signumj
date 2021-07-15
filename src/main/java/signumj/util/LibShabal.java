package signumj.util;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class LibShabal {
    public static final Throwable LOAD_ERROR;
    public static final String VERSION;

    static {
        Throwable loadError = null;
        String version = "";
        try {
            Native.register(NativeLibrary.getInstance("shabal"));
            shabal_init();
            version = libshabal_version();
        } catch (Throwable error) {
            // This block cannot allow any error to escape because this will cause other classes to fail to load,
            // and we must be able to run with no native optimizations at all.
            loadError = error;
        }
        LOAD_ERROR = loadError;
        VERSION = version;
    }

    private static native String libshabal_version();
    private static native void shabal_init();

    public static native void create_plots(long account_id, long start_nonce, long nonce_count, byte poc_version, byte[] plot_buffer, int plot_buffer_offset);
    public static native void create_plot(long account_id, long nonce, byte poc_version, byte[] plot_buffer, int plot_buffer_offset);
    public static native void create_scoop(long account_id, long start_nonce, int scoop, byte poc_version, byte[] scoop_buffer, int scoop_buffer_offset);

    public static native void curve25519_get_public_key(byte[] private_key, byte[] public_key_buffer);
    public static native void curve25519_get_shared_secret(byte[] private_key, byte[] public_key, byte[] shared_secret_buffer);
    public static native void curve25519_sign(byte[] private_key, byte[] message_sha256, byte[] signature_buffer);
    public static native byte curve25519_verify(byte[] public_key, byte[] signature, byte[] message_sha256, byte enforce_canonical);
    
    private static native long shabal_findBestDeadline(byte[] scoops, long numScoops, byte[] gensig);
}
