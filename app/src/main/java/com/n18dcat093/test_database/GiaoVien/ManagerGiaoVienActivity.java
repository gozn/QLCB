package com.n18dcat093.test_database.GiaoVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.Hoadon.DsGiaoVien;
import com.n18dcat093.test_database.HomeActivity;
import com.n18dcat093.test_database.MainActivity;
import com.n18dcat093.test_database.MonHoc.QuanLiMonHoc;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.TTPCB.QuanLiTTPCB;

import java.util.ArrayList;

public class ManagerGiaoVienActivity extends AppCompatActivity {

    DatabaseQLCB dbGiaoVien;
    ListView listView;
    GiaoVienAdapter adapter;
    ArrayList<GiaoVien> data;
    Button btnAdd;
    TextView magv,hoten,sdt,header;
    ArrayList<String> activities = new ArrayList<>();
    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_giao_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void initActivity(){
        activities.add("Quản lí giáo viên");
        activities.add("Quản lí môn học");
        activities.add("Quản lí phiếu chấm bài");
    }

    public void setControl(){
        magv = findViewById(R.id.magvShow);
        hoten = findViewById(R.id.hotenShow);
        sdt = findViewById(R.id.sdtShow);
        //header = findViewById(R.id.showHeader);
        listView = findViewById(R.id.list_listview);
        btnAdd = findViewById(R.id.btnThem);
        botNav = findViewById(R.id.botNavGV);

    }
    public void setEvent(){
        botNav.setSelectedItemId(R.id.QLGV);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.QLPCBz:
                        Intent intent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.QLMH:
                        Intent intent2 = new Intent(getApplicationContext(), QuanLiMonHoc.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 1);
                        return true;
                    case R.id.TTPCB:
                        Intent intent1 = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 1);
                        return true;
                    case R.id.XHD:
                        Intent intent3 = new Intent(getApplicationContext(), DsGiaoVien.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 1);
                        return true;
                }
                return false;
            }
        });

        dbGiaoVien = new DatabaseQLCB(this);
        /*GiaoVien gv1 = new GiaoVien("GV02","Thanh Tu","0374576967");
        dbGiaoVien.insertGV(gv1);*/
        data = dbGiaoVien.getListGiaoVien();
        adapter = new GiaoVienAdapter(this,R.layout.giaovien_item,data);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddGiaoVienActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GiaoVien gv = data.get(i);
                Intent intent = new Intent(getApplicationContext(),EditGiaoVienActivity.class);
                intent.putExtra("MAGV",gv.getId());
                intent.putExtra("HOTEN",gv.getHoTen());
                intent.putExtra("SDT",gv.getSdt());
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