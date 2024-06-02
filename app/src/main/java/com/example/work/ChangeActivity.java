package com.example.work;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public class ChangeActivity extends AppCompatActivity {

    private EditText StuNo,StuName,Zhuanye,BookNo;
    private String StuName1,Zhuanye1,StuNo1,BookNo1;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StuNo = (EditText) findViewById(R.id.change_username);
        StuName = (EditText) findViewById(R.id.change_name);
        Zhuanye = (EditText) findViewById(R.id.change_zhuanye);
        BookNo = (EditText) findViewById(R.id.change_book);


    }
    //更改
    public void setChange (View v){
        StuNo1=StuNo.getText().toString();
        BookNo1=BookNo.getText().toString();
        StuName1=StuName.getText().toString();
        Zhuanye1=Zhuanye.getText().toString();

                SQLiteHelper dbHelper = new SQLiteHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values =new ContentValues();
                values.put("StuName",StuName1);
                values.put("Zhuanye",Zhuanye1);
                values.put("BookNo",BookNo1);

                db.update("Student",values,"StuNo=?",new String[]{StuNo1});
                Toast.makeText(ChangeActivity.this, "数据更改完成", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(ChangeActivity.this, "数据查询完成", Toast.LENGTH_SHORT).show();
                }
            catch (Exception e) {
                Toast.makeText(ChangeActivity.this, "未查询到数据", Toast.LENGTH_SHORT).show();
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