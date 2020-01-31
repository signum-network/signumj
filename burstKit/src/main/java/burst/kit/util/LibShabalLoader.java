package burst.kit.util;

import com.sun.jna.Native;

public class LibShabalLoader {
    private static LibShabal INSTANCE;

    public static synchronized void ensureLoaded() {
        if (INSTANCE != null) return;

        try {
            INSTANCE = Native.load("libshabal", LibShabal.class);
        } catch (UnsatisfiedLinkError error) {
            throw new RuntimeException("Could not load LibShabal", error);
        }
        System.out.println("Success loading!");
        INSTANCE.shabal_init();
    }

    public static LibShabal getInstance() {
        return INSTANCE;
    }
}
