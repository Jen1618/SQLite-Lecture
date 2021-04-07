package com.example.sqlitelectureexample;

import android.provider.BaseColumns;

public class DatabaseContract {

    //container to define names of tables, columns, etc.

    // to prevent someone accidentally creating a DatabaseContract object
    // we will make the constructor private

    private DatabaseContract(){

    }

    // inner classes will be used to define table contents
    // 1 class per table

    public static class UserEntry implements BaseColumns {
        // provide names of the table, columns
        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";


    }

    //create more classes for other tables
}
