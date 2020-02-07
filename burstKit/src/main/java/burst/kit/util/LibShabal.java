package burst.kit.util;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;

@SuppressWarnings("squid:S1181")
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
    
    public static native Pointer shabal256_new();
    public static native void shabal256_update(Pointer shabal, byte[] data, int offset, int length);
    public static native void shabal256_digest(Pointer shabal, byte[] buffer, int offset);
    public static native void shabal256_reset(Pointer shabal);
    public static native void shabal256_destroy(Pointer shabal);

    public static native void create_plots(long account_id, long start_nonce, long nonce_count, byte poc_version, byte[] plot_buffer, int plot_buffer_offset);
    public static native void create_plot(long account_id, long nonce, byte poc_version, byte[] plot_buffer, int plot_buffer_offset);
    public static native void create_scoop(long account_id, long start_nonce, int scoop, byte poc_version, byte[] scoop_buffer, int scoop_buffer_offset);
}
