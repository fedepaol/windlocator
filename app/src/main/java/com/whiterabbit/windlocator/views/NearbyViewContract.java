package com.whiterabbit.windlocator.views;

import com.whiterabbit.windlocator.model.WeatherResults;

public interface NearbyViewContract {
    void updateWeatherResult(WeatherResults results);

    void setProgress(boolean pending);

    void goToDetail(long weatherDetail);
}
