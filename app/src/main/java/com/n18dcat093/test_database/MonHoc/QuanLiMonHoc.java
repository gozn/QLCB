package com.n18dcat093.test_database.MonHoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.GiaoVien.ManagerGiaoVienActivity;
import com.n18dcat093.test_database.Hoadon.DsGiaoVien;
import com.n18dcat093.test_database.HomeActivity;
import com.n18dcat093.test_database.MainActivity;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.TTPCB.QuanLiTTPCB;

import java.util.ArrayList;

public class QuanLiMonHoc extends AppCompatActivity implements MonHocAdapter.MonHocClickListener{

    DatabaseQLCB dbMonHoc;
    ListView listView;
    ListView listView_search;
    MonHocAdapter monHocAdapter;
    MonHoc monHoc;
    ArrayList<MonHoc> data;
    Button btnAdd;
    TextView mamh,tenmh,chiphi,header;
    private SearchView searchView;
    BottomNavigationView botNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_mon_hoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void setControl(){
        mamh = findViewById(R.id.mamonshow);
        tenmh = findViewById(R.id.tenmonhocshow);
        chiphi = findViewById(R.id.chiphishow);
        header = findViewById(R.id.showHeader);
        listView = findViewById(R.id.list_listview);
        btnAdd = findViewById(R.id.btnThem);
        botNav=findViewById(R.id.botNavMH);
    }

    public void setEvent(){
        dbMonHoc = new DatabaseQLCB(this);
        botNav.setSelectedItemId(R.id.QLMH);
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
        data = dbMonHoc.DocDL();
        monHocAdapter = new MonHocAdapter(this,R.layout.monhoc_item,data,this::select);
        listView.setAdapter(monHocAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ThemMonHoc.class);
                startActivity(intent);
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                MonHoc mh = data.get(i);
//                Intent intent = new Intent(getApplicationContext(), SuaMonHoc.class);
//                intent.putExtra("MAMH",mh.getMaMH());
//                intent.putExtra("TENMH",mh.getTenMH());
//                intent.putExtra("CHIPHI",mh.getChiPhi());
//                startActivity(intent);
//            }
//        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                monHocAdapter.getFilter().filter(query);
                // lọc filter khi dữ liệu truy vấn
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                monHocAdapter.getFilter().filter(newText);
                // lọc filter khi dữ liệu thay đổi
                return false;
            }

        });
        return true;
    }
    // thu thanh serch view trc khi thoat ung dung
    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);

            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(myIntent);
        return true;
    }


    //code moi===============
    @Override
    public void select(MonHoc monHoc) {
        startActivity(new Intent(this,SuaMonHoc.class).putExtra("data",monHoc));
        // su dung putextra de truyen doi tuong qua suamonhoc
    }
}
