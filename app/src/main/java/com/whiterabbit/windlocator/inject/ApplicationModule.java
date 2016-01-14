package com.whiterabbit.windlocator.inject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.whiterabbit.windlocator.rest.OpenWeatherClient;
import com.whiterabbit.windlocator.storage.WeatherDbHelperExt;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

/**
 * Created by fedepaol on 28/06/15.
 */
@Module
public class ApplicationModule {
    private Application mApp;

    public ApplicationModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(mApp);
    }

    @Provides
    @Singleton
    OpenWeatherClient provideWeatherClient() {
        return new OpenWeatherClient();
    }

    @Provides
    @Singleton
    ReactiveLocationProvider provideLocation() {
        return new ReactiveLocationProvider(mApp.getApplicationContext());
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    WeatherDbHelperExt provideDbHelper() {
        return new WeatherDbHelperExt(mApp.getApplicationContext());
    }
}
