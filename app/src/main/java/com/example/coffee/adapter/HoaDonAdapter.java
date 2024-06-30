package com.example.coffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.ItemRCVClick;
import com.example.coffee.MainActivity;
import com.example.coffee.R;
import com.example.coffee.activity.CTHDActivity;
import com.example.coffee.dao.HoaDonDAO;
import com.example.coffee.model.HoaDon;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HoaDon> list;
    private HoaDonDAO hoaDonDAO;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list, HoaDonDAO hoaDonDAO) {
        this.context = context;
        this.list = list;
        this.hoaDonDAO = hoaDonDAO;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = ((Activity) context).getLayoutInflater();
       View view= inflater.inflate(R.layout.item_rcv_hoadon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewHolder holder, int position) {
        HoaDon hoaDon = list.get(position);
          holder.tvMaHoaDon.setText("Mã hóa đơn: "+String.valueOf(list.get(position).getMahoadon()));
        holder.tvMaTN.setText("Mã thu ngân: "+list.get(position).getMatn());
        holder.tvNgayMua.setText("Ngày: "+list.get(position).getNgaymua());

        holder.setItemRCVClick(new ItemRCVClick() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                Intent intent = new Intent(context, CTHDActivity.class);
                intent.putExtra("mahd",hoaDon.getMahoadon());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvMaHoaDon, tvMaTN,tvNgayMua,tvTenDoUong,tvSoLuong,tvTenSize,tvDongia,tvGhiChu,tvGiaTien;
        private ItemRCVClick itemRCVClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
              tvMaHoaDon = itemView.findViewById(R.id.tvMaHoaDon);
            tvMaTN = itemView.findViewById(R.id.tvMaTN);
            tvNgayMua = itemView.findViewById(R.id.tvNgayMua);
            itemView.setOnClickListener(this);

        }

        public void setItemRCVClick(ItemRCVClick itemRCVClick) {
            this.itemRCVClick = itemRCVClick;
        }

        @Override
        public void onClick(View view) {
            itemRCVClick.onClick(view,getAdapterPosition(),false);
        }
    }
}
