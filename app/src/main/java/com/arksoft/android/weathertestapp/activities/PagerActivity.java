package com.arksoft.android.weathertestapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arksoft.android.weathertestapp.R;
import com.arksoft.android.weathertestapp.fragments.LocationFragment;

public class PagerActivity extends AppCompatActivity {
    private FragmentManager fm;
    private Fragment fragment;
    private ViewPager mViewPager;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("PagerA", "OnDestroy");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        mViewPager = findViewById(R.id.view_pager);
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position){
                    case 0:
                        return LocationFragment.newInstance(0);

                    case 1:
                        return LocationFragment.newInstance(1);

                    case 2:
                        return LocationFragment.newInstance(2);

                    case 3:
                        return LocationFragment.newInstance(3);

                    case 4:
                        return LocationFragment.newInstance(4);

                    default: return LocationFragment.newInstance(0);
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.current_location_menu:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.new_york_menu:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.tokyo_menu:
                mViewPager.setCurrentItem(2);
                return true;
            case R.id.roma_menu:
                mViewPager.setCurrentItem(3);
                return true;
            case R.id.moscu_menu:
                mViewPager.setCurrentItem(4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
