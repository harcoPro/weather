package com.example.kirill.weather;


import com.example.kirill.weather.data.preferences.SPDependencies;
import com.example.kirill.weather.ui.screens.main.MainPresenter;
import com.example.kirill.weather.ui.screens.weather.WeatherPresenter;

public interface CommonDependencies extends SPDependencies {

    void inject(App app);

    void inject(MainPresenter mainPresenter);

    void inject(WeatherPresenter weatherPresenter);
}
