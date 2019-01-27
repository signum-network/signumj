package burst.kit.test;

import burst.kit.entity.response.BRSResponse;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SingleTestUtils {
    public static <T> T testSingle(Single<T> single) {
        assertNotNull(single);
        TestObserver<T> observer = single.test();
        assertTrue(observer.awaitTerminalEvent());
        observer.assertNoErrors();
        T object = observer.values().get(0);
        assertNotNull(object);
        if (object instanceof BRSResponse) {
            assertFalse(((BRSResponse) object).getErrorDescription(), ((BRSResponse) object).hasError());
        }
        return object;
    }
}
