package com.arksoft.android.weathertestapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arksoft.android.weathertestapp.R;
import com.arksoft.android.weathertestapp.activities.MainActivity;
import com.arksoft.android.weathertestapp.interfaces.BroadcastKey;
import com.arksoft.android.weathertestapp.model.LocationWeather;
import com.arksoft.android.weathertestapp.singleton.LocationsTon;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LocationFragment extends Fragment {

    private TextView mCurrentWeather;
    private TextView mLocationTitle;
    private TextView mDescription;
    private ImageView mImageView;
    private static final String BASE_URL_IMAGE = "http://openweathermap.org/img/w/";
    private TextView mCurrentDay;
    private TextView mDay2;
    private TextView mDay2MinTemp;
    private TextView mDay2MaxTemp;
    private TextView mDay3;
    private TextView mDay3MinTemp;
    private TextView mDay3MaxTemp;
    private TextView mDay4;
    private TextView mDay4MinTemp;
    private TextView mDay4MaxTemp;
    private TextView mDay5;
    private TextView mDay5MinTemp;
    private TextView mDay5MaxTemp;
    private static  final String ARG_LOC_NUM = "0";
    private int locaNumber;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private IntentFilter myIntentFilter;
    private BroadcastReceiverFragment mBroadcastReceiverFragment;

    public static LocationFragment newInstance(int currentLocationNumber) {
        Bundle args = new Bundle();
        args.putInt(ARG_LOC_NUM, currentLocationNumber);
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myIntentFilter = new IntentFilter();
        mBroadcastReceiverFragment = new BroadcastReceiverFragment();
        myIntentFilter.addAction(BroadcastKey.UPDATE_KEY_FINISHED);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverFragment, myIntentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiverFragment);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.location_fragment, container, false);
        locaNumber = getArguments().getInt(ARG_LOC_NUM);


        mLocationTitle = view.findViewById(R.id.location_txt_view);
        mDescription = view.findViewById(R.id.current_weather_descp_text_view);
        mImageView = view.findViewById(R.id.current_weather_img_view);
        mCurrentWeather = view.findViewById(R.id.current_weather_txt_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_to_referesh);

        mCurrentDay = view.findViewById(R.id.current_day_txt_view);
        mDay2 = view.findViewById(R.id.day_2_txt_view);
        mDay3 = view.findViewById(R.id.day_3_txt_view);
        mDay4 = view.findViewById(R.id.day_4_txt_view);
        mDay5 = view.findViewById(R.id.day_5_txt_view);

        mDay2MinTemp = view.findViewById(R.id.day_2_min_temp_txt_view);
        mDay2MaxTemp = view.findViewById(R.id.day_2_max_temp_txt_view);
        mDay3MinTemp = view.findViewById(R.id.day_3_min_temp_txt_view);
        mDay3MaxTemp = view.findViewById(R.id.day_3_max_temp_txt_view);
        mDay4MinTemp = view.findViewById(R.id.day_4_min_temp_txt_view);
        mDay4MaxTemp = view.findViewById(R.id.day_4_max_temp_txt_view);
        mDay5MinTemp = view.findViewById(R.id.day_5_min_temp_txt_view);
        mDay5MaxTemp = view.findViewById(R.id.day_5_max_temp_txt_view);
        setDaysTextViews();

        switch (locaNumber){
            case 0:
                displayInfo(LocationsTon.get().getCurrentLocation());
                break;
            case 1:
                displayInfo(LocationsTon.get().getNewYorkLocation());
                break;
            case 2:
                displayInfo(LocationsTon.get().getTokyoLocation());
                break;
            case 3:
                displayInfo(LocationsTon.get().getRomeLocation());
                break;
            case 4:
                displayInfo(LocationsTon.get().getMoscowLocation());
                break;
        }
        updateView();
        pullToRefresh();
        return view;
    }

    public void pullToRefresh(){

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent local123 = new Intent();
                local123.setAction(BroadcastKey.UPDATE_KEY_BROADCAST);
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(local123);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void displayInfo(LocationWeather location){

        mLocationTitle.setText(location.getCity().getName());
        mDescription.setText((location.getList().get(0).getWeather().get(0).getDescription()));
        mCurrentWeather.setText(location.getList().get(0).getMain().getTempCel() + "°C");
        Picasso.get()
                .load(BASE_URL_IMAGE + location.getList().get(0).getWeather().get(0).getIcon() + ".png")
                .into(mImageView);

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();

        for(int i = 0; i< location.getList().size(); i++) {

            int temp = location.getList().get(i).getMain().getTempCel();
            String weatherDate = location.getList().get(i).getDt_txt().substring(0, 10);
            if (weatherDate.equals(addDayToCurrentDate(1))) {
                list.add(temp);
                Collections.sort(list);
            }
            else if(weatherDate.equals(addDayToCurrentDate(2))){
                list2.add(temp);
                Collections.sort(list2);
            }
            else if(weatherDate.equals(addDayToCurrentDate(3))) {
                list3.add(temp);
                Collections.sort(list3);
            }
            else if(weatherDate.equals(addDayToCurrentDate(4))) {
                list4.add(temp);
                Collections.sort(list4);
            }
        }

        if(list.size() > 0 && list2.size() > 0 && list3.size() > 0 && list4.size() > 0) {
            mDay2MinTemp.setText("" + list.get(0));
            mDay2MaxTemp.setText("" + list.get(list.size() - 1));
            mDay3MinTemp.setText("" + list2.get(0));
            mDay3MaxTemp.setText("" + list2.get(list2.size() - 1));
            mDay4MinTemp.setText("" + list3.get(0));
            mDay4MaxTemp.setText("" + list3.get(list3.size() - 1));
            mDay5MinTemp.setText("" + list4.get(0));
            mDay5MaxTemp.setText("" + list4.get(list4.size() - 1));
        }
        else {
            Toast.makeText(getActivity(), "Error displaying extended forecast...Check your device date", Toast.LENGTH_LONG).show();
        }
    }

    public String addDayToCurrentDate(int days){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, days); // Adding days
        String date = sdf.format(c.getTime());
        return date;
    }

    public void updateView(){

        switch (locaNumber){
            case 0:
                displayInfo(LocationsTon.get().getCurrentLocation());
                break;
            case 1:
                displayInfo(LocationsTon.get().getNewYorkLocation());
                break;
            case 2:
                displayInfo(LocationsTon.get().getTokyoLocation());
                break;
            case 3:
                displayInfo(LocationsTon.get().getRomeLocation());
                break;
            case 4:
                displayInfo(LocationsTon.get().getMoscowLocation());
                break;
        }
    }

    public void setDaysTextViews(){

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                mCurrentDay.setText(R.string.sunday);
                mDay2.setText(R.string.monday);
                mDay3.setText(R.string.tuesday);
                mDay4.setText(R.string.wednesday);
                mDay5.setText(R.string.thursday);
                break;
            case Calendar.MONDAY:
                mCurrentDay.setText(R.string.monday);
                mDay2.setText(R.string.tuesday);
                mDay3.setText(R.string.wednesday);
                mDay4.setText(R.string.thursday);
                mDay5.setText(R.string.friday);
                break;
            case Calendar.TUESDAY:
                mCurrentDay.setText(R.string.tuesday);
                mDay2.setText(R.string.wednesday);
                mDay3.setText(R.string.thursday);
                mDay4.setText(R.string.friday);
                mDay5.setText(R.string.saturday);
                break;
            case Calendar.WEDNESDAY:
                mCurrentDay.setText(R.string.wednesday);
                mDay2.setText(R.string.thursday);
                mDay3.setText(R.string.friday);
                mDay4.setText(R.string.saturday);
                mDay5.setText(R.string.sunday);
                break;
            case Calendar.THURSDAY:
                mCurrentDay.setText(R.string.thursday);
                mDay2.setText(R.string.friday);
                mDay3.setText(R.string.saturday);
                mDay4.setText(R.string.sunday);
                mDay5.setText(R.string.monday);
                break;
            case Calendar.FRIDAY:
                mCurrentDay.setText(R.string.friday);
                mDay2.setText(R.string.saturday);
                mDay3.setText(R.string.sunday);
                mDay4.setText(R.string.monday);
                mDay5.setText(R.string.tuesday);
                break;
            case Calendar.SATURDAY:
                mCurrentDay.setText(R.string.saturday);
                mDay2.setText(R.string.sunday);
                mDay3.setText(R.string.monday);
                mDay4.setText(R.string.tuesday);
                mDay5.setText(R.string.wednesday);
                break;
        }
    }

    public class BroadcastReceiverFragment extends BroadcastReceiver {

        public BroadcastReceiverFragment(){
            super();
        }
        @Override public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null)
            {
                switch (intent.getAction()) {

                    case BroadcastKey.UPDATE_KEY_FINISHED:

                        updateView();

                        break;
                }
            }
        }
    }
}
