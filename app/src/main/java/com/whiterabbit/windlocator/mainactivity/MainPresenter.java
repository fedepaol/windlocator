package com.whiterabbit.windlocator.mainactivity;

import android.location.Address;


import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;

import rx.Observable;

/**
 * Created by fedepaol on 29/01/16.
 */
public interface MainPresenter {
    void setAddressObservable(Observable<SearchViewQueryTextEvent> observable);

    void onPause();

    void onResume();

    void onAddressSelected(Address a);
}
