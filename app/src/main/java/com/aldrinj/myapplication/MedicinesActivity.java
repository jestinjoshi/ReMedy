package com.aldrinj.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.aldrinj.myapplication.MainActivity.myDataBase;

public class MedicinesActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_medicines);

        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS MedicineTable(Name VARCHAR, Doasge INTEGER, Time1 TIMESTAMP, Time2 TIMESTAMP, Time3 TIMESTAMP, Time4 TIMESTAMP);");

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.save){

        }
            return super.onOptionsItemSelected(item);
    }

    public void addMedicine(View view) {
        Intent intent = new Intent(this, AddMedicinesActivity.class);
        startActivity(intent);
    }

    public void onSelectEveryday(View view) {
        CheckBox everyday = findViewById(R.id.everydayCheckbox);
        View everydaySelect = findViewById(R.id.weekdayCheckbox);
        if (everyday.isChecked()) {
            everydaySelect.setVisibility(View.GONE);
        } else {
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
                ((Button) view).setText(selectedHour % 12 + ":" + selectedMinute + " " + ((selectedHour >= 12) ? "PM" : "AM"));
                switch (view.getTag().toString()) {
                    case "time1":
                        Button editText = findViewById(R.id.time2);
                        editText.setVisibility(View.VISIBLE);
                        break;
                    case "time2":
                        Button editText1 = findViewById(R.id.time3);
                        editText1.setVisibility(View.VISIBLE);
                        break;
                    case "time3":
                        Button editText2 = findViewById(R.id.time4);
                        editText2.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
