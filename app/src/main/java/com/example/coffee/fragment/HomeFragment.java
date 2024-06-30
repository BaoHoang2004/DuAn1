package com.example.coffee.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.coffee.R;
import com.example.coffee.activity.SearchActivity;
import com.example.coffee.adapter.DoUongAdapter;
import com.example.coffee.model.DoUong;
import com.example.coffee.dao.DoUongDAO;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
RecyclerView rcvManHinhChinh;
DoUongDAO doUongDAO;
ViewFlipper viewFlipper;
TextView tvXemThem;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvManHinhChinh = view.findViewById(R.id.rcvManHinhChinh);
        doUongDAO = new DoUongDAO(getContext());
        viewFlipper = view.findViewById(R.id.viewflipper);
        tvXemThem = view.findViewById(R.id.tvXemThem);

        tvXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        actionViewFlipper();
        
        loaddata();

        return view;
    }

    private void actionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://nhadepso.com/wp-content/uploads/2023/01/hinh-anh-ly-ca-phe_1.jpg");
        mangquangcao.add("https://nhadepso.com/wp-content/uploads/2023/01/hinh-anh-ly-ca-phe_21.jpg");
        mangquangcao.add("https://nhadepso.com/wp-content/uploads/2023/01/hinh-anh-ly-ca-phe_36.jpg");

        for (int i = 0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getContext());

            Glide.with(getContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slidein = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation slideout = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slidein);
        viewFlipper.setOutAnimation(slideout);
    }

    private void loaddata() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcvManHinhChinh.setLayoutManager(gridLayoutManager);
        ArrayList<DoUong> list = doUongDAO.getListDoUong();
        DoUongAdapter adapter = new DoUongAdapter(getContext(),list,doUongDAO);
        rcvManHinhChinh.setAdapter(adapter);
    }


}