package com.whiterabbit.windlocator.views;

/**
 * Created by fedepaol on 28/06/15.
 */

import com.whiterabbit.windlocator.ActivityScope;
import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.nearby.NearbyListFragment;

import dagger.Component;

@ActivityScope
@Component(modules = {MainActivityModule.class},
           dependencies = {ApplicationComponent.class})
public interface MainActivityComponent {
    void inject(MainActivity a);
}

