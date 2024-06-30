package com.example.coffee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffee.database.Dbhelper;
import com.example.coffee.model.Loai;

import java.util.ArrayList;

public class LoaiDAO {
    Dbhelper dbhelper;
    public LoaiDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<Loai> getListLoai(){
        ArrayList<Loai> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select*from Loai",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new Loai(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoai(String tenloai){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",tenloai);

        long check = database.insert("Loai",null,values);
        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean capNhatLoai(int maloai, String tenloai){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",tenloai);

        long check = database.update("Loai",values,"maloai = ?",new String[]{String.valueOf(maloai)});
        return check != -1;
    }

    public int xoaLoai(int maloai){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        Cursor cursor =database.rawQuery("select*from DoUong where maloai =?",new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = database.delete("Loai","maloai =?",new String[]{String.valueOf(maloai)});
        if (check == -1){
            return 0;
        }
        return 1;
    }
}
