package com.example.coffee.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.ItemRCVClick;
import com.example.coffee.R;
import com.example.coffee.activity.ChiTietActivity;
import com.example.coffee.dao.DoUongDAO;
import com.example.coffee.model.DoUong;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietAdapter extends RecyclerView.Adapter<ChiTietAdapter.ViewHolde> {

    private Context context;
    private ArrayList<DoUong> list;
    private DoUongDAO doUongDAO;

    public ChiTietAdapter(Context context, ArrayList<DoUong> list, DoUongDAO doUongDAO) {
        this.context = context;
        this.list = list;
        this.doUongDAO = doUongDAO;
    }

    @NonNull
    @Override
    public ChiTietAdapter.ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_chitiet,parent,false);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietAdapter.ViewHolde holder, int position) {
        DoUong doUong = list.get(position);
        holder.tvMaDoUong.setText("Mã đồ uống: " + String.valueOf(list.get(position).getMadouong()) );
        holder.tvTenDoUong.setText(list.get(position).getTendouong());

        int imgId = ((Activity)context).getResources().getIdentifier(
                list.get(position).getAvatar(),"drawable",
                ((Activity)context).getPackageName());
        holder.imageView.setImageResource(imgId);
        //lấy ảnh từ Internet
        if (list.get(position).getAvatar().startsWith("http://")||
                list.get(position).getAvatar().startsWith("https://")){
            Picasso.get().load(list.get(position).getAvatar()).into(holder.imageView);
        }

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

//        holder.tvTrangThai.setText("Trạng thái: " + trangthai);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tvGia.setText(String.valueOf("Giá: " + decimalFormat.format(list.get(position).getDongia())) + "VNĐ");

        //
        holder.setItemRCVClick(new ItemRCVClick() {
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

    public class ViewHolde extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvMaDoUong, tvTenDoUong, tvTrangThai, tvGia;
        ImageView imageView;
        CardView cardView;
        private ItemRCVClick itemRCVClick;
        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            tvMaDoUong = itemView.findViewById(R.id.tvMaDoUong);
            tvTenDoUong = itemView.findViewById(R.id.tvTenDoUong);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvGia = itemView.findViewById(R.id.tvGiaDouong);
            imageView = itemView.findViewById(R.id.imgViewDU);
            cardView = itemView.findViewById(R.id.cardViewCT);
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
