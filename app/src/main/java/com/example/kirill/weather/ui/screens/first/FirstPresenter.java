package com.example.kirill.weather.ui.screens.first;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.kirill.weather.App;
import com.example.kirill.weather.data.DataService;
import com.example.kirill.weather.data.api.flickr.FlickrApiService;
import com.example.kirill.weather.data.api.flickr.FlickrService;
import com.example.kirill.weather.data.api.weather.WeatherService;
import com.example.kirill.weather.ui.misc.Utils;
import com.example.kirill.weather.ui.mvp.BasePresenter;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import rx.Subscription;

@InjectViewState
public class FirstPresenter extends BasePresenter<FirstView> {

    @Inject
    public WeatherService weatherService;

    @Inject
    public FlickrApiService flickrService;

    public final RxPermissions permissions;

    public FirstPresenter(RxPermissions rxPermissions) {
        this.permissions = rxPermissions;
        App.component().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }


    public void loadWeather() {
        Subscription s = flickrService
                .info("34004319823")
                .compose(Utils.applySchedulers())
                .subscribe(
                        response -> {
                            Log.d("TAG", "response: " + response);
                        },

                        throwable -> {
                            throwable.printStackTrace();
                        }
                );
        unsubscribeOnDestroy(s);
    }

}
