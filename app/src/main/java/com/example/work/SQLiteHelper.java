package com.example.work;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Book";
    static final int DB_VERSION = 2;
    private Context context;

    public SQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db);
    }

    public void updateMyDatabase(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Student (StuNo Integer PRIMARY KEY AUTOINCREMENT, "
                    + "StuName TEXT, "
                    + "Zhuanye TEXT, "
                    + "BookNo TEXT);");

    }

    public void insertstudent(SQLiteDatabase db,Integer StuNo,String StuName, String Zhuanye, String BookNo) {
        ContentValues studentValues = new ContentValues();
        studentValues.put("StuNo", StuNo);
        studentValues.put("StuName", StuName);
        studentValues.put("Zhuanye", Zhuanye);
        studentValues.put("BookNo", BookNo);
        db.insert("student", null, studentValues);
    }

}

