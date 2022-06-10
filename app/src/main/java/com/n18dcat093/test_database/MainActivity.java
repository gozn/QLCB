package com.n18dcat093.test_database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.n18dcat093.test_database.GiaoVien.ManagerGiaoVienActivity;
import com.n18dcat093.test_database.Hoadon.DsGiaoVien;
import com.n18dcat093.test_database.MonHoc.QuanLiMonHoc;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.TTPCB.QuanLiTTPCB;
import com.n18dcat093.test_database.User.UserActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(MainActivity.this, LoginActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}