package com.whiterabbit.windlocator.detail;

/**
 * Created by fedepaol on 28/06/15.
 */

import com.whiterabbit.windlocator.ActivityScope;
import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.mainactivity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {DetailModule.class},
           dependencies = {ApplicationComponent.class})
public interface DetailComponent {
    void inject(DetailFragment f);
}

