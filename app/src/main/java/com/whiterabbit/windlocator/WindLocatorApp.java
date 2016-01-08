package com.whiterabbit.windlocator;

import android.app.Application;

import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.inject.ApplicationModule;
import com.whiterabbit.windlocator.inject.DaggerApplicationComponent;


public class WindLocatorApp extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //enableRxTrack();
        initComponent();
    }

    ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }

    void initComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .build();

    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
