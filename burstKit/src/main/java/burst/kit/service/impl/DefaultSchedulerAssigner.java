package burst.kit.service.impl;

import burst.kit.util.SchedulerAssigner;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DefaultSchedulerAssigner implements SchedulerAssigner {
    @Override
    public <T> Single<T> assignSchedulers(Single<T> source) {
        return source.subscribeOn(Schedulers.io());
    }
}
