package com.example.kirill.weather.data.api.weather;


import com.example.kirill.weather.data.api.weather.models.ResponseWeatherByCityName;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OpenWeatherService {

    @GET("/weather")
    Observable<ResponseWeatherByCityName>
    getWeatherByCityName(
            @Query(QueryParamNames.CITY_NAME)   String city,
            @Query(QueryParamNames.APP_ID)      String appKey,
            @Query(QueryParamNames.LANG)        String lang
    );


}
