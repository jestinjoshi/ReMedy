package com.aldrinj.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aldrinj.myapplication.databaseFiles.RemedyDBHelper;

import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_AGE;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_FULL_NAME;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_NAME;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_PASSWORD;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_TABLE;

public class RegisterActivity extends AppCompatActivity {

    public static String UserName;

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
        UserName = username.getText().toString();
        EditText pass = findViewById(R.id.Pass);
        String Pass = pass.getText().toString();
        EditText age = findViewById(R.id.Age);
        String Age = age.getText().toString();

//        Search database for username

        RemedyDBHelper helper = new RemedyDBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor result = database.rawQuery("SELECT " + USER_NAME + " FROM " + USER_TABLE + " WHERE " + USER_NAME + " LIKE '" + UserName + "';", null);
        result.moveToFirst();

//        Validation

        try {
            RemedyDBHelper remedyDBHelper = new RemedyDBHelper(this);
            SQLiteDatabase sqLiteDatabase = remedyDBHelper.getWritableDatabase();

            ContentValues content = new ContentValues();
            if (result.getCount() != 0) {
                if (UserName.equals(result.getString(0))) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (Name.equals("")) {
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
                    Intent intent = new Intent(this, MedicineListActivity.class);
                    startActivity(intent);
                    content.put(USER_FULL_NAME, Name);
                    content.put(USER_NAME, UserName);
                    content.put(USER_PASSWORD, Pass);
                    content.put(USER_AGE, Age);

                    sqLiteDatabase.insert(USER_TABLE, null, content);
                }
            }
            result.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}