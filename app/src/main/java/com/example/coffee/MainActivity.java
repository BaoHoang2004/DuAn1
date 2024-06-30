package com.example.coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.coffee.activity.GioHangActivity;
import com.example.coffee.activity.InfoActivity;
import com.example.coffee.activity.LoginActivity;
import com.example.coffee.activity.SearchActivity;
import com.example.coffee.activity.ThemThuNganActivity;
import com.example.coffee.dao.ThuNganDAO;
import com.example.coffee.fragment.DoanhThuFragment;
import com.example.coffee.fragment.HoaDonFragment;
import com.example.coffee.fragment.HomeFragment;
import com.example.coffee.fragment.LoaiFragment;
import com.example.coffee.fragment.QLSPFragment;
import com.example.coffee.fragment.ThongKeFragment;
import com.example.coffee.model.Loai;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        frameLayout = findViewById(R.id.fragmentContainer);

        setSupportActionBar(toolbar);


        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,homeFragment);
        fragmentTransaction.commit();

        toolbar.setTitle("Trang chủ");



//        View headerlayout = navigationView.getHeaderView(0);
//        TextView tvTen =headerlayout.findViewById(R.id.tvTen);
//        TextView tvLoaiTK = headerlayout.findViewById(R.id.tvLoaiTK);

        //
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.trangchu){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,homeFragment);
                    fragmentTransaction.commit();

                } else if (item.getItemId() == R.id.thongke){
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,doanhThuFragment);
                    fragmentTransaction.commit();
                }
                else if (item.getItemId() == R.id.sanpham){
                    startActivity(new Intent(MainActivity.this,SearchActivity.class));
                }
                else if (item.getItemId() == R.id.hoadon){
                    HoaDonFragment hoaDonFragment = new HoaDonFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,hoaDonFragment);
                    fragmentTransaction.commit();
                }else if (item.getItemId() == R.id.gioHang){
                    startActivity(new Intent(MainActivity.this,GioHangActivity.class));
                }

                return true;
            }
        });



        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan","");
        if (!loaitaikhoan.equals("Admin")){
            Menu menu = bottomNavigationView.getMenu();
            menu.findItem(R.id.sanpham).setVisible(false);
        }
        String tentn = sharedPreferences.getString("tentn","");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.thongke){
            ThongKeFragment thongKeFragment = new ThongKeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,thongKeFragment);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.hoadon) {
            startActivity(new Intent(MainActivity.this, GioHangActivity.class));
        }else if (item.getItemId() == R.id.logout){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.icSearch) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        } else if (item.getItemId() == R.id.loaidouong) {
            LoaiFragment loaiFragment = new LoaiFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,loaiFragment);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.themthungan) {
            startActivity(new Intent(MainActivity.this, ThemThuNganActivity.class));
        } else if (item.getItemId() == R.id.icinfo) {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
        } else if (item.getItemId() == R.id.qlsp) {
            QLSPFragment qlspFragment = new QLSPFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,qlspFragment);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.doimatkhau) {
            showdialogDMK();
        }
        return super.onOptionsItemSelected(item);

    }

    private void showdialogDMK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doi_mk,null);

        builder.setView(view);
        AlertDialog alertDialog = builder.create();

        TextInputEditText edtMKCu = view.findViewById(R.id.edtMKCu);
        TextInputEditText edtMKMoi = view.findViewById(R.id.edtMKMoi);
        TextInputEditText edtNhaplai = view.findViewById(R.id.edtNhapLaiMK);
        Button btnHuy = view.findViewById(R.id.btnHuyMK);
        Button btnCapnhat = view.findViewById(R.id.btnDongY);
        TextView tvTen = view.findViewById(R.id.tvTen);
        TextView tvLoaiTK = view.findViewById(R.id.tvLoaiTK);

        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String tentn = sharedPreferences.getString("tentn","");
        String matt = sharedPreferences.getString("matn", "");
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan","");

        tvLoaiTK.setText("Xin chào: " +loaitaikhoan);
        tvTen.setText(tentn);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String oldpass = edtMKCu.getText().toString();
                String newpass = edtMKMoi.getText().toString();
                String nhaplai = edtNhaplai.getText().toString();
                if (oldpass.isEmpty() || newpass.isEmpty() || nhaplai.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (newpass.equals(nhaplai)) {
                        ThuNganDAO thuThuDAO = new ThuNganDAO(MainActivity.this);
                        boolean check = thuThuDAO.capNhapMK(matt, newpass, oldpass);
                        if (check) {
                            Toast.makeText(MainActivity.this, "Cập nhật mk thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Cập nhật mk thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Nhập mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}