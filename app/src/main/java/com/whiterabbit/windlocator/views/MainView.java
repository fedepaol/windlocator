package com.whiterabbit.windlocator.views;

import android.location.Address;

import com.whiterabbit.windlocator.model.Weather;

import java.util.List;

/**
 * Created by fedepaol on 29/01/16.
 */
public interface MainView {
    void showAddresses(List<Address> l);

    void goToWeatherDetail(Weather w);
}
