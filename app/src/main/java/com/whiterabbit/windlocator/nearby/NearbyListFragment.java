package com.whiterabbit.windlocator.nearby;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.WindLocatorApp;
import com.whiterabbit.windlocator.model.WeatherResults;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NearbyListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, NearbyView, WeatherListAdapter.ListElemClick {
    @Bind(R.id.nearby_list) RecyclerView mRecyclerView;
    @Bind(R.id.swipe_container) SwipeRefreshLayout mRefreshLayout;

    @Inject
    NearbyPresenter mPresenter;

    @Inject
    SharedPreferences mShared;
    private WeatherListAdapter mAdapter;

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
        WindLocatorApp app = (WindLocatorApp) getActivity().getApplication();

        DaggerWeatherListComponent.builder()
                                  .applicationComponent(app.getComponent())
                                  .weatherListModule(new WeatherListModule(this))
                                  .build().inject(this);

        setRetainInstance(true);
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
        mAdapter = new WeatherListAdapter(this, getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        return res;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onRefresh() {
        mPresenter.update();
    }

    @Override
    public void updateWeatherResult(WeatherResults results) {
        mAdapter.updateData(results);
    }

    @Override
    public void setProgress(boolean pending) {
        if (pending) {
          mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(true));
        } else {
            mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(false));
        }
    }

    @Override
    public void goToDetail(long weatherDetail) {

    }

    @Override
    public void askForPermission() {
        final String[] INITIAL_PERMS={
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(getActivity(), INITIAL_PERMS, 23);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 23) {
            return;
        }
        for (Integer s : grantResults) {
            if (s == PackageManager.PERMISSION_GRANTED) {
                mPresenter.onPermissionResult(true);
                return;
            }
        }
        mPresenter.onPermissionResult(false);
    }

    @Override
    public void onViewClicked(long id) {

    }

    @Override
    public void onPreferredClicked(long id) {

    }
}
