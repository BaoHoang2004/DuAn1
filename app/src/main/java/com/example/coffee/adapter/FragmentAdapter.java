package com.example.coffee.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.coffee.fragment.DoanhThuFragment;
import com.example.coffee.fragment.HomeFragment;
import com.example.coffee.fragment.QLSPFragment;
import com.example.coffee.fragment.TKFragment;
import com.example.coffee.fragment.ThongKeFragment;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new DoanhThuFragment();
            case 1: return new QLSPFragment();
            default:return new DoanhThuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
