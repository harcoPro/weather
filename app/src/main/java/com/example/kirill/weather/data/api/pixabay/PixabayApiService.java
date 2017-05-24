package com.example.kirill.weather.data.api.pixabay;

import android.app.Application;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.ApiError;
import com.example.kirill.weather.data.api.pixabay.models.ImageRest;
import com.example.kirill.weather.data.api.pixabay.models.PixabayResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@ApplicationScope
public class PixabayApiService {

    private final Application app;
    private final PixabayService pixabayService;

    @Inject
    public PixabayApiService(Application app, PixabayService pixabayService) {
        this.app = app;
        this.pixabayService = pixabayService;
    }

    public Observable<List<ImageRest>> search(String city) {
        return pixabayService.search(city)
                .onErrorResumeNext(throwable -> Observable.error(ApiError.from(throwable, app.getResources())))
                .flatMap(this::handleApiResponse)
                .map(response -> response.images)
                ;
    }

    private <R extends PixabayResponse> Observable<R> handleApiResponse(R response) {
        if (response.total != null && response.total == 0) {
            return Observable.error(new PixabayApiError(300, "Pixabay load error"));
        } else {
            return Observable.just(response);
        }
    }
}
