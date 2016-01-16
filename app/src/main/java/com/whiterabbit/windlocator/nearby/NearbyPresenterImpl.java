package com.whiterabbit.windlocator.nearby;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.storage.WeatherFacade;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NearbyPresenterImpl implements NearbyPresenter {
    WeatherFacade mWeatherClient;
    private NearbyView mView;
    private Subscription mDiskSubscription;
    private Subscription mProgressSubscription;

    private Observable<WeatherResults> mDiskObservable;
    private Context mContext;

    public NearbyPresenterImpl(NearbyView view, WeatherFacade weather, Context context) {
        mContext = context;
        mWeatherClient = weather;
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
        // Todo se l'ultima volta e' tanto tempo fa, forza update
    }

    private boolean hasLocationPermission() {
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {
            return true;
        }

        return (mContext.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION")
                == PackageManager.PERMISSION_GRANTED) ||
                (mContext.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION")
                == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void update() {
        if (hasLocationPermission()) {
            mView.setProgress(true);
            fetchUpdates();
        } else {
            askForPermission();
        }
    }

    private void fetchUpdates() {
        Observable<String> progressObservable = mWeatherClient.updateLocalWeather();
        mProgressSubscription = progressObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> mView.setProgress(false),
                        e -> { Log.d("RX", "There has been an error" + e.getMessage());
                            mView.setProgress(false);
                        },
                        () -> mView.setProgress(false));
    }
}
