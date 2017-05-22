package com.example.kirill.weather.ui.screens.first;

import android.location.Location;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.kirill.weather.ui.models.Weather;
import com.example.kirill.weather.ui.models.WeatherWithImage;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FirstView extends MvpView {

    void startObtainUserWeather();
    void finishObtainUserWeather();
    void obtainUserWeatherSuccess(WeatherWithImage weather);
    void obtainUserWeatherFail(String message);
}
