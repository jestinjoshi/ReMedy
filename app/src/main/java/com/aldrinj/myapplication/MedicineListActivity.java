package com.aldrinj.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.aldrinj.myapplication.MainActivity.myDataBase;


public class MedicineListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);
        populateList();
    }

    public void populateList() {
        ListView listView = findViewById(R.id.list_medicine);
        Cursor result = myDataBase.rawQuery("Select Name FROM MedicineTable;", null);
        ArrayList<String> medicineNames = new ArrayList<>();
        result.moveToFirst();
        while (result.isAfterLast()) {
            medicineNames.add(result.getString(0));
            result.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medicineNames);
        listView.setAdapter(adapter);
        result.close();
    }

    public void addMedicine(View view) {
        startActivity(new Intent(this,MedicinesActivity.class));
    }
}
