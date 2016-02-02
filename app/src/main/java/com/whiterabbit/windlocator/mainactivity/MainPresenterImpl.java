package com.whiterabbit.windlocator.mainactivity;

import android.content.Context;
import android.location.Address;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.schedule.SchedulersProvider;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import rx.Observable;
import rx.Subscription;

/**
 * Created by fedepaol on 29/01/16.
 */
public class MainPresenterImpl implements MainPresenter {
    private final MainView mView;
    private final Context mContext;
    private final WeatherFacade mFacade;
    private Subscription mSubscription;
    private Observable<String> mAddressObservable;
    private final LocationWeatherFinder mWeatherFinder;
    private final SchedulersProvider mSchedulers;

    public MainPresenterImpl(MainView view,
                             WeatherFacade f,
                             LocationWeatherFinder finder,
                             SchedulersProvider schedulers,
                             Context c) {
        mView = view;
        mContext = c;
        mFacade = f;
        mWeatherFinder = finder;
        mSchedulers = schedulers;
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
                    .observeOn(mSchedulers.provideMainThreadScheduler())
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
                .subscribeOn(mSchedulers.provideBackgroundScheduler())
                .observeOn(mSchedulers.provideMainThreadScheduler())
                .subscribe(this::onDetailWeatherReceived);
    }

    @Override
    public void onAddressSelected(Address a) {
        // TODO Show progress
        mWeatherFinder.getAddressWeatherObservable(a)
                .subscribeOn(mSchedulers.provideBackgroundScheduler())
                .observeOn(mSchedulers.provideMainThreadScheduler())
                .subscribe(this::onDetailWeatherReceived);
    }

    private void onDetailWeatherReceived(Weather w) {
        mView.goToWeatherDetail(w);
    }
}
