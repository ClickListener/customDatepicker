package com.example.zhangxu.datepickerpractise;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.zhangxu.datepickerpractise.CustomDatepicker.CustomDatePicker;
import com.example.zhangxu.datepickerpractise.CustomDatepicker.DateCallback;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.datapickerDialog)
    Button button;
    @BindView(R.id.systemDialog)
    Button systemDialogButton;
    @BindView(R.id.spinnerWheelText)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.datapickerDialog)
    public void onClick() {

        final CustomDatePicker customDatePicker = CustomDatePicker.createDataPicker();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2016, Calendar.JULY, 12, 11, 30);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2018, Calendar.JANUARY, 1, 23, 30);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(2017, Calendar.JANUARY, 2, 13, 30);


        customDatePicker.setStartCalendar(startCalendar)
                .setEndCalendar(endCalendar)
                .setCalendar(currentCalendar)
                .setVisibleItem(3);


        customDatePicker.init(MainActivity.this)
//                .addTimeColumn(CustomDatePicker.TimeType.YEAR, new SimpleDateFormat("yyyy"), 5)
                .addTimeColumn(CustomDatePicker.TimeType.MONTH, new SimpleDateFormat("yyyy-MM"), 5)
//                .addTimeColumn(CustomDatePicker.TimeType.DAY, new SimpleDateFormat("dd"), 5)
//                .addTimeColumn(CustomDatePicker.TimeType.HOUR, new SimpleDateFormat("HH"), 5)
//                .addTimeColumn(CustomDatePicker.TimeType.MINUTE, new SimpleDateFormat("mm"), 5)
                .display(R.id.base_extra_layout, new DateCallback() {
                    @Override
                    public void onCallback(Calendar selectCalendar) {
                        textView.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(selectCalendar.getTime()));
                    }
                });


    }

    @OnClick(R.id.systemDialog)
    public void showSystemDialog() {
        DatePickerDialog timePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, 2018, 12, 12);

        timePickerDialog.show();
    }
}
