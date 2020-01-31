package burst.kit.crypto.hash.shabal;

import burst.kit.util.LibShabalLoader;
import com.sun.jna.Pointer;

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

    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {

    }

    @Override
    protected byte[] engineDigest() {
        return new byte[0];
    }

    @Override
    protected void engineReset() {

    }
}
