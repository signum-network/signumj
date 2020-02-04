package burst.kit.crypto.hash.shabal;

import burst.kit.util.LibShabalLoader;
import com.sun.jna.Pointer;

import java.security.DigestException;
import java.security.MessageDigest;

// TODO implement cloneable
public class Shabal256Native extends MessageDigest {
    private final Pointer nativeObject;

    public Shabal256Native() {
        super(Shabal256.ALGORITHM);
        nativeObject = LibShabalLoader.getInstance().shabal256_new();
    }

    @Override
    protected void engineUpdate(byte input) {
        engineUpdate(new byte[input], 0, 1);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        LibShabalLoader.getInstance().shabal256_update(nativeObject, input, offset, len);
    }

    @Override
    protected byte[] engineDigest() {
        byte[] buffer = new byte[32];
        LibShabalLoader.getInstance().shabal256_digest(nativeObject, buffer, 0);
        return buffer;
    }

    @Override
    protected int engineDigest(byte[] buf, int offset, int len) throws DigestException {
        if (len < 32)
            throw new DigestException("partial digests not returned");
        if (buf.length - offset < 32)
            throw new DigestException("insufficient space in the output buffer to store the digest");

        LibShabalLoader.getInstance().shabal256_digest(nativeObject, buf, offset);
        return 32;
    }

    @Override
    protected void finalize() {
        LibShabalLoader.getInstance().shabal256_destroy(nativeObject);
    }

    @Override
    protected void engineReset() {
        LibShabalLoader.getInstance().shabal256_reset(nativeObject);
    }
}
