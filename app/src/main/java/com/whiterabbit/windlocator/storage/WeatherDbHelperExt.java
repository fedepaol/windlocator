package com.whiterabbit.windlocator.storage;

import android.content.Context;
import android.database.Cursor;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;

import java.util.Date;

public class WeatherDbHelperExt extends  WeatherDbHelper {
    public WeatherDbHelperExt(Context context) {
        super(context);
    }

    public WeatherResults getLastNearbyWeather() {
        open();
        Cursor c = getAllNearbyWeather();
        Weather[] weathers = new Weather[c.getCount()];
        if (weathers.length == 0) {
            WeatherResults res = new WeatherResults(weathers);
            c.close();
            close();
            return res;
        }
        c.moveToFirst();
        int count = 0;
        do {
            Weather w = new Weather(c.getLong(NEARBYWEATHER_ID_COLUMN_POSITION),
                                    new Date(c.getLong(NEARBYWEATHER_TIME_COLUMN_POSITION)),
                   c.getDouble(NEARBYWEATHER_WINDSPEED_COLUMN_POSITION),
                    c.getDouble(NEARBYWEATHER_WINDDEGREE_COLUMN_POSITION),
                    c.getDouble(NEARBYWEATHER_TEMPERATURE_COLUMN_POSITION),
                    c.getString(NEARBYWEATHER_CITYNAME_COLUMN_POSITION),
                    c.getDouble(NEARBYWEATHER_LATITUDE_COLUMN_POSITION),
                    c.getDouble(NEARBYWEATHER_LONGITUDE_COLUMN_POSITION),
                    c.getInt(NEARBYWEATHER_WEATHERENUM_COLUMN_POSITION));

            weathers[count++] = w;
        } while (c.moveToNext());
        c.close();
        close();
        return new WeatherResults(weathers);
    }

    public boolean isFavourite(long id) {
        Cursor res = mDb.query(FAVORITEWEATHER_TABLE, new String[] {
                ROW_ID,
                FAVORITEWEATHER_ID_COLUMN
        }, FAVORITEWEATHER_ID_COLUMN + " = " + id, null, null, null, null);

        return res.getCount() > 0;
    }

    public void setFavourite(Weather w) {
        addFavoriteWeather(w.getTime(),
                           w.getWindSpeed(),
                           w.getWindDegree(),
                           w.getTemperature(),
                           w.getCityName(),
                           w.getLatitude(),
                           w.getLongitude(),
                           w.getWeatherEnum(),
                           w.getId());
    }

    public boolean removeFromFavourite(long id) {
        return mDb.delete(FAVORITEWEATHER_TABLE, FAVORITEWEATHER_ID_COLUMN + " = " + id, null) > 0;
    }
}
