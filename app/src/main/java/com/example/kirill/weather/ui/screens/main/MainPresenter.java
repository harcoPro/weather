package com.example.kirill.weather.ui.screens.main;

import android.app.Application;
import android.location.Location;
import android.text.TextUtils;

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
public class MainPresenter extends BasePresenter<MainView> {

    @Inject public Application app;

    public final RxPermissions permissions;

    public MainPresenter(RxPermissions rxPermissions) {
        this.permissions = rxPermissions;
        App.component().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        requestPermissions();
    }

    public void requestPermissions() {
        getViewState().startObtainUserCity();
        Subscription s = permissions
                .request(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
                .flatMap(this::getLocation)
                .subscribe(this::getCity, this::onError);
        unsubscribeOnDestroy(s);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        getViewState().finishObtainUserCity();
        getViewState().obtainUserCityFailed(throwable.getMessage());
    }

    private void onResult(String city) {
        getViewState().finishObtainUserCity();
        getViewState().obtainUserCitySuccess(city);
    }

    private void getCity(Location location) {
        Subscription s = new ReactiveLocationProvider(app)
                .getReverseGeocodeObservable(location.getLatitude(), location.getLongitude(), 1)
                .flatMap(
                        list -> {
                            if (list.size() > 0 && !TextUtils.isEmpty(list.get(0).getLocality()))
                                return Observable.just(list.get(0).getLocality());

                            else
                                return Observable.error(new Throwable("Error obtained city name from location!"));
                        }
                )
                .compose(Utils.applySchedulers())
                .subscribe(this::onResult, this::onError);

        unsubscribeOnDestroy(s);
    }

    @SuppressWarnings("MissingPermission")
    private Observable<Location> getLocation(Boolean permissionGranted) {
        if (!permissionGranted) return Observable.error(new Throwable("For work application need location permissions!"));

        LocationRequest request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100);

        return new ReactiveLocationProvider(app)
                .getUpdatedLocation(request)
                .onErrorResumeNext(throwable -> Observable.error(new Throwable("Error request locations")))
                ;
    }



}
