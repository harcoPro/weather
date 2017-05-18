package com.example.kirill.weather.data.api;

import android.accounts.NetworkErrorException;
import android.content.res.Resources;
import android.text.TextUtils;

import com.example.kirill.weather.R;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

public class ApiError extends Throwable {

    public int code;
    public String msg;

    public ApiError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiError from(int code, Resources resources) {
        int c = code;
        int resId = R.string.error_api;

        switch (code) {
            case -1:
                resId = R.string.error_api_network_disabled;
                break;
        }
        return new ApiError(c, resources.getString(resId));
    }

    public static ApiError from(Throwable throwable, Resources resources) {
        if (throwable instanceof UnknownHostException
                || throwable instanceof NetworkErrorException) {
            return from(-1, resources);

        } else if (throwable instanceof HttpException) {
            HttpException ex = (HttpException) throwable;
            return from(ex.response().code(), resources);
        } else {
            return from(0, resources);
        }
    }

    @Override
    public String getMessage() {
        if (TextUtils.isEmpty(msg))
            return super.getMessage();
        return msg;
    }


}
