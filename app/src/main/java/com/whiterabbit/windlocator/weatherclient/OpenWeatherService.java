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

package com.whiterabbit.windlocator.weatherclient;


import com.whiterabbit.windlocator.model.ForecastResults;
import com.whiterabbit.windlocator.model.WeatherResults;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface OpenWeatherService {
    @GET("/data/2.5/find")
    Observable<WeatherResults> listNearbyStations(@Query("lat") double lat,
                                                  @Query("lon") double lon,
                                                  @Query("cnt") long cnt);

    @GET("/data/2.5/forecast")
    Observable<ForecastResults> getLocationForecasts(@Query("id") long id);


}
