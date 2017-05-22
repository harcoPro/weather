package com.example.kirill.weather.ui.screens.first;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.kirill.weather.R;
import com.example.kirill.weather.ui.misc.MessageView;
import com.example.kirill.weather.ui.models.WeatherWithImage;
import com.example.kirill.weather.ui.mvp.BaseActivity;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;

public class FirstActivity extends BaseActivity implements FirstView {

    @BindView(R.id.fab)                             FloatingActionButton            fab;
    @BindView(R.id.first_screen_recycler_view)      RecyclerView                    recyclerView;
    @BindView(R.id.first_screen_city_image)         ImageView                       cityImage;

    @BindView(R.id.message_view)                    MessageView                     messageView;
    @BindView(R.id.smoke_view)                      View                            smoke;
    @BindView(R.id.first_screen_content)            View                            content;

    @InjectPresenter FirstPresenter presenter;

    private Navigator navigator = new Navigator() {
        @Override
        public void applyCommand(Command command) {

        }
    };

    @ProvidePresenter
    FirstPresenter provideFirstPresenter() {
        return new FirstPresenter(new RxPermissions(this), new ReactiveLocationProvider(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected Navigator navigator() {
        return navigator;
    }

    @Override
    public void startObtainUserWeather() {
        smoke.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        messageView.setVisibility(View.GONE);
    }

    @Override
    public void finishObtainUserWeather() {
        smoke.setVisibility(View.GONE);
    }

    @Override
    public void obtainUserWeatherSuccess(WeatherWithImage weather) {

    }

    @Override
    public void obtainUserWeatherFail(String message) {

    }
}
