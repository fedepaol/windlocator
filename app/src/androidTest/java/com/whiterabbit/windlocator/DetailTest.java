package com.whiterabbit.windlocator;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.whiterabbit.windlocator.detail.DetailActivity;
import com.whiterabbit.windlocator.detail.DetailFragment;
import com.whiterabbit.windlocator.detail.DetailModule;
import com.whiterabbit.windlocator.detail.DetailPresenter;
import com.whiterabbit.windlocator.detail.DetailView;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.weatherclient.OpenWeatherClient;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    private Weather mWeather;
    private DetailPresenter mMockPresenter;


    @Rule
    public ActivityTestRule<DetailActivity> activity = new ActivityTestRule<>(DetailActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        mTestDate = new Date();
        mWeather = new Weather(2, mTestDate, windSpeed, windDegree, temperature, cityName, latitude, longitude, weatherEnum);
        DetailModule m = mock(DetailModule.class);
        mMockPresenter = mock(DetailPresenter.class);

        when(m.provideDetailView()).thenReturn(mock(DetailView.class));
        when(m.provideDetailPresenter(any(DetailView.class), any(OpenWeatherClient.class)))
                .thenReturn(mMockPresenter);

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        TestWindLocatorApp app
                = (TestWindLocatorApp) instrumentation.getTargetContext().getApplicationContext();
        app.setDetailModule(m);
    }

    @Test
    public void testRendering() {
        Intent i = new Intent();
        i.putExtra(Constants.WEATHER_EXTRA, mWeather);
        activity.launchActivity(i);

        onView(withId(R.id.detail_detail));
        verify(mMockPresenter).setWeather(any(Weather.class));

        DetailView view = (DetailFragment) activity.getActivity().getSupportFragmentManager()
                                                    .findFragmentById(R.id.detail_fragment);

        view.showWeather(mWeather);

    }
}