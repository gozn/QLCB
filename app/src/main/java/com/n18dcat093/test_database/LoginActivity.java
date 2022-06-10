package com.n18dcat093.test_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    DatabaseQLCB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final LoadingDialog loadingDialog=new LoadingDialog(LoginActivity.this);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.pasword);
        AppCompatButton btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
//        AppCompatButton btnSignup = (AppCompatButton) findViewById(R.id.btnSignup);
        TextView forgetLable = (TextView) findViewById(R.id.forgetLable);
        TextView registerLable = (TextView) findViewById(R.id.registerLable) ;
        db = new DatabaseQLCB(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals("")) {
                    Toast.makeText(LoginActivity.this,"Vui lòng không bỏ trống các trường!",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean check = db.checkAccount(user, pass);
                    if (check == true) {
                        loadingDialog.startLoadingDialog();
                        Toast.makeText(LoginActivity.this,"Login Succeed",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("session", user);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Thông tin tài khoản hoặc mật khẩu không chính xác!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
//                startActivity(intent);
//            }
//        });

        forgetLable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotActivity.class);
                startActivity(intent);
            }
        });

        registerLable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}