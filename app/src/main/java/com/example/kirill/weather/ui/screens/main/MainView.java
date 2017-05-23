package com.example.kirill.weather.ui.screens.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.kirill.weather.ui.models.WeatherWithImage;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void startObtainUserCity();
    void finishObtainUserCity();

    @StateStrategyType(SingleStateStrategy.class)
    void obtainUserCitySuccess(String city);
    void obtainUserCityFailed(String message);
}
