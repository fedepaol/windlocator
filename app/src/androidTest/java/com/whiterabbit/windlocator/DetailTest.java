package com.whiterabbit.windlocator;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.whiterabbit.windlocator.detail.DetailActivity;
import com.whiterabbit.windlocator.model.Weather;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Date;

import rx.Observable;

/**
 * Created by fedepaol on 09/07/16.
 */

@RunWith(AndroidJUnit4.class)
public class DetailTest {
    private Date mTestDate;
    private double windSpeed = 23;
    private double windDegree = 60;
    private double temperature = 12;
    private String cityName = "Pisa";
    private double latitude = 50;
    private double longitude = 60;
    private int weatherEnum = 2;
    private Weather mWeeather;


    @Rule
    public ActivityTestRule<DetailActivity> activity = new ActivityTestRule<>(DetailActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        mTestDate = new Date();
        mWeeather = new Weather(2, mTestDate, windSpeed, windDegree, temperature, cityName, latitude, longitude, weatherEnum);


    }

    @Test
    public void testRendering() {
        Intent i = new Intent();
        i.putExtra(Constants.WEATHER_EXTRA, mWeeather);
        activity.launchActivity(i);
    }
}