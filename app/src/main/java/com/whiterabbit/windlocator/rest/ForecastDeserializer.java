package com.whiterabbit.windlocator.rest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.whiterabbit.windlocator.model.Forecast;
import com.whiterabbit.windlocator.model.Weather;

import java.lang.reflect.Type;
import java.util.Date;

public class ForecastDeserializer implements JsonDeserializer<Forecast> {

    public Forecast deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        double temperature = obj.get("main").getAsJsonObject().get("temp").getAsDouble();
        Date date = new Date(obj.get("dt").getAsInt());

        JsonObject wind = obj.get("wind").getAsJsonObject();
        double speed = wind.get("speed").getAsDouble();
        double direction = wind.get("deg").getAsDouble();

        int weatherId = obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();

        return new Forecast(date, speed, direction, temperature, weatherId);
    }
}