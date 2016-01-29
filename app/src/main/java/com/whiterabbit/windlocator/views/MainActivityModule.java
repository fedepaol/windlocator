package com.whiterabbit.windlocator.views;


import android.content.Context;

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
    public MainPresenter provideMainPresenter(MainView view,
                                              WeatherFacade facade,
                                              Context c) {
        return new MainPresenterImpl(view, facade, c);
    }
}
