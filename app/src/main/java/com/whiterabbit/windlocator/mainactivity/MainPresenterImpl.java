package com.whiterabbit.windlocator.mainactivity;

import android.content.Context;
import android.location.Address;

import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.schedule.SchedulersProvider;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by fedepaol on 29/01/16.
 */
public class MainPresenterImpl implements MainPresenter {
    private final MainView mView;
    private final Context mContext;
    private final WeatherFacade mFacade;
    private CompositeSubscription mSubscription;
    private Observable<SearchViewQueryTextEvent> mAddressObservable;
    private final LocationWeatherFinder mWeatherFinder;
    private final SchedulersProvider mSchedulers;
    private PublishSubject<SearchViewQueryTextEvent> mSearchSubject;

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
    public void setAddressObservable(Observable<SearchViewQueryTextEvent> observable) {
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
            mSearchSubject = PublishSubject.create();
            mAddressObservable.subscribe(e -> mSearchSubject.onNext(e));

            mSubscription = new CompositeSubscription();
            mSubscription.add(mSearchSubject
                    .flatMap(a -> mFacade.getAddressWeatherObservable(a.queryText().toString(), mContext))
                    .observeOn(mSchedulers.provideMainThreadScheduler())
                    .subscribe(mView::showAddresses));

            mSubscription.add(mSearchSubject.
                        filter(SearchViewQueryTextEvent::isSubmitted)
                        .map(q -> q.queryText().toString())
                        .observeOn(mSchedulers.provideMainThreadScheduler())
                        .subscribe(this::onQueryPressed));

        }
    }

    @Override
    public void onResume() {
        subscribe();
    }

    @Override
    public void onAddressSelected(Address a) {
        mView.setProgress(true);
        mWeatherFinder.getAddressWeatherObservable(a)
                .subscribeOn(mSchedulers.provideBackgroundScheduler())
                .observeOn(mSchedulers.provideMainThreadScheduler())
                .subscribe(this::onDetailWeatherReceived);
    }

    private void onDetailWeatherReceived(Weather w) {
        mView.setProgress(false);
        mView.goToWeatherDetail(w);
    }

    private void onQueryPressed(String query) {
        mView.setProgress(true);
        mWeatherFinder.getAddressWeatherObservable(query)
                        .subscribeOn(mSchedulers.provideBackgroundScheduler())
                        .observeOn(mSchedulers.provideMainThreadScheduler())
                        .subscribe(this::onDetailWeatherReceived);
    }
}
