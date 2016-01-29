package com.whiterabbit.windlocator.views;

import android.location.Address;

import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;

import java.util.List;

import rx.Observable;

/**
 * Created by fedepaol on 29/01/16.
 */
public interface MainPresenter {
    void setAddressObservable(Observable<String> observable);

    void onPause();

    void onResume();

    void setSearchViewObservable(Observable<SearchViewQueryTextEvent> observable);
}
