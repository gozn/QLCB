package com.n18dcat093.test_database.Hoadon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.HomeActivity;
import com.n18dcat093.test_database.MonHoc.MonHoc;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBai;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiAdapter;
import com.n18dcat093.test_database.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PhieuThanhToan extends AppCompatActivity {
    DatabaseQLCB db;
    ListView listView;
    ArrayList<MonHoc> data;
    ArrayList<PhieuChamBai> p;
    Button btnback, btnxuat;
    TextView SoPhieuHD, NgayGiaoHD, Thanhtien;
    TextView magv,hoten,sdt, tenmh, sobai, chiphi, mamh;
    PhieuChamBaiAdapter adapterPCB;
    AdapterPhieuThanhToan adapter;
    ArrayList<THONGTINCHAMBAI> listThongTin;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_thanh_toan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }
    public void setControl(){

        magv = findViewById(R.id.magvhd);
        hoten = findViewById(R.id.hotengvhd);
        sdt = findViewById(R.id.sdtgvhd);
        listView = findViewById(R.id.lvHD);
        SoPhieuHD = findViewById(R.id.soPhieuHD);
        NgayGiaoHD = findViewById(R.id.ngayGiaoHD);
        tenmh = findViewById(R.id.monhoc);
        mamh = findViewById(R.id.mamh);
        sobai = findViewById(R.id.sobaiHD);
        chiphi = findViewById(R.id.chiphiHD);
        Thanhtien = findViewById(R.id.thanhtien);
        btnxuat = findViewById(R.id.btnin);
        btnback = findViewById(R.id.btnback);

        Intent intent = getIntent();
        magv.setText(intent.getStringExtra("MAGV"));
        hoten.setText(intent.getStringExtra("HOTENGV"));
        sdt.setText(intent.getStringExtra("SODT"));
        id = magv.getText().toString();
//        Toast.makeText(PhieuThanhToan.this,id,Toast.LENGTH_SHORT).show();
    }
    public void setEvent() {

//        data = db.getPCB1GV(magv.getText().toString());
//        adapterPCB = new PhieuChamBaiAdapter(this,R.layout.phieuthanhtoan,p);
//        listView.setAdapter(adapterPCB);
//        GiaoVien gv = db.getGiaoVienById(id);
//         p = db.getPCB1GV(id);
//        PhieuthanhtoanObject ph = new PhieuthanhtoanObject();
//        ph.setList(p);
//        ph.setGv(gv);
        db = new DatabaseQLCB(this);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        listThongTin = db.getPCB1GV1(id);
        adapter = new AdapterPhieuThanhToan(this, R.layout.phieuthanhtoan,listThongTin);
        listView.setAdapter(adapter);
        long total =0;
        for (int i=0; i<listThongTin.size(); i++){
            total+= Long.valueOf(listThongTin.get(i).getThanhtien());
        }
        Thanhtien.setText(formatter.format(total));
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DsGiaoVien.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), DsGiaoVien.class);
        startActivity(myIntent);
        return true;
    }

}
