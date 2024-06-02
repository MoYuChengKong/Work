package com.example.work;


import android.annotation.SuppressLint;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public class AddActivity extends AppCompatActivity {

    private EditText StuNo, StuName, Zhuanye, BookNo;
    private String StuName1, Zhuanye1, BookNo1,StuNo1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StuNo = (EditText) findViewById(R.id.add_username);
        StuName = (EditText) findViewById(R.id.add_name);
        Zhuanye = (EditText) findViewById(R.id.add_zhuanye);
        BookNo = (EditText) findViewById(R.id.add_book);
    }

//返回按钮是网上找的,51CTO https://blog.51cto.com/u_16213358/10592833
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // 处理返回按钮点击事件
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setAdd(View v) {
        try {
            StuNo1 = StuNo.getText().toString();
            BookNo1 = BookNo.getText().toString();
            StuName1 = StuName.getText().toString();
            Zhuanye1 = Zhuanye.getText().toString();
            SQLiteHelper dbHelper = new SQLiteHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            dbHelper.insertstudent(db, Integer.valueOf(StuNo1), StuName1, Zhuanye1, BookNo1);
            Toast.makeText(AddActivity.this, "已成功添加", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}