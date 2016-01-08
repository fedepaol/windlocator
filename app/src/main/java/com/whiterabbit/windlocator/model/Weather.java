package com.whiterabbit.windlocator.model;

import java.util.Date;

public class Weather {
    Date time;
    double  windSpeed;
    double  windDegree;
    double  temperature;
    String  cityName;
    double  latitude;
    double  longitude;
    int     weatherEnum;
    long    id;

    public Weather(long id,
                   Date time,
                   double windSpeed,
                   double windDegree,
                   double temperature,
                   String cityName,
                   double latitude,
                   double longitude,
                   int weatherEnum) {

        this.id = id;
        this.time = time;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.temperature = temperature;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getWeatherEnum() {
        return weatherEnum;
    }

    public long getId() {
        return id;
    }
}
