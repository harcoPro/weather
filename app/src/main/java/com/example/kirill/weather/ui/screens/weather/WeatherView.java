package com.example.kirill.weather.ui.screens.weather;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.kirill.weather.ui.models.WeatherWithImage;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherView extends MvpView {

    void startLoadingWeather();
    void finishLoadingWeather();
    void loadingWeatherFail(String message);

    @StateStrategyType(SingleStateStrategy.class)
    void loadingWeatherSuccess(WeatherWithImage weather);

}
