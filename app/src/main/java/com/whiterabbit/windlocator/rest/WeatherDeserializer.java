package com.whiterabbit.windlocator.rest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonObject;



import java.lang.reflect.Type;
import java.util.Date;

import com.whiterabbit.windlocator.model.Weather;

public class WeatherDeserializer implements JsonDeserializer<Weather> {

    public Weather deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        String cityName = obj.get("name").getAsString();
        long id = obj.get("id").getAsLong();
        JsonObject coord = obj.get("coord").getAsJsonObject();

        double latitude = coord.get("lat").getAsDouble();
        double longitude = coord.get("lon").getAsDouble();
        double temperature = obj.get("main").getAsJsonObject().get("temp").getAsDouble();
        Date date = new Date(obj.get("dt").getAsInt());

        JsonObject wind = obj.get("wind").getAsJsonObject();
        double speed = wind.get("speed").getAsDouble();
        double direction = wind.get("deg").getAsDouble();

        int weatherId = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

        return new Weather(id, date, speed, direction, temperature, cityName, latitude,
                                          longitude, weatherId);
    }
}