package com.whiterabbit.windlocator;

import android.app.Application;

import com.whiterabbit.windlocator.detail.DetailModule;
import com.whiterabbit.windlocator.detail.DetailView;
import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.inject.ApplicationModule;
import com.whiterabbit.windlocator.inject.DaggerApplicationComponent;


public class WindLocatorApp extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }

    void initComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(getApplicationModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public DetailModule getDetailModule(DetailView view) {
        return new DetailModule(view);
    }
}
