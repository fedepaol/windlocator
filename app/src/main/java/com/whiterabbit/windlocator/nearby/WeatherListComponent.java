package com.whiterabbit.windlocator.nearby;

/**
 * Created by fedepaol on 28/06/15.
 */

import com.whiterabbit.windlocator.ActivityScope;
import com.whiterabbit.windlocator.inject.ApplicationComponent;


import dagger.Component;

@ActivityScope
@Component(modules = {WeatherListModule.class},
           dependencies = {ApplicationComponent.class})
public interface WeatherListComponent {
    void inject(NearbyListFragment fragment);
}

