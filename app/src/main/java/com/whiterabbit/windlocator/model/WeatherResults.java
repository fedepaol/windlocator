package com.whiterabbit.windlocator.model;

import com.google.gson.annotations.SerializedName;

public class WeatherResults {
    public Weather[] getWeathers() {
        return weathers;
    }

    @SerializedName("list") private Weather[] weathers;

    public WeatherResults(Weather[] weathers) {
        this.weathers = weathers;
    }
}
