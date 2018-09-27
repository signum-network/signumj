package burst.kit.util;

import io.reactivex.Single;

public interface SchedulerAssigner {
    <T> Single<T> assignSchedulers(Single<T> source);
}
