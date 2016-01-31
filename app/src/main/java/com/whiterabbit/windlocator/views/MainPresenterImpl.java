package com.whiterabbit.windlocator.views;

import android.content.Context;
import android.location.Address;

import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;
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

    public MainPresenterImpl(MainView view, WeatherFacade f, Context c) {
        mView = view;
        mContext = c;
        mFacade = f;
    }

    @Override
    public void setAddressObservable(Observable<String> observable) {
        mAddressObservable = observable;
    }

    @Override
    public void onPause() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    private void subscribe() {
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
        // TODO dire all'activity di cercare e far vedere il dettaglio
    }

    @Override
    public void onAddressSelected(Address a) {
        // TODO dire all'activity di cercare e far vedere il dettaglio
    }
}
