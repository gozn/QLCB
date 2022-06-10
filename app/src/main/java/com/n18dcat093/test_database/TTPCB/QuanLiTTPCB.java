package com.n18dcat093.test_database.TTPCB;

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
import com.n18dcat093.test_database.GiaoVien.ManagerGiaoVienActivity;
import com.n18dcat093.test_database.Hoadon.DsGiaoVien;
import com.n18dcat093.test_database.HomeActivity;
import com.n18dcat093.test_database.MainActivity;
import com.n18dcat093.test_database.MonHoc.MonHocAdapter;
import com.n18dcat093.test_database.MonHoc.QuanLiMonHoc;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.R;

import java.util.ArrayList;

public class QuanLiTTPCB extends AppCompatActivity {

    DatabaseQLCB dbTTPCB;
    ListView listView;
    TTPCBAdapter ttpcbAdapter;
    ArrayList<PCB> data;
    Button btnAdd;
    TextView maphieu,mamonhoc,sobai,header;
    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_ttpcb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void setControl(){
        maphieu = findViewById(R.id.maphieushow);
        mamonhoc = findViewById(R.id.mamonhocshow);
        sobai = findViewById(R.id.sobaishow);
        header = findViewById(R.id.showHeader);
        listView = findViewById(R.id.list_listview);
        btnAdd = findViewById(R.id.btnThem);
        botNav = findViewById(R.id.botNavTTPCB);
    }
    public void setEvent(){
        botNav.setSelectedItemId(R.id.TTPCB);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.QLGV:
                        Intent intent = new Intent(getApplicationContext(), ManagerGiaoVienActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.QLPCBz:
                        Intent intent2 = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 1);
                        return true;
                    case R.id.QLMH:
                        Intent intent1 = new Intent(getApplicationContext(), QuanLiMonHoc.class);
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
        dbTTPCB = new DatabaseQLCB(this);
        data = dbTTPCB.getListTTPCB();
        ttpcbAdapter = new TTPCBAdapter(this,R.layout.phieuchambai_item,data);
        listView.setAdapter(ttpcbAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ThemTTPCB.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PCB pcb = data.get(i);
                Intent intent = new Intent(getApplicationContext(),SuaTTPCB.class);
                intent.putExtra("MAPHIEU",pcb.getMaPhieu());
                intent.putExtra("MAMONHOC",pcb.getMaMH());
                intent.putExtra("SOBAI",pcb.getSoBai());
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