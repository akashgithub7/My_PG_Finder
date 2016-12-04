package com.mymindakash.my_pg_finder.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Vikram on 12/4/2016.
 */

public class DataTables {

   private Sqlite_Connection sql;

    // constructor...
    public DataTables(Context context) {
        sql = new Sqlite_Connection(context);
    }

    // Insert Query....
    public void insertData(String name,String number,String email, String password) {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Sqlite_Connection.NAME, name);
        contentValues.put(Sqlite_Connection.NUMBER,number);
        contentValues.put(Sqlite_Connection.PASSWORD, password);
        contentValues.put(Sqlite_Connection.EMAIL,email);
        long id = db.insert(Sqlite_Connection.TABLE_NAME, null, contentValues);
        db.close();
    }
    // select Query....

    public ArrayList<Contect_List> showdata() {
        ArrayList<Contect_List> list_itemses = new ArrayList<>();
        SQLiteDatabase db = sql.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Sqlite_Connection.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Contect_List contect = new Contect_List();
                contect.setId(Integer.parseInt(cursor.getString(0)));
                contect.setName(cursor.getString(1));
                contect.setPassword(cursor.getString(2));
                list_itemses.add(contect);
            } while (cursor.moveToNext());
        }
        return list_itemses;
    }

    public String showAllData() {
        StringBuffer stringBuffer = new StringBuffer();
        SQLiteDatabase db = sql.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Sqlite_Connection.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(1);
                int id = cursor.getInt(0);
                String number=cursor.getString(2);
                String email=cursor.getString(3);
                String password = cursor.getString(4);
                stringBuffer.append(id + " " + name + " " + number +  " " + email + " " + password +"\n");
            } while (cursor.moveToNext());
        }
        return stringBuffer.toString();
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = sql.getWritableDatabase();
        db.delete(Sqlite_Connection.TABLE_NAME, Sqlite_Connection.UID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    public void update(String newName, String newPassword, int id) {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues newValue = new ContentValues();
        newValue.put(Sqlite_Connection.NAME, newName);
        newValue.put(Sqlite_Connection.PASSWORD, newPassword);
        db.update(Sqlite_Connection.TABLE_NAME, newValue, Sqlite_Connection.UID + " =  ?", new String[]{String.valueOf(id)});
        db.close();
    }




  static class Sqlite_Connection extends SQLiteOpenHelper {
      private static final String DATABASE_NAME = "pg.db";
      private static final String TABLE_NAME = "DATA_TABLE";
      private static final int DATABASE_VERSION = 1;
      private static final String UID = "_ID";
      private static final String NAME = "Name";
      private static final String PASSWORD = "password";
      private static final String NUMBER = "number";
      private static final String EMAIL = "email";
      private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " +
              "" + PASSWORD + " VARCHAR(255), " + NUMBER + " VARCHAR(255), " + EMAIL + " VARCHAR(255));";
      private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

      private Context context;

      // constructor ....
      public Sqlite_Connection(Context context) {
          super(context, DATABASE_NAME, null, DATABASE_VERSION);
          this.context = context;
      }

      @Override
      public void onCreate(SQLiteDatabase db) {
          try {
              db.execSQL(CREATE_TABLE);
            //  Message.message(context, "onCreate was called ");
          } catch (Exception e) {
              Message.message(context, "" + e);
          }
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          try {
              db.execSQL(DROP_TABLE);
              onCreate(db);
            //  Message.message(context, "onUpgrade was called ");
          } catch (Exception e) {
              Message.message(context, "" + e);
          }
      }

  }

}