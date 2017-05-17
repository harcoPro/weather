package com.example.kirill.weather.data.api.weather;


import android.app.Application;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.Settings.System.DATE_FORMAT;

@Module
public class WeatherModule {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    public static final int CACHE_SIZE = 10 * 1024 * 1024;      //10 Mib

    @Provides
    @ApplicationScope
    @WeatherQualifier
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    @WeatherQualifier
    Gson provideGson() {
        return new GsonBuilder().setDateFormat(DATE_FORMAT).create();
    }

    @Provides
    @ApplicationScope
    @WeatherQualifier
    OkHttpClient provideSimpleClient(@WeatherQualifier Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @ApplicationScope
    @WeatherQualifier
    Retrofit provideRfetrofitStage(@WeatherQualifier OkHttpClient client, @WeatherQualifier Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }

    @Provides
    @ApplicationScope
    WeatherService provideWeatherService(@WeatherQualifier Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

}
