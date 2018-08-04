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
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.OnWheelChangedListener;
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.OnWheelScrollListener;
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.WheelVerticalView;

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



    private ArrayList<Integer> dayList = new ArrayList<>();
    private ArrayList<Integer> monthList = new ArrayList<>();
    private ArrayList<Integer> yearList = new ArrayList<>();
    private ArrayList<Integer> hourList = new ArrayList<>();
    private ArrayList<Integer> minuteList = new ArrayList<>();

    private Calendar startCalendar;
    private Calendar endCalendar;

    private Calendar currentCalendar;


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

        initYear();

        initMonth();
        initDay();
        initHour();
        initMinute();




    }


    private void initYear() {

        yearList.clear();
        for (int i = startCalendar.get(Calendar.YEAR); i <= endCalendar.get(Calendar.YEAR); i++) {
            yearList.add(i);
        }
    }

    private void initMonth() {

        monthList.clear();
        if (currentCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)) {

            for (int i = startCalendar.get(Calendar.MONTH); i <= Calendar.DECEMBER; i++) {
                monthList.add(i + 1);
            }

            if (currentCalendar.before(startCalendar)) {
                currentCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH));
            }
            return;
        }

        if (currentCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR)) {
            for (int i = Calendar.JANUARY; i <= endCalendar.get(Calendar.MONTH); i++) {
                monthList.add(i + 1);
            }

            if (currentCalendar.after(endCalendar)) {
                currentCalendar.set(Calendar.MONTH, endCalendar.get(Calendar.MONTH));
            }

            return;
        }

        for (int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {
            monthList.add(i + 1);
        }


    }

    private void initDay() {

        dayList.clear();

        if (currentCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)) {


            for (int i = startCalendar.get(Calendar.DAY_OF_MONTH);
                 i <= currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                dayList.add(i);
            }

            if (currentCalendar.before(startCalendar)) {
                currentCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.get(Calendar.DAY_OF_MONTH));
            }

            return;
        }

        if (currentCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)) {

            for (int i = currentCalendar.getActualMinimum(Calendar.DAY_OF_MONTH);
                 i <= endCalendar.get(Calendar.DAY_OF_MONTH); i++) {
                dayList.add(i);
            }

            if (currentCalendar.after(endCalendar)) {
                currentCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.get(Calendar.DAY_OF_MONTH));
            }

            return;
        }

        for (int i = currentCalendar.getActualMinimum(Calendar.DAY_OF_MONTH);
             i <= currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            int max = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            dayList.add(i);
        }

    }

    private void initHour() {

        hourList.clear();
        if (currentCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)
                && currentCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)) {

            for (int i = startCalendar.get(Calendar.HOUR_OF_DAY);
                 i <= currentCalendar.getActualMaximum(Calendar.HOUR_OF_DAY); i++) {
                hourList.add(i);
            }

            if (currentCalendar.before(startCalendar)) {
                currentCalendar.set(Calendar.HOUR_OF_DAY, startCalendar.get(Calendar.HOUR_OF_DAY));
            }

            return;

        }

        if (currentCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)
                && currentCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH)) {

            for (int i = currentCalendar.getActualMinimum(Calendar.HOUR_OF_DAY);
                 i <= endCalendar.get(Calendar.HOUR_OF_DAY); i++) {
                hourList.add(i);
            }

            if (currentCalendar.after(endCalendar)) {
                currentCalendar.set(Calendar.HOUR_OF_DAY, endCalendar.get(Calendar.HOUR_OF_DAY));
            }

            return;
        }

        for (int i = currentCalendar.getActualMinimum(Calendar.HOUR_OF_DAY);
             i <= currentCalendar.getActualMaximum(Calendar.HOUR_OF_DAY); i++) {
            hourList.add(i);
        }
    }

    private void initMinute() {

        minuteList.clear();

        if (currentCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)
                && currentCalendar.get(Calendar.DAY_OF_MONTH) == startCalendar.get(Calendar.DAY_OF_MONTH)
                && currentCalendar.get(Calendar.HOUR_OF_DAY) == startCalendar.get(Calendar.HOUR_OF_DAY)) {

            for (int i = startCalendar.get(Calendar.MINUTE);
                 i <= currentCalendar.getActualMaximum(Calendar.MINUTE); i++) {
                minuteList.add(i);
            }

            if (currentCalendar.before(startCalendar)) {
                currentCalendar.set(Calendar.MINUTE, startCalendar.get(Calendar.MINUTE));
            }

            return;

        }

        if (currentCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR)
                && currentCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)
                && currentCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH)
                && currentCalendar.get(Calendar.HOUR_OF_DAY) == endCalendar.get(Calendar.HOUR_OF_DAY)) {

            for (int i = currentCalendar.getActualMinimum(Calendar.MINUTE);
                 i <= endCalendar.get(Calendar.MINUTE); i++) {
                minuteList.add(i);
            }

            if (currentCalendar.after(endCalendar)) {
                currentCalendar.set(Calendar.MINUTE, endCalendar.get(Calendar.MINUTE));
            }

            return;
        }

        for (int i = currentCalendar.getActualMinimum(Calendar.MINUTE);
             i <= currentCalendar.getActualMaximum(Calendar.MINUTE); i++) {
            minuteList.add(i);
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
        titleTv.setText("heheh");

        LinearLayout wheelContainer = view.findViewById(R.id.wheelContainer);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;

        for (WheelTimeInfo wheelTimeInfo: dateBean.getTimeInfoArrayList()) {
            loadWheelView(wheelContainer, params, wheelTimeInfo);
        }

        updateWheel();

        view.findViewById(R.id.complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dateCallback.onCallback(yearArrayAdapter.getItemTime(monthWheel.getCurrentItem()));
            }
        });

    }

    private void loadWheelView(LinearLayout wheelContainer, LinearLayout.LayoutParams params, WheelTimeInfo timeInfo) {


        java.text.DateFormat dateFormat = timeInfo.getDateFormat();


        AbstractWheel wheel = new WheelVerticalView(getActivity());
        wheel.setTag(timeInfo.getTimeType());
        wheel.setLayoutParams(params);
        wheel.setVisibleItems(timeInfo.getVisiableItem());

        WheelArrayAdapter arrayAdapter = new WheelArrayAdapter(getActivity());
        ArrayList<String> stringArrayList = new ArrayList<>();
        switch (timeInfo.getTimeType()) {
            case YEAR:
                stringArrayList = calendarToString(yearList);
                break;
            case MONTH:
                stringArrayList = calendarToString(monthList);
                break;
            case DAY:
                stringArrayList = calendarToString(dayList);
                break;
            case HOUR:
                stringArrayList = calendarToString(hourList);
                break;
            case MINUTE:
                stringArrayList = calendarToString(minuteList);
                break;

        }
        arrayAdapter.setTimes(stringArrayList);

        wheel.setViewAdapter(arrayAdapter);

        wheel.addChangingListener(onWheelChangedListener);
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


            switch ((CustomDatePicker.TimeType) wheel.getTag()) {
                case YEAR:

                    currentCalendar.set(Calendar.YEAR, yearList.get(wheel.getCurrentItem()));

                    initMonth();
                    initDay();
                    initHour();
                    initMinute();

                    break;

                case MONTH:


                    // Calendar设置月份时，如果当前日字段大于要设置的月份的天数，则自动跳转到合适的月份，而不是设置的月份
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    calendar.set(Calendar.MONTH, monthList.get(wheel.getCurrentItem()) - 1);

                    if (currentCalendar.get(Calendar.DAY_OF_MONTH) > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                        currentCalendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    }

                    currentCalendar.set(Calendar.MONTH, monthList.get(wheel.getCurrentItem()) - 1);


                    initDay();
                    initHour();
                    initMinute();


                    break;

                case DAY:
                    currentCalendar.set(Calendar.DAY_OF_MONTH, dayList.get(wheel.getCurrentItem()));

                    initHour();
                    initMinute();


                    break;

                case HOUR:

                    currentCalendar.set(Calendar.HOUR_OF_DAY, hourList.get(wheel.getCurrentItem()));

                    initMinute();

                    break;

                case MINUTE:

                    currentCalendar.set(Calendar.MINUTE, minuteList.get(wheel.getCurrentItem()));

                    break;
            }

            updateWheel();
        }
    };

    private OnWheelChangedListener onWheelChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
            // wheel.getTag()
            // 1 check wheel type
            // update current calendar
            // refresh all wheel
        }
    };



    // 处理日期为相应的时间格式
    private ArrayList<String> calendarToString(ArrayList<Integer> calendarArrayList) {

        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Integer integer: calendarArrayList) {
            stringArrayList.add(String.valueOf(integer));
        }

        return  stringArrayList;
    }


    // 根据List数据更新Wheel
    private void updateWheel() {
        for (Map.Entry wheel: wheelMap.entrySet()) {

            AbstractWheel abstractWheel = (AbstractWheel) wheel.getValue();

            WheelArrayAdapter wheelArrayAdapter = new WheelArrayAdapter(getActivity());


            switch ((CustomDatePicker.TimeType)wheel.getKey()) {
                case YEAR:
                    wheelArrayAdapter.setTimes(calendarToString(yearList));

                    abstractWheel.setCurrentItem(yearList.indexOf(currentCalendar.get(Calendar.YEAR)));
                    break;

                case MONTH:
                    wheelArrayAdapter.setTimes(calendarToString(monthList));

                    int month = monthList.get(0) > (currentCalendar.get(Calendar.MONTH) + 1) ?
                            0 : monthList.indexOf(currentCalendar.get(Calendar.MONTH) + 1);

                    abstractWheel.setCurrentItem(month);
                    break;

                case DAY:
                    wheelArrayAdapter.setTimes(calendarToString(dayList));

                    int day = dayList.get(0) > (currentCalendar.get(Calendar.DAY_OF_MONTH)) ?
                            0 : dayList.indexOf(currentCalendar.get(Calendar.DAY_OF_MONTH));

                    abstractWheel.setCurrentItem(day);
                    break;

                case HOUR:
                    wheelArrayAdapter.setTimes(calendarToString(hourList));

                    int hour = hourList.get(0) > currentCalendar.get(Calendar.HOUR_OF_DAY) ?
                            0 : hourList.indexOf(currentCalendar.get(Calendar.HOUR_OF_DAY));

                    abstractWheel.setCurrentItem(hour);
                    break;

                case MINUTE:
                    wheelArrayAdapter.setTimes(calendarToString(minuteList));


                    int minute = minuteList.get(0) > currentCalendar.get(Calendar.MINUTE) ?
                            0 : minuteList.indexOf(currentCalendar.get(Calendar.MINUTE));

                    abstractWheel.setCurrentItem(minute);
                    break;
            }

            abstractWheel.setViewAdapter(wheelArrayAdapter);
        }
    }




}
