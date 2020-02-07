package burst.kit.crypto.hash.shabal;

import burst.kit.util.LibShabal;
import com.sun.jna.Pointer;

import java.security.DigestException;
import java.security.MessageDigest;

// TODO implement cloneable
/**
 * Using this will throw NPE if the native library is not loaded.
 */
public class Shabal256Native extends MessageDigest {
    private final Pointer nativeObject;

    public Shabal256Native() {
        super(Shabal256.ALGORITHM);
        nativeObject = LibShabal.INSTANCE.shabal256_new();
    }

    @Override
    protected void engineUpdate(byte input) {
        engineUpdate(new byte[input], 0, 1);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        LibShabal.INSTANCE.shabal256_update(nativeObject, input, offset, len);
    }

    @Override
    protected byte[] engineDigest() {
        byte[] buffer = new byte[32];
        LibShabal.INSTANCE.shabal256_digest(nativeObject, buffer, 0);
        return buffer;
    }

    @Override
    protected int engineDigest(byte[] buf, int offset, int len) throws DigestException {
        if (len < 32)
            throw new DigestException("partial digests not returned");
        if (buf.length - offset < 32)
            throw new DigestException("insufficient space in the output buffer to store the digest");

        LibShabal.INSTANCE.shabal256_digest(nativeObject, buf, offset);
        return 32;
    }

    @Override
    protected void finalize() {
        LibShabal.INSTANCE.shabal256_destroy(nativeObject);
    }

    @Override
    protected void engineReset() {
        LibShabal.INSTANCE.shabal256_reset(nativeObject);
    }
}
