package com.example.coffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.dao.CTHDDAO;
import com.example.coffee.model.CTHD;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CTHDAdapter extends RecyclerView.Adapter<CTHDAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CTHD> list;
    private CTHDDAO cthddao;

    public CTHDAdapter(Context context, ArrayList<CTHD> list, CTHDDAO cthddao) {
        this.context = context;
        this.list = list;
        this.cthddao = cthddao;
    }

    @NonNull
    @Override
    public CTHDAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_cthd,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CTHDAdapter.ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tvTenDoUong.setText("Tên đồ uống: " + list.get(position).getTendouong());
        holder.tvSoLuong.setText("Số lượng: "+ String.valueOf(list.get(position).getSoluong()));
        holder.tvTenSize.setText(list.get(position).getTensize());
        holder.tvGhiChu.setText("Ghi chú: "+list.get(position).getGhichu());
        holder.tvDongia.setText("Tổng tiền: "+decimalFormat.format(list.get(position).getGiatien()) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDoUong,tvSoLuong,tvTenSize,tvDongia,tvGhiChu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDoUong = itemView.findViewById(R.id.tvTenDoUong);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvTenSize = itemView.findViewById(R.id.tvTenSize);
            tvDongia = itemView.findViewById(R.id.tvDongia);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);

        }
    }
}
