package com.whiterabbit.windlocator.inject;

import android.content.Context;
import android.content.SharedPreferences;

import com.whiterabbit.windlocator.rest.OpenWeatherClient;
import com.whiterabbit.windlocator.storage.WeatherDbHelperExt;

import dagger.Module;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

import static org.mockito.Mockito.mock;

/**
 * Created by fedepaol on 28/06/15.
 */
@Module
public class MockModule extends ApplicationModule {
    ReactiveLocationProvider mLocationProvider;
    OpenWeatherClient mWeatherClient;
    WeatherDbHelperExt mDbHelper;

    public MockModule(OpenWeatherClient c, WeatherDbHelperExt dbHelper) {
        super(null);
        mWeatherClient = c;
        mDbHelper = dbHelper;
    }

    @Override
    SharedPreferences provideSharedPrefs() {
        return null;
    }

    @Override
    OpenWeatherClient provideWeatherClient() {
        return mWeatherClient;
    }

    @Override
    ReactiveLocationProvider provideLocation() {
        return mLocationProvider;
    }

    @Override
    Context provideContext() {
        return mock(Context.class);
    }

    @Override
    WeatherDbHelperExt provideDbHelper() {
        return mDbHelper;
    }

    public void setmLocationProvider(ReactiveLocationProvider mLocationProvider) {
        this.mLocationProvider = mLocationProvider;
    }
}
