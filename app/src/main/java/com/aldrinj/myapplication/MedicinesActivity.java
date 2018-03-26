package com.aldrinj.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.aldrinj.myapplication.MainActivity.myDataBase;

public class MedicinesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_medicines);

        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS MedicineTable(Name TEXT, Doasge INTEGER, " +
                "Time1 TIME, Time2 TIME, Time3 TIME, Time4 TIME," +
                "isMon TEXT, isTue TEXT, isWed TEXT, isThu TEXT, isFri TEXT, isSat TEXT, isSun TEXT);");

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            try {
                String name = ((EditText) findViewById(R.id.mName)).getText().toString();
                int dose = Integer.parseInt(((EditText) findViewById(R.id.dosage)).getText().toString());
                Time timestamp1 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(((Button) (findViewById(R.id.time1))).getText().toString()).getTime());
                Time timestamp2 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(((Button) (findViewById(R.id.time2))).getText().toString()).getTime());
                Time timestamp3 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(((Button) (findViewById(R.id.time3))).getText().toString()).getTime());
                Time timestamp4 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(((Button) (findViewById(R.id.time4))).getText().toString()).getTime());
                boolean everyday = ((CheckBox) findViewById(R.id.everydayCheckbox)).isChecked();
                boolean[] weekday = {((CheckBox) findViewById(R.id.monday)).isChecked(),
                        ((CheckBox) findViewById(R.id.tuesday)).isChecked(),
                        ((CheckBox) findViewById(R.id.wednesday)).isChecked(),
                        ((CheckBox) findViewById(R.id.thursday)).isChecked(),
                        ((CheckBox) findViewById(R.id.friday)).isChecked(),
                        ((CheckBox) findViewById(R.id.saturday)).isChecked(),
                        ((CheckBox) findViewById(R.id.sunday)).isChecked()};
                if (!everyday) {

                    myDataBase.execSQL("INSERT INTO MedicineTable VALUES('" + name + "'," + dose + ",'" + timestamp1 + "','" + timestamp2 + "','" + timestamp3 + "','" + timestamp4 + "','" +
                            "'" + weekday[0] + "','" + weekday[1] + "','" + weekday[2] + "','" + weekday[3] + "','" + weekday[4] + "','" + weekday[5] + "','" + weekday[6] + ");");
                    startActivity(new Intent(this,MedicineListActivity.class));

                } else {
                    myDataBase.execSQL("INSERT INTO MedicineTable VALUES('" + name + "'," + dose + ",'" + timestamp1 + "','" + timestamp2 + "','" + timestamp3 + "','" + timestamp4 + "'," +
                            "'true','true','true','true','true','true','true');");
                    startActivity(new Intent(this,MedicineListActivity.class));
                }

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
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
        try {
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
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
