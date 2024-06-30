package com.example.coffee.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.R;
import com.example.coffee.dao.DoUongDAO;
import com.example.coffee.dao.SizeDAO;
import com.example.coffee.model.DoUong;
import com.example.coffee.model.Size;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class QLSPAdapter extends RecyclerView.Adapter<QLSPAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DoUong> list;
    private DoUongDAO doUongDAO;
    int size;
    int giasieze;
    String tensize;
    SizeDAO sizeDAO;
    String ss;
    public QLSPAdapter(Context context, ArrayList<DoUong> list, DoUongDAO doUongDAO) {
        this.context = context;
        this.list = list;
        this.doUongDAO = doUongDAO;
    }

    @NonNull
    @Override
    public QLSPAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_qlsp,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLSPAdapter.ViewHolder holder, int position) {
        DoUong doUong = list.get(position);
        holder.tvMaDoUong.setText(String.valueOf(list.get(position).getMadouong()));
        holder.tvTenDoUong.setText(String.valueOf(list.get(position).getTendouong()));

        //ảnh từ drawable
        int imgId = ((Activity)context).getResources().getIdentifier(
                list.get(position).getAvatar(),"drawable",
                ((Activity)context).getPackageName());
        holder.imageViewDU.setImageResource(imgId);
        //lấy ảnh từ Internet
        if (list.get(position).getAvatar().startsWith("http://")||
                list.get(position).getAvatar().startsWith("https://")){
            Picasso.get().load(list.get(position).getAvatar()).into(holder.imageViewDU);
        }

        String trangthai="";
        if (list.get(position).getTrangthai() == 1){
            trangthai = "Hết hàng";
            //setBacground
            holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorOutOfStock,null));
        }else {
            trangthai = "Vẫn còn";
            //setBacground
            holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorAvailable, null));
        }


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvTrangThai.setText("Trạng thái: "+trangthai);
        holder.tvGia.setText("Giá: "+decimalFormat.format(list.get(position).getDongia()));

        holder.btnCapNhatTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newTrangThai = (doUong.getTrangthai() == 1) ? 0 : 1;
                doUong.setTrangthai(newTrangThai);

                if (doUongDAO != null) {
                    doUongDAO.capNhatTrangThai(doUong.getMadouong(), newTrangThai);
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.icCrateDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog(list.get(holder.getAdapterPosition()));
            }
        });

        holder.icDeleteDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = doUongDAO.xoaDoUong(list.get(holder.getAdapterPosition()).getMadouong());
                if (check){
                    list.clear();
                    list = doUongDAO.getListDoUong();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDoUong, tvTenDoUong, tvTrangThai, tvGia;
        ImageView icDeleteDU, icCrateDoUong, imageViewDU;
        Button btnCapNhatTrangThai;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDoUong = itemView.findViewById(R.id.tvMaDoUong);
            tvTenDoUong = itemView.findViewById(R.id.tvTenDoUong);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvGia = itemView.findViewById(R.id.tvGiaDouong);
            btnCapNhatTrangThai = itemView.findViewById(R.id.btnCapNhatTrangthai);
            icDeleteDU = itemView.findViewById(R.id.icDeleteDU);
            icCrateDoUong = itemView.findViewById(R.id.icEditDU);
            imageViewDU = itemView.findViewById(R.id.imgViewDU);
            cardView = itemView.findViewById(R.id.cardViewQLSP);
        }
    }

    public void showdialog(DoUong doUong){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_sanpham,null);
        builder.setView(view);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        TextInputEditText edtTenDU = view.findViewById(R.id.edtTenDUDialog);
        TextInputEditText edtGiaSDU = view.findViewById(R.id.edtGiaSDUDialog);
//        TextInputEditText edtGiaMDU = view.findViewById(R.id.edtGiaMDUDialog);
//        TextInputEditText edtGiaLDU = view.findViewById(R.id.edtGiaLDUDialog);
        TextInputEditText edtThongTinDU = view.findViewById(R.id.edtThongTinDUDialog);
        Spinner spinnerSize = view.findViewById(R.id.spinnerSizeDialog);

        edtTenDU.setText(doUong.getTendouong());
        edtThongTinDU.setText(doUong.getThongtin());
        edtGiaSDU.setText(String.valueOf(doUong.getDongia()));
//        edtGiaMDU.setText(String.valueOf(doUong.getDongia()));
//        edtGiaLDU.setText(String.valueOf(doUong.getDongia()));
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                getDataSize(doUong.getMadouong()),
                android.R.layout.simple_list_item_1,
                new String[]{"tensize"},
                new int[]{android.R.id.text1}
        );
        spinnerSize.setAdapter(simpleAdapter);
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> hs = (HashMap<String, Object>) spinnerSize.getSelectedItem();
                size = (int) hs.get("masize");
                giasieze = (int) hs.get("dongia");
                tensize = (String) hs.get("tensize");
                edtGiaSDU.setText(String.valueOf(giasieze));

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sizeDAO = new SizeDAO(context);

                String tenDU = edtTenDU.getText().toString();
                String thongtin = edtThongTinDU.getText().toString();
                int dongia = Integer.parseInt(edtGiaSDU.getText().toString());

                boolean check = doUongDAO.capNhatDoUong(doUong.getMadouong(),tenDU,thongtin);
                boolean s = sizeDAO.capNhatSize(size,dongia);

                if (check){
                    list.clear();
                    list = doUongDAO.getListDoUong();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

                if (s){
                    list.clear();
                    list = doUongDAO.getListDoUong();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDataSize(int madouong){
        SizeDAO sizeDAO = new SizeDAO(context);
        ArrayList<Size> list = sizeDAO.getListSize(madouong);

        ArrayList<HashMap<String, Object>> listHashMaps = new ArrayList<>();
        for (Size s : list){
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("masize",s.getMasize());
            hm.put("madouong",s.getMadouong());
            hm.put("tensize",s.getTensize());
            hm.put("dongia",s.getDongia());
            listHashMaps.add(hm);
        }
        return listHashMaps;
    }
}
