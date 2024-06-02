package com.example.work;

import android.annotation.SuppressLint;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText password,account,sure;
    private Intent ToMain;
    private Button register,tomain;
    private String password1,account1,sure1;
    private CheckBox checkbox;


    @SuppressLint({"SuspiciousIndentation", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        account = (EditText) findViewById(R.id.register_account);
        password = (EditText) findViewById(R.id.register_password);
        sure = (EditText) findViewById(R.id.register_surepwd);

        ToMain =new Intent("android.intent.action.MAIN");
        ToMain.addCategory("android.intent.category.LAUNCHER");
        tomain = (Button)findViewById(R.id.tomain);
        tomain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();

                checkbox = (CheckBox) findViewById(R.id.remember);
                account1 = account.getText().toString();
                password1 = password.getText().toString();
                sure1 = sure.getText().toString();
                if (account1.equals("admin") && sure1.equals(password1)){
                    editor.putString("account", account1);
                    editor.putString("password", password1);
                    editor.putBoolean("true",true);
                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(ToMain);
                }
                else
                    Toast.makeText(RegisterActivity.this, "管理员账号为非admin或两次密码不一样", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
