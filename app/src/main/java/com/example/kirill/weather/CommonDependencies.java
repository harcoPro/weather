package com.example.kirill.weather;


import com.example.kirill.weather.data.preferences.SPDependencies;

public interface CommonDependencies extends SPDependencies {

    void inject(App app);

}
