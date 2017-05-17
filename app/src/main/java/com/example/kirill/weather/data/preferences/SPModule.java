package com.example.kirill.weather.data.preferences;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.preferences.qualifiers.TestQualifiers;
import com.example.kirill.weather.data.preferences.types.StringPreference;

import dagger.Module;
import dagger.Provides;

@Module
public class SPModule {

    public static final String SP_NAME                                  = "WEATHER";
    public static final String SP_TEST                                  = "token";


    @Provides
    @ApplicationScope
    SharedPreferences provideSP(Application application) {
        return application.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @ApplicationScope
    @TestQualifiers
    StringPreference proviedTest(SharedPreferences sp) {
        return new StringPreference(sp, SP_TEST);
    }


}
