package burst.kit.util;

import io.reactivex.Observable;
import io.reactivex.Single;

@Deprecated
public interface SchedulerAssigner { // TODO remove
    <T> Single<T> assignSchedulers(Single<T> source);
    <T> Observable<T> assignSchedulers(Observable<T> source);
}
