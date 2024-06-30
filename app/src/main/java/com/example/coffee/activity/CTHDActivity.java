package com.example.coffee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coffee.R;
import com.example.coffee.adapter.CTHDAdapter;
import com.example.coffee.dao.CTHDDAO;
import com.example.coffee.model.CTHD;
import com.example.coffee.model.HoaDon;

import java.util.ArrayList;

public class CTHDActivity extends AppCompatActivity {
    int mahoadon;
    CTHDDAO cthddao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthdactivity);

        RecyclerView rcvCTHD = findViewById(R.id.rcvCTHD);
        Toolbar toolbar = findViewById(R.id.toolbarCTHD);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        mahoadon = intent.getIntExtra("mahd",0);

        cthddao = new CTHDDAO(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCTHD.setLayoutManager(linearLayoutManager);
        ArrayList<CTHD> list = cthddao.getListCTHD(mahoadon);

        CTHDAdapter cthdAdapter = new CTHDAdapter(this,list,cthddao);

        rcvCTHD.setAdapter(cthdAdapter);


    }
}