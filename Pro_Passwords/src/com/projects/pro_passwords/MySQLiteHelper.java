package com.projects.pro_passwords;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_USERS = "users";
  public static final String COLUMN_USERNAME = "username";
  public static final String COLUMN_PASSWORD = "password";
  

  private static final String DATABASE_NAME = "project_passwords.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String USERS_TABLE_CREATE = "create table "
      + TABLE_USERS + "(" + COLUMN_USERNAME
      + " text primary key not null unique, " + COLUMN_PASSWORD
      + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(USERS_TABLE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    onCreate(db);
  }

} 
