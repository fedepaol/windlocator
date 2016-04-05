package com.whiterabbit.windlocator.detail;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.weatherclient.OpenWeatherClient;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fedepaol on 11/02/16.
 */
public class DetailPresenterImpl implements DetailPresenter {
    private final DetailView mView;
    private final OpenWeatherClient mClient;

    public DetailPresenterImpl(DetailView mView, OpenWeatherClient client) {
        this.mView = mView;
        mClient = client;
    }

    @Override
    public void setWeather(Weather w) {
        mView.showWeather(w);
        mView.showLoadingForecasts(true);
        mClient.getForecasts(w.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::showForecasts,
                        e -> {
                            mView.showLoadingForecasts(false);
                            mView.showLoadingForecastsError();
                        },
                        () -> mView.showLoadingForecasts(false));
    }


}
