package burst.kit.test.crypto.hash;

import burst.kit.crypto.hash.shabal.Shabal256Native;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.security.MessageDigest;

@RunWith(JUnit4.class)
public class Shabal256NativeImplTest extends Shabal256Test {
    @Override
    protected MessageDigest getShabal256() {
        return new Shabal256Native();
    }
}
