package com.whiterabbit.windlocator.nearby;


import com.whiterabbit.windlocator.storage.WeatherFacade;


import dagger.Module;
import dagger.Provides;

@Module
public class WeatherListModule {
    private NearbyView mView;
    public WeatherListModule(NearbyView view) {
        mView = view;
    }

    @Provides
    public NearbyView provideNearbyView() {
        return mView;
    }

    @Provides
    public NearbyPresenter provideNearbyPresenter(NearbyView view, WeatherFacade facade) {
        return new NearbyPresenterImpl(view, facade);
    }
}
