package com.example.kirill.weather.data.api.flickr;

import android.app.Application;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.BuildConfig;
import com.example.kirill.weather.data.api.weather.WeatherQualifier;
import com.example.kirill.weather.data.api.weather.WeatherService;
import com.example.kirill.weather.data.preferences.qualifiers.LocaleQualifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Inject;

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
public class FlickrApiModule {

    private static final String FLICKR_URL = "https://api.flickr.com/services/";
    private static final String FLICKR_KEY = "577d1193f73f7909486aec520de3cdb8";
    private static final String API_KEY = "api_key";
    private static final String FORMAT = "format";
    private static final String JSON = "json";


    public static final int CACHE_SIZE = 10 * 1024 * 1024;      //10 Mib

    @Provides
    @ApplicationScope
    @FlickrQualifier
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    @FlickrQualifier
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @ApplicationScope
    @FlickrQualifier
    OkHttpClient provideSimpleClient(@FlickrQualifier Cache cache, @LocaleQualifier String locale) {
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
                            .addQueryParameter(API_KEY, FLICKR_KEY)
                            .addQueryParameter(FORMAT, JSON)
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
    @FlickrQualifier
    Retrofit provideRfetrofitStage(@FlickrQualifier OkHttpClient client, @FlickrQualifier Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(FLICKR_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }

    @Provides
    @ApplicationScope
    FlickrService provideWeatherService(@FlickrQualifier Retrofit retrofit) {
        return retrofit.create(FlickrService.class);
    }

}
