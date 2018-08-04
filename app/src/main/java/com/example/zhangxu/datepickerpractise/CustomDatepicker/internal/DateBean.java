package com.example.zhangxu.datepickerpractise.CustomDatepicker.internal;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * 用于存放要显示的日期时间，
 * Create by ZhangXu
 * Date: 2018/8/2
 */
public class DateBean {


    private String title;

    private Calendar showCalendar;

    private ArrayList<WheelTimeInfo> timeInfoArrayList;


    private Calendar startCalendar;
    private Calendar endCalendar;

    public Calendar getStartCalendar() {
        return startCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
    }

    public Calendar getEndCalendar() {
        return endCalendar;
    }

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Calendar getShowCalendar() {
        return showCalendar;
    }

    public void setShowCalendar(Calendar showCalendar) {
        this.showCalendar = showCalendar;
    }



    public ArrayList<WheelTimeInfo> getTimeInfoArrayList() {
        return timeInfoArrayList;
    }

    public void setTimeInfoArrayList(ArrayList<WheelTimeInfo> timeInfoArrayList) {
        this.timeInfoArrayList = timeInfoArrayList;
    }




}
