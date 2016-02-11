package com.whiterabbit.windlocator.detail;

import com.whiterabbit.windlocator.model.Weather;

/**
 * Created by fedepaol on 11/02/16.
 */
public class DetailPresenterImpl implements DetailPresenter {
    private DetailView mView;

    public DetailPresenterImpl(DetailView mView) {
        this.mView = mView;
    }

    @Override
    public void setWeather(Weather w) {
        mView.showWeather(w);
    }
}
