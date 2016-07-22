package com.whiterabbit.windlocator;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by fedepaol on 14/07/16.
 */
public class EspressoTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws
            IllegalAccessException, ClassNotFoundException, InstantiationException {
        return super.newApplication(cl, TestWindLocatorApp.class.getName(), context);
    }
}
