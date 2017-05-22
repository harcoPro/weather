package com.example.kirill.weather.data;

import android.app.Application;
import android.location.Location;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.flickr.FlickrApiService;
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
    private final FlickrApiService flickrService;

    @Inject
    public DataService(Application app,
                       WeatherService weatherService,
                       FlickrApiService flickrService) {

        this.app = app;
        this.weatherService = weatherService;
        this.flickrService = flickrService;
    }

    public Observable<WeatherWithImage> getWeatherByLocation(Location location) {
            return new ReactiveLocationProvider(app)
                    .getReverseGeocodeObservable(location.getLatitude(), location.getLongitude(), 1)
                    .flatMap(
                            list -> {
                                if (list.size() > 0 && !TextUtils.isEmpty(list.get(0).getLocality())) {
                                    return Observable.just(list.get(0).getLocality());
                                } else {
                                    return Observable.error(new Throwable("Error obtained city name from location!"));
                                }
                            }
                    )
                    .flatMap(this::getWeatherWithImageByCity)
                    ;
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
        return flickrService
                .search(city)
                .flatMap(rest -> flickrService.info(rest.photos.get(0).id))
                .map(CityImage::from)
                ;
    }

}
