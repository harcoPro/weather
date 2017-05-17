package com.example.kirill.weather.ui.screens.first;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kirill.weather.R;
import com.example.kirill.weather.ui.mvp.BaseActivity;

import butterknife.BindView;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;

public class FirstActivity extends BaseActivity {

    @BindView(R.id.fab) FloatingActionButton fab;

    private Navigator navigator = new Navigator() {
        @Override
        public void applyCommand(Command command) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected Navigator navigator() {
        return navigator;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
