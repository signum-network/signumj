package burst.kit.util;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

@SuppressWarnings("squid:S00100")
public interface LibShabal extends Library {
    void shabal_init();
    Pointer shabal256_new();
    void shabal256_update(Pointer shabal, byte[] data, int offset, int length);
    void shabal256_digest(Pointer shabal, byte[] buffer, int offset);
    void shabal256_reset(Pointer shabal);
    void shabal256_destroy(Pointer shabal);

    void create_plots(long account_id, long start_nonce, long nonce_count, byte poc_version, byte[] plot_buffer, int plot_buffer_offset);
    void create_plot(long account_id, long nonce, byte poc_version, byte[] plot_buffer, int plot_buffer_offset);
    void create_scoop(long account_id, long start_nonce, int scoop, byte poc_version, byte[] scoop_buffer, int scoop_buffer_offset);
}
