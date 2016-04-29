package com.whiterabbit.windlocator;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.whiterabbit.windlocator.storage.WeatherDbHelperExt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by fedepaol on 27/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DatabaseTest {
    private WeatherDbHelperExt mDbHelper;

    public DatabaseTest() {
        mDbHelper = new WeatherDbHelperExt(RuntimeEnvironment.application);
    }

    @Test
    public void testNoFavorites() {
        mDbHelper.open();
        mDbHelper.addNearbyWeather(new Date(), 99.0, 95.0, 37.0, "Pisa", 23.0, 28.0, 12, 23);
        mDbHelper.addNearbyWeather(new Date(), 99.0, 95.0, 37.0, "Pisa", 23.0, 28.0, 12, 28);
        mDbHelper.close();
        mDbHelper.open();
        Cursor c = mDbHelper.getAllNearbyWeather();

        assertEquals(c.getCount(), 2);
        c.moveToFirst();
        mDbHelper.close();

    }
}
