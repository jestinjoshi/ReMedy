package com.aldrinj.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase myDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataBase = openOrCreateDatabase("MyDataBase", MODE_PRIVATE, null);
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS UserTable(Name VARCHAR,Username VARCHAR,Password VARCHAR,Age INTEGER);");
        myDataBase.execSQL("INSERT INTO UserTable VALUES('Dummy','Dummy','Dummy',0);");
    }

    public void toRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this,UserAreaActivity.class);
        startActivity(intent);
    }
}

