package burst.kit.util;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

@SuppressWarnings("squid:S00100")
public interface LibShabal extends Library {
    void shabal_init();
    Pointer shabal256_new();
    void shabal256_update(Pointer shabal, byte[] data, int data_length);
    void shabal256_digest(Pointer shabal, byte[] buffer, int buffer_len, int offset);
    void shabal256_destroy(Pointer shabal);
}
