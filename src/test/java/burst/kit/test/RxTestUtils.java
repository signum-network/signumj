package burst.kit.test;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RxTestUtils {
    public static <T> T testSingle(Single<T> single) {
        assertNotNull("Single itself was null", single);
        // If you don't do this it blocks trying to do the operation and therefore can't observe the results
        single = single.subscribeOn(Schedulers.io());
        TestObserver<T> observer = single.test();
        assertTrue("Observer failed to reach terminal event", observer.awaitTerminalEvent());
        observer.assertNoErrors();
        T object = observer.values().get(0);
        assertNotNull("Returned object was null - RxJava should not allow this", object);
        return object;
    }

    public static <T> List<T> testObservable(Observable<T> observable, int awaitCount) {
        assertNotNull(observable);
        if (awaitCount == 1) {
            return Collections.singletonList(testSingle(observable.firstOrError()));
        }
        // If you don't do this it blocks trying to do the operation and therefore can't observe the results
        observable = observable.subscribeOn(Schedulers.io());
        TestObserver<T> observer = observable.test();
        observer.awaitCount(awaitCount);
        observer.assertNoErrors();
        assertEquals(awaitCount, observer.valueCount());
        observer.values().forEach(Assert::assertNotNull);
        return observer.values();
    }
}
