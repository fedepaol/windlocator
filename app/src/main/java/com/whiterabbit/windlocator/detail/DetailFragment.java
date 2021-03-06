package com.whiterabbit.windlocator.detail;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whiterabbit.windlocator.Constants;
import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.WindLocatorApp;
import com.whiterabbit.windlocator.model.Forecast;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.weatherclient.WeatherCodes;
import com.whiterabbit.windlocator.utils.WeatherElementUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements DetailView {
    @Inject
    DetailPresenter mPresenter;

    @Inject
    WeatherElementUtils mElemUtils;

    @Bind(R.id.detail_detail)
    WeatherDetailSummary mDetailSummary;

    @Bind(R.id.detail_forecasts)
    RecyclerView mNextDaysForecasts;

    @Bind(R.id.detail_current)
    DetailCurrentTime mCurrentTime;

    @Bind(R.id.detail_speed)
    TextView mWindSpeed;

    @Bind(R.id.detail_weather_desc)
    TextView mWeatherDesc;

    @Bind(R.id.detail_weather_icon)
    ImageView mWeatherIcon;

    @Bind(R.id.detail_temperature)
    TextView mTemperature;

    private ForecastListAdapter mAdapter;

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
                .detailModule(app.getDetailModule(this))
                .build().inject(this);


        //mNextDaysForecasts.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                                                        getActivity().getApplicationContext(),
                                                        LinearLayoutManager.HORIZONTAL, false);
        mNextDaysForecasts.setLayoutManager(layoutManager);
        mNextDaysForecasts.setNestedScrollingEnabled(false);

        Weather w = getArguments().getParcelable(Constants.WEATHER_EXTRA);
        Log.d("FEDE", "Presenter set weather");
        mPresenter.setWeather(w);
    }

    @Override
    public void showWeather(Weather w) {
        Log.d("FEDE", "show weather before");

        double degree = w.getWindDegree();
        String label = WeatherElementUtils.getShortWindOrientationFromDegrees(degree,
                                                        getActivity().getApplicationContext());
        mDetailSummary.setWindDirection(degree, label);
        setTodayValues(w.getTime());
        mWindSpeed.setText(String.format("%d", (int) w.getWindSpeed()));
        mWeatherIcon.setImageResource(WeatherCodes.getIconFromWeatherDesc(w.getWeatherEnum()));
        mTemperature.setText(String.format("%d", (int) w.getTemperature()));
        mWeatherDesc.setText(WeatherCodes.getWeatherDescFromId(w.getWeatherEnum()));
        mAdapter = new ForecastListAdapter(getActivity().getApplicationContext(),
                null);
        mNextDaysForecasts.setAdapter(mAdapter);
        Log.d("FEDE", "show weather after");
    }

    private void setTodayValues(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        String monthString = getResources().getStringArray(R.array.months)[month];

        SimpleDateFormat sdfDate = new SimpleDateFormat("MMM, dd",
                                                         getResources().getConfiguration().locale);
        mCurrentTime.setCurrentDate(sdfDate.format(today));
        mCurrentTime.setCurrentTime(mElemUtils.timeToStringTime(today.getTime()));
    }

    @Override
    public void showForecasts(List<Forecast> forecasts) {
        Log.d("FEDE", "updating forecast" + forecasts.get(0).getTemperature());
        mAdapter.updateData(forecasts);
    }

    @Override
    public void showLoadingForecasts(boolean loading) {
        // TODO
    }

    @Override
    public void showLoadingForecastsError() {

    }
}
