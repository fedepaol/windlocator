package com.whiterabbit.windlocator.mainactivity;


import android.content.Context;

import com.whiterabbit.windlocator.schedule.SchedulersProvider;
import com.whiterabbit.windlocator.rest.OpenWeatherClient;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private MainView mView;
    public MainActivityModule(MainView view) {
        mView = view;
    }

    @Provides
    public MainView provideMainView() {
        return mView;
    }

    @Provides
    LocationWeatherFinder provideWeatherFinder(Context c, OpenWeatherClient client) {
        return new LocationWeatherFinder(c, client);
    }

    @Provides
    public MainPresenter provideMainPresenter(MainView view,
                                              WeatherFacade facade,
                                              LocationWeatherFinder finder,
                                              SchedulersProvider schedulers,
                                              Context c) {
        return new MainPresenterImpl(view, facade, finder, schedulers, c);
    }
}
