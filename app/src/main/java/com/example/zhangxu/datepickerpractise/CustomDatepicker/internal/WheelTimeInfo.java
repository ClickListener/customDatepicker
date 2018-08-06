package com.example.zhangxu.datepickerpractise.CustomDatepicker.internal;

import com.example.zhangxu.datepickerpractise.CustomDatepicker.CustomDatePicker;

import java.text.DateFormat;

/**
 * Create by ZhangXu
 * Date: 2018/8/2
 */
public class WheelTimeInfo {

    private CustomDatePicker.TimeType timeType; // 显示的时间类型： 年、月、日、小时、分钟
    private DateFormat dateFormat; // 要显示的时间格式



    private int interval;   // 间隔

    public CustomDatePicker.TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(CustomDatePicker.TimeType timeType) {
        this.timeType = timeType;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
