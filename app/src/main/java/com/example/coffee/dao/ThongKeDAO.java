package com.example.coffee.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffee.database.Dbhelper;

public class ThongKeDAO {
    Dbhelper dbhelper;
    public ThongKeDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ngaybatdau = ngaybatdau.replace("/","");// xóa dấu /
        ngayketthuc = ngayketthuc.replace("/","");

        Cursor cursor= database.rawQuery("select sum(ct.giatien) from CTHD ct JOIN HoaDon hd on ct.mahoadon = hd.mahoadon where  substr(ngaymua,7) || substr(ngaymua,4,2) || substr(ngaymua,1,2) between ? and ?",new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
