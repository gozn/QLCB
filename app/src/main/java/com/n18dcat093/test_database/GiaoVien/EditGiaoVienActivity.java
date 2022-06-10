package com.n18dcat093.test_database.GiaoVien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.n18dcat093.test_database.DatabaseQLCB;
import com.n18dcat093.test_database.PhieuChamBai.PhieuChamBai;
import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.validate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditGiaoVienActivity extends AppCompatActivity {

    private static final int SELECT_FILE = 1;
    TextInputLayout txtMagv,txtHoten,txtSdt;
    Button btnSua, btnXoa, btnBack;
    DatabaseQLCB dbGiaoVien;
    Button btnClose, btnCloseE;
    TextView txtSuccess, txtError;
    ImageView imgSuccess, imgError;
    Dialog dialog;
    ImageView imageGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_giao_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    public void setControl(){
        dbGiaoVien = new DatabaseQLCB(this);
        txtMagv = findViewById(R.id.editMagv);
        txtHoten = findViewById(R.id.editHoten);
        txtSdt = findViewById(R.id.editSdt);
        btnSua = findViewById(R.id.btnEdit);
        btnXoa = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);
        imageGV = findViewById(R.id.editImage);

        Intent intent = getIntent();
        GiaoVien giaoVien = dbGiaoVien.getGiaoVienByMaGV(intent.getStringExtra("MAGV"));
        txtMagv.getEditText().setText(giaoVien.getId());
        txtHoten.getEditText().setText(giaoVien.getHoTen());
        txtSdt.getEditText().setText(giaoVien.getSdt());
        ByteArrayInputStream imageStream = new ByteArrayInputStream(giaoVien.getHinhanh());
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        imageGV.setImageBitmap(bitmap);

        dialog = new Dialog(this);
        btnClose = dialog.findViewById(R.id.btnClose);
        imgSuccess = dialog.findViewById(R.id.imageSuccess);
        txtSuccess = (TextView) dialog.findViewById(R.id.txtSuccess);

        btnCloseE = dialog.findViewById(R.id.btnCloseE);
        imgError = dialog.findViewById(R.id.imageError);
        txtError = (TextView) dialog.findViewById(R.id.txtError);
    }

    public void setEvent(){
        imageGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_FILE);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String magv = txtMagv.getEditText().getText().toString();
                String hoten = txtHoten.getEditText().getText().toString();
                String sdt = txtSdt.getEditText().getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageGV.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                if(validateMaGiaoVien(magv) == true && validate.validateLetters(hoten) == true && validate.validatephoneNumber(sdt) == true){
                    hoten = validate.chuanHoaDanhTuRieng(hoten);
                    GiaoVien gv = new GiaoVien(magv,hoten,sdt,hinhanh);
                    if(dbGiaoVien.updateGV(gv) == true){
                        showDialogSuccess("Sửa thành công!");
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), ManagerGiaoVienActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else{
                       //Toast.makeText(EditGiaoVienActivity.this,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                        showDialogError("Sửa thất bại!");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                }
                else{
                    if(validateMaGiaoVien(magv) == false){
                        //Toast.makeText(EditGiaoVienActivity.this,"Họ tên không hợp lệ",Toast.LENGTH_SHORT).show();
                        showDialogError("Mã giáo viên không hợp lệ");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }else if(validate.validateLetters(hoten) == false){
                        //Toast.makeText(EditGiaoVienActivity.this,"Họ tên không hợp lệ",Toast.LENGTH_SHORT).show();
                        showDialogError("Họ tên không hợp lệ");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }else if(validate.validatephoneNumber(sdt) == false){
                        //Toast.makeText(EditGiaoVienActivity.this,"Số điện thoại không hợp lệ",Toast.LENGTH_SHORT).show();
                        showDialogError("Số điện thoại không hợp lệ");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }else{
                        showDialogError("Họ tên không hợp lệ");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
//                        Toast.makeText(EditGiaoVienActivity.this,"Họ tên không hợp lệ",Toast.LENGTH_SHORT).show();
//                        Toast.makeText(EditGiaoVienActivity.this,"Số điện thoại không hợp lệ",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String magv = txtMagv.getEditText().getText().toString();

                ArrayList<PhieuChamBai> listP = dbGiaoVien.getListGiaoVienInPhieuChamBai(magv);
                if(listP.size()>0){
                    showDialogError("Không được phép xóa");
                    btnCloseE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
//                    Intent intent = new Intent(getApplicationContext(),ManagerGiaoVienActivity.class);
//                    startActivity(intent);
                }else{
                    if(dbGiaoVien.deleteGV(magv) == true){
                        showDialogSuccess("Xóa thành công!");
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), ManagerGiaoVienActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else{
                        showDialogError("Xóa");
                        btnCloseE.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
//                        Intent intent = new Intent(getApplicationContext(),ManagerGiaoVienActivity.class);
//                        startActivity(intent);
                    }

                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ManagerGiaoVienActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), ManagerGiaoVienActivity.class);
        startActivity(myIntent);
        return true;
    }

    public static boolean validateMaGiaoVien(String magv) {
        Pattern pattern = Pattern.compile("(GV)+([0-9]+$)\\b");
        Matcher matcher  = pattern.matcher(magv);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageGV.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
//    public static boolean validateLetters(String txt) {
//        Pattern pattern = Pattern.compile("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$",Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(txt);
//        return matcher.find();
//    }
//
//    public static boolean validatephoneNumber(String sdt){
//        Pattern pattern = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
//        Matcher matcher  = pattern.matcher(sdt);
//        if(matcher.matches()){
//            return true;
//        }
//        return false;
//    }
//
//    public static String chuanHoa(String s) {
//        s = s.trim();
//        s = s.replaceAll("\\s+", " ");
//        return s;
//    }
//    public static String chuanHoaDanhTuRieng(String str) {
//        str = chuanHoa(str);
//        String temp[] = str.split(" ");
//        str = "";
//        for (int i = 0; i < temp.length; i++) {
//            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1).toLowerCase();
//            if (i < temp.length - 1) {
//                str += " ";
//            }
//        }
//        return str;
//    }

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