package com.aldrinj.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.aldrinj.myapplication.MainActivity.myDataBase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void Register(View v) {

//        Get text from the User input

        EditText name = findViewById(R.id.Name);
        String Name = name.getText().toString();
        EditText username = findViewById(R.id.UserName);
        String UserName = username.getText().toString();
        EditText pass = findViewById(R.id.Pass);
        String Pass = pass.getText().toString();
        EditText age = findViewById(R.id.Age);
        String Age = age.getText().toString();

//        Search database for username

        Cursor result = myDataBase.rawQuery("SELECT Username FROM MEDICINE WHERE Username='" + UserName + "';", null);
        result.moveToFirst();

//        Validation

        try{
            if(result.getCount()!=0){
                if (UserName.equals(result.getString(0))) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else if (Name.equals("")) {
                    Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (UserName.equals("")) {
                    Toast.makeText(this, "Username cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (Pass.equals("")) {
                    Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (Age.equals("")) {
                    Toast.makeText(this, "Age cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (Pass.length() < 8) {
                    Toast.makeText(this, "Password length should be at least 8 characters", Toast.LENGTH_SHORT).show();
                } else {

//            If all the data is correct then it is inserted into the database and the next activity is brought up

                    Intent intent = new Intent(this, MedicinesActivity.class);
                    startActivity(intent);
                    myDataBase.execSQL("INSERT INTO MEDICINE VALUES('" + Name + "','" + UserName + "','" + Pass + "'," + Age + ");");


                }
            }
            else{
                Intent intent = new Intent(this, MedicinesActivity.class);
                startActivity(intent);
                myDataBase.execSQL("INSERT INTO MEDICINE VALUES('" + Name + "','" + UserName + "','" + Pass + "'," + Age + ");");
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
