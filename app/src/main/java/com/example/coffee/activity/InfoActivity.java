package com.example.coffee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.coffee.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView tvLoaiTK = findViewById(R.id.tvLoaiTK);
        TextView tvten = findViewById(R.id.tvTen);
        Toolbar toolbar = findViewById(R.id.toolbarInfo);

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan","");
        if (!loaitaikhoan.equals("Admin")){

        }
        String tentn = sharedPreferences.getString("tentn","");

        tvLoaiTK.setText("Xin chào: "+loaitaikhoan);
        tvten.setText(tentn);

    }
}