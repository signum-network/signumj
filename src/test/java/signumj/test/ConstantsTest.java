package signumj.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import signumj.Constants;

@RunWith(JUnit4.class)
public class ConstantsTest {

    @Test
    public void version_GivenClassesNotLoadedFromReleaseJar_IsDev() {
        // the tests run from build/classes, which has no manifest; release jars carry the tag (see release.yml)
        assertEquals("dev", Constants.VERSION);
    }
}
