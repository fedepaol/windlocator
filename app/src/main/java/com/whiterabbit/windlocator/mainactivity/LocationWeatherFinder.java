package com.whiterabbit.windlocator.mainactivity;

import android.content.Context;
import android.location.Address;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.weatherclient.OpenWeatherClient;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;

public class LocationWeatherFinder {
    Context mContext;
    OpenWeatherClient mClient;

    @Inject
    public LocationWeatherFinder(Context c, OpenWeatherClient client) {
        mContext = c;
        mClient = client;
    }

    public Observable<Weather> getAddressWeatherObservable(String address) {

        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(mContext);
        Observable<Weather> weatherObservable = locationProvider
                                                .getGeocodeObservable(address, 1)
                            .flatMap(a -> {
                                Address add = a.get(0);
                                return mClient.getNearbyWeather(add.getLatitude(), add.getLongitude());
                            }).flatMap(w -> Observable.just(w.getWeathers()[0]));

        return weatherObservable;
    }

    public Observable<Weather> getAddressWeatherObservable(Address address) {
        Observable<Weather> weatherObservable =
                            mClient.getNearbyWeather(address.getLatitude(), address.getLongitude()).
                            flatMap(w -> Observable.just(w.getWeathers()[0]));
        return weatherObservable;
    }
}
