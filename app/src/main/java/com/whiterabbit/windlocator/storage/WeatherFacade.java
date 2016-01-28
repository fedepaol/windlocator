package com.whiterabbit.windlocator.storage;

import com.google.android.gms.location.LocationRequest;
import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.rest.OpenWeatherClient;

import java.util.List;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by paolinelli on 1/8/2016.
 */
public class WeatherFacade {
    @Inject ReactiveLocationProvider mLocationProvider;
    @Inject OpenWeatherClient mRestClient;
    @Inject ObservableDbHelper mDatabase;

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

        Observable<WeatherResults> observable =
                mLocationProvider.getUpdatedLocation(request)
                        .first()
                        .subscribeOn(Schedulers.io())
                        .flatMap(location -> mRestClient.getNearbyWeather(location.getLatitude(),
                                                                          location.getLongitude()));

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(n -> mDatabase.insertNearbyWeather(n),
                        requestSubject::onError,
                        requestSubject::onCompleted);

        return requestSubject.asObservable();
    }
}
