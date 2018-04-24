package com.aldrinj.myapplication.databaseFiles;

import android.provider.BaseColumns;

public class RemedyContract {

    public static final class UserList implements BaseColumns {
        public static final String USER_TABLE = "user_list";
        public static final String USER_NAME = "username";
        public static final String USER_PASSWORD = "password";
        public static final String USER_FULL_NAME = "name";
        public static final String USER_AGE = "age";
    }

    public static final class MedicineList implements BaseColumns {
        public static final String MEDICINE_TABLE = "medicine_list";
        public static final String USER_NAME = "username";
        public static final String MEDICINE_NAME = "medicine_name";
        public static final String MEDICINE_DOSAGE = "medicine_dosage";
        public static final String MEDICINE_TIME1 = "time1";
        public static final String MEDICINE_TIME2 = "time2";
        public static final String MEDICINE_TIME3 = "time3";
        public static final String MEDICINE_TIME4 = "time4";
        public static final String IS_MON = "is_mon";
        public static final String IS_TUE = "is_tue";
        public static final String IS_WED = "is_wed";
        public static final String IS_THU = "is_thu";
        public static final String IS_FRI = "is_fri";
        public static final String IS_SAT = "is_sat";
        public static final String IS_SUN = "is_sun";
    }
}
