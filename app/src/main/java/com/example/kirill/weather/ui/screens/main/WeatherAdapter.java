package com.example.kirill.weather.ui.screens.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kirill.weather.ui.screens.weather.WeatherFragment;

import java.util.ArrayList;
import java.util.List;


public class WeatherAdapter extends FragmentPagerAdapter {

    private List<String> items = new ArrayList<>();

    public WeatherAdapter(FragmentManager fm, String city) {
        super(fm);
        this.items.add(city);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.start(items.get(position));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void addItem(String city) {
        if (!items.contains(city)) {
            items.add(city);
            notifyDataSetChanged();
        }
    }

}
