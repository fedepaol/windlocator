package com.whiterabbit.windlocator.detail;

import android.os.Bundle;
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
import com.whiterabbit.windlocator.model.ForecastResults;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.weatherclient.WeatherCodes;
import com.whiterabbit.windlocator.utils.WeatherElementUtils;

import java.util.Calendar;
import java.util.Date;

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

    @Bind(R.id.detail_date)
    TextView mTodayDate;

    @Bind(R.id.detail_time)
    TextView mTodayTime;

    @Bind(R.id.detail_speed)
    TextView mWindSpeed;

    @Bind(R.id.detail_weather_desc)
    TextView mWeatherDesc;

    @Bind(R.id.detail_weather_icon)
    ImageView mWeatherIcon;

    @Bind(R.id.detail_temperature)
    TextView mTemperature;

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


        //mNextDaysForecasts.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                                                        getActivity().getApplicationContext(),
                                                        LinearLayoutManager.HORIZONTAL, false);
        mNextDaysForecasts.setLayoutManager(layoutManager);
        mNextDaysForecasts.setNestedScrollingEnabled(false);
    }

    @Override
    public void showWeather(Weather w) {
        double degree = w.getWindDegree();
        String label = WeatherElementUtils.getShortWindOrientationFromDegrees(degree,
                                                        getActivity().getApplicationContext());
        mDetailSummary.setWindDirection(degree, label);
        setTodayValues(w.getTime());
        mWindSpeed.setText(String.format("%d", (int) w.getWindSpeed()));
        mWeatherIcon.setImageResource(WeatherCodes.getIconFromWeatherDesc(w.getWeatherEnum()));
        mTemperature.setText(String.format("%d", (int) w.getTemperature()));
        mWeatherDesc.setText(WeatherCodes.getWeatherDescFromId(w.getWeatherEnum()));
    }

    @Override
    public void onResume() {
        super.onResume();
        Weather w = getArguments().getParcelable(Constants.WEATHER_EXTRA);
        mPresenter.setWeather(w);
    }

    private void setTodayValues(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        String dateString = String.format("Sep, %d", day); // TODO Month
        mTodayDate.setText(dateString);
        mTodayTime.setText(mElemUtils.timeToStringTime(today.getTime()));
    }

    @Override
    public void showForecasts(ForecastResults forecasts) {
        Log.d("FEDE", "Forecasts " + forecasts.getForecasts()[0].getWindDegree());
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
