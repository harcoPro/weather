package com.example.kirill.weather.data;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.weather.WeatherService;
import com.example.kirill.weather.data.api.weather.models.WeatherByCityNameResponse;

import javax.inject.Inject;

import rx.Observable;

@ApplicationScope
public class DataService {

    private final Application app;
    private final WeatherService weatherService;

    @Inject
    public DataService(Application app,
                       WeatherService weatherService) {

        this.app = app;
        this.weatherService = weatherService;
    }

    public Observable<WeatherByCityNameResponse> getWeatherByCity(@NonNull String city) {
        return weatherService
                .getWeatherByCityName(city)
                ;
    }

}
