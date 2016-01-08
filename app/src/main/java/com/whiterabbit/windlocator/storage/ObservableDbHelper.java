package com.whiterabbit.windlocator.storage;

import android.content.Context;

import com.whiterabbit.windlocator.model.WeatherResults;

import rx.Observable;
import rx.subjects.PublishSubject;

public class ObservableDbHelper {
    private PublishSubject<WeatherResults> mSubject = PublishSubject.create();
    private WeatherDbHelperExt mDbHelper;

    public ObservableDbHelper(Context c) {
        mDbHelper = new WeatherDbHelperExt(c);
    }

    public Observable<WeatherResults> getNearbyObservable() {
        Observable<WeatherResults> firstTimeObservable =
                Observable.fromCallable(this::getLastNearby);
        return firstTimeObservable.concatWith(mSubject);
    }

    WeatherResults getLastNearby() {
        return mDbHelper.getLastNearbyWeather();
    }
}
