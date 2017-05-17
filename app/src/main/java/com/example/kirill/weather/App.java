package com.example.kirill.weather;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;


public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        Picasso built = builder.build();
        if (BuildConfig.DEBUG) {
            built.setIndicatorsEnabled(true);
            built.setLoggingEnabled(true);
        }

        Picasso.setSingletonInstance(built);

        buildComponentAndInject();
    }

    private void buildComponentAndInject() {
        component = AppComponent.Initializer.init(this);
        component.inject(this);
    }

    public static AppComponent component() {
        return component;
    }

    public static App get(@NonNull Context context) {
        return (App) context.getApplicationContext();
    }

}
