package com.example.kirill.weather.ui.screens.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.kirill.weather.R;
import com.example.kirill.weather.ui.misc.MessageView;
import com.example.kirill.weather.ui.misc.Utils;
import com.example.kirill.weather.ui.mvp.BaseActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.fab)                         FloatingActionButton            fab;
    @BindView(R.id.pager)                       ViewPager                       pager;
    @BindView(R.id.tabs)                        TabLayout                       tabs;
    @BindView(R.id.main_screen_bottom_sheet)    FrameLayout                     bottomSheet;
    @BindView(R.id.main_screen_city_till)       TextInputLayout                 till;
    @BindView(R.id.main_screen_city)            EditText                        city;

    @BindView(R.id.message_view)                MessageView                     messageView;
    @BindView(R.id.smoke_view)                  View                            smoke;

    private BottomSheetBehavior behavior;
    private WeatherAdapter adapter;

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


        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    presenter.bottomHidden();
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }

        });
        fab.setOnClickListener(v -> {

            if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                presenter.clickShowBottomSheet();

            } else {
                presenter.clickAdd(city.getText().toString());
            }
        });
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
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
    public void obtainUserCitySuccess(ArrayList<String> cities) {
        pager.setVisibility(View.VISIBLE);
        tabs.setVisibility(View.VISIBLE);
        adapter = new WeatherAdapter(getSupportFragmentManager(), cities);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void obtainUserCityFailed(String message) {
        messageView.setVisibility(View.VISIBLE);
        messageView.bind(message, () -> presenter.requestPermissions());
    }

    @Override
    public void showAddCityView() {
        fab.setImageResource(R.drawable.ic_done_white_24dp);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Utils.showKeyboard(city);
    }

    @Override
    public void showFormError(Integer error) {
        till.setError(getString(error));
    }

    @Override
    public void hideFormError() {
        till.setError(null);
    }

    @Override
    public void updatePager(ArrayList<String> cities) {
        fab.setImageResource(R.drawable.ic_add_white_24dp);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        adapter.update(cities);
        pager.setCurrentItem(adapter.getLastItemPosition());
        city.setText("");
        Utils.hideKeyboard(city);
    }

    @Override
    public void showPlusFab() {
        fab.setImageResource(R.drawable.ic_add_white_24dp);
    }

}
