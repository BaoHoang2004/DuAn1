package com.example.coffee.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.dao.LoaiDAO;
import com.example.coffee.model.Loai;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Loai> list;
    private LoaiDAO loaiDAO;

    public LoaiAdapter(Context context, ArrayList<Loai> list, LoaiDAO loaiDAO) {
        this.context = context;
        this.list = list;
        this.loaiDAO = loaiDAO;
    }

    @NonNull
    @Override
    public LoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_loai,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiAdapter.ViewHolder holder, int position) {
        holder.tvMaLoai.setText("Ma loại: "+list.get(position).getMaloai());
        holder.tvTenLoai.setText("Tên loại: "+list.get(position).getTenloai());



        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiDAO = new LoaiDAO(context);
                int check = loaiDAO.xoaLoai(list.get(holder.getAdapterPosition()).getMaloai());
                if (check == 1){
                    list.clear();
                    list = loaiDAO.getListLoai();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else if (check == -1) {
                    Toast.makeText(context, "Không thể xóa, loại '"+list.get(holder.getAdapterPosition()).getTenloai()+"' đang tồn tại sản phẩm ", Toast.LENGTH_SHORT).show();
                } else if (check == 0) {
                    Toast.makeText(context, "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.icCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog(list.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoai, tvMaLoai;
        ImageView icDelete,icCreate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLoai = itemView.findViewById(R.id.tvMaLoai);
            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            icCreate = itemView.findViewById(R.id.icEditL);
            icDelete = itemView.findViewById(R.id.icDeleteL);
        }
    }

    private void showdialog(Loai loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_loai,null);
        builder.setView(view);

        TextInputEditText edtTenLoai = view.findViewById(R.id.edtTenLUpdate);
        TextView tvMaLoaiUpdate = view.findViewById(R.id.tvMaloaiUpdate);

        tvMaLoaiUpdate.setText("Mã loại: "+loai.getMaloai());
        edtTenLoai.setText(loai.getTenloai());

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenLoai = edtTenLoai.getText().toString();

                boolean check = loaiDAO.capNhatLoai(loai.getMaloai(),tenLoai);
                if (check){
                    list.clear();
                    list = loaiDAO.getListLoai();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
