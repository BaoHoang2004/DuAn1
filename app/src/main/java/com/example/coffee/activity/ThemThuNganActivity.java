package com.example.coffee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.dao.ThuNganDAO;
import com.example.coffee.database.Dbhelper;
import com.google.android.material.textfield.TextInputEditText;

public class ThemThuNganActivity extends AppCompatActivity {
    TextInputEditText edtUserNameResigter;
    TextInputEditText edtFullNameResigter;
    TextInputEditText edtPasswordResigter;
    TextInputEditText edtLoaiTK;
    TextInputEditText edtConfirmPasswordresigter;
    Button btnResigter;
    Toolbar toolbarTTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thu_ngan);

        edtUserNameResigter = findViewById(R.id.userNameResigter);
        edtFullNameResigter = findViewById(R.id.fullNameResigter);
        edtLoaiTK = findViewById(R.id.edtLoaiTK);
        edtPasswordResigter = findViewById(R.id.passwordResigter);
        edtConfirmPasswordresigter = findViewById(R.id.confirmPasswordResigter);
        btnResigter = findViewById(R.id.btnResigter);

        toolbarTTN = findViewById(R.id.toolbarTTN);

        setSupportActionBar(toolbarTTN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTTN.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnResigter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addthungan();
            }
        });
    }

    private void addthungan() {
        String tenDanhNhap = edtUserNameResigter.getText().toString();
        String hoTen = edtFullNameResigter.getText().toString();
        String matKhau = edtPasswordResigter.getText().toString();
        String confirmPassword = edtConfirmPasswordresigter.getText().toString();
        String loaiTK = edtLoaiTK.getText().toString();

        ThuNganDAO thuNganDAO = new ThuNganDAO(ThemThuNganActivity.this);

        if (tenDanhNhap.isEmpty() || hoTen.isEmpty() || matKhau.isEmpty() || confirmPassword.isEmpty() || loaiTK.isEmpty()){
            Toast.makeText(ThemThuNganActivity.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            boolean check = thuNganDAO.checkUserName(tenDanhNhap);
            if (check){
                edtUserNameResigter.setError("Tên đăng nhập đã tồn tại");
            }else {
                if (confirmPassword.equals(matKhau)){
                    thuNganDAO.themtaikhoan(tenDanhNhap,hoTen,matKhau,loaiTK);
                    Toast.makeText(ThemThuNganActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThemThuNganActivity.this, LoginActivity.class));
                }else {
                    Toast.makeText(ThemThuNganActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}