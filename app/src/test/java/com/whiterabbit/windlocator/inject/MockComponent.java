package com.whiterabbit.windlocator.inject;

import com.whiterabbit.windlocator.FacadeTest;
import com.whiterabbit.windlocator.inject.MockModule;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fedepaol on 08/01/16.
 */


@Singleton
@Component(modules = {MockModule.class})
public interface MockComponent {
    WeatherFacade getFacade();
    void inject(FacadeTest t);
}
