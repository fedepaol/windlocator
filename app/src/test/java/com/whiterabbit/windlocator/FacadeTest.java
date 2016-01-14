package com.whiterabbit.windlocator;

import android.location.Location;

import com.google.android.gms.location.LocationRequest;
import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.inject.DaggerApplicationComponent;
import com.whiterabbit.windlocator.inject.MockModule;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.rest.OpenWeatherClient;
import com.whiterabbit.windlocator.storage.WeatherDbHelperExt;
import com.whiterabbit.windlocator.storage.WeatherFacade;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fedepaol on 08/01/16.
 */
public class FacadeTest {
    private WeatherFacade mFacade;
    private MockModule module;
    private WeatherDbHelperExt dbHelper;

    private ReactiveLocationProvider buildMockLocationProvider() {
        ReactiveLocationProvider locProvider = mock(ReactiveLocationProvider.class);
        Location l = mock(Location.class);
        when(l.getLatitude()).thenReturn(23.0);
        when(l.getLongitude()).thenReturn(28.0);
        Observable<Location> mockRes = Observable.just(l);
        when(locProvider.getUpdatedLocation(any(LocationRequest.class))).thenReturn(mockRes);
        return locProvider;
    }

    private OpenWeatherClient buildMockWeatherClient() {
        OpenWeatherClient mock = mock(OpenWeatherClient.class);
        Weather w = new Weather(23, new Date(), 99.0, 95.0, 37.0, "Pisa", 23.0, 28.0, 12);
        Weather w1 = new Weather(28, new Date(), 99.0, 95.0, 37.0, "Lucca", 23.0, 28.0, 12);
        Weather[] weathers = {w, w1};
        WeatherResults res = new WeatherResults(weathers);
        when(mock.getNearbyWeather(any(Double.class), any(Double.class))).thenReturn(Observable.just(res));
        return mock;
    }

    @Before
    public void setup() {
        dbHelper = mock(WeatherDbHelperExt.class);
        module = new MockModule(buildMockWeatherClient(), dbHelper);
        module.setmLocationProvider(buildMockLocationProvider());
        ApplicationComponent c = DaggerApplicationComponent.builder()
                                                    .applicationModule(module).build();
        mFacade = c.getFacade();
    }

    @Test
    public void testFacadeUpdateLocalWeather() {
        Observable<String> resultObservable = mFacade.updateLocalWeather();
        verify(dbHelper, times(2)).addNearbyWeather(any(Date.class), any(Double.class), any(Double.class), any(Double.class),
                any(String.class), any(Double.class), any(Double.class), any(Integer.class), any(Long.class));

    }
}
