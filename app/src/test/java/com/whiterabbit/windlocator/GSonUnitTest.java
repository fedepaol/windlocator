package com.whiterabbit.windlocator;

import android.provider.MediaStore;

import com.google.gson.Gson;
import com.whiterabbit.windlocator.inject.ApplicationComponent;
import com.whiterabbit.windlocator.model.ForecastResults;
import com.whiterabbit.windlocator.rest.OpenWeatherClient;

import junit.framework.Assert;

import com.whiterabbit.windlocator.model.WeatherResults;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static org.mockito.Mockito.mock;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class GSonUnitTest {
    private String nearbyResult;
    private String forecastResult;

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private static File getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }

    @Before
    public void setup() {
        nearbyResult = "{\"message\":\"accurate\",\"cod\":\"200\",\"count\":2,\"list\":[{\"id\":495260,\"name\":\"Shcherbinka\",\"coord\":{\"lon\":37.559719,\"lat\":55.499722},\"main\":{\"temp\":260.83,\"pressure\":1011,\"humidity\":84,\"temp_min\":259.15,\"temp_max\":261.95},\"dt\":1452113601,\"wind\":{\"speed\":1,\"deg\":0},\"sys\":{\"country\":\"\"},\"clouds\":{\"all\":68},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}]},{\"id\":564517,\"name\":\"Dubrovitsy\",\"coord\":{\"lon\":37.486698,\"lat\":55.43969},\"main\":{\"temp\":260.84,\"pressure\":1011,\"humidity\":84,\"temp_min\":259.15,\"temp_max\":261.95},\"dt\":1452113601,\"wind\":{\"speed\":1,\"deg\":0},\"sys\":{\"country\":\"\"},\"clouds\":{\"all\":64},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}]}]}\n";
    }

    @Test
    public void testDeserializer() throws Exception {
        Gson gson = OpenWeatherClient.getGSon();
        WeatherResults res = gson.fromJson(nearbyResult, WeatherResults.class);
        Assert.assertEquals(res.getWeathers().length, 2);
        Assert.assertEquals(res.getWeathers()[0].getCityName(), "Shcherbinka");
        Assert.assertEquals(res.getWeathers()[0].getId(), 495260);
        mock(ApplicationComponent.class);
    }

    @Test
    public void testDeserializeForecasts() throws Exception {
        Gson gson = OpenWeatherClient.getGSon();
        File file = getFileFromPath(this, "res/forecasts.json");
        FileInputStream s = new FileInputStream(file);
        String json = convertStreamToString(s);
        ForecastResults res = gson.fromJson(json, ForecastResults.class);
    }
}