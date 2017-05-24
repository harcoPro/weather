package com.example.kirill.weather.data.api.pixabay;

import android.app.Application;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.BuildConfig;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class PixabayApiModule {

    private static final String API_URL = "https://pixabay.com/";
    private static final String FLICKR_KEY = "5440025-4746243edc124c2f2fdca485b";
    private static final String API_KEY = "key";


    public static final int CACHE_SIZE = 10 * 1024 * 1024;      //10 Mib

    @Provides
    @ApplicationScope
    @PixabayQualifier
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    @PixabayQualifier
    Moshi provideMoshi() {
        return new Moshi.Builder().build();
    }


    @Provides
    @ApplicationScope
    @PixabayQualifier
    OkHttpClient provideSimpleClient(@PixabayQualifier Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache);

        builder.addInterceptor(
                chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter(API_KEY, FLICKR_KEY)
                            .build();

                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
        );


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @ApplicationScope
    @PixabayQualifier
    Retrofit provideRfetrofitStage(@PixabayQualifier OkHttpClient client, @PixabayQualifier Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }

    @Provides
    @ApplicationScope
    PixabayService provideImageService(@PixabayQualifier Retrofit retrofit) {
        return retrofit.create(PixabayService.class);
    }

}
