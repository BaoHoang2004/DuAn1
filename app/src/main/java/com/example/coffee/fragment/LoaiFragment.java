package com.example.coffee.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.adapter.LoaiAdapter;
import com.example.coffee.dao.LoaiDAO;
import com.example.coffee.model.Loai;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class LoaiFragment extends Fragment {

RecyclerView rcvLoai;
LoaiDAO loaiDAO;

    public LoaiFragment() {
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
        View view = inflater.inflate(R.layout.fragment_loai, container, false);
        rcvLoai = view.findViewById(R.id.rcvLoai);
        TextInputEditText edtTenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnThem = view.findViewById(R.id.btnAddLoai);

        loaiDAO = new LoaiDAO(getContext());
        loaddata();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai =edtTenLoai.getText().toString();
                if (tenLoai.isEmpty()){
                    Toast.makeText(getContext(), "Bạn cần nhập tên loại", Toast.LENGTH_SHORT).show();
                }else {
                    if (loaiDAO.themLoai(tenLoai)){
                        loaddata();
                        edtTenLoai.setText("");
                        Toast.makeText(getContext(), "Thêm loại thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return view;
    }

    private void loaddata() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvLoai.setLayoutManager(linearLayoutManager);
        ArrayList<Loai> list = loaiDAO.getListLoai();
        LoaiAdapter adapter = new LoaiAdapter(getContext(),list,loaiDAO);
        rcvLoai.setAdapter(adapter);
    }
}