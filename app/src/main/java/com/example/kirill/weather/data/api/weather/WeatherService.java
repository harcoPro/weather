package com.example.kirill.weather.data.api.weather;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.flickr.FlickrApiError;
import com.example.kirill.weather.data.api.flickr.models.FlickrResponse;
import com.example.kirill.weather.data.api.weather.models.WeatherByCityNameResponse;
import com.example.kirill.weather.data.api.weather.models.WeatherResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

@ApplicationScope
public class WeatherService {

    private final OpenWeatherService weatherService;

    @Inject
    public WeatherService(OpenWeatherService openWeatherService) {
        this.weatherService = openWeatherService;
    }

    public Observable<WeatherByCityNameResponse> getWeatherByCityName(@NonNull String city) {
        return weatherService
                .getWeatherByCityName(city)
                .flatMap(this::handleApiResponse);
    }

    private <R extends WeatherResponse> Observable<R> handleApiResponse(R response) {
        if (response.code != null && response.code != 200) {
            return Observable.error(new WeatherApiError(response.code, response.message));
        } else {
            return Observable.just(response);
        }
    }

}
