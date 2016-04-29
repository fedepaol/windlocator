package com.whiterabbit.windlocator.model;

import com.google.gson.annotations.SerializedName;

public class WeatherResults {
    public Weather[] getWeathers() {
        return weathers;
    }
    boolean[] favorites;
    @SerializedName("list") private Weather[] weathers;

    public WeatherResults(Weather[] weathers) {
        this.weathers = weathers;
        favorites = new boolean[weathers.length];
    }

    public WeatherResults(Weather[] weathers, boolean[] favorites) {
        this.weathers = weathers;
        this.favorites = favorites;
    }

    public void setFavorite(int position) {
        favorites[position] = true;
    }
}
