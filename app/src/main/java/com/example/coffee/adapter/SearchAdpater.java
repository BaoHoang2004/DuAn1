package com.example.coffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.ItemRCVClick;
import com.example.coffee.R;
import com.example.coffee.activity.ChiTietActivity;
import com.example.coffee.model.DoUong;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchAdpater extends RecyclerView.Adapter<SearchAdpater.ViewHolder> {
    private Context context;
    private ArrayList<DoUong> list;
    private ArrayList<DoUong> listSearch;

    public void setFilterList(ArrayList<DoUong> filterList){
        this.list = filterList;
        notifyDataSetChanged();

    }
    public SearchAdpater(Context context, ArrayList<DoUong> list) {
        this.context = context;
        this.list = list;
        this.listSearch = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public SearchAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdpater.ViewHolder holder, int position) {
        DoUong doUong = list.get(position);
        holder.tvMaDoUong.setText("Mã đồ uống: " + String.valueOf(list.get(position).getMadouong()) );
        holder.tvTenDoUong.setText(list.get(position).getTendouong());

        String trangthai="";
        if (list.get(position).getTrangthai() == 1){
            trangthai = "Hết hàng";
            //setBacground
            holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorOutOfStock,null));
        }else {
            trangthai = "Vẫn còn";
            //setBacground
            holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAvailable,null));
        }
        holder.tvTrangThai.setText("Trạng thái: " + trangthai);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tvGia.setText(String.valueOf("Giá: " + decimalFormat.format(list.get(position).getDongia())) + "VNĐ");

        holder.setItemRCVOnclick(new ItemRCVClick() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (!isLongClick){
                    if (list.get(pos).getTrangthai() == 0){
                        Intent intent =new Intent(context, ChiTietActivity.class);
                        intent.putExtra("chitiet",doUong);
                        intent.putExtra("maloai",doUong.getMaloai());
                        context.startActivity(intent);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvMaDoUong, tvTenDoUong, tvTrangThai, tvGia;
        CardView cardView;
        private ItemRCVClick itemRCVOnclick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDoUong = itemView.findViewById(R.id.tvMaDoUong);
            tvTenDoUong = itemView.findViewById(R.id.tvTenDoUong);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvGia = itemView.findViewById(R.id.tvGiaDouong);
            cardView = itemView.findViewById(R.id.cardviewSearch);
            itemView.setOnClickListener(this);
        }

        public void setItemRCVOnclick(ItemRCVClick itemRCVOnclick) {
            this.itemRCVOnclick = itemRCVOnclick;
        }
        @Override
        public void onClick(View view) {
            itemRCVOnclick.onClick(view,getAdapterPosition(),false);
        }
    }



}
