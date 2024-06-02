package com.example.work;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


public class DeleteActivity extends AppCompatActivity{

    private EditText StuNo,StuName,Zhuanye,BookNo;
    private String StuName1,Zhuanye1,StuNo1,BookNo1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StuNo = (EditText) findViewById(R.id.delete_username);
        StuName = (EditText) findViewById(R.id.delete_name);
        Zhuanye = (EditText) findViewById(R.id.delete_zhuanye);
        BookNo = (EditText) findViewById(R.id.delete_book);


    }
    //删除
    public void setDelete (View v) {
        try {
            StuNo1 = StuNo.getText().toString();
            BookNo1 = BookNo.getText().toString();
            StuName1 = StuName.getText().toString();
            Zhuanye1 = Zhuanye.getText().toString();
            SQLiteHelper dbHelper = new SQLiteHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("Student", "StuNo=?", new String[]{StuNo1});
            Toast.makeText(DeleteActivity.this, "数据删除完成", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(DeleteActivity.this, "未删除数据", Toast.LENGTH_SHORT).show();
        }
    }


    //查询
    public void setFind (View v){

        try{
            StuNo1=StuNo.getText().toString();
            BookNo1=BookNo.getText().toString();
            StuName1=StuName.getText().toString();
            Zhuanye1=Zhuanye.getText().toString();
            SQLiteOpenHelper dbHelper = new SQLiteHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query ("student",
                    new String[] {"StuNo", "StuName", "Zhuanye","BookNo"},
                    "StuNo = ?",
                    new String[] {StuNo1},
                    null, null,null);
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String data1 = cursor.getString(cursor.getColumnIndex("StuName"));
                @SuppressLint("Range") String data2 = cursor.getString(cursor.getColumnIndex("Zhuanye"));
                @SuppressLint("Range") String data3 = cursor.getString(cursor.getColumnIndex("BookNo"));
                StuName.setText(data1);
                Zhuanye.setText(data2);
                BookNo.setText(data3);
            }
            cursor.close();
            db.close();
            Toast.makeText(DeleteActivity.this, "数据查询完成", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(DeleteActivity.this, "未查询到数据", Toast.LENGTH_SHORT).show();
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // 处理返回按钮点击事件
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}