package burst.kit.test;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RxTestUtils {
    public static <T> T testSingle(Single<T> single) {
        assertNotNull(single);
        TestObserver<T> observer = single.test();
        assertTrue(observer.awaitTerminalEvent());
        observer.assertNoErrors();
        T object = observer.values().get(0);
        assertNotNull(object);
        return object;
    }

    public static <T> List<T> testObservable(Observable<T> observable, int awaitCount) {
        assertNotNull(observable);
        TestObserver<T> observer = observable.test();
        observer.awaitCount(awaitCount);
        observer.assertNoErrors();
        assertEquals(awaitCount, observer.valueCount());
        observer.values().forEach(Assert::assertNotNull);
        return observer.values();
    }
}
