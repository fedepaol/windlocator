package com.whiterabbit.windlocator.storage;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

public class ObservableDbHelper {
    private PublishSubject<WeatherResults> mSubject = PublishSubject.create();
    @Inject WeatherDbHelperExt mDbHelper;

    @Inject
    public ObservableDbHelper() {
    }

    public Observable<WeatherResults> getNearbyObservable() {
        Observable<WeatherResults> firstTimeObservable =
                Observable.fromCallable(this::getLastNearby);
        return firstTimeObservable.concatWith(mSubject);
    }

    WeatherResults getLastNearby() {
        return mDbHelper.getLastNearbyWeather();
    }

    public void insertNearbyWeather(List<Weather> nearby) {
        mDbHelper.open();

        mDbHelper.removeAllNearbyWeather();
        for (Weather localWeather : nearby) {
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
        mDbHelper.close();

        Weather[] weathers = new Weather[nearby.size()];
        nearby.toArray(weathers);       // this could be more efficient
        WeatherResults results = new WeatherResults(weathers);
        mSubject.onNext(results);
    }
}
