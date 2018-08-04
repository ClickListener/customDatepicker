package com.example.zhangxu.datepickerpractise.CustomDatepicker.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangxu.datepickerpractise.R;
import com.example.zhangxu.datepickerpractise.thirdparty.spinnerwheel.AbstractWheelTextAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Create by ZhangXu
 * Date: 2018/8/1
 */
public class WheelArrayAdapter extends AbstractWheelTextAdapter {

    private ArrayList<String> timeList;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    private int validIndex = 0;

    WheelArrayAdapter(Context context) {
        super(context, R.layout.time_picker_custom_time, R.id.time_value);
        timeList = new ArrayList<>();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return null;
    }

    @Override
    public int getItemsCount() {
        return timeList.size();
    }

    public void setTimes(ArrayList<String> timeList) {
        this.timeList = timeList;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);
        TextView date = (TextView) view.findViewById(R.id.time_value);
        date.setText(timeList.get(index));

        return view;
    }

    public String getItemTime(int index) {
        return timeList.get(index);
    }
}
