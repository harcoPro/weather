package com.example.kirill.weather.data;

import android.app.Application;

import com.example.kirill.weather.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class DataService {

    private final Application app;

    @Inject
    public DataService(Application app) {
        this.app = app;
    }


}
