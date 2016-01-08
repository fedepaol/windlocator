package com.whiterabbit.windlocator.inject;

/**
 * Created by fedepaol on 28/06/15.
 */

import android.content.SharedPreferences;

import com.whiterabbit.windlocator.WindLocatorApp;
import com.whiterabbit.windlocator.views.NearbyListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(WindLocatorApp app);
    SharedPreferences getSharedPrefs();
}

