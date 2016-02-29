package com.whiterabbit.windlocator.detail;

import com.whiterabbit.windlocator.model.ForecastResults;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;

public interface DetailView {
    void showWeather(Weather w);
    void showForecasts(ForecastResults forecasts);
    void showLoadingForecasts(boolean loading);

    void showLoadingForecastsError();
}
