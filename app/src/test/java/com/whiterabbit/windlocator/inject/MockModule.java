package com.whiterabbit.windlocator.inject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.whiterabbit.windlocator.inject.ApplicationModule;
import com.whiterabbit.windlocator.rest.OpenWeatherClient;
import com.whiterabbit.windlocator.storage.WeatherDbHelper;
import com.whiterabbit.windlocator.storage.WeatherDbHelperExt;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

import static org.mockito.Mockito.mock;

/**
 * Created by fedepaol on 28/06/15.
 */
@Module
public class MockModule extends ApplicationModule {


    public MockModule() {
        super(null);
    }

    @Override
    SharedPreferences provideSharedPrefs() {
        return null;
    }

    @Override
    OpenWeatherClient provideWeatherClient() {
        return mock(OpenWeatherClient.class);
    }

    @Override
    ReactiveLocationProvider provideLocation() {
        return mock(ReactiveLocationProvider.class);
    }

    @Override
    Context provideContext() {
        return mock(Context.class);
    }

    @Override
    WeatherDbHelperExt provideDbHelper() {
        return mock(WeatherDbHelperExt.class);
    }
}
