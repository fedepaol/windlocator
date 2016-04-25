package com.whiterabbit.windlocator.detail;

import com.whiterabbit.windlocator.model.Forecast;
import com.whiterabbit.windlocator.model.ForecastResults;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;

import java.util.List;

public interface DetailView {
    void showWeather(Weather w);
    void showForecasts(List<Forecast> forecasts);
    void showLoadingForecasts(boolean loading);

    void showLoadingForecastsError();
}
