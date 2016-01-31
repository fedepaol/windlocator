package com.whiterabbit.windlocator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Weather implements Parcelable {
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

    public Weather(Parcel p) {
        this.id = p.readLong();
        this.time = new Date(p.readLong());
        this.windSpeed = p.readDouble();
        this.windDegree = p.readDouble();
        this.temperature = p.readDouble();
        this.cityName = p.readString();
        this.latitude = p.readDouble();
        this.longitude = p.readDouble();
        this.weatherEnum = p.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(time.getTime());
        parcel.writeDouble(windSpeed);
        parcel.writeDouble(windDegree);
        parcel.writeDouble(temperature);
        parcel.writeString(cityName);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(weatherEnum);
    }

    public static final Parcelable.Creator<Weather> CREATOR
            = new Parcelable.Creator<Weather>() {
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int i) {
            return new Weather[0];
        }

    };

}
