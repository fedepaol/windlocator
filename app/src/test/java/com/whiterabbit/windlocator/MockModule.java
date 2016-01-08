package com.whiterabbit.windlocator;

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

import static org.mockito.Mockito.mock;

/**
 * Created by fedepaol on 28/06/15.
 */
@Module
public class MockModule {
    @Provides
    @Singleton
    OpenWeatherClient provideWeatherClient() {
        return new OpenWeatherClient();
    }

    @Provides
    @Singleton
    ReactiveLocationProvider provideLocation() {
        return mock(ReactiveLocationProvider.class);
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mock(Context.class);
    }

    @Provides
    @Singleton
    WeatherDbHelperExt provideDbHelper() {
        return mock(WeatherDbHelperExt.class);
    }
}
