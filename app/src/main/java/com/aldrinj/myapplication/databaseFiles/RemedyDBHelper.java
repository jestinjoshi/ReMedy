package com.aldrinj.myapplication.databaseFiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_FRI;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_MON;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_SAT;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_SUN;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_THU;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_TUE;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.IS_WED;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_DOSAGE;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_NAME;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TABLE;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TIME1;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TIME2;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TIME3;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.MEDICINE_TIME4;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.MedicineList.USER_NAME;
import static com.aldrinj.myapplication.databaseFiles.RemedyContract.UserList.*;

public class RemedyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Medicines.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE
            + "(" + USER_NAME + " TEXT," + USER_PASSWORD + " TEXT," + USER_AGE + " INTEGER," + USER_FULL_NAME + " TEXT);";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
            + MEDICINE_TABLE + "(" + USER_NAME + " TEXT, " + MEDICINE_NAME + " TEXT, "
            + MEDICINE_DOSAGE + " INTEGER, " + MEDICINE_TIME1 + " TEXT, " + MEDICINE_TIME2 + " TEXT, "
            + MEDICINE_TIME3 + " TEXT, " + MEDICINE_TIME4 + " TEXT, " + IS_MON + " TEXT, " + IS_TUE
            + " TEXT, " + IS_WED + " TEXT, " + IS_THU + " TEXT, " + IS_FRI + " TEXT, " + IS_SAT
            + " TEXT, " + IS_SUN + " TEXT);";

    public RemedyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE + ";");
        onCreate(sqLiteDatabase);
    }
}
