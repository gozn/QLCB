package com.n18dcat093.test_database.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.GiaoVien.ManagerGiaoVienActivity;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBai;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBaiActivity;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.User.User;
import com.n18dcat093.test_database.validate;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUser extends AppCompatActivity {

    TextInputLayout txtusername,txtpassword;
    Button btnThem, btnBack;
    DatabaseQLCB db;
    Button btnClose, btnCloseE;
    TextView txtSuccess, txtError;
    ImageView imgSuccess, imgError;
    Dialog dialog;
    ImageView imageGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void setControl(){
        txtusername = findViewById(R.id.addusername);
        txtpassword = findViewById(R.id.addpassword);
        btnThem = findViewById(R.id.btnAddUser);
        btnBack = findViewById(R.id.btnBackUser);
        dialog = new Dialog(this);
        btnClose = dialog.findViewById(R.id.btnClose);
        imgSuccess = dialog.findViewById(R.id.imageSuccess);
        txtSuccess = (TextView) dialog.findViewById(R.id.txtSuccess);

        btnCloseE = dialog.findViewById(R.id.btnCloseE);
        imgError = dialog.findViewById(R.id.imageError);
        txtError = (TextView) dialog.findViewById(R.id.txtError);
    }

    public void setEvent(){
        db = new DatabaseQLCB(this);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtusername.getEditText().getText().toString();
                String password = txtpassword.getEditText().getText().toString();
                    User us = new User(username, password);
                    if(db.insertUser(us) == true && !username.equals("")) {
//                        Toast.makeText(AddPhieuChamBaiActivity.this, "Thêm phiếu thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), PhieuChamBaiActivity.class);
//                        startActivity(intent);
                        showDialogSuccess("Thêm phiếu thành công!");
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                startActivity(intent);
                            }
                        });

                    } else {
                        showDialogError("Thêm phiếu thất bại!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }





    public static String chuanHoa(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }
    public static String chuanHoaDanhTuRieng(String str) {
        str = chuanHoa(str);
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1).toLowerCase();
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), UserActivity.class);
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