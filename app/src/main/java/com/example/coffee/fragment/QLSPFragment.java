package com.example.coffee.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.adapter.DoUongAdapter;
import com.example.coffee.adapter.QLSPAdapter;
import com.example.coffee.dao.DoUongDAO;
import com.example.coffee.dao.LoaiDAO;
import com.example.coffee.dao.SizeDAO;
import com.example.coffee.model.DoUong;
import com.example.coffee.model.Loai;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class QLSPFragment extends Fragment {
RecyclerView rcvQLSP;
DoUongDAO doUongDAO;
ImageView imageView;


    public QLSPFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_s_p, container, false);
        rcvQLSP = view.findViewById(R.id.rcvQLSP);
        doUongDAO = new DoUongDAO(getContext());
        imageView = view.findViewById(R.id.icAddDU);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });

        loaddata();

        return view;
    }



    private void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_sanpham,null);
        builder.setView(view);

        AlertDialog alertDialog =builder.create();

        TextInputEditText edtTenDU = view.findViewById(R.id.edtTenDUDialog);
        TextInputEditText edtThongTinDU = view.findViewById(R.id.edtThongtinDUDialog);
        TextInputEditText edtGiaSTinDU = view.findViewById(R.id.edtGiaSDUDialog);
        TextInputEditText edtGiaMTinDU = view.findViewById(R.id.edtGiaMDUDialog);
        TextInputEditText edtGiaLTinDU = view.findViewById(R.id.edtGiaLDUDialog);
        TextInputEditText edtAvatar = view.findViewById(R.id.edtAvatarDUDialog);
        Button btnAdd = view.findViewById(R.id.btnAddDU);
        Button btnHuy = view.findViewById(R.id.btnHuyDU);
        Spinner spinnerLoai = view.findViewById(R.id.spinnerLoai);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spinnerLoai.setAdapter(simpleAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SizeDAO sizeDAO = new SizeDAO(getContext());
                String tenDU = edtTenDU.getText().toString();
                String thongtin = edtThongTinDU.getText().toString();
                String avatar = edtAvatar.getText().toString();

                String dongiaS = edtGiaSTinDU.getText().toString();
                String dongiaM = edtGiaMTinDU.getText().toString();
                String dongiaL = edtGiaLTinDU.getText().toString();

                if (tenDU.isEmpty() || thongtin.isEmpty() || avatar.isEmpty() || dongiaS.isEmpty() || dongiaM.isEmpty() || dongiaL.isEmpty()){
                    Toast.makeText(getContext(), "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String, Object> hs = (HashMap<String, Object>) spinnerLoai.getSelectedItem();// truyền vào bằng hm nên lấy ra bằng hm
                    int maloai = (int) hs.get("maloai");
                    int dongias = Integer.parseInt(dongiaS);
                    int dongiam = Integer.parseInt(dongiaM);
                    int dongial = Integer.parseInt(dongiaL);

                    long check = doUongDAO.themdouong(tenDU,thongtin,maloai,0,avatar);

                    boolean s = sizeDAO.themSize(Math.toIntExact(check),"Size S",dongias);
                    boolean m = sizeDAO.themSize(Math.toIntExact(check),"Size M",dongiam);
                    boolean l = sizeDAO.themSize(Math.toIntExact(check),"Size L",dongial);

                    if (check != 0){
                        if (s && m && l) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            loaddata();
                        }else {
                            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }
    private void loaddata() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        rcvQLSP.setLayoutManager(linearLayoutManager);
        ArrayList<DoUong> list = doUongDAO.getListDoUong();
        QLSPAdapter adapter = new QLSPAdapter(getContext(),list,doUongDAO);
        rcvQLSP.setAdapter(adapter);
    }

    private ArrayList<HashMap<String,Object>> getDSLoaiSach(){
        LoaiDAO loaiDAO = new LoaiDAO(getContext());
        ArrayList<Loai> list = loaiDAO.getListLoai();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (Loai loai : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai",loai.getMaloai());
            hs.put("tenloai",loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }
}