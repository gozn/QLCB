package com.n18dcat093.test_database.PhieuChamBai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.validate;

import java.util.Calendar;
import java.util.List;

public class AddPhieuChamBaiActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextInputLayout txtSoPhieu , txtNgayGiao, txtMaGV;
    Button btnAddPCB, btnBack, btnClose, btnCloseE;
    DatabaseQLCB db;
    TextView txtSuccess, txtError;
    ImageView imgSuccess, imgError;
    String x;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.n18dcat093.test_database.R.layout.activity_add_phieu_cham_bai);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
        datePickerDialog.getDatePicker().getTouchables().get(0).performClick();
        datePickerDialog.show();

    }

    private void setControl() {
        txtSoPhieu = findViewById(R.id.addSoPhieu);
        txtNgayGiao = findViewById(R.id.addNgayGiao);
        txtMaGV = findViewById(R.id.addMaGV);
        btnAddPCB = findViewById(R.id.btnAddPCB);
        btnBack = findViewById(R.id.btnBackPCB);
        //btnCalendar = findViewById(R.id.btnCalendar);
        dialog = new Dialog(this);
        btnClose = dialog.findViewById(R.id.btnClose);
        imgSuccess = dialog.findViewById(R.id.imageSuccess);
        txtSuccess = (TextView) dialog.findViewById(R.id.txtSuccess);

        btnCloseE = dialog.findViewById(R.id.btnCloseE);
        imgError = dialog.findViewById(R.id.imageError);
        txtError = (TextView) dialog.findViewById(R.id.txtError);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month += 1;
        String date = day + "/" + month + "/" + year;
        txtNgayGiao.getEditText().setText(date);
    }

    private boolean checkMaGV(String MaGV) {
        db = new DatabaseQLCB(this);
        int flag = 0;
        List<String> id = db.getIdGiaoVien();
        for(int i = 0; i < id.size(); i++) {
            if(MaGV.equals(id.get(i))) {
                flag += 1;
            }
        }
        if(flag > 0) return true;
        return false;
    }

    private void setEvent() {
        db = new DatabaseQLCB(this);
        btnAddPCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SoPhieu = txtSoPhieu.getEditText().getText().toString();
                String NgayGiao = txtNgayGiao.getEditText().getText().toString();
                String MaGV = txtMaGV.getEditText().getText().toString();
                if(validate.validateDate(NgayGiao) == true && checkMaGV(MaGV) == true && validate.isNumber(SoPhieu)==true){
                    PhieuChamBai pcb = new PhieuChamBai(SoPhieu, NgayGiao, MaGV);
                    if(db.insertPCB(pcb) == true && !SoPhieu.equals("")) {
//                        Toast.makeText(AddPhieuChamBaiActivity.this, "Th??m phi???u th??nh c??ng", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
//                        startActivity(intent);
                        showDialogSuccess("Th??m phi???u th??nh c??ng!");
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
                                startActivity(intent);
                            }
                        });

                    } else {
                        showDialogError("Th??m phi???u th???t b???i!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                }
                else {
                    if(validate.isNumber(SoPhieu)==false) {
                        showDialogError("S??? phi???u kh??ng ch???a ch???");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                    else if(SoPhieu.equals("")) {
                        showDialogError("S??? phi???u tr???ng!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                    else if(validate.validateDate(NgayGiao) == false){
                        showDialogError("Ng??y giao kh??ng h???p l???!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }

                    else if(checkMaGV(MaGV) == false) {
                        showDialogError("M?? gi??o vi??n sai ho???c kh??ng t???n t???i!");
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
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
                startActivity(intent);
            }
        });
        txtNgayGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
        startActivityForResult(myIntent, 0);
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