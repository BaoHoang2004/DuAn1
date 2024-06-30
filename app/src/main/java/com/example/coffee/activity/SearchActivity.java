package com.example.coffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffee.MainActivity;
import com.example.coffee.R;
import com.example.coffee.adapter.DoUongAdapter;
import com.example.coffee.adapter.SearchAdpater;
import com.example.coffee.dao.DoUongDAO;
import com.example.coffee.model.DoUong;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    DoUongDAO doUongDAO;
    RecyclerView rcvSearch;
    SearchAdpater adapter;
    ArrayList<DoUong> list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rcvSearch = findViewById(R.id.rcvSearch);
        toolbar = findViewById(R.id.toolbarSearch);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });

        doUongDAO = new DoUongDAO(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSearch.setLayoutManager(linearLayoutManager);
        list = doUongDAO.getListDoUongSearch();
        adapter = new SearchAdpater(this,list);
        rcvSearch.setAdapter(adapter);




    }
    private void filterlist(String newText){
        ArrayList<DoUong> filterlist = new ArrayList<>();
        for (DoUong doUong : list){
            if (doUong.getTendouong().toLowerCase().contains(newText.toLowerCase())){
                filterlist.add(doUong);
            }
        }
           adapter.setFilterList(filterlist);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.icSearch) {
            startActivity(new Intent(SearchActivity.this, SearchActivity.class));
        } else if (item.getItemId() == R.id.icHone) {
            startActivity(new Intent(SearchActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(SearchActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(SearchActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.icGioHang) {
            startActivity(new Intent(SearchActivity.this, GioHangActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

}