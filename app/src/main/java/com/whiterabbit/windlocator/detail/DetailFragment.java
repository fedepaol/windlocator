package com.whiterabbit.windlocator.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiterabbit.windlocator.Constants;
import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.WindLocatorApp;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.utils.WeatherElementUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements DetailView {
    @Inject
    DetailPresenter mPresenter;

    @Bind(R.id.detail_detail)
    WeatherDetailSummary mDetailSummary;

    @Bind(R.id.detail_forecasts)
    RecyclerView mNextDaysForecasts;

    public static DetailFragment createInstance(Weather w) {
        DetailFragment res = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(Constants.WEATHER_EXTRA, w);
        res.setArguments(b);
        return res;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, res);
        return res;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WindLocatorApp app = (WindLocatorApp) getActivity().getApplication();

        DaggerDetailComponent.builder()
                .applicationComponent(app.getComponent())
                .detailModule(new DetailModule(this))
                .build().inject(this);


        Weather w = getArguments().getParcelable(Constants.WEATHER_EXTRA);
        mNextDaysForecasts.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                                                        getActivity().getApplicationContext(),
                                                        LinearLayoutManager.HORIZONTAL, false);
        mNextDaysForecasts.setLayoutManager(layoutManager);
        mNextDaysForecasts.setNestedScrollingEnabled(false);
        mPresenter.setWeather(w);

    }

    @Override
    public void showWeather(Weather w) {
        double degree = w.getWindDegree();
        String label = WeatherElementUtils.getShortWindOrientationFromDegrees(degree,
                                                        getActivity().getApplicationContext());
        mDetailSummary.setWindDirection(degree, label);
    }

    @Override
    public void showForecasts(WeatherResults forecasts) {
        ForecastListAdapter adp = new ForecastListAdapter(getActivity().getApplicationContext(),
                                                          forecasts);
        mNextDaysForecasts.setAdapter(adp);
    }

    @Override
    public void showLoadingForecasts(boolean loading) {
        // TODO
    }

    @Override
    public void showLoadingForecastsError() {

    }
}
