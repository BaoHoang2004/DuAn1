package com.example.coffee.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffee.R;
import com.example.coffee.dao.ThongKeDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Calendar;


public class DoanhThuFragment extends Fragment {



    public DoanhThuFragment() {
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
        View view =inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        TextInputEditText edtStart = view.findViewById(R.id.edtStart);
        TextInputEditText edtend = view.findViewById(R.id.edtEnd);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView tvKeyQua = view.findViewById(R.id.tvKetQua);

        Calendar calendar = Calendar.getInstance();

        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay ="";
                        String thang="";
                        if (i2<10){
                            ngay = "0"+i2;
                        }else{
                            ngay = String.valueOf(i2);
                        }
                        if ((i1+1)<10){
                            thang = "0"+(i1+1);
                        }else {
                            thang = String.valueOf((i1+1));
                        }
                        edtStart.setText(i+"/"+thang+"/"+ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        edtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay ="";
                        String thang="";
                        if (i2<10){
                            ngay = "0"+i2;
                        }else{
                            ngay = String.valueOf(i2);
                        }
                        if ((i1+1)<10){
                            thang = "0"+(i1+1);
                        }else {
                            thang = String.valueOf((i1+1));
                        }
                        edtend.setText(i+"/"+thang+"/"+ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        //
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                String ngaybatdau =edtStart.getText().toString();
                String ngayketthuc = edtend.getText().toString();
                if (ngaybatdau.isEmpty() || ngayketthuc.isEmpty()){
                    Toast.makeText(getContext(), "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    int doanhthu = thongKeDAO.getDoanhThu(ngaybatdau, ngayketthuc);
                    tvKeyQua.setText("Doanh thu từ " + ngaybatdau +" đến " + ngayketthuc + ": "+ decimalFormat.format(doanhthu)+ " VNĐ");
                }
            }
        });

        return view;
    }
}