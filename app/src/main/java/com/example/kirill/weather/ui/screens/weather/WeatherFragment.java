package com.example.kirill.weather.ui.screens.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.kirill.weather.R;
import com.example.kirill.weather.ui.misc.MessageView;
import com.example.kirill.weather.ui.models.WeatherWithImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {
    private static final String BF_CITY     =   "WeatherFragment.City";

    @BindView(R.id.weather_fragment_image)              ImageView       image;
    @BindView(R.id.weather_fragment_degrees)            TextView        degrees;

    @BindView(R.id.smoke_view)                          View            smoke;
    @BindView(R.id.message_view)                        MessageView     messageView;
    @BindView(R.id.weather_fragment_content)            View            content;

    @InjectPresenter WeatherPresenter presenter;

    @ProvidePresenter WeatherPresenter provide() {
        String city = getArguments().getString(BF_CITY);
        return new WeatherPresenter(city);
    }

    public static WeatherFragment start(String city) {
        Bundle bundle = new Bundle();
        bundle.putString(BF_CITY, city);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void startLoadingWeather() {
        smoke.setVisibility(View.VISIBLE);
        messageView.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
    }

    @Override
    public void finishLoadingWeather() {
        smoke.setVisibility(View.GONE);
    }

    @Override
    public void loadingWeatherFail(String message) {
        messageView.setVisibility(View.VISIBLE);
        messageView.bind(message, () -> presenter.loadWeather());
    }

    @Override
    public void loadingWeatherSuccess(WeatherWithImage weather) {
        content.setVisibility(View.VISIBLE);
        Picasso.with(null).load(weather.cityImage.url).into(image);
        degrees.setText(weather.weather.temp + " " + weather.weather.description);
    }

}
