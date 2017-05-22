package com.example.kirill.weather.ui.screens.first;

import android.location.Location;

import com.arellomobile.mvp.InjectViewState;
import com.example.kirill.weather.App;
import com.example.kirill.weather.data.DataService;
import com.example.kirill.weather.ui.misc.Utils;
import com.example.kirill.weather.ui.mvp.BasePresenter;
import com.google.android.gms.location.LocationRequest;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.Subscription;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

@InjectViewState
public class FirstPresenter extends BasePresenter<FirstView> {

    @Inject
    public DataService dataService;

    public final RxPermissions permissions;
    public final ReactiveLocationProvider rxLocations;

    public FirstPresenter(RxPermissions rxPermissions,
                          ReactiveLocationProvider rxLocations) {
        this.permissions = rxPermissions;
        this.rxLocations = rxLocations;
        App.component().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        requestPermissions();
    }

    public void requestPermissions() {
        getViewState().startObtainUserWeather();
        Subscription s = permissions
                .request(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
                .flatMap(this::getLocation)
                .subscribe(
                        this::loadWeather,
                        throwable -> {
                            getViewState().finishObtainUserWeather();
                            getViewState().obtainUserWeatherFail(throwable.getMessage());
                        }
                );
        unsubscribeOnDestroy(s);
    }

    private void loadWeather(Location location) {
        Subscription s = dataService
                .getWeatherByLocation(location)
                .compose(Utils.applySchedulers())
                .subscribe(
                        weather -> {
                            getViewState().finishObtainUserWeather();
                            getViewState().obtainUserWeatherSuccess(weather);
                        },
                        error -> {
                            getViewState().finishObtainUserWeather();
                            getViewState().obtainUserWeatherFail(error.getMessage());
                        }
                );
        unsubscribeOnDestroy(s);
    }

    public void loadWeather(String city) {
        Subscription s = dataService
                .getWeatherWithImageByCity(city)
                .compose(Utils.applySchedulers())
                .subscribe(
                        weather -> {
                        },
                        error -> {
                        }
                );
        unsubscribeOnDestroy(s);
    }

    @SuppressWarnings("MissingPermission")
    private Observable<Location> getLocation(Boolean permissionGranted) {
        if (!permissionGranted) return Observable.error(new Throwable("For work application need location permissions!"));

        LocationRequest request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100);

        return rxLocations
                .getUpdatedLocation(request)
                .onErrorResumeNext(throwable -> Observable.error(new Throwable("Error request locations")))
                ;
    }

}
