package com.example.kirill.weather.ui.screens.first;

import android.location.Location;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FirstView extends MvpView {

    void startObtainPermissions();
    void finishObtainPermissions();
    void permissionObtainFaled();

    void startObtainLocation();
    void finishObtainLocation();

    @StateStrategyType(SkipStrategy.class)
    void obtainLocationSuccessful(Location location);
    void obtainLocationFaled(Integer message);

    void showFormError(Integer cityError);
    void hideFormError();

}
