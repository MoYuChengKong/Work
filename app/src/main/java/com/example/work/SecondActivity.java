package com.example.work;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class SecondActivity extends Activity{



    private Intent goToAdd,goToChange,goToDelete,goToFind,goToQuit;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        goToAdd=new Intent(this,AddActivity.class);
        goToChange=new Intent(this,ChangeActivity.class);
        goToDelete=new Intent(this,DeleteActivity.class);
        goToFind=new Intent(this,FindActivity.class);
        goToQuit=new Intent(this,MainActivity.class);
    }

    @SuppressLint("SetTextI18n")
    public void add(View v) {
        startActivity(goToAdd);
    }
    public void change(View v) {
        startActivity(goToChange);
    }
    public void delete(View v) {
        startActivity(goToDelete);
    }
    public void find(View v) {
        startActivity(goToFind);
    }
    public void quit(View v) {
        startActivity(goToQuit);
    }
}
