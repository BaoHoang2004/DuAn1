package com.example.coffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffee.MainActivity;
import com.example.coffee.R;
import com.example.coffee.adapter.ChiTietAdapter;
import com.example.coffee.adapter.GioHangAdapter;
import com.example.coffee.dao.CTHDDAO;
import com.example.coffee.dao.HoaDonDAO;
import com.example.coffee.model.CTHD;
import com.example.coffee.model.HoaDon;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    RecyclerView rcvGioHang;
    TextInputEditText edtGhiChu;
    static TextView tvTongTien;
    Button btnThanhToan;
    Toolbar toolbarGioHang;
    CTHDDAO cthddao;
    static ArrayList<CTHD> list;
    RadioButton radioTienMat;
    RadioButton radioQRCode;
    int mahd;
    static int t = 0;
    int tong =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        rcvGioHang = findViewById(R.id.rcvGioHang);
        tvTongTien = findViewById(R.id.tvTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        toolbarGioHang = findViewById(R.id.toolbarGioHang);
        radioTienMat = findViewById(R.id.radio_tienmat);
        radioQRCode = findViewById(R.id.radio_qrcode);

        actionbar();

        cthddao = new CTHDDAO(this);

        loaddata();

        SharedPreferences sharedPreferences = getSharedPreferences("mahoadon", Context.MODE_PRIVATE);
        mahd = sharedPreferences.getInt("mahd",0);


        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   thanhtoan();

                }

        });


    }

    private void loaddata() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvGioHang.setLayoutManager(linearLayoutManager);
        list = cthddao.getListGioHang();
        GioHangAdapter adapter = new GioHangAdapter(this,list,cthddao);
        rcvGioHang.setAdapter(adapter);
    }

    private void actionbar() {
        toolbarGioHang.setTitle(null);
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        if (view.getId() == R.id.radio_tienmat){

        } else if (view.getId() == R.id.radio_qrcode) {

        }
    }


    private void thanhtoan(){
        if (list.size() > 0) {

            if (radioTienMat.isChecked()) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(GioHangActivity.this);
                boolean kiemtra = hoaDonDAO.thanhtoan(mahd);
                if (kiemtra) {
                    loaddata();
                    Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GioHangActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            } else if (radioQRCode.isChecked()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_thanhtoan_qrcode, null);
                builder.setView(view);

                builder.setNegativeButton("Hoàn thành", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HoaDonDAO hoaDonDAO = new HoaDonDAO(GioHangActivity.this);
                        boolean kiemtra = hoaDonDAO.thanhtoan(mahd);
                        if (kiemtra) {
                            loaddata();
                            Toast.makeText(GioHangActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GioHangActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }

    }

    public static void tongtien(){
        for (int i =0; i<list.size(); i++){
            t = t+list.get(i).getGiatien();
        }
        tvTongTien.setText(""+t);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_giohang,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.icSearch) {
            startActivity(new Intent(GioHangActivity.this, SearchActivity.class));
        } else if (item.getItemId() == R.id.icHone) {
            startActivity(new Intent(GioHangActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(GioHangActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(GioHangActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}