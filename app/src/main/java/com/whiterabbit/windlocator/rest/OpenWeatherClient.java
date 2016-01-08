/*
 * Copyright (C) 2015 Federico Paolinelli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.whiterabbit.windlocator.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

public class OpenWeatherClient {
    private OpenWeatherService mClient;

    public static Gson getGSon() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Weather.class, new WeatherDeserializer());
        return builder.create();
    }

    public OpenWeatherClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl newUrl = original.httpUrl().newBuilder()
                                      .addQueryParameter("APPID", "")
                                      .addQueryParameter("units", "metric").build();

                Request enhancedRequest = original.newBuilder()
                                                .url(newUrl).build();
                return chain.proceed(enhancedRequest);
            }
        });

        mClient = new Retrofit.Builder()
                              .baseUrl("http://api.openweathermap.org/data/2.5/")
                              .client(client)
                              .addConverterFactory(GsonConverterFactory.create(getGSon()))
                              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                              .build()
                              .create(OpenWeatherService.class);
    }

    public Observable<WeatherResults> getNearbyWeather(double latitude, double longitude) {
        return mClient.listNearbyStations(latitude, longitude, 10);
    }

}
