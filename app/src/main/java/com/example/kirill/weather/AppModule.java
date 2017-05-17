package com.example.kirill.weather;

import android.app.Application;


import com.example.kirill.weather.data.preferences.qualifiers.LocaleQualifier;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application app) {
        application = app;
    }

    @Provides
    @ApplicationScope
    Application providesApplication() {
        return application;
    }

    @Provides
    @ApplicationScope
    @LocaleQualifier
    String provideLocale(Application application) {
        return application.getString(R.string.locale);
    }

}
