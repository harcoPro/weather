package com.example.kirill.weather.ui.screens.first;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.kirill.weather.R;
import com.example.kirill.weather.ui.mvp.BaseActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;

public class FirstActivity extends BaseActivity implements FirstView {

    @BindView(R.id.fab)                             FloatingActionButton            fab;
    @BindView(R.id.first_screen_recycler_view)      RecyclerView                    recyclerView;
    @BindView(R.id.first_screen_city_image)         ImageView                       cityImage;

    @InjectPresenter FirstPresenter presenter;

    private Navigator navigator = new Navigator() {
        @Override
        public void applyCommand(Command command) {

        }
    };

    @ProvidePresenter
    FirstPresenter provideFirstPresenter() {
        return new FirstPresenter(new RxPermissions(this));
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


    @OnClick(R.id.find_weather)
    void findWeather() {
        presenter.loadWeather();
    }


    @Override
    public void startObtainPermissions() {

    }

    @Override
    public void finishObtainPermissions() {

    }

    @Override
    public void permissionObtainFaled() {

    }

    @Override
    public void startObtainLocation() {

    }

    @Override
    public void finishObtainLocation() {

    }

    @Override
    public void obtainLocationSuccessful(Location location) {

    }

    @Override
    public void obtainLocationFaled(Integer message) {

    }

    @Override
    public void showFormError(Integer cityError) {

    }

    @Override
    public void hideFormError() {

    }
}
