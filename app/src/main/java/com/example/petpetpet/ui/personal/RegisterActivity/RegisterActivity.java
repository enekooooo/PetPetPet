package com.example.petpetpet.ui.personal.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petpetpet.MainActivity;
import com.example.petpetpet.R;
import com.example.petpetpet.mysql.DBOpenHelper;
import com.example.petpetpet.mysql.DBUserHelper;
import com.example.petpetpet.ui.personal.LoginActivity.LoginActivity;
import com.example.petpetpet.ui.personal.PersonalFragment;
import com.example.petpetpet.ui.personal.db.User;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {//注册


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_register);



        Button register_button = findViewById(R.id.register_button);//注册并登录
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2024.2.4 注册（未设置社区/个人、所在社区）

                EditText register_name = findViewById(R.id.register_name);
                EditText register_pwd = findViewById(R.id.register_pwd);
                EditText register_pwd2 = findViewById(R.id.register_pwd2);

                String name = register_name.getText().toString();
                String password = register_pwd.getText().toString();
                String password2 = register_pwd2.getText().toString();

                if(Objects.equals(DBUserHelper.query("userName", "userName", name), name)){
                    Toast.makeText(getApplicationContext(),"该用户名已存在",Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(password2)){
                    Toast.makeText(getApplicationContext(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }
                else {
                    DBUserHelper.insert(name, password,1,null);//userType为1时为个人，userType为0时为社区
                    User user = new User();
                    user.setUserName(name);
                    user.setUserId(Integer.parseInt(DBUserHelper.query("userName", "userId", name)));
                    user.setUserType(1);
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent("android.intent.action.CART_BROADCAST");
//                    intent.putExtra("data","refresh");
//                    LocalBroadcastManager.getInstance(RegisterActivity.this).sendBroadcast(intent);
//                    sendBroadcast(intent);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });



    }
}