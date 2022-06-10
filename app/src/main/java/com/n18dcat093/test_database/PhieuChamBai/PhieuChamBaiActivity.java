package com.n18dcat093.test_database.PhieuChamBai;

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
import com.n18dcat093.test_database.MonHoc.QuanLiMonHoc;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.TTPCB.QuanLiTTPCB;

import java.util.ArrayList;

public class PhieuChamBaiActivity extends AppCompatActivity {

    DatabaseQLCB db;
    ListView listView;
    PhieuChamBaiAdapter adapterPCB;
    ArrayList<PhieuChamBai> data;
    Button btnAddPCB;
    TextView SoPhieu, NgayGiao, MaGV;
    ArrayList<String> activities = new ArrayList<>();
    BottomNavigationView botNav;


    private void initActivity(){
        activities.add("Quản lí giáo viên");
        activities.add("Quản lí môn học");
        activities.add("Quản lí phiếu chấm bài");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_cham_bai);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        SoPhieu = findViewById(R.id.txtSoPhieu);
        NgayGiao = findViewById(R.id.txtNgayGiao);
        MaGV = findViewById(R.id.txtMaGV);
        listView = findViewById(R.id.lvPCB);
        btnAddPCB = findViewById(R.id.btnAddPCB);
        botNav = findViewById(R.id.botNavPCB);
    }

    private void setEvent() {
        db = new DatabaseQLCB(this);
        botNav.setSelectedItemId(R.id.QLPCBz);
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

        data = db.getListPCB();
        adapterPCB = new PhieuChamBaiAdapter(this,R.layout.phieuchambai_item,data);
        listView.setAdapter(adapterPCB);
        btnAddPCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddPhieuChamBaiActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhieuChamBai pcb = data.get(i);
                Intent intent = new Intent(getApplicationContext(), EditPhieuChamBaiActivity.class);
                intent.putExtra("SOPHIEU",pcb.getSoPhieu());
                intent.putExtra("NGAYGIAO",pcb.getNgayGiao());
                intent.putExtra("MAGV",pcb.getMaGV());
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