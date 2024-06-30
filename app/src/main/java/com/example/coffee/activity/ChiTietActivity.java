package com.example.coffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffee.MainActivity;
import com.example.coffee.R;
import com.example.coffee.adapter.ChiTietAdapter;
import com.example.coffee.adapter.DoUongAdapter;
import com.example.coffee.dao.CTHDDAO;
import com.example.coffee.dao.DoUongDAO;
import com.example.coffee.dao.HoaDonDAO;
import com.example.coffee.dao.SizeDAO;
import com.example.coffee.model.CTHD;
import com.example.coffee.model.DoUong;
import com.example.coffee.model.HoaDon;
import com.example.coffee.model.Size;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ChiTietActivity extends AppCompatActivity {
    TextView tvTenDoUong, tvGia, tvthongtin,tvXemThem;
    TextInputEditText edtGhiChu;
    TextInputEditText edtSoLuong;
    RecyclerView rcvChiTiet;
    ImageView imageView;
    Spinner spinnerSize, spinnerSoluong;
    Button btnThemVaoGioHang;
    Toolbar toolbarChiTiet;
    DoUong doUong;
    int soluong = 1;
    int size;
    int gia;
    Integer giasieze;
    String tensize;
    CTHD cthd;
    DoUongDAO doUongDAO;
    int maloai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        tvTenDoUong= findViewById(R.id.tvTenDoUongCT);
        tvGia= findViewById(R.id.tvGia);
        tvthongtin= findViewById(R.id.tvthongtin);
        spinnerSize= findViewById(R.id.spinnerSize);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThemVaoGioHang= findViewById(R.id.btnThemVaoGioHang);
        toolbarChiTiet= findViewById(R.id.toolbarChiTiet);
        edtGhiChu = findViewById(R.id.edtGhiChu);
        imageView =findViewById(R.id.imgChiTiet);
        rcvChiTiet = findViewById(R.id.rcvChiTiet);
        tvXemThem = findViewById(R.id.tvXemThem);
        doUongDAO = new DoUongDAO(this);
        Intent intent = getIntent();
        maloai = intent.getIntExtra("maloai",0);
        edtSoLuong.setText(String.valueOf(1));

        tvXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietActivity.this,SearchActivity.class));
            }
        });

        loaddata();


        actionToolBar();

        //set thông tin sp
        doUong = (DoUong) getIntent().getSerializableExtra("chitiet");
        tvTenDoUong.setText(doUong.getTendouong());
        tvthongtin.setText(doUong.getThongtin());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGia.setText("Giá: " +decimalFormat.format((doUong.getDongia()))+"VNĐ");

        //load ảnh
        int imgId = this.getResources().getIdentifier(
                doUong.getAvatar(),"drawable",
                this.getPackageName());
        imageView.setImageResource(imgId);
        //
        if (doUong.getAvatar().startsWith("http://")||
                doUong.getAvatar().startsWith("https://")){
            Picasso.get().load(doUong.getAvatar()).into(imageView);
        }


        // set size
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                getDataSize(doUong.getMadouong()),
                android.R.layout.simple_list_item_1,
                new String[]{"tensize"},
                new int[]{android.R.id.text1}
        );
        spinnerSize.setAdapter(simpleAdapter);

        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                soluong = Integer.parseInt(edtSoLuong.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spinnerSize.getSelectedItem();
                size = (int) hs.get("masize");
                giasieze = (int) hs.get("dongia");
                tensize = (String) hs.get("tensize");
                gia = soluong * giasieze;
                tvGia.setText("Giá: " +decimalFormat.format(gia)+" VNĐ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Lấy mã thu ngân
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String matn = sharedPreferences.getString("matn", "");

                //Lấy ngày
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String ngay = simpleDateFormat.format(currentTime);

                String ghichu = edtGhiChu.getText().toString();
                HoaDonDAO hoaDonDAO = new HoaDonDAO(ChiTietActivity.this);
                HoaDon hoaDon = new HoaDon(ngay, matn, 0);
                CTHDDAO cthddao = new CTHDDAO(ChiTietActivity.this);


                long maHD = hoaDonDAO.checkHD(matn);


                // check hóa đơn
                if (maHD == 0) {
                    maHD = hoaDonDAO.themHoaDon(hoaDon);
                }

                long maSize = cthddao.checkDU(size);
                ArrayList<CTHD> list = cthddao.getListGioHang();
                int a = list.size();
                int soLuongTmp = 0;
                soluong = Integer.parseInt(edtSoLuong.getText().toString());
                for (int i =0; i < list.size();i++){
                    if (list.get(i).getMasize() == maSize){
                        soLuongTmp = soluong + list.get(i).getSoluong();
                    }
                }
                //check đã có sp chưa - có thì cập nhật sl -chưa thì thêm sp vào giỏ hàng
                if (maSize == size) {
                    gia = soLuongTmp * giasieze;
                    boolean check = cthddao.capNhatSoLuong(Math.toIntExact(maSize),soLuongTmp);
                    if (check){
                        edtSoLuong.setText(String.valueOf(1));
                        Toast.makeText(ChiTietActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChiTietActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(ChiTietActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    soluong = Integer.parseInt(edtSoLuong.getText().toString());
                    //mahoadon, soluong integer, ghichu text, giatien integer, trangthai integer, masize
                    cthd = new CTHD(Math.toIntExact(maHD), soluong, ghichu, giasieze, size);
                    boolean kiemtra = cthddao.themVaoGioHang(cthd);
                    if (kiemtra) {
                        SharedPreferences sharedPreferences1 = getSharedPreferences("mahoadon", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences1.edit();
                        editor.putInt("mahd", Math.toIntExact(maHD));
                        editor.commit();
                        Intent intent = new Intent(ChiTietActivity.this, GioHangActivity.class);
                        intent.putExtra("dongia",giasieze);
                        Toast.makeText(ChiTietActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChiTietActivity.this, MainActivity.class));
                        edtSoLuong.setText(String.valueOf(1));
                    } else {
                        Toast.makeText(ChiTietActivity.this, "thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private void loaddata() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rcvChiTiet.setLayoutManager(linearLayoutManager);
        ArrayList<DoUong> list = doUongDAO.getListDoUongLoai(maloai);
        ChiTietAdapter chiTietAdapter = new ChiTietAdapter(this,list,doUongDAO);
        rcvChiTiet.setAdapter(chiTietAdapter);

    }


    private void actionToolBar() {
        toolbarChiTiet.setTitle(null);
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private ArrayList<HashMap<String, Object>> getDataSize(int madouong){
        SizeDAO sizeDAO = new SizeDAO(getApplicationContext());
        ArrayList<Size> list = sizeDAO.getListSize(madouong);

        ArrayList<HashMap<String, Object>> listHashMaps = new ArrayList<>();
        for (Size s : list){
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("masize",s.getMasize());
            hm.put("madouong",s.getMadouong());
            hm.put("tensize",s.getTensize());
            hm.put("dongia",s.getDongia());
            listHashMaps.add(hm);
        }
        return listHashMaps;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.icSearch) {
            startActivity(new Intent(ChiTietActivity.this, SearchActivity.class));
        } else if (item.getItemId() == R.id.icHone) {
            startActivity(new Intent(ChiTietActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(ChiTietActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(ChiTietActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.icGioHang) {
            startActivity(new Intent(ChiTietActivity.this, GioHangActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }
}