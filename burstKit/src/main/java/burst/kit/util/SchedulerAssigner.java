package burst.kit.util;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface SchedulerAssigner {
    <T> Single<T> assignSchedulers(Single<T> source);
    <T> Observable<T> assignSchedulers(Observable<T> source);
}
