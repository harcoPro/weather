package com.example.kirill.weather.ui.screens.weather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.example.kirill.weather.App;
import com.example.kirill.weather.data.DataService;
import com.example.kirill.weather.ui.misc.Utils;
import com.example.kirill.weather.ui.mvp.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class WeatherPresenter extends BasePresenter<WeatherView> {

    @Inject
    DataService dataService;
    private String city;

    public WeatherPresenter(@NonNull String city) {
        this.city = city;
        App.component().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadWeather();
    }

    public void loadWeather() {
        getViewState().startLoadingWeather();
        Subscription s = dataService
                .getWeatherWithImageByCity(city)
                .compose(Utils.applySchedulers())
                .subscribe(
                        weather -> {
                            getViewState().finishLoadingWeather();
                            getViewState().loadingWeatherSuccess(weather);
                        },
                        error -> {
                            getViewState().finishLoadingWeather();
                            getViewState().loadingWeatherFail(error.getMessage());
                        }
                );

        unsubscribeOnDestroy(s);
    }
}

