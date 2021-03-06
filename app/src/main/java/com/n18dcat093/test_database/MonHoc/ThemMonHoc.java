package com.n18dcat093.test_database.MonHoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.validate;

import java.util.ArrayList;

public class ThemMonHoc extends AppCompatActivity {

    TextInputLayout txtmaMH,txttenMH, txtChiphi;
    Button btnThem, btnQuaylai;
    DatabaseQLCB dbMonHoc;
    Button btnClose, btnCloseE;
    TextView txtSuccess, txtError;
    ImageView imgSuccess, imgError;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_hoc);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setControl(){
        txtmaMH = findViewById(R.id.txtmamonhoc);
        txttenMH = findViewById(R.id.txttenmon);
        txtChiphi = findViewById(R.id.txtchiphi);
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
    public void setEvent(){
        dbMonHoc= new DatabaseQLCB(this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList <MonHoc> ds = new ArrayList<>(dbMonHoc.danhsachID());
                String inputmaMH = txtmaMH.getEditText().getText().toString().trim();
                String inputtenMH=txttenMH.getEditText().getText().toString().trim();
                String inputChiPhi=txtChiphi.getEditText().getText().toString().trim();
                if(inputmaMH.equals("") || inputtenMH.equals("") || inputChiPhi.equals("") ){
                    showDialogError("kh??ng ????? th??ng tin tr???ng");
                    btnCloseE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    return;
                }
                else if(validate.validateLetters(inputtenMH)==false){
                    showDialogError("t??n m??n h???c kh??ng h???p l???");
                    btnCloseE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    return;
                }
                else if(validate.isNumber(inputChiPhi)==false){
                    showDialogError("Kh??ng nh???p chi ph?? b???ng ch??? v?? chi ph?? kh??ng ch???a kho???n c??ch!");
                    btnCloseE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    return;
                }
                else if(validate.KiemTraChiPhi(inputChiPhi)==false){
                            showDialogError("Chi Ph?? kh??ng h???p l???!, chi ph?? th???p nh???t l?? 10,000 va l???n nh???t l?? 50,000");
                            btnCloseE.setOnClickListener(new View.OnClickListener() {
                                @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });                    return;
                }
                for(MonHoc mh : ds ){
                    if(inputmaMH.equals(mh.getMaMH())){
                        showDialogError("M?? m??n h???c"+inputmaMH+"???? t???n t???i!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                        return;}
                }
                MonHoc monHoc=getMonHoc();
                dbMonHoc.ThemDL(monHoc);
                showDialogSuccess("Th??m m??n h???c th??nh c??ng!");
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), QuanLiMonHoc.class);
                        startActivity(intent);
                    }
                });
            }
            private MonHoc getMonHoc() {
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMH(txtmaMH.getEditText().getText().toString().trim().toUpperCase());
                monHoc.setTenMH(validate.chuanHoa(txttenMH.getEditText().getText().toString().trim().toUpperCase()));
                monHoc.setChiPhi(txtChiphi.getEditText().getText().toString().trim());
                return monHoc;
            }
        });
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),QuanLiMonHoc.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), QuanLiMonHoc.class);
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