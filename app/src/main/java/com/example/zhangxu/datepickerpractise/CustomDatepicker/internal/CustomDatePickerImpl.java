package com.example.zhangxu.datepickerpractise.CustomDatepicker.internal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.zhangxu.datepickerpractise.CustomDatepicker.CustomDatePicker;
import com.example.zhangxu.datepickerpractise.CustomDatepicker.DateCallback;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Create by ZhangXu
 * Date: 2018/8/1
 */
public class CustomDatePickerImpl extends CustomDatePicker {


    private FragmentManager fragmentManager;
    private String tag = "custom_date_picker_tag";


    private String title;

    private Calendar startCalendar;
    private Calendar endCalendar;
    private Calendar showCalendar;

    private ArrayList<WheelTimeInfo> timeInfoArrayList = new ArrayList<>();

    @Override
    public CustomDatePicker init(FragmentActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        return this;
    }

    @Override
    public CustomDatePicker setCalendar(Calendar calendar) {
        this.showCalendar = calendar;
        return this;
    }


    @Override
    public CustomDatePicker setStartCalendar(Calendar calendar) {
        this.startCalendar = calendar;
        return this;
    }

    @Override
    public CustomDatePicker setEndCalendar(Calendar calendar) {

        this.endCalendar = calendar;
        return this;
    }

    @Override
    public CustomDatePicker setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public CustomDatePicker addTimeColumn(TimeType timeType, DateFormat dateFormat, int interval, int visibleItem) {

        WheelTimeInfo timeInfo = new WheelTimeInfo();
        timeInfo.setTimeType(timeType);
        timeInfo.setDateFormat(dateFormat);
        timeInfo.setInterval(interval);
        timeInfo.setVisiableItem(visibleItem);

        timeInfoArrayList.add(timeInfo);
        return this;
    }

    @Override
    public void display(int layoutId, DateCallback dateCallback) {


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment old = fragmentManager.findFragmentByTag(tag);

        if (old != null) {
            fragmentTransaction.remove(old);
        }

        DateBean dateBean = packageData();

        CustomDatePickerFragment customDatepickerFragment = new CustomDatePickerFragment(dateBean, dateCallback);

        fragmentTransaction.add(layoutId, customDatepickerFragment, tag);

        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag(tag);
        if (prev != null) {
            fragmentTransaction.remove(prev).commitAllowingStateLoss();
        }

    }


    private DateBean packageData() {

        DateBean dateBean = new DateBean();

        if (showCalendar != null) {
            dateBean.setShowCalendar(showCalendar);
        }

        dateBean.setTimeInfoArrayList(timeInfoArrayList);

        dateBean.setStartCalendar(startCalendar);
        dateBean.setEndCalendar(endCalendar);
        dateBean.setTitle(title);


        return dateBean;

    }



}
