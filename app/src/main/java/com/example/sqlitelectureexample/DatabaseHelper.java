package com.example.sqlitelectureexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // context
        // name -> name of the database
        // factory -> null
        // version -> 1

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this is going to be called when you access the database for the first time
        // creating new tables inside of this method
        // sql statements
        // CREATE TABLE NAME (COLUMN_1 TYPE, COLUMN_2 TYPE, ...)

        // create a table called user_table with columns id as primary key, name, and phone
        // CREATE TABLE user_table (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT)
        String sqlStatement = "CREATE TABLE " + DatabaseContract.UserEntry.TABLE_NAME + "(" +
                DatabaseContract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.UserEntry.COLUMN_NAME + " TEXT," +
                DatabaseContract.UserEntry.COLUMN_PHONE + " TEXT)";
        // storing the string into a SQLiteStatement and execute the SQLiteStatement
        SQLiteStatement statement = db.compileStatement(sqlStatement);
        statement.execute();
        // not the best way to write any sql statements
        // sql injection

        // prepared statement -> Java & mySQL
        // SQLiteStatement

        // sqlStatement += "DROP DATABASE";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this is going to be called when the version number update


    }

    //entending SQLiteHelper to implement methods to interact with our database

    // methods that interact with your database will be in your database helper class

    // add

    // write a method to add a user to the database
    // return the ID of the given user after adding
    public long addUser(User user){
        // read only mode -> multiple processors access
        // write mode -> locks the database when you write
        SQLiteDatabase database = this.getWritableDatabase();
        // write our insert statement
        // INSERT INTO  table_name(column1, column2, ... VALUES(value_1, value_2, ...);
        // INSERT INTO user_table (name, phone) values ("Celia", "123");
        String sqlQuery = "INSERT INTO " + DatabaseContract.UserEntry.TABLE_NAME +
                "(" + DatabaseContract.UserEntry.COLUMN_NAME + ", "
                + DatabaseContract.UserEntry.COLUMN_PHONE + ") Values (?, ?)";
        // ? -> placeholder
        // printf ("  %s %s ", hello, hello);
        SQLiteStatement statement = database.compileStatement(sqlQuery);

        String name = user.getName();
        String phone = user.getPhone();

        // binding -> will bind a value to something
        // bind the value to the ?  based on the order of ?
        // in binding, you start from 1
        statement.bindString(1, name);
        statement.bindString(2, phone);

        long rowId = statement.executeInsert();
        database.close();
        return rowId;
    }
    // select
    // query all the user from the table
    public List<User> getAllUsers(){
        //SELECT *  FROM table_name;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<User> users = new ArrayList<>();

        // cursor -> a set of results
        String sqlQuery = "SELECT * FROM " + DatabaseContract.UserEntry.TABLE_NAME;
        Cursor resultSet = sqLiteDatabase.rawQuery(sqlQuery, null);

        // resultSet.moveToFirst() -> false -> you don't have any results
        if (resultSet.moveToFirst()){
            // loop through the entire resultSet

            do{
                // get each row and save it into a User object
                // add the object to the list
                String user_name = resultSet.getString(1);
                String user_phone = resultSet.getString(2);
                User user = new User(user_name, user_phone);
                long user_id = resultSet.getLong(0);
                user.setId(user_id);
                users.add(user);

            }while(resultSet.moveToNext());
        }
        resultSet.close();
        sqLiteDatabase.close();
        return users;
    }

    // delete a user based off its name
    // most of the time, you should delete row based on the primary key

    // delete user with id 5
    // DELETE FROM table WHERE condition

    // An example:
    // DELETE FROM user_table WHERE id = 2;
    public int deleteUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        String sqlQuery = "DELETE FROM " + DatabaseContract.UserEntry.TABLE_NAME + " where "
                + DatabaseContract.UserEntry.COLUMN_ID + " =?";
        SQLiteStatement statement = database.compileStatement(sqlQuery);
        statement.bindLong(1, user.getId());

        int numRows = statement.executeUpdateDelete();
        database.close();
        return numRows;
    }
}
