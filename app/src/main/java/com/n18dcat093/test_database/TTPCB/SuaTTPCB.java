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
import com.n18dcat093.test_database.R;

import java.util.List;

public class SuaTTPCB extends AppCompatActivity {

    TextInputLayout txtmaphieu, txtmamonhoc, txtsobai;
    Button btnSua, btnXoa, btnBack;
    DatabaseQLCB dbTTPCB;Button btnClose, btnCloseE;
    TextView txtSuccess, txtError;
    ImageView imgSuccess, imgError;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ttpcb);
        setControl();
        setEvent();
    }

    public void setControl() {
        txtmaphieu = findViewById(R.id.editmaphieu);
        txtmamonhoc = findViewById(R.id.editmamonhoc);
        txtsobai = findViewById(R.id.editsobai);
        btnSua = findViewById(R.id.btnEdit);
        btnXoa = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);
        dialog = new Dialog(this);
        btnClose = dialog.findViewById(R.id.btnClose);
        imgSuccess = dialog.findViewById(R.id.imageSuccess);
        txtSuccess = (TextView) dialog.findViewById(R.id.txtSuccess);

        btnCloseE = dialog.findViewById(R.id.btnCloseE);
        imgError = dialog.findViewById(R.id.imageError);
        txtError = (TextView) dialog.findViewById(R.id.txtError);

        Intent intent = getIntent();
        txtmaphieu.getEditText().setText(intent.getStringExtra("MAPHIEU"));
        txtmamonhoc.getEditText().setText(intent.getStringExtra("MAMONHOC"));
        txtsobai.getEditText().setText(intent.getStringExtra("SOBAI"));
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

    public void setEvent() {
        dbTTPCB = new DatabaseQLCB(this);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maphieu = txtmaphieu.getEditText().getText().toString();
                String mamh = txtmamonhoc.getEditText().getText().toString();
                String sobai = txtsobai.getEditText().getText().toString();
                if( checkMaMH(mamh) == true && checkMaPhieu(maphieu)==true){
                    PCB pcb = new PCB(maphieu, mamh, sobai);
                    if(dbTTPCB.updateTTPCB(pcb) == true) {
                        showDialogSuccess("S???a th??ng tin phi???u ch???m b??i th??nh c??ng!");
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                                startActivity(intent);
                            }
                        });
                    }
                    else{
                        showDialogError("S???a phi???u ch???m b??i th???t b???i!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                }
                else {
                    if(checkMaMH(mamh) == false) {
                        showDialogError("M?? m??n h???c sai ho???c kh??ng t???n t???i");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    } else if(checkMaPhieu(maphieu)==false){
                        showDialogError("M?? phi???u sai ho???c kh??ng t???n t???i");
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
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maphieu = txtmaphieu.getEditText().getText().toString();
                if (dbTTPCB.deleteTTPCB(maphieu) == true) {
                    showDialogSuccess("X??a th??ng tin phi???u ch???m b??i th??nh c??ng!");
                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    showDialogError("X??a th???t b???i");
                    btnCloseE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                }
                Intent intent = new Intent(getApplicationContext(), QuanLiTTPCB.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
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

