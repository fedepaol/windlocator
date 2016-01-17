package com.whiterabbit.windlocator.inject;

/**
 * Created by fedepaol on 28/06/15.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.whiterabbit.windlocator.WindLocatorApp;
import com.whiterabbit.windlocator.rest.OpenWeatherClient;
import com.whiterabbit.windlocator.storage.WeatherDbHelperExt;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import javax.inject.Singleton;

import dagger.Component;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(WindLocatorApp app);
    SharedPreferences getSharedPrefs();
    OpenWeatherClient getOpenWeatherClient();
    ReactiveLocationProvider getReactiveLocationProvider();
    WeatherDbHelperExt getDbHelper();
    WeatherFacade getFacade();
    Context getContext();
}

