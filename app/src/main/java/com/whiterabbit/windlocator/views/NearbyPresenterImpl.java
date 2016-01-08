package com.whiterabbit.windlocator.views;


import android.util.Log;

import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NearbyPresenterImpl implements NearbyPresenter {
    @Inject WeatherFacade mWeatherClient;
    private NearbyView mView;
    private Subscription mDiskSubscription;
    private Subscription mProgressSubscription;

    private Observable<WeatherResults> mDiskObservable;

    public NearbyPresenterImpl(NearbyView view) {
        mView = view;
        mDiskObservable = mWeatherClient.getDatabaseObservable();
        mDiskObservable.unsubscribeOn(Schedulers.computation());
    }

    @Override
    public void onPause() {
        if (mDiskSubscription != null) {
            mDiskSubscription.unsubscribe();
        }
        if (mProgressSubscription != null) {
            mProgressSubscription.unsubscribe();
        }
    }

    @Override
    public void onResume() {
        mDiskSubscription = mDiskObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe(l -> {
                                mView.updateWeatherResult(l);
                            });
    }

    @Override
    public void update() {
        mView.setProgress(true);
        fetchUpdates();
    }

    private void fetchUpdates() {
        Observable<String> progressObservable = mWeatherClient.updateLocalWeather();
        mProgressSubscription = progressObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {},
                        e -> { Log.d("RX", "There has been an error");
                            mView.setProgress(false);
                        },
                        () -> mView.setProgress(false));
    }
}
