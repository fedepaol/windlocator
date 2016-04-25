package com.whiterabbit.windlocator.storage;

import android.content.Context;
import android.location.Address;

import com.google.android.gms.common.data.DataBufferObserver;
import com.google.android.gms.location.LocationRequest;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.weatherclient.OpenWeatherClient;
import com.whiterabbit.windlocator.schedule.SchedulersProvider;

import java.util.List;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by paolinelli on 1/8/2016.
 */
public class WeatherFacade {
    @Inject ReactiveLocationProvider mLocationProvider;
    @Inject OpenWeatherClient mRestClient;
    @Inject ObservableDbHelper mDatabase;
    @Inject SchedulersProvider mSchedulers;

    @Inject
    public WeatherFacade() {
    }

    public Observable<WeatherResults> getDatabaseObservable() {
        return mDatabase.getNearbyObservable();
    }

    public Observable<String> updateLocalWeather() {
        BehaviorSubject<String> requestSubject = BehaviorSubject.create();

        LocationRequest request = LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setNumUpdates(1)
                .setInterval(100);

        Observable<List<Weather>> observable =
                mLocationProvider.getUpdatedLocation(request)
                        .first()
                        .observeOn(mSchedulers.provideBackgroundScheduler())
                        .flatMap(location -> mRestClient.getNearbyWeather(location.getLatitude(),
                                                                          location.getLongitude()))
                        .flatMap(w -> Observable.from(w.getWeathers()))
                        .distinct(Weather::getCityName)
                        .toList();

        observable.subscribeOn(mSchedulers.provideBackgroundScheduler())
                .observeOn(mSchedulers.provideBackgroundScheduler())
                .subscribe(n -> mDatabase.insertNearbyWeather(n),
                        requestSubject::onError,
                        requestSubject::onCompleted);

        return requestSubject.asObservable();
    }

    public Observable<List<Address>> getAddressWeatherObservable(String address, Context c) {

        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(c);
        Observable<List<Address>> geocodeObservable = locationProvider
                .getGeocodeObservable(address, 5);
        return geocodeObservable;
    }
}
