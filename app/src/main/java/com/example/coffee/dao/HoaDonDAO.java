package com.example.coffee.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffee.database.Dbhelper;
import com.example.coffee.model.HoaDon;

import java.util.ArrayList;

public class HoaDonDAO {
    Dbhelper dbhelper;

    public HoaDonDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<HoaDon> getListHoaDon(){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select*from HoaDon where trangthai = 1 ORDER BY mahoadon desc",null);
        if (cursor.getCount() >0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
            }while (cursor.moveToNext());

        }
        return list;
    }
    public long themHoaDon(HoaDon hoaDon){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ngaymua",hoaDon.getNgaymua());
        values.put("matn",hoaDon.getMatn());
        values.put("trangthai",hoaDon.getTrangthai());

        long check = database.insert("HoaDon",null,values);
        return check;
    }

    public long checkHD(String matn) {
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor= database.rawQuery("select*from HoaDon where matn=? and trangthai = 0 limit 1",new String[]{matn});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }
    public boolean thanhtoan(int mahoadon){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangthai",1);
        long check = database.update("HoaDon",values,"mahoadon=?",new String[]{String.valueOf(mahoadon)});
        return check != -1;
    }
}
