package com.whiterabbit.windlocator;

import android.content.Context;
import android.location.Address;

import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.storage.WeatherFacade;
import com.whiterabbit.windlocator.mainactivity.LocationWeatherFinder;
import com.whiterabbit.windlocator.mainactivity.MainPresenterImpl;
import com.whiterabbit.windlocator.mainactivity.MainView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MainActivityPresenterTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    MainPresenterImpl presenter;
    @Mock
    MainView view;
    @Mock
    WeatherFacade facade;
    @Mock
    LocationWeatherFinder finder;
    @Mock
    Context context;
    @Mock
    Weather weather;

    @Before
    public void setup() {
        presenter = new MainPresenterImpl(view, facade, finder, new FakeSchedulersProvider(), context);
    }

    @Test
    public void testAddressObservable() throws Exception {
        Observable<SearchViewQueryTextEvent> observable =
                Observable.just(SearchViewQueryTextEvent.create(null ,"Pisa", false));
        ArrayList<Address> addresses = new ArrayList<Address>(1);
        addresses.add(mock(Address.class));

        when(facade.getAddressWeatherObservable(anyString(), any(Context.class)))
                .thenReturn(Observable.just(addresses));
        presenter.setAddressObservable(observable);

        verify(view).showAddresses(addresses);

        presenter.onPause();
        presenter.onResume();
        verify(view, times(2)).showAddresses(addresses);
    }

    @Test
    public void testSearchResult() throws Exception {
        Observable<SearchViewQueryTextEvent> observable =
                Observable.just(SearchViewQueryTextEvent.create(null ,"Pisa", true));


        ArrayList<Address> addresses = new ArrayList<Address>(1);
        addresses.add(mock(Address.class));

        when(facade.getAddressWeatherObservable(anyString(), any(Context.class)))
                .thenReturn(Observable.just(addresses));


        when(finder.getAddressWeatherObservable(anyString())).thenReturn(Observable.just(weather));
        presenter.setAddressObservable(observable);
        verify(view).setProgress(true);
        verify(view).setProgress(false);
        verify(view).goToWeatherDetail(weather);
    }
}