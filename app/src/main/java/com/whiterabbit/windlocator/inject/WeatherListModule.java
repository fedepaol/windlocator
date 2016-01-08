package com.whiterabbit.windlocator.inject;


import com.whiterabbit.windlocator.views.NearbyPresenter;
import com.whiterabbit.windlocator.views.NearbyPresenterContract;
import com.whiterabbit.windlocator.views.NearbyViewContract;


import dagger.Module;
import dagger.Provides;

@Module
public class WeatherListModule {
    private NearbyViewContract mView;
    public WeatherListModule(NearbyViewContract view) {
        mView = view;
    }

    @Provides
    public NearbyPresenterContract provideNearbyPresenter() {
        return new NearbyPresenter(mView);
    }
}
