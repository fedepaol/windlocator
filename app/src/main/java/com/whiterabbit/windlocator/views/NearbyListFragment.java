package com.whiterabbit.windlocator.views;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.inject.DaggerWeatherListComponent;
import com.whiterabbit.windlocator.inject.WeatherListModule;
import com.whiterabbit.windlocator.model.WeatherResults;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NearbyListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, NearbyViewContract {
    @Bind(R.id.nearby_list) RecyclerView mRecyclerView;
    @Bind(R.id.swipe_container) SwipeRefreshLayout mRefreshLayout;

    @Inject
    NearbyPresenterContract mPresenter;

    @Inject
    SharedPreferences mShared;
    //private WeatherListAdapter mAdapter;

    public NearbyListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerWeatherListComponent.builder()
                                  .weatherListModule(new WeatherListModule(this))
                                  .build().inject(this);

        setRetainInstance(true);
        // createObservable();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.fragment_weather_list, container, false);
        ButterKnife.bind(this, res);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        /*
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity().getApplicationContext())
                .color(Color.WHITE)
                .sizeResId(R.dimen.divider)
                .build()); */
        mRecyclerView.setNestedScrollingEnabled(false);

        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(this);
        return res;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void updateWeatherResult(WeatherResults results) {

    }

    @Override
    public void setProgress(boolean pending) {

    }

    @Override
    public void goToDetail(long weatherDetail) {

    }
}