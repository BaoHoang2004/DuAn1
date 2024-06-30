package com.example.coffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.activity.GioHangActivity;
import com.example.coffee.dao.CTHDDAO;
import com.example.coffee.model.CTHD;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CTHD> list;
    private CTHDDAO cthddao;
    int gia;
    int sl = 1;

    public GioHangAdapter(Context context, ArrayList<CTHD> list, CTHDDAO cthddao) {
        this.context = context;
        this.list = list;
        this.cthddao = cthddao;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_giohang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tvTenDUGH.setText(list.get(position).getTendouong());
        holder.tvSizeGH.setText(list.get(position).getTensize());
        holder.tvGhiChu.setText("Ghi chú: " +list.get(position).getGhichu());
        sl = list.get(position).getSoluong();
        gia = list.get(position).getGiatien();
        holder.tvSLGH.setText(String.valueOf(sl));
        holder.tvGiaGH.setText("Giá: " + decimalFormat.format(sl*gia)+" VNĐ");

        int imgId = ((Activity)context).getResources().getIdentifier(
                list.get(position).getAvatar(),"drawable",
                ((Activity)context).getPackageName());
        holder.imageViewGioHang.setImageResource(imgId);
        //lấy ảnh từ Internet
        if (list.get(position).getAvatar().startsWith("http://")||
                list.get(position).getAvatar().startsWith("https://")){
            Picasso.get().load(list.get(position).getAvatar()).into(holder.imageViewGioHang);
        }

        holder.icDeleteGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int macthd = list.get(holder.getAdapterPosition()).getMacthd();
                boolean check = cthddao.xoaGH(macthd);
//                GioHangActivity.tongtien();
                if (check){
                    list.clear();
                    list = cthddao.getListGioHang();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.icGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl = list.get(holder.getAdapterPosition()).getSoluong() - 1;
                gia = (list.get(holder.getAdapterPosition()).getGiatien() / list.get(holder.getAdapterPosition()).getSoluong()) * sl;
                boolean check = cthddao.capNhatSoLuong(list.get(holder.getAdapterPosition()).getMasize(), sl);
//                GioHangActivity.tongtien();
                if (sl <= 0) {
                    sl = 1;
                } else {
                    if (check) {
                        list.clear();
                        list = cthddao.getListGioHang();
                        notifyDataSetChanged();

                    } else {
                        Toast.makeText(context, "thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.icTangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl = list.get(holder.getAdapterPosition()).getSoluong() +1;
                boolean check = cthddao.capNhatSoLuong(list.get(holder.getAdapterPosition()).getMasize(),sl);
//                GioHangActivity.tongtien();
                if (check){
                    list.clear();
                    list = cthddao.getListGioHang();
                    notifyDataSetChanged();
                    GioHangActivity.tongtien();
                }else {
                    Toast.makeText(context, "thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int tinhTongGia(){
        int tonggia = 0;
        for (CTHD cthd : list){
            int gia = cthd.getGiatien();
            int soluong = cthd.getSoluong();
            tonggia += gia*soluong;
        }
        return tonggia;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDUGH, tvSizeGH, tvGiaGH,tvSLGH,tvGhiChu;
        ImageView imageViewGioHang,icGiamSL,icTangSL,icDeleteGH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDUGH = itemView.findViewById(R.id.tvTenDUGH);
            tvGiaGH = itemView.findViewById(R.id.tvGiaGioHang);
            tvSLGH = itemView.findViewById(R.id.tvSoLuongGioHang);
            tvSizeGH = itemView.findViewById(R.id.tvSizeGH);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
            imageViewGioHang = itemView.findViewById(R.id.imgGioHang);
            icGiamSL = itemView.findViewById(R.id.icGiamSL);
            icTangSL = itemView.findViewById(R.id.icTangSL);
            icDeleteGH = itemView.findViewById(R.id.icDeleteGH);
        }
    }
}
