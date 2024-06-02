package com.example.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private EditText password,account;
    private Intent ToSecond,ToRegister;
    private Button login,register;
    private String password1,account1;
    private CheckBox checkbox;


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        try {
            SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
            if (sp.getBoolean("true", false))

                account.setText(sp.getString("account", ""));
            password.setText(sp.getString("password", ""));
            checkbox.setChecked(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ToSecond =new Intent(this,SecondActivity.class);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);

                checkbox = (CheckBox) findViewById(R.id.remember);
                account1 = account.getText().toString();
                password1 = password.getText().toString();
                if (account1.equals(sp.getString("account", "")) && password1.equals(sp.getString("password", ""))){
                    Toast.makeText(MainActivity.this, "欢迎进入管理员操作台", Toast.LENGTH_SHORT).show();
                    startActivity(ToSecond);
                }
                else
                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();

                if (checkbox.isChecked()) {
                    editor.putString("account", account1);
                    editor.putString("password", password1);
                    editor.putBoolean("true",true);
                    editor.apply();
                }
                else
                {
                    account.setText("");
                    password.setText("");
                    editor.putString("account",null);
                    editor.putString("password",null);
                    editor.putBoolean("true",true);
                    editor.apply();
                }
            }
        });
        ToRegister =new Intent("android.intent.action.Register");
        ToRegister.addCategory("android.intent.category.Register");
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(ToRegister);
            }
        });
    }
}
