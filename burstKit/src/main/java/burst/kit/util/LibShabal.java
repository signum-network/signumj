package burst.kit.util;

import com.sun.jna.Native;

@SuppressWarnings("squid:S1181")
public class LibShabal {
    public static final LibShabalApi INSTANCE;
    public static final Throwable LOAD_ERROR;
    public static final String VERSION;

    static {
        LibShabalApi instance = null;
        Throwable loadError = null;
        String version = "";
        try {
            instance = Native.load("libshabal", LibShabalApi.class);
            instance.shabal_init();
            version = instance.libshabal_version();
        } catch (Throwable error) {
            // This block cannot allow any error to escape because this will cause other classes to fail to load,
            // and we must be able to run with no native optimizations at all.
            loadError = error;
        }
        INSTANCE = instance;
        LOAD_ERROR = loadError;
        VERSION = version;
    }
}
