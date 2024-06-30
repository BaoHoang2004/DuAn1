package com.example.coffee.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffee.R;
import com.example.coffee.adapter.CTHDAdapter;
import com.example.coffee.adapter.HoaDonAdapter;
import com.example.coffee.dao.CTHDDAO;
import com.example.coffee.dao.HoaDonDAO;
import com.example.coffee.model.CTHD;
import com.example.coffee.model.HoaDon;

import java.util.ArrayList;


public class HoaDonFragment extends Fragment {
RecyclerView rcvHoaDon;
CTHDDAO cthddao;
HoaDonDAO hoaDonDAO;


    public HoaDonFragment() {
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
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        rcvHoaDon = view.findViewById(R.id.rcvHoaDon);

        cthddao = new CTHDDAO(getContext());
        hoaDonDAO = new HoaDonDAO(getContext());

        loaddata();

        return view;
    }

    private void loaddata() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvHoaDon.setLayoutManager(linearLayoutManager);
        ArrayList<HoaDon> list = hoaDonDAO.getListHoaDon();
        HoaDonAdapter adapter = new HoaDonAdapter(getContext(),list,hoaDonDAO);
        rcvHoaDon.setAdapter(adapter);
    }
}