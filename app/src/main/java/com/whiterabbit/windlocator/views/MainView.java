package com.whiterabbit.windlocator.views;

import android.location.Address;

import java.util.List;

/**
 * Created by fedepaol on 29/01/16.
 */
public interface MainView {
    void showAddresses(List<Address> l);
}
