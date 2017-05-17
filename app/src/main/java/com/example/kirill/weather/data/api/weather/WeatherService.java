package com.example.kirill.weather.data.api.weather;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.weather.models.ResponseWeatherByCityName;
import com.example.kirill.weather.data.preferences.qualifiers.LocaleQualifier;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

@ApplicationScope
public class WeatherService {

    public static final String API_KEY = "df45f890a0b873e8ffea573ef07339b6";


    private final Application application;
    private final OpenWeatherService openWeatherService;
    private final @LocaleQualifier String locale;

    @Inject
    public WeatherService(Application application,
                          OpenWeatherService openWeatherService, String locale) {
        this.application = application;
        this.openWeatherService = openWeatherService;
        this.locale = locale;
    }


    public Observable<ResponseWeatherByCityName> getWeatherByCityName(@NonNull String city) {
        return openWeatherService.getWeatherByCityName(city, API_KEY, locale)
                .flatMap(new Func1<ResponseWeatherByCityName, Observable<ResponseWeatherByCityName>>() {
                    @Override
                    public Observable<ResponseWeatherByCityName> call(ResponseWeatherByCityName response) {
                        if (TextUtils.isEmpty(response.message) && response.code == 200)
                            return Observable.just(response);

                        return Observable.error(new WeatherApiError(response.code, response.message));
                    }
                }
                );
    }


}
