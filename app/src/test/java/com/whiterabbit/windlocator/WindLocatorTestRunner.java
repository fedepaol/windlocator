package com.whiterabbit.windlocator;

import junit.textui.TestRunner;

import org.junit.runners.model.InitializationError;

import rx.Scheduler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

public class WindLocatorTestRunner extends TestRunner {
    public WindLocatorTestRunner(Class<?> testClass) throws InitializationError {

        RxJavaTestPlugins.resetPlugins();
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        });
    }
}
