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

public class OldMainActivity extends AppCompatActivity {

    Button btnManager, btnManagerPCB, btnquanlimonhoc, btnThongKe,btnQLTTPCB, btnhoadon,btnQlUser;
    DatabaseQLCB db;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    @SuppressLint("ResourceType")
    public void setControl(){
        btnManager = findViewById(R.id.mangerGV);
        btnManagerPCB = findViewById(R.id.managerPCB);
        btnquanlimonhoc = findViewById(R.id.qlmonhocMain);
        btnThongKe = findViewById(R.id.btnThongKe);
        //spinner = findViewById(R.id.spinner);
        btnQLTTPCB=findViewById(R.id.btnQLTTPCB);
        btnhoadon = findViewById(R.id.btnhoadon);
        btnQlUser=findViewById(R.id.btnqlUser);
    }
    public void setEvent(){
        db = new DatabaseQLCB(this);
        //test.setText(x);
        btnManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManagerGiaoVienActivity.class);
                startActivity(intent);
            }
        });

        btnManagerPCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
                startActivity(intent);
            }
        });
        btnquanlimonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLiMonHoc.class);
                startActivity(intent);
            }
        });
        btnQLTTPCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                startActivity(intent);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), chart.class);
                startActivity(intent);
            }
        });
        btnhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DsGiaoVien.class);
                startActivity(intent);
            }
        });
        btnQlUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }
}