package com.whiterabbit.windlocator.storage;

import android.content.Context;

import com.whiterabbit.windlocator.model.Weather;
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

    public void insertNearbyWeather(WeatherResults nearby) {
        Weather[] weathers = nearby.getWeathers();
        mDbHelper.removeAllNearbyWeather();
        for (int i = 0; i < weathers.length; i++) {
            Weather localWeather = weathers[i];
            mDbHelper.addNearbyWeather(localWeather.getTime(),
                                        localWeather.getWindSpeed(),
                                        localWeather.getWindDegree(),
                                        localWeather.getTemperature(),
                                        localWeather.getCityName(),
                                        localWeather.getLatitude(),
                                        localWeather.getLongitude(),
                                        localWeather.getWeatherEnum(),
                                        localWeather.getId());
        }
    }
}
