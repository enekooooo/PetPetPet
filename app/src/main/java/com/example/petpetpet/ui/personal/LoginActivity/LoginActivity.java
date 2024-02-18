package com.example.petpetpet.ui.personal.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petpetpet.MainActivity;
import com.example.petpetpet.R;
import com.example.petpetpet.mysql.DBUserHelper;
import com.example.petpetpet.ui.personal.PersonalAboveActivity.PersonalFollow;
import com.example.petpetpet.ui.personal.RegisterActivity.RegisterActivity;
import com.example.petpetpet.ui.personal.db.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {//登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_login);

        Button login_register_button = findViewById(R.id.login_register_button);//未注册，跳转到注册页面
        login_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button login_button = findViewById(R.id.login_button);    //登录
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: 2024.2.4 登录处理
                EditText login_name = findViewById(R.id.login_name);
                EditText login_pwd = findViewById(R.id.login_pwd);

                String name = login_name.getText().toString();
                String password = login_pwd.getText().toString();


                if(!Objects.equals(DBUserHelper.query("userName", "userName", name), name)) {
                    Toast.makeText(getApplicationContext(),"该用户名未注册，请前往注册",Toast.LENGTH_SHORT).show();
                }else if(!Objects.equals(DBUserHelper.query("userName", "userPassword", name),password)){
                    Toast.makeText(getApplicationContext(),"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    user.setUserName(name);
                    user.setUserId(Integer.parseInt(DBUserHelper.query("userName", "userId", name)));
                    user.setUserType(1);
                    Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}