package com.whiterabbit.windlocator.mainactivity;

import android.location.Address;

import rx.Observable;

/**
 * Created by fedepaol on 29/01/16.
 */
public interface MainPresenter {
    void setAddressObservable(Observable<String> observable);

    void onPause();

    void onResume();

    void onQueryPressed(String query);

    void onAddressSelected(Address a);
}
