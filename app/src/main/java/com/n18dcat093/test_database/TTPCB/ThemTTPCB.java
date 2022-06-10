package com.n18dcat093.test_database.TTPCB;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.validate;

import java.util.List;

public class ThemTTPCB extends AppCompatActivity {

    TextInputLayout txtmaphieuCB,txtmaMH, txtSobai;
    Button btnThem, btnQuaylai;
    DatabaseQLCB dbTTPCB;
    Button btnClose, btnCloseE;
    TextView txtSuccess, txtError;
    ImageView imgSuccess, imgError;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ttpcb);
        setControl();
        setEvent();
    }

    public void setControl(){
        txtmaphieuCB = findViewById(R.id.txtmaphieu);
        txtmaMH = findViewById(R.id.txtmamonhoc);
        txtSobai = findViewById(R.id.txtsobai);
        btnThem = findViewById(R.id.btnAdd);
        btnQuaylai = findViewById(R.id.btnBack);
        dialog = new Dialog(this);
        btnClose = dialog.findViewById(R.id.btnClose);
        imgSuccess = dialog.findViewById(R.id.imageSuccess);
        txtSuccess = (TextView) dialog.findViewById(R.id.txtSuccess);

        btnCloseE = dialog.findViewById(R.id.btnCloseE);
        imgError = dialog.findViewById(R.id.imageError);
        txtError = (TextView) dialog.findViewById(R.id.txtError);
    }

    private boolean checkMaMH(String MAMH) {
        dbTTPCB = new DatabaseQLCB(this);
        int flag = 0;
        List<String> id = dbTTPCB.getIdMonHoc();
        for(int i = 0; i < id.size(); i++) {
            if(MAMH.equals(id.get(i))) {
                flag += 1;
            }
        }
        if(flag > 0) return true;
        return false;
    }
    private boolean checkMaPhieu(String maPhieu) {
        dbTTPCB = new DatabaseQLCB(this);
        int flag = 0;
        List<String> id = dbTTPCB.getIdPhieu();
        for(int i = 0; i < id.size(); i++) {
            if(maPhieu.equals(id.get(i))) {
                flag += 1;
            }
        }
        if(flag > 0) return true;
        return false;
    }

    private void setEvent() {
        dbTTPCB = new DatabaseQLCB(this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maphieu = txtmaphieuCB.getEditText().getText().toString();
                String mamh = txtmaMH.getEditText().getText().toString().toUpperCase();
                String sobai = txtSobai.getEditText().getText().toString();
                if ( checkMaMH(mamh) == true && checkMaPhieu(maphieu)==true && validate.isNumber(sobai)==true) {
                    PCB pcb = new PCB(maphieu, mamh, sobai);
                    if (dbTTPCB.insertTTPCB(pcb) == true) {
                        showDialogSuccess("Thêm thông tin phiếu chấm bài thành công!");
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        showDialogError("Thêm phiếu chấm bài thất bại!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });                    }
                } else {
                    if (checkMaMH(mamh) == false) {
                        showDialogError("Mã môn học sai hoặc không tồn tại!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });                    }
                    else if(checkMaPhieu(maphieu)==false){
                        showDialogError("Mã phiếu không tồn tại!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                    else if(validate.isNumber(sobai)==false){
                        showDialogError("Số bài không chứa chữ cái!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                }
            }
        });
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
        startActivity(myIntent);
        return true;
    }
    private void showDialogSuccess(String success) {
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgSuccess = dialog.findViewById(R.id.imageSuccess);
        btnClose = dialog.findViewById(R.id.btnClose);
        txtSuccess = dialog.findViewById(R.id.txtSuccess);
        txtSuccess.setText(success);
        dialog.show();
    }

    private void showDialogError(String error) {
        dialog.setContentView(R.layout.error_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgError = dialog.findViewById(R.id.imageError);
        btnCloseE = dialog.findViewById(R.id.btnCloseE);
        txtError = dialog.findViewById(R.id.txtError);
        txtError.setText(error);
        dialog.show();
    }
}