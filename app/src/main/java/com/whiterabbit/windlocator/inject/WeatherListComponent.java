package com.whiterabbit.windlocator.inject;

/**
 * Created by fedepaol on 28/06/15.
 */

import com.whiterabbit.windlocator.ActivityScope;
import com.whiterabbit.windlocator.views.NearbyListFragment;


import dagger.Component;

@ActivityScope
@Component(modules = {WeatherListModule.class},
           dependencies = {ApplicationComponent.class})
public interface WeatherListComponent {
    void inject(NearbyListFragment fragment);
}

