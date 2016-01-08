package com.whiterabbit.windlocator.nearby;


import com.whiterabbit.windlocator.nearby.NearbyPresenter;
import com.whiterabbit.windlocator.nearby.NearbyPresenterImpl;
import com.whiterabbit.windlocator.nearby.NearbyView;


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
