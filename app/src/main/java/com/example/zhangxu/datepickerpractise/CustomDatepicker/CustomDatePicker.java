package com.example.zhangxu.datepickerpractise.CustomDatepicker;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.zhangxu.datepickerpractise.CustomDatepicker.internal.CustomDatePickerImpl;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Create by ZhangXu
 * Date: 2018/8/1
 */
public abstract class CustomDatePicker {


    protected static FragmentManager fragmentManager;

    /**
     * 用于设置时间类型，包含年，月，日，小时，分钟
     */
    public enum TimeType {
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE
    }

    /**
     * 创建一个Datapicker
     * @return CustomDatePicker
     */
    public static CustomDatePicker createDataPicker(FragmentActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        return new CustomDatePickerImpl();
    }


    public abstract CustomDatePicker init(FragmentActivity activity);

    public abstract CustomDatePicker setCalendar(Calendar calendar);
    public abstract CustomDatePicker setStartCalendar(Calendar calendar);
    public abstract CustomDatePicker setEndCalendar(Calendar calendar);
    public abstract CustomDatePicker setVisibleItem(int visible);
    public abstract CustomDatePicker setTitle(String title);
    public abstract CustomDatePicker addTimeColumn(TimeType timeType, DateFormat dateFormat, int interval);

    public abstract void display(int parentResId, DateCallback dateCallback);

    public abstract void dismiss();

}
