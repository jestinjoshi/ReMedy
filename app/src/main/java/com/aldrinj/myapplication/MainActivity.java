package com.aldrinj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        myDataBase = openOrCreateDatabase("MyDataBase", MODE_PRIVATE, null);
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS UserTable(Name VARCHAR,Username VARCHAR,Password VARCHAR,Age INTEGER);");
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS MedicineTable(Name TEXT, Doasge INTEGER, " +
                "Time1 TIME, Time2 TIME, Time3 TIME, Time4 TIME," +
                "isMon TEXT, isTue TEXT, isWed TEXT, isThu TEXT, isFri TEXT, isSat TEXT, isSun TEXT);");
        myDataBase.execSQL("INSERT INTO UserTable VALUES('Dummy','Dummy','Dummy',0);");
=======
>>>>>>> 0a1a807cdbe086d26a59aef74db269433644b3dd
    }

    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

