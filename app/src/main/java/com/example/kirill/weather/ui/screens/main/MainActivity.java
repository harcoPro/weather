package com.example.kirill.weather.ui.screens.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.fab)                 FloatingActionButton            fab;
    @BindView(R.id.pager)               ViewPager                       pager;
    @BindView(R.id.tabs)                TabLayout                       tabs;
    @BindView(R.id.message_view)        MessageView                     messageView;
    @BindView(R.id.smoke_view)          View                            smoke;

    @InjectPresenter    MainPresenter   presenter;

    @ProvidePresenter
    MainPresenter provideFirstPresenter() {
        return new MainPresenter(new RxPermissions(this));
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
    public void startObtainUserCity() {
        pager.setVisibility(View.GONE);
        tabs.setVisibility(View.GONE);
        messageView.setVisibility(View.GONE);
        smoke.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishObtainUserCity() {
        smoke.setVisibility(View.GONE);
    }

    @Override
    public void obtainUserCitySuccess(String city) {
        pager.setVisibility(View.VISIBLE);
        tabs.setVisibility(View.VISIBLE);
//        TODO implements update adapter here
    }

    @Override
    public void obtainUserCityFailed(String message) {
        messageView.setVisibility(View.VISIBLE);
        messageView.bind(message, () -> presenter.requestPermissions());
    }

}
