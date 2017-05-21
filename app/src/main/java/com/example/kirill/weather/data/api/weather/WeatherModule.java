package com.example.kirill.weather.data.api.weather;


import android.app.Application;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.BuildConfig;
import com.example.kirill.weather.data.preferences.qualifiers.LocaleQualifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.Settings.System.DATE_FORMAT;

@Module
public class WeatherModule {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_KEY = "df45f890a0b873e8ffea573ef07339b6";
    public static final String APP_ID = "APPID";
    public static final String LANG = "lang";


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
    OkHttpClient provideSimpleClient(@WeatherQualifier Cache cache,
                                     @LocaleQualifier String locale) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        builder.addInterceptor(
                chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter(APP_ID, API_KEY)
                            .addQueryParameter(LANG, locale)
                            .build();

                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
        );

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
