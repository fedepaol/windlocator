package com.whiterabbit.windlocator.model;

import com.google.gson.annotations.SerializedName;

public class ForecastResults {
    public Forecast[] getForecasts() {
        return forecasts;
    }

    @SerializedName("list") private Forecast[] forecasts;

    public ForecastResults(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }
}
