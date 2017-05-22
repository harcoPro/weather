package com.example.kirill.weather.ui.misc;

import android.content.Context;
import android.location.LocationManager;

public class LocationUtils {

    private LocationUtils(){
        throw new AssertionError("No instances!");
    }

    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isNetworkEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static boolean isLocationEnabled(Context context) {
        return isGpsEnabled(context) && isNetworkEnabled(context);
    }

}
