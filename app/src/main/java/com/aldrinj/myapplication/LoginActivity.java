package com.aldrinj.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aldrinj.myapplication.databaseFiles.RemedyDBHelper;

import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TABLE;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_NAME;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_PASSWORD;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.USER_TABLE;


public class LoginActivity extends AppCompatActivity {

    public static String UserName;
    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_user_area);
    }

    public void Login(View view) {
        try {
            EditText user = findViewById(R.id.LoginUserName);
            UserName = user.getText().toString();
            EditText pass = findViewById(R.id.LoginPassword);
            String Password = pass.getText().toString();

            RemedyDBHelper helper = new RemedyDBHelper(this);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor result = database.rawQuery("SELECT " + USER_NAME + "," + USER_PASSWORD + " FROM " + USER_TABLE + " WHERE Username LIKE'" + UserName + "';", null);
            Cursor result1 = database.rawQuery("SELECT * FROM " + MEDICINE_TABLE + ";", null);
            result.moveToFirst();

            if (result.getCount() != 0) {
                if (Password.equals(result.getString(1))) {
                    Intent intent = new Intent(this, MedicineListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
            result.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
