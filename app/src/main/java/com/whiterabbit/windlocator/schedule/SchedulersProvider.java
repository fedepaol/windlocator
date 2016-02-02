package com.whiterabbit.windlocator.schedule;

import rx.Scheduler;

public interface SchedulersProvider {
    Scheduler provideMainThreadScheduler();

    Scheduler provideBackgroundScheduler();
}
