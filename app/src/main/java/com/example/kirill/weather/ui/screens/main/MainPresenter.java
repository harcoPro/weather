package com.example.kirill.weather.ui.screens.main;

import android.app.Application;
import android.location.Location;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.example.kirill.weather.App;
import com.example.kirill.weather.R;
import com.example.kirill.weather.ui.misc.LocationUtils;
import com.example.kirill.weather.ui.misc.Utils;
import com.example.kirill.weather.ui.mvp.BasePresenter;
import com.google.android.gms.location.LocationRequest;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

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
    private ArrayList<String> cities = new ArrayList<>();

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
        addCity(city);
        getViewState().finishObtainUserCity();
        getViewState().obtainUserCitySuccess(cities);
    }

    private void addCity(String city) {
        if (!cities.contains(city)) {
            cities.add(city);
        }
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
        if (!LocationUtils.isGpsEnabled(app)) return Observable.error(new Throwable("Check your GPS settings enabled"));


        LocationRequest request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100);

        return new ReactiveLocationProvider(app)
                .getUpdatedLocation(request)
                .onErrorResumeNext(throwable -> Observable.error(new Throwable("Error request locations")))
                ;
    }

    public void clickAdd(String city) {
        getViewState().hideFormError();

        if (TextUtils.isEmpty(city)) {
            getViewState().showFormError(R.string.city_form_error);
            return;
        }

        addCity(city.trim());
        getViewState().updatePager(cities);
    }

    public void clickShowBottomSheet() {
        getViewState().showAddCityView();
    }

    public void bottomHidden() {
        getViewState().showPlusFab();
    }

}
