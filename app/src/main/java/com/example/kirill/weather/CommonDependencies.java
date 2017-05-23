package com.example.kirill.weather;


import com.example.kirill.weather.data.preferences.SPDependencies;
import com.example.kirill.weather.ui.screens.first.FirstPresenter;
import com.example.kirill.weather.ui.screens.weather.WeatherPresenter;

public interface CommonDependencies extends SPDependencies {

    void inject(App app);

    void inject(FirstPresenter firstPresenter);

    void inject(WeatherPresenter weatherPresenter);
}
