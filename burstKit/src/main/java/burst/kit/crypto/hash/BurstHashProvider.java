package burst.kit.crypto.hash;

import burst.kit.crypto.hash.shabal.Shabal256;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BurstHashProvider extends Provider {

    private static final String NAME = "BurstHashProvider";
    private static final double VERSION = 1.0;
    private static final String INFO = "A Shabal-256 MessageDigest provider";

    public static void init() {
        Security.addProvider(new BurstHashProvider());
    }

    private BurstHashProvider() {
        super(NAME, VERSION, INFO);
        putService(new ShabalService(this));
        putService(new RipeMDService(this));
    }

    private static class ShabalService extends Service {
        private ShabalService(Provider provider) {
            super(provider, "MessageDigest", Shabal256.ALGORITHM, Shabal256.class.toString(), Shabal256.ALIASES, Collections.emptyMap());
        }

        @Override
        public Object newInstance(Object constructorParameter) {
            return new Shabal256();
        }
    }

    private static class RipeMDService extends Service {
        private static final String ALGORITHM = "RIPEMD-160";
        private static final List<String> ALIASES = Arrays.asList("RIPEMD160", "Ripemd160", "Ripemd-160", "ripemd160", "ripemd-160", "ripemd", "Ripemd", "RIPEMD");

        private RipeMDService(Provider provider) {
            super(provider, "MessageDigest", ALGORITHM, RIPEMD160.Digest.class.toString(), ALIASES, Collections.emptyMap());
        }

        @Override
        public Object newInstance(Object constructorParameter) {
            return new RIPEMD160.Digest();
        }
    }
}
