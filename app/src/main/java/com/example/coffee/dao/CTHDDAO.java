package com.example.coffee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffee.database.Dbhelper;
import com.example.coffee.model.CTHD;

import java.util.ArrayList;

public class CTHDDAO {
    Dbhelper dbhelper;
    public CTHDDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<CTHD> getListCTHD(int mahoadon){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<CTHD> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ct.macthd, ct.mahoadon, ct.soluong, ct.ghichu, ct.giatien, hd.trangthai, s.masize, du.tendouong, s.tensize, hd.ngaymua FROM CTHD ct, Size s, DoUong du, HoaDon hd WHERE ct.masize = s.masize AND s.madouong = du.madouong AND ct.mahoadon = hd.mahoadon AND hd.trangthai = 1 AND hd.mahoadon = ?",new String[]{String.valueOf(mahoadon)});
        if (cursor.getCount() >0){
            cursor.moveToFirst();
            do {
                list.add(new CTHD(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8),cursor.getString(9)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themVaoGioHang(CTHD cthd){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //mahoadon integer references HoaDon(mahoadon), soluong integer, ghichu text, giatien integer, trangthai integer, masize
        values.put("mahoadon",String.valueOf(cthd.getMahoadon()));
        values.put("soluong",String.valueOf(cthd.getSoluong()));
        values.put("ghichu",cthd.getGhichu());
        values.put("giatien",String.valueOf(cthd.getGiatien()));
        values.put("masize",String.valueOf(cthd.getMasize()));

        long check = database.insert("CTHD",null,values);
        if (check == -1){
            return false;
        }
        return true;
    }
    public ArrayList<CTHD> getListGioHang(){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<CTHD> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ct.macthd,ct.mahoadon, ct.soluong, ct.ghichu, ct.giatien, hd.trangthai, s.masize, du.tendouong, s.tensize, hd.ngaymua, avatar text FROM CTHD ct, Size s, DoUong du, HoaDon hd WHERE ct.masize = s.masize AND s.madouong = du.madouong AND ct.mahoadon = hd.mahoadon AND hd.trangthai = 0",null);
        if (cursor.getCount() >0){
            cursor.moveToFirst();
            do {
                list.add(new CTHD(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public long checkDU(int masize){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT ct.masize, hd.trangthai FROM CTHD ct, HoaDon hd WHERE ct.mahoadon = hd.mahoadon AND hd.trangthai = 0 AND ct.masize = ?",new String[]{String.valueOf(masize)});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }else{
            return 0;
        }
    }

    public boolean capNhatSoLuong(int masize, int soluong){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soluong",soluong);

        long check = database.update("CTHD",values,"masize = ?",new String[]{String.valueOf(masize)});
        return check!= -1;
    }

    public boolean xoaGH(int macthd){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        long check = database.delete("CTHD","macthd = ?",new String[]{String.valueOf(macthd)});
        return check != -1;
    }

}
