package com.n18dcat093.test_database.Hoadon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.GiaoVien.GiaoVien;
import com.n18dcat093.test_database.GiaoVien.GiaoVienAdapter;
import com.n18dcat093.test_database.GiaoVien.ManagerGiaoVienActivity;
import com.n18dcat093.test_database.HomeActivity;
import com.n18dcat093.test_database.MainActivity;
import com.n18dcat093.test_database.MonHoc.QuanLiMonHoc;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.TTPCB.QuanLiTTPCB;

import java.util.ArrayList;

public class DsGiaoVien extends AppCompatActivity {
    DatabaseQLCB dbGiaoVien;
    ListView listView;
    GiaoVienAdapter adapter;
    ArrayList<GiaoVien> data;
    TextView magv,hoten,sdt,header;
    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_giao_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void setControl(){
        magv = findViewById(R.id.magvShow);
        hoten = findViewById(R.id.hotenShow);
        sdt = findViewById(R.id.sdtShow);
        header = findViewById(R.id.showHeader);
        listView = findViewById(R.id.list_listview);
        botNav = findViewById(R.id.botNavXHD);
       /* Typeface typeface = ResourcesCompat.getFont(this,R.font.consola);
        magv.setTypeface(typeface);
        hoten.setTypeface(typeface);
        sdt.setTypeface(typeface);
        header.setTypeface(typeface);*/
    }
    public void setEvent() {
        botNav.setSelectedItemId(R.id.XHD);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.QLGV:
                        Intent intent = new Intent(getApplicationContext(), ManagerGiaoVienActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.QLMH:
                        Intent intent2 = new Intent(getApplicationContext(), QuanLiMonHoc.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 1);
                        return true;
                    case R.id.QLPCBz:
                        Intent intent1 = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.TTPCB:
                        Intent intent3 = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 1);
                        return true;
                }
                return false;
            }
        });
        dbGiaoVien = new DatabaseQLCB(this);
        data = dbGiaoVien.getListGiaoVien();
        adapter = new GiaoVienAdapter(this, R.layout.giaovien_item, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GiaoVien gv = data.get(i);
                Intent intent = new Intent(getApplicationContext(), PhieuThanhToan.class);
                intent.putExtra("MAGV",gv.getId());
                intent.putExtra("HOTENGV",gv.getHoTen());
                intent.putExtra("SODT",gv.getSdt());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(myIntent);
        return true;
    }

}
