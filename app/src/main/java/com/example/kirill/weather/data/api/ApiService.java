package com.example.kirill.weather.data.api;

import android.app.Application;

import com.example.kirill.weather.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class ApiService {

    private final Application app;

    @Inject
    public ApiService(Application app) {
        this.app = app;
    }

}
