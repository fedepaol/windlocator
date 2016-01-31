package com.whiterabbit.windlocator.views;

import android.content.Context;
import android.location.Address;
import android.util.Log;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fedepaol on 29/01/16.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mView;
    private Context mContext;
    private WeatherFacade mFacade;
    private Subscription mSubscription;
    private Observable<String> mAddressObservable;
    private LocationWeatherFinder mWeatherFinder;

    public MainPresenterImpl(MainView view,
                             WeatherFacade f,
                             LocationWeatherFinder finder,
                             Context c) {
        mView = view;
        mContext = c;
        mFacade = f;
        mWeatherFinder = finder;
    }

    @Override
    public void setAddressObservable(Observable<String> observable) {
        mAddressObservable = observable;
        subscribe();
    }

    @Override
    public void onPause() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    private void subscribe() {
        // not null check before subscribe might get called before
        // observable valorization
        if (mAddressObservable != null) {
            mSubscription = mAddressObservable
                    .flatMap(a -> mFacade.getAddressWeatherObservable(a, mContext))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(l -> mView.showAddresses(l));
        }
    }

    @Override
    public void onResume() {
        subscribe();
    }

    @Override
    public void onQueryPressed(String query) {
        // TODO Show progress
        mWeatherFinder.getAddressWeatherObservable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDetailWeatherReceived);
        // TODO dire all'activity di cercare e far vedere il dettaglio
    }

    @Override
    public void onAddressSelected(Address a) {
        // TODO Show progress
        mWeatherFinder.getAddressWeatherObservable(a)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDetailWeatherReceived);
        // TODO dire all'activity di cercare e far vedere il dettaglio
    }

    private void onDetailWeatherReceived(Weather w) {
        Log.d("FEDE", w.getCityName());
    }


}
