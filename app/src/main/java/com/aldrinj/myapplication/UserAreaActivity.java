package com.aldrinj.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_user_area);
    }

    public void Login(View view) {
        EditText user = findViewById(R.id.LoginUserName);
        String UserName = user.getText().toString();
        EditText pass= findViewById(R.id.LoginPassword);
        String Password = pass.getText().toString();
        Cursor result = RegisterActivity.myDataBase.rawQuery("SELECT Username,Password FROM MEDICINE WHERE Username='" + UserName + "';", null);
        result.moveToFirst();
        
        if(UserName.equals(result.getString(0))){
            if(Password.equals(result.getString(1))){
                Intent intent=new Intent(this,MedicinesActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
    }
}
