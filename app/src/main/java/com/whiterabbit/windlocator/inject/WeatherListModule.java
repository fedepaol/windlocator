package com.whiterabbit.windlocator.inject;


import com.whiterabbit.windlocator.views.NearbyPresenter;
import com.whiterabbit.windlocator.views.NearbyPresenterImpl;
import com.whiterabbit.windlocator.views.NearbyView;


import dagger.Module;
import dagger.Provides;

@Module
public class WeatherListModule {
    private NearbyView mView;
    public WeatherListModule(NearbyView view) {
        mView = view;
    }

    @Provides
    public NearbyPresenter provideNearbyPresenter() {
        return new NearbyPresenterImpl(mView);
    }
}
