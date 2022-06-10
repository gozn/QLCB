package com.n18dcat093.test_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DatabaseQLCB db;
    EditText username, password, repassword;
    AppCompatButton btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnSignup = (AppCompatButton) findViewById(R.id.btnSignup);
        TextView loginLable = (TextView) findViewById(R.id.loginLable);
        db = new DatabaseQLCB(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(SignupActivity.this, "Không được bỏ trống các trường",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(repass)) {
                        boolean checkUsername = db.checkUsername(user);
                        if(checkUsername == false) {
                            boolean Create = db.CreateUser(user,pass);
//                            boolean Create = db.Create(user,pass);
                            if(Create == true) {
                                Toast.makeText(SignupActivity.this,"Đăng ký tài khoản thành công!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Đăng ký tài khoản thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        loginLable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}