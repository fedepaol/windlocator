package com.whiterabbit.windlocator.nearby;

import com.whiterabbit.windlocator.model.WeatherResults;

public interface NearbyView {
    void updateWeatherResult(WeatherResults results);

    void setProgress(boolean pending);

    void goToDetail(long weatherDetail);
}
