package com.whiterabbit.windlocator.views;

import android.content.Context;

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
    private Subscription mEventSubscription;
    private Observable<String> mAddressObservable;
    private Observable<SearchViewQueryTextEvent> mEventObservable;

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

        if (mEventSubscription != null)
            mEventSubscription.unsubscribe();
    }

    private void subscribe() {
        if (mAddressObservable != null) {
            mSubscription = mAddressObservable
                    .flatMap(a -> mFacade.getAddressWeatherObservable(a.toString(), mContext))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(l -> mView.showAddresses(l));
        }
        if (mEventObservable != null) {
            mEventSubscription = mEventObservable.subscribe(this::handleSearchEvent);
        }
    }

    @Override
    public void onResume() {
        subscribe();
    }

    @Override
    public void setSearchViewObservable(Observable<SearchViewQueryTextEvent> observable) {
        mEventObservable = observable;
        subscribe();
    }

    private void handleSearchEvent(SearchViewQueryTextEvent event) {
        if (event.isSubmitted()) {
            // TODO dire all'activity di far vedere il dettaglio
        }
    }
}
