package com.example.kirill.weather.ui.screens.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.kirill.weather.ui.models.WeatherWithImage;

import java.util.ArrayList;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void startObtainUserCity();
    void finishObtainUserCity();

    @StateStrategyType(SingleStateStrategy.class)
    void obtainUserCitySuccess(ArrayList<String> cities);
    void obtainUserCityFailed(String message);

    void showAddCityView();

    void showFormError(Integer error);
    void hideFormError();

    void updatePager(ArrayList<String> cities);

    void showPlusFab();
}
