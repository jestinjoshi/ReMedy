package com.aldrinj.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aldrinj.myapplication.databaseFiles.RemedyDBHelper;

import java.util.ArrayList;

import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TABLE;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.USER_NAME;


public class MedicineListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_medicineList));

        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this,MainActivity.class));
        return super.onOptionsItemSelected(item);
    }

    public void populateList() {
        ListView listView = findViewById(R.id.list_medicine);
<<<<<<< HEAD
<<<<<<< HEAD
        Cursor result = myDataBase.rawQuery("Select Name FROM MedicineTable;", null);
        if (result.getCount() != 0) {
            ArrayList<String> medicineNames = new ArrayList<>();
            result.moveToLast();
            while (!result.isBeforeFirst()) {
                medicineNames.add(result.getString(0));
                result.moveToPrevious();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medicineNames);
            listView.setAdapter(adapter);

=======
=======
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
        RemedyDBHelper helper = new RemedyDBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor result = database.rawQuery("SELECT * FROM " + MEDICINE_TABLE + " WHERE " + USER_NAME + " LIKE '" + MedicinesActivity.username + "';", null);
        ArrayList<String> medicineNames = new ArrayList<>();
        result.moveToLast();
        if (result.getCount() > 0) {
            while (!result.isBeforeFirst()) {
                medicineNames.add(result.getString(1));
                result.moveToPrevious();
            }
<<<<<<< HEAD
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
=======
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
        }
        result.close();
        database.close();
    }

    public void addMedicine(View view) {
        startActivity(new Intent(this, MedicinesActivity.class));
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
<<<<<<< HEAD
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
=======
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
    }
}
