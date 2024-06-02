package com.example.work;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import java.util.List;


public class FindActivity extends AppCompatActivity{

    private EditText StuNo,StuName,Zhuanye,BookNo;
    private ListView First,Second,Third,Fourth;
    private String StuName1,Zhuanye1,StuNo1,BookNo1;
    private ArrayAdapter<String> adapter1,adapter2,adapter3,adapter4;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            First = (ListView) findViewById(R.id.first);
            ArrayList<String> dataList1 = new ArrayList<>();
            adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataList1);
            First.setAdapter(adapter1);

            Second = (ListView) findViewById(R.id.second);
            ArrayList<String> dataList2 = new ArrayList<>();
            adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataList2);
            Second.setAdapter(adapter2);

            Third = (ListView) findViewById(R.id.third);
            ArrayList<String> dataList3 = new ArrayList<>();
            adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataList3);
            Third.setAdapter(adapter3);

            Fourth = (ListView) findViewById(R.id.fouth);
            ArrayList<String> dataList4 = new ArrayList<>();
            adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList4);
            Fourth.setAdapter(adapter4);


            SQLiteOpenHelper dbHelper = new SQLiteHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("student",
                    null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String data0 = cursor.getString(cursor.getColumnIndex("StuNo"));
                @SuppressLint("Range") String data1 = cursor.getString(cursor.getColumnIndex("StuName"));
                @SuppressLint("Range") String data2 = cursor.getString(cursor.getColumnIndex("Zhuanye"));
                @SuppressLint("Range") String data3 = cursor.getString(cursor.getColumnIndex("BookNo"));
                dataList1.add(data0);
                dataList2.add(data1);
                dataList3.add(data2);
                dataList4.add(data3);
            }
            cursor.close();
            db.close();
            Toast.makeText(FindActivity.this, "数据查询完成", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            e.printStackTrace();
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