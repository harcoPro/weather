package com.example.kirill.weather.data.api.pixabay;


import com.example.kirill.weather.data.api.pixabay.models.PixabayResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PixabayService {

    @GET("/api")
    Observable<PixabayResponse> search(@Query("q") String city);

}
