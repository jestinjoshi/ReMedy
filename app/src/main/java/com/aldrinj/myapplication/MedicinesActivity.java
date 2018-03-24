package com.aldrinj.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import static android.view.View.*;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.aldrinj.myapplication.RegisterActivity.myDataBase;

public class MedicinesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_medicines);

        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS MedicineTable(Name VARCHAR, Doasge INTEGER, Time1 TIMESTAMP, Time2 TIMESTAMP, Time3 TIMESTAMP, Time4 TIMESTAMP);");
    }

    public void addMedicine(View view) {
        Intent intent = new Intent(this, AddMedicinesActivity.class);
        startActivity(intent);
    }

    public void onSelectEveryday(View view) {
        CheckBox everyday=findViewById(R.id.everydayCheckbox);
        View everydaySelect = findViewById(R.id.weekdayCheckbox);
        if(everyday.isChecked()){
            everydaySelect.setVisibility(View.GONE);
        }
        else {
            everydaySelect.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void timeInput(final View view) {

        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(MedicinesActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                ((EditText)view).setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
    }
