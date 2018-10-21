package com.aldrinj.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.aldrinj.myapplication.databaseFiles.RemedyDBHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.*;

public class MedicinesActivity extends AppCompatActivity {

    public static final String username = ((LoginActivity.UserName == null) ? "" : LoginActivity.UserName) + ((RegisterActivity.UserName == null) ? "" : RegisterActivity.UserName);

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_medicines);
<<<<<<< HEAD
=======

>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
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
                String time1 = ((Button) findViewById(R.id.time1)).getText().toString();
                String time2 = ((Button) (findViewById(R.id.time2))).getText().toString();
                String time3 = ((Button) (findViewById(R.id.time3))).getText().toString();
                String time4 = ((Button) (findViewById(R.id.time4))).getText().toString();

                Time timestamp1 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(time1).getTime());
                Time timestamp2 = null;
                Time timestamp3 = null;
                Time timestamp4 = null;
                if (!time2.equals("")) {
                    timestamp2 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(time2).getTime());
                }
                if (!time3.equals("")) {
                    timestamp3 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(time3).getTime());
                }
                if (!time4.equals("")) {
                    timestamp4 = new Time(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(time4).getTime());
                }
                boolean everyday = ((CheckBox) findViewById(R.id.everydayCheckbox)).isChecked();
                boolean[] weekday = {((CheckBox) findViewById(R.id.monday)).isChecked(),
                        ((CheckBox) findViewById(R.id.tuesday)).isChecked(),
                        ((CheckBox) findViewById(R.id.wednesday)).isChecked(),
                        ((CheckBox) findViewById(R.id.thursday)).isChecked(),
                        ((CheckBox) findViewById(R.id.friday)).isChecked(),
                        ((CheckBox) findViewById(R.id.saturday)).isChecked(),
                        ((CheckBox) findViewById(R.id.sunday)).isChecked()};

<<<<<<< HEAD
                    myDataBase.execSQL("INSERT INTO MedicineTable VALUES('" + name + "'," + dose + ",'" + timestamp1 + "','" + timestamp2 + "','" + timestamp3 + "','" + timestamp4 + "'," +
                            "'" + weekday[0] + "','" + weekday[1] + "','" + weekday[2] + "','" + weekday[3] + "','" + weekday[4] + "','" + weekday[5] + "','" + weekday[6] + "');");
                    startActivity(new Intent(this,MedicineListActivity.class));
=======
                RemedyDBHelper helper = new RemedyDBHelper(this);
                SQLiteDatabase database = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd

                if (!everyday) {
                    values.put(USER_NAME, username);
                    values.put(MEDICINE_NAME, name);
                    values.put(MEDICINE_DOSAGE, dose);
                    values.put(MEDICINE_TIME1, timestamp1.toString());
                    if (timestamp2 != null) {
                        values.put(MEDICINE_TIME2, timestamp2.toString());
                    }
                    if (timestamp3 != null) {
                        values.put(MEDICINE_TIME3, timestamp3.toString());
                    }
                    if (timestamp4 != null) {
                        values.put(MEDICINE_TIME4, timestamp4.toString());
                    }
                    values.put(IS_MON, weekday[0]);
                    values.put(IS_TUE, weekday[1]);
                    values.put(IS_WED, weekday[2]);
                    values.put(IS_THU, weekday[3]);
                    values.put(IS_FRI, weekday[4]);
                    values.put(IS_SAT, weekday[5]);
                    values.put(IS_SUN, weekday[6]);
                } else {
                    values.put(USER_NAME, username);
                    values.put(MEDICINE_NAME, name);
                    values.put(MEDICINE_DOSAGE, dose);
                    values.put(MEDICINE_TIME1, timestamp1.toString());
                    if (timestamp2 != null) {
                        values.put(MEDICINE_TIME2, timestamp2.toString());
                    }
                    if (timestamp3 != null) {
                        values.put(MEDICINE_TIME3, timestamp3.toString());
                    }
                    if (timestamp4 != null) {
                        values.put(MEDICINE_TIME4, timestamp4.toString());
                    }
                    values.put(IS_MON, "true");
                    values.put(IS_TUE, "true");
                    values.put(IS_WED, "true");
                    values.put(IS_THU, "true");
                    values.put(IS_FRI, "true");
                    values.put(IS_SAT, "true");
                    values.put(IS_SUN, "true");
                }

                database.insert(MEDICINE_TABLE, null, values);
                database.close();

                Intent notifyIntent = new Intent(this, BroReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast
                        (this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
                if (alarmManager != null) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp1.getTime(), pendingIntent);
                    if(timestamp2!=null){alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp2.getTime(), pendingIntent);}
                    if(timestamp3!=null){alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp3.getTime(), pendingIntent);}
                    if(timestamp4!=null){alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp4.getTime(), pendingIntent);}
                }

                startActivity(new Intent(this, MedicineListActivity.class));

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