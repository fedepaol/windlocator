package com.whiterabbit.windlocator.model;

import java.util.Date;

public class Forecast {
    Date time;
    double  windSpeed;
    double  windDegree;
    double  temperature;
    int     weatherEnum;

    public Forecast(Date time, double windSpeed, double windDegree, double temperature, int weatherEnum) {
        this.time = time;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.temperature = temperature;
        this.weatherEnum = weatherEnum;
    }

    public Date getTime() {
        return time;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getWeatherEnum() {
        return weatherEnum;
    }
}
