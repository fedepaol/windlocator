package com.whiterabbit.windlocator.schedule;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RealSchedulersProvider implements SchedulersProvider {
    @Override
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }
}
