package com.example.kirill.weather.data.api.weather;


import com.example.kirill.weather.data.api.weather.models.WeatherByCityNameResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OpenWeatherService {

    @GET("data/2.5/weather")
    Observable<WeatherByCityNameResponse>
    getWeatherByCityName(@Query("q")   String city);

}
