package com.example.zhangxu.datepickerpractise.CustomDatepicker.internal;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangxu.datepickerpractise.CustomDatepicker.CustomDatePicker;
import com.example.zhangxu.datepickerpractise.CustomDatepicker.DateCallback;
import com.example.zhangxu.datepickerpractise.R;
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.AbstractWheel;
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.OnWheelScrollListener;
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.WheelVerticalView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class CustomDatePickerFragment extends Fragment {



    private View view;


    private DateCallback dateCallback;

    private DateBean dateBean;



    private ArrayList<Calendar> dayList = new ArrayList<>();

    private ArrayList<Calendar> monthList = new ArrayList<>();

    private ArrayList<Calendar> yearList = new ArrayList<>();

    private ArrayList<String> hourList = new ArrayList<>();
    private ArrayList<String> minuteList = new ArrayList<>();

    private Calendar startCalendar;
    private Calendar endCalendar;

    private Calendar currentCalendar;

    private int yearInterval = 1;
    private int monthInterval = 1;
    private int dayInterval = 1;
    private int hourInterval = 1;
    private int minuteInterval = 1;

    // 存放生成的wheel
    private Map<CustomDatePicker.TimeType, AbstractWheel> wheelMap = new HashMap<>();


    public CustomDatePickerFragment(DateBean dateBean, DateCallback dateCallback) {
        this.dateCallback = dateCallback;
        this.dateBean = dateBean;

        initData();

    }

    private void initData() {

        startCalendar = dateBean.getStartCalendar();
        endCalendar = dateBean.getEndCalendar();

        currentCalendar = dateBean.getShowCalendar();

        for (WheelTimeInfo wheelTimeInfo: dateBean.getTimeInfoArrayList()) {

            switch (wheelTimeInfo.getTimeType()) {
                case YEAR:
                    yearInterval = wheelTimeInfo.getInterval();
                    break;
                case MONTH:
                    monthInterval = wheelTimeInfo.getInterval();
                    break;
                case DAY:
                    dayInterval = wheelTimeInfo.getInterval();
                    break;
                case HOUR:
                    hourInterval = wheelTimeInfo.getInterval();
                    break;
                case MINUTE:
                    minuteInterval = wheelTimeInfo.getInterval();
                    break;

            }
        }

        initYear(yearInterval);
        initMonth(monthInterval);
        initDay(dayInterval);
        initHour(1, 23, hourInterval);
        initMinute(0, 59, minuteInterval);

    }


    // 初始化年份列表
    private void initYear(int interval) {

        Calendar calendar = (Calendar) startCalendar.clone();

        while (calendar.before(endCalendar) ||
                (calendar.after(endCalendar) && calendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR))) {
            yearList.add(calendar);
            calendar = (Calendar) calendar.clone();
            calendar.add(Calendar.YEAR, interval);

        }

    }


    // 初始化月份列表
    private void initMonth(int interval) {

        Calendar calendar = (Calendar) startCalendar.clone();

        while (calendar.before(endCalendar) ||
                (calendar.after(endCalendar) && calendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH))) {

            monthList.add(calendar);
            calendar = (Calendar) calendar.clone();
            calendar.add(Calendar.MONTH, interval);
        }
    }


    // 初始化天数列表
    private void initDay(int interval) {

        Calendar calendar = (Calendar) startCalendar.clone();

        while (calendar.before(endCalendar) ||
                (calendar.after(endCalendar) && calendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH))) {
            dayList.add(calendar);
            calendar = (Calendar) calendar.clone();
            calendar.add(Calendar.DAY_OF_MONTH, interval);

        }

    }

    // 初始化 小时列表
    private void initHour(int min, int max, int interval) {

        hourList.clear();
        for (int i = min; i <= max; i += interval) {
            if (i <= max ) {
                hourList.add(String.valueOf(i));
            }

        }
    }
    // 初始化分钟列表
    private void initMinute(int min, int max, int interval) {

        minuteList.clear();
        for (int i = min; i <= max; i+= interval) {

            minuteList.add(String.valueOf(i));

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view =  inflater.inflate(R.layout.fragment_custom_datepicker, container, false);

        initView();

        return view;
    }


    private void initView() {

        TextView titleTv = view.findViewById(R.id.title_text);
        titleTv.setText(dateBean.getTitle());

        LinearLayout wheelContainer = view.findViewById(R.id.wheelContainer);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;

        for (WheelTimeInfo wheelTimeInfo: dateBean.getTimeInfoArrayList()) {
            loadWheelView(wheelContainer, params, wheelTimeInfo);
        }


        view.findViewById(R.id.complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateCallback.onCallback(currentCalendar);
            }
        });

        updateWheel();


    }


    // 根据设置的Wheel个数,格式，动态加载Wheel
    private void loadWheelView(LinearLayout wheelContainer, LinearLayout.LayoutParams params, WheelTimeInfo timeInfo) {


        java.text.DateFormat dateFormat = timeInfo.getDateFormat();


        AbstractWheel wheel = (AbstractWheel) LayoutInflater.from(getActivity()).inflate(R.layout.wheel_item, null);

        wheel.setTag(timeInfo.getTimeType());
        wheel.setLayoutParams(params);
        wheel.setVisibleItems(dateBean.getVisible());


        WheelArrayAdapter arrayAdapter = new WheelArrayAdapter(getActivity());
        ArrayList<String> stringArrayList = new ArrayList<>();
        switch (timeInfo.getTimeType()) {
            case YEAR:
                stringArrayList = calendarToString(yearList, dateFormat);
                break;
            case MONTH:
                stringArrayList = calendarToString(monthList, dateFormat);
                break;
            case DAY:
                stringArrayList = calendarToString(dayList, dateFormat);
                break;
            case HOUR:
                stringArrayList = hourList;
                break;
            case MINUTE:
                stringArrayList = minuteList;
                break;

        }
        arrayAdapter.setTimes(stringArrayList);

        wheel.setViewAdapter(arrayAdapter);

        wheel.addScrollingListener(onWheelScrollListener);

        wheelMap.put(timeInfo.getTimeType(), wheel);

        wheelContainer.addView(wheel);

    }
    private OnWheelScrollListener onWheelScrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(AbstractWheel wheel) {

        }

        @Override
        public void onScrollingFinished(AbstractWheel wheel) {

            updateWheel(wheel);

        }
    };



    // 处理日期为相应的时间格式
    private ArrayList<String> calendarToString(ArrayList<Calendar> calendarArrayList, DateFormat dateFormat) {

        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Calendar calendar: calendarArrayList) {
            stringArrayList.add(dateFormat.format(calendar.getTime()));
        }
        return  stringArrayList;
    }


    // 根据当前时间，更新各个Wheel
    private void updateWheel() {
        for (Map.Entry wheel: wheelMap.entrySet()) {

            AbstractWheel abstractWheel = (AbstractWheel) wheel.getValue();

            switch ((CustomDatePicker.TimeType)wheel.getKey()) {
                case YEAR:

                    for (int i = 0; i < yearList.size(); i ++ ) {
                        if (yearList.get(i).get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
                            abstractWheel.setCurrentItem(i);
                        }
                    }
                    break;

                case MONTH:

                    for (int i = 0; i < monthList.size(); i ++ ) {
                        if (monthList.get(i).get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
                                && monthList.get(i).get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                            abstractWheel.setCurrentItem(i);
                        }
                    }
                    break;

                case DAY:
                    for (int i = 0; i < dayList.size(); i ++ ) {
                        if (dayList.get(i).get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
                                && dayList.get(i).get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
                                && dayList.get(i).get(Calendar.DAY_OF_MONTH) == currentCalendar.get(Calendar.DAY_OF_MONTH)) {
                            abstractWheel.setCurrentItem(i);
                        }
                    }
                    break;

                case HOUR:


                    for (int i = 0; i < hourList.size(); i ++ ) {
                        if (Integer.parseInt(hourList.get(i)) == currentCalendar.get(Calendar.HOUR_OF_DAY)) {
                            abstractWheel.setCurrentItem(i);
                        }
                    }

                    WheelArrayAdapter hourWheelAdapter = new WheelArrayAdapter(getActivity());

                    hourWheelAdapter.setTimes(hourList);

                    abstractWheel.setViewAdapter(hourWheelAdapter);


                    break;

                case MINUTE:


                    for (int i = 0; i < minuteList.size(); i ++ ) {
                        if (Integer.parseInt(minuteList.get(i)) == currentCalendar.get(Calendar.MINUTE)) {
                            abstractWheel.setCurrentItem(i);
                        }
                    }

                    WheelArrayAdapter minuteWheelAdapter = new WheelArrayAdapter(getActivity());

                    minuteWheelAdapter.setTimes(minuteList);

                    abstractWheel.setViewAdapter(minuteWheelAdapter);

                    break;
            }

        }
    }



    // 当Wheel滚动结束时，根据转动到的位置重新设置当前时间
    private void updateWheel(AbstractWheel wheel) {


        switch ((CustomDatePicker.TimeType)wheel.getTag()) {
            case YEAR:

                currentCalendar.set(Calendar.YEAR, yearList.get(wheel.getCurrentItem()).get(Calendar.YEAR));

                setCurrentCalendar();

                break;

            case MONTH:
                currentCalendar.set(Calendar.YEAR, monthList.get(wheel.getCurrentItem()).get(Calendar.YEAR));
                currentCalendar.set(Calendar.MONTH, monthList.get(wheel.getCurrentItem()).get(Calendar.MONTH));

                setCurrentCalendar();

                break;

            case DAY:
                currentCalendar.set(Calendar.YEAR, dayList.get(wheel.getCurrentItem()).get(Calendar.YEAR));
                currentCalendar.set(Calendar.MONTH, dayList.get(wheel.getCurrentItem()).get(Calendar.MONTH));
                currentCalendar.set(Calendar.DAY_OF_MONTH, dayList.get(wheel.getCurrentItem()).get(Calendar.DAY_OF_MONTH));

                setCurrentCalendar();

                break;

            case HOUR:
                currentCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourList.get(wheel.getCurrentItem())));

                setCurrentCalendar();
                break;

            case MINUTE:
                currentCalendar.set(Calendar.MINUTE, Integer.parseInt(minuteList.get(wheel.getCurrentItem())));
                break;
        }


        updateWheel();
    }


    // 设置当前时间
    private void setCurrentCalendar() {
        if (currentCalendar.after(endCalendar) || currentCalendar.equals(endCalendar)) {
            currentCalendar = (Calendar) endCalendar.clone();

            int hour_max = endCalendar.get(Calendar.HOUR_OF_DAY);
            initHour(1, hour_max, hourInterval);

            int minute_max = endCalendar.get(Calendar.MINUTE);
            initMinute(0, minute_max, minuteInterval);
        } else if (currentCalendar.before(startCalendar) || currentCalendar.equals(startCalendar)) {
            currentCalendar = (Calendar) startCalendar.clone();

            int hour_min = startCalendar.get(Calendar.HOUR_OF_DAY);
            initHour(hour_min, 24, hourInterval);

            int munite_min = startCalendar.get(Calendar.MINUTE);
            initMinute(munite_min, 59, minuteInterval);
        } else {
            initHour(1, 23, hourInterval);
            initMinute(0, 59, minuteInterval);
        }
    }





}
