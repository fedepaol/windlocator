package com.whiterabbit.windlocator;

import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.inject.DaggerApplicationComponent;
import com.whiterabbit.windlocator.inject.MockModule;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by fedepaol on 08/01/16.
 */
public class FacadeTest {
    private WeatherFacade mFacade;
    @Before
    public void setup() {
        ApplicationComponent c = DaggerApplicationComponent.builder().applicationModule(new MockModule()).build();
        mFacade = c.getFacade();
    }

    @Test
    public void testFacade() {

    }
}
