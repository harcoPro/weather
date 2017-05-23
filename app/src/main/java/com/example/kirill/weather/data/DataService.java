package com.example.kirill.weather.data;

import android.app.Application;
import android.location.Location;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.flickr.FlickrApiService;
import com.example.kirill.weather.data.api.pixabay.PixabayApiService;
import com.example.kirill.weather.data.api.pixabay.PixabayService;
import com.example.kirill.weather.data.api.weather.WeatherService;
import com.example.kirill.weather.data.api.weather.models.WeatherByCityNameResponse;
import com.example.kirill.weather.ui.models.CityImage;
import com.example.kirill.weather.ui.models.Weather;
import com.example.kirill.weather.ui.models.WeatherWithImage;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;

@ApplicationScope
public class DataService {

    private final Application app;
    private final WeatherService weatherService;
    private final PixabayApiService pixabayApiService;

    @Inject
    public DataService(Application app,
                       WeatherService weatherService,
                       PixabayApiService pixabayApiService) {

        this.app = app;
        this.weatherService = weatherService;
        this.pixabayApiService = pixabayApiService;
    }

    public Observable<WeatherWithImage> getWeatherWithImageByCity(@NonNull String city) {
        return weatherService
                .getWeatherByCityName(city)
                .map(rest -> Weather.fromRest(rest, city))
                .flatMap(
                        weather ->
                        getCityImage(city)
                        .map(image -> new WeatherWithImage(weather, image))
                )
                ;
    }

    public Observable<CityImage> getCityImage(String city) {
        return pixabayApiService
                .search(city)
                .map(rest -> rest.get(0))
                .map(CityImage::from)
                ;
    }

}
