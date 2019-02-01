package burst.kit.service.impl;

import burst.kit.util.SchedulerAssigner;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public final class DefaultSchedulerAssigner implements SchedulerAssigner {
    @Override
    public <T> Single<T> assignSchedulers(Single<T> source) {
        return source.subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Observable<T> assignSchedulers(Observable<T> source) {
        return source.subscribeOn(Schedulers.io());
    }
}
